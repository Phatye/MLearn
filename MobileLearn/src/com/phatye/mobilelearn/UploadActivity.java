package com.phatye.mobilelearn;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UploadActivity extends Activity {

	private static final int REQUEST_CODE_GETFILE = 1;
	TextView textCat, textFileURL;
	EditText txtTitle;
	Spinner spinType;
	public String[] Types = {"Audio", "Video", "Note"};
	String fileSelected, fileExtension, fileCategory, fileSize;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		
		textCat = (TextView) findViewById(R.id.textCat);
		textFileURL = (TextView) findViewById(R.id.textFileURL);
		txtTitle = (EditText) findViewById(R.id.txtTitle);
		spinType = (Spinner) findViewById(R.id.spinType);
		
		Intent intent = getIntent();
		fileCategory = intent.getStringExtra("category");
		textCat.setText("Upload material for " + fileCategory);
		
		ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Types);
		routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinType.setAdapter(routeAdapter);
	}
	
	public void btnChooseFile_Click(View view){
		
		try{
			Intent intent = new Intent(UploadActivity.this, FileChooser.class);
			startActivityForResult(intent, REQUEST_CODE_GETFILE);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	    if ((requestCode == REQUEST_CODE_GETFILE) && (resultCode == -1)) {
	        fileSelected = data.getStringExtra("Path");	   
	        fileSize = data.getStringExtra("fileSize");
	        String fileName = data.getStringExtra("Name");	    
	        textFileURL.setText(fileName);
	        try{	        	
	        	String filenameArray[] = fileName.split("\\.");
		        fileExtension = filenameArray[filenameArray.length-1];
	        }
	        catch(Exception ex){
	        	fileExtension = fileName;
	        }
	    }                   
	}
	
	public void btnUploadMaterial_Click(View view){
		dialog = new ProgressDialog(this);
		dialog.setMessage("Uploading resource...");
        
        Runnable runnable = new Runnable() {
        	String message = "";
	    @Override
	    public void run() {
	    	try {
	        	//BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	        	//String link = blobstoreService.createUploadUrl("/postItem");
	        	//String link = "http://10.0.2.2:8888/blobUrl";
	        	String link = "http://hikmamlearn.appspot.com/blobUrl";
	        	String uploadLink;
	        	
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(link);
	            org.apache.http.HttpResponse response = httpClient.execute(httpGet);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	            String str = reader.readLine();
	            JSONObject json  = new JSONObject(str);
	            
	            String genLink = json.getString("uploadUrl");
	            
	            uploadLink = genLink;//.replaceAll("localhost", "10.0.2.2");
	            
	        	HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(uploadLink);
	            
	            MultipartEntity entity = new MultipartEntity();
	            entity.addPart("type", new StringBody(spinType.getSelectedItem().toString()));
	            entity.addPart("category", new StringBody(fileCategory));
	            entity.addPart("fileExtension", new StringBody(fileExtension));
	            entity.addPart("fileSize", new StringBody(fileSize));
	            entity.addPart("title", new StringBody(txtTitle.getText().toString() + "." + fileExtension));
	            File file = new File(fileSelected);
	            entity.addPart("uploadedBlob", new FileBody(file));

	            httppost.setEntity(entity);
	            httpclient.execute(httppost);
	            message = "Resource was successfully uploaded";
	        } catch (Exception e) {
	        	message = "Unable to upload resource. \n" + e.getMessage();
	        	e.printStackTrace();
	        }  
	        
	        dialog.dismiss();
	        runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getBaseContext(),message,Toast.LENGTH_LONG).show();
                }
            });
	    	}
        };
		new Thread(runnable).start();
		dialog.show();
	}
}
