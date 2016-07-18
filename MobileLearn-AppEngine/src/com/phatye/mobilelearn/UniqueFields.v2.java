package com.phatye.mobilelearn;

import java.util.ConcurrentModificationException;

import org.datanucleus.util.StringUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

//no access modifier (default)
//UniqueFields class is available only its in own package

class UniqueFields {	
	
	//set this to true if the unique values are not case-sensitive
	private final static boolean IGNORE_CASE = true;

	private final static String REF_FIELD = "ref_id";
		
	//name of the Entity Store that we will use store the unique field
	//e.g. if you are storing a unique employee_email in Employee entity,
	//then Entity Store name would be Employee_employee_email
	static String getUniqueFieldStoreName(String entity_name, String field_name){
		return entity_name + "_" + field_name;
	}
	
	//returns the string value of unique field (field_name) from Entity (entity_name) 
	//where Entity's Unique Id is ref_id
	//e.g. 
	//getStringValue("Employee", "employee_email", employee_id);	
	//will return the unique email address of the Employee with employee_id
	static String getStringValue(String entity_name, String field_name, String ref_id) {
		
		Entity result = getUniqueFieldEntity(entity_name, field_name, ref_id);
		
		if (result != null ) {				
			return result.getKey().getName();
		} else {
			return null;
		}
	}
	
	//returns the key of the unique field (field_name) entity
	//where Parent Entity's Unique Id is ref_id
	//e.g. 
	//getKey("Employee", "employee_email", employee_id);	
	//will return the key of the unique field entity that
	//contains the unique email address of the Employee with employee_id
	private static Key getKey(String entity_name, String field_name, String ref_id) {
		
		Entity result = getUniqueFieldEntity(entity_name, field_name, ref_id);
		
		if (result != null ) {				
			return result.getKey();
		} else {
			return null;
		}
	}

	//returns the unique field entity
	private static Entity getUniqueFieldEntity(String entity_name, String field_name, String ref_id){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query(getUniqueFieldStoreName(entity_name, field_name));
		q.addFilter(REF_FIELD, Query.FilterOperator.EQUAL, ref_id);
		
		PreparedQuery pq = datastore.prepare(q);
		
		Entity result = pq.asSingleEntity();
		
		return result;
	
	}
	
	//returns false if the value (unique value) for a unique field (field_name) is already assigned
	//return true if the value is available
	//e.g. 
	//isAvailable("Employee", "employee_email", "john.smith@gmail.com"); 
	//will return true if john.smith@gmail.com has not been assigned to another Employee
	static boolean isAvailable(String entity_name, String field_name, String value) {
		
		String value_to_check = getValueToCheck(value);		

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
		try {
			Key fieldKey = KeyFactory.createKey(getUniqueFieldStoreName(entity_name, field_name), value_to_check);
			@SuppressWarnings("unused")
			Entity fieldEntity = datastore.get(fieldKey);
			return false;
		} catch (EntityNotFoundException e) {
			return true;
		}
		
	}	
	
	//updates the value of the unique field
	//takes care of deleting the old values so that
	//the old values are available again
	//
	//returns false, if the value could not be set, probably because it was not unique
	//returns true, if a unique value has been set
	static boolean update(String entity_name, String field_name, String value, String ref_id, Key entity_key) {
		
		//get the existing unique field entity 
		Entity old_unique_field_entity = getUniqueFieldEntity(entity_name, field_name, ref_id);
		
		//set the new unique value 
		boolean result = set(entity_name, field_name, value, ref_id, entity_key);
		
		//if the value of the unique field is successfully set
		if (result) {
			
			//if there was an old value, delete it 
			if (old_unique_field_entity != null) {
			
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				datastore.delete( old_unique_field_entity.getKey() );
			}
			
		}
		
		//return the result of the set function
		return result;
	}

	//returns false, if the value could not be set, probably because it was not unique
	//returns true, if a unique value has been set
	private static boolean set(String entity_name, String field_name, String value, String ref_id, Key entity_key) {
		
		boolean retval = false;
		
		String value_to_check = getValueToCheck(value);
		String value_to_store = getValueToStore(value);		 
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key fieldKey = KeyFactory.createKey(getUniqueFieldStoreName(entity_name, field_name), value_to_check);
		
		//All further gets and puts will read and write to the same 
		//datastore snapshot that is retrieved at the time when
		//transaction begins
		Transaction txn = datastore.beginTransaction();
		try {
			//value for field_name is already taken
			@SuppressWarnings("unused")
			Entity fieldEntity = datastore.get(fieldKey);
			retval =  false;
		} catch (EntityNotFoundException e) {
			
			//value not taken
			
			Entity fieldEntity = new Entity(getUniqueFieldStoreName(entity_name, field_name), value_to_check);
			fieldEntity.setProperty(REF_FIELD, ref_id);
			
			datastore.put(fieldEntity); //store the unique field in Unique Field Store
			
			retval = true;
		}
		
		txn.commit();
		
		
		//also store the value of the field in the Entity itself
		//this will help in searching and indexing based on the value
		//It is safe to do so, since at this point we know that the value is unique
		if (retval) { 
			
			//fieldEntity was successfully created
			//now store the value in the Entity itself
			
			int retries = 5;
			while (retries > 0) {			
				Transaction txn1 = datastore.beginTransaction();
				try {
				    
					Entity entity_obj = datastore.get(entity_key);
					entity_obj.setProperty(field_name, value_to_store);
	
				    datastore.put(entity_obj);
	
				    txn1.commit();
				    
				    retval = true;
				    break; //transaction successful, do not retry
				    
				} catch (EntityNotFoundException en) {
					
					retval = false;
					
				} catch (ConcurrentModificationException e) {
			        if (retries == 0) {
			            //throw e;			        	
			        	break; //retries over, do not retry
			        }
			        
			        retval = false;
			        
			        // Allow retry to occur
			        --retries;
			    } finally {
				    if (txn1.isActive()) {
				    	txn1.rollback();
				    	retval = false;
				    }
				}
			}//while
		
		}
		
		return retval;
		
	}
	
	//returns the Unique Id of the Entity to which the value for the field name belongs
	//e.g. 
	//getRefId("Employee", "employee_email", "john.smith@gmail.com")
	//will return the Employee Id of John Smith (generally speaking)
	static String getRefId(String entity_name, String field_name, String value){
		
		String value_to_check = getValueToCheck(value);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		try {
			Key fieldKey = KeyFactory.createKey(getUniqueFieldStoreName(entity_name, field_name), value_to_check);
			Entity fieldEntity = datastore.get(fieldKey);
			
			Object ref_id = fieldEntity.getProperty(REF_FIELD);
			
			if (ref_id != null) {				
				return (String) ref_id;
			} else {				
				return null;				
			}
			
			 
		} catch (EntityNotFoundException e) {
			return null;
		}
	}	 
	
	
	private static String getValueToCheck(String value){
		if (IGNORE_CASE) {
			return value.toLowerCase();
		} else {
			return value;
		}
		
	}
	
	private static String getValueToStore(String value){
		return value;
	}
	
	
	
}
