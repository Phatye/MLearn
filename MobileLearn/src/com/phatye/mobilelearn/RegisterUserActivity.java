package com.phatye.mobilelearn;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;
import com.phatye.mobilelearn.userendpoint.Userendpoint;
import com.phatye.mobilelearn.userendpoint.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterUserActivity extends Activity {

	EditText userName, userEmail;
	Spinner spinType;
	ProgressDialog dialog;
	public String[] Types = {"Admin", "Student", "Instructor"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_user);
		
		userName = (EditText)findViewById(R.id.userName);
		userEmail = (EditText)findViewById(R.id.userEmail);
		spinType = (Spinner)findViewById(R.id.spinType);
		
		Arrays.sort(Types);
		ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Types);
		routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinType.setAdapter(routeAdapter);
		
	}
	
	public void btnRegister_Click (View view){
		
		dialog = new ProgressDialog(RegisterUserActivity.this);
		dialog.setMessage("Registering User...");		
		dialog.show();
		boolean success = false;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		RunnableFuture f = new FutureTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				boolean create = false;
				
				User u = new User();
				u.setPassword(userName.getText().toString().toLowerCase()
						.replaceAll("\\p{Z}",""));
				u.setType((String)spinType.getSelectedItem());
				u.setUserEmail(userEmail.getText().toString());
		        u.setUserName(userName.getText().toString());
		        Userendpoint.Builder builder = new Userendpoint.Builder(
	  	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	  	          null);
		        builder = CloudEndpointUtils.updateBuilder(builder);
		        Userendpoint endpoint = builder.build();
		        
		        try {
					endpoint.insertUser(u).execute();
					create = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        dialog.dismiss();
		        return create;
			}
		  // implement call
		});
		// start the thread to execute it (you may also use an Executor)
		new Thread(f).start();
		// get the result
		try {
			success = (Boolean) f.get(); 
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String message = "";
		if(success){	
			message = "User has been succesfully created";
			userName.setText(null);
			userEmail.setText(null);
		}
		else{
			message = "Unable to create User";
		}
		
	   	 AlertDialog alertDialog = new AlertDialog.Builder(
	             this).setTitle("Create User")
	            .setMessage(message).create();
	    alertDialog.show();	
		/*Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		    	
		    	User u = new User();
				u.setPassword(userName.getText().toString().toLowerCase()
						.replaceAll("\\p{Z}",""));
				u.setType((String)spinType.getSelectedItem());
				u.setUserEmail(userEmail.getText().toString());
		        u.setUserName(userName.getText().toString());
		        Userendpoint.Builder builder = new Userendpoint.Builder(
	  	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	  	          null);
		        builder = CloudEndpointUtils.updateBuilder(builder);
		        Userendpoint endpoint = builder.build();
		        
		        try {
					endpoint.insertUser(u).execute();
					userName.setText(null);
					userEmail.setText(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		};

		new Thread(runnable).start(); */
	}
}
