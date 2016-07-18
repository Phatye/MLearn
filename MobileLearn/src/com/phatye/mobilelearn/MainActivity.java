package com.phatye.mobilelearn;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;
import com.phatye.mobilelearn.userendpoint.Userendpoint;
import com.phatye.mobilelearn.userendpoint.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText Username, Password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Username = (EditText)findViewById(R.id.username);
		Password = (EditText)findViewById(R.id.password);
		//new UserTask(this).execute();
	}
	
	public void btnLogin_Click(View view){
		String password  = Password.getText().toString();
		String email = Username.getText().toString();
		
		UserTask u = new UserTask(this);
		u.userName = email;
		u.userPassword = password;
		u.execute();
	}
	
	public void handleLoginResult(User user) {
		
		if(user != null){
			String type = user.getType();
			
			if(type.equals("Admin")){
				Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
				startActivity(intent);
			}
			else if (type.equals("Student") || type.equals("Instructor")){
				Intent intent = new Intent(MainActivity.this, HomeActivity.class);
				intent.putExtra("UserName", user.getUserName());
				intent.putExtra("UserType", user.getType());
				startActivity(intent);
			}
		}
		else{
			AlertDialog alertDialog = new AlertDialog.Builder(
                    this).setTitle("Error")
                    .setMessage("Unable to retrieve user").create();
		     alertDialog.show();
		}
	}
	 @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	//getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	  }
	
	  /**
	   * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
	   */
	  private class UserTask extends AsyncTask<Void, Void, User> {
		  private MainActivity mContext;
		  public String userName;
		  public String userPassword;
		  ProgressDialog progressDialog;
		  
		  public UserTask(MainActivity c){
			  mContext = c;
		  }
		  
	    public void onPreExecute()
	    {
	        progressDialog = new ProgressDialog(mContext);
	        progressDialog.setMessage("Logging in...");
	        progressDialog.show();
	    }
	 
	    @Override
	    public void onPostExecute(User result)
	    {
	    	progressDialog.dismiss();
	        mContext.handleLoginResult(result);
	    }

	    /**
	     * Calls appropriate CloudEndpoint to indicate that user checked into a place.
	     *
	     * @param params the place where the user is checking in.
	     */
	    @Override
	    protected User doInBackground(Void... params) {
	    	User u = null;
	    	
	    	if(this.userName.equalsIgnoreCase("Admin") && this.userPassword.equalsIgnoreCase("admin")){
	    		u = new User();
	    		u.setType("Admin");
	    		u.setUserEmail("Admin");
	    		
	    	}
	    	else{
	      Userendpoint.Builder builder = new Userendpoint.Builder(
	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	          null);
	          
	      builder = CloudEndpointUtils.updateBuilder(builder);

	      Userendpoint endpoint = builder.build();
	      
      	try {
      		//u = endpoint.getUser(5066549580791808L).execute();
	        u = endpoint.confirmUser(userName, userPassword).execute();
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	    	 // AlertDialog alertDialog = new AlertDialog.Builder(
             //         mContext).setTitle("Error")
              //        .setMessage(e.toString()).create();
		      // Showing Alert Message
		      //alertDialog.show();
	        e.printStackTrace();
	      }
      	}
	      return u;
	    }
	  }
}
