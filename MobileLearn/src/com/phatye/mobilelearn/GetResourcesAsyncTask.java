package com.phatye.mobilelearn;

import java.io.IOException;
import java.util.List;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;
import com.phatye.mobilelearn.itemendpoint.Itemendpoint;
import com.phatye.mobilelearn.itemendpoint.model.CollectionResponseItem;
import com.phatye.mobilelearn.itemendpoint.model.Item;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class GetResourcesAsyncTask extends AsyncTask<Void, Void, Item[]>{

	private String category;
	private String type;
	private ResourceDetailFragment fragment;
	private ProgressDialog progressDialog;
	
	public GetResourcesAsyncTask(String category,String type,ResourceDetailFragment fragment){
		this.category = category;
		this.type = type;
		this.fragment = fragment;
	}
	
	public void onPreExecute()
    {
        progressDialog = new ProgressDialog(fragment.getActivity());
        progressDialog.setMessage("Retrieving resources...");
        progressDialog.show();
    }
	
	@Override
    public void onPostExecute(Item[] result)
    {
        progressDialog.dismiss();
        fragment.handleGetResourcesResult(result);
    }
	
	@Override
	protected Item[] doInBackground(Void... params) {
		Item[] items = null;
		try {	    		
    		Itemendpoint.Builder builder = new Itemendpoint.Builder(
    		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
    		          null);
    		builder = CloudEndpointUtils.updateBuilder(builder);
    		
    		Itemendpoint endpoint = builder.build();
    		
    		try {
    			CollectionResponseItem result = endpoint
    					.getItems(this.category, this.type).execute();
    			
    			if(result == null || result.getItems() == null || result.getItems().size() < 1)
    				items = null;
    			else{
    				List<Item> itemList = result.getItems();
    				items = itemList.toArray(new Item[itemList.size()]);
    			}
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } catch (Exception e) {
        	e.printStackTrace();
        }  
		
		return items;
	}
}
