package com.phatye.mobilelearn;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

public class DownloadResourceAsyncTask extends AsyncTask<Void, Void, Boolean> {
	
	String directory, fileName, blobKey;
	Context context;
	//String baseUrl = "http://10.0.2.2:8888/serveBlob?id=";
	String baseUrl = "http://hikmamlearn.appspot.com/serveBlob?id=";
	ProgressDialog progressDialog;

	public DownloadResourceAsyncTask(String blobKey, String fileName, String directory, Context context){
		this.blobKey = blobKey;
		this.fileName = fileName;
		this.directory = directory;
		this.context = context;
	}
	
	@Override
	public void onPreExecute()
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Downloading resource...");
        progressDialog.show();
    }

	@Override
	protected void onPostExecute(Boolean success) {
		super.onPostExecute(success);
		
		progressDialog.dismiss();
        
		String message = "Unable to download resource";
		if(success) message = "Resource downloaded successfully";
	    Toast.makeText(context,message,Toast.LENGTH_SHORT).show(); 
	}
	@Override
	
	protected Boolean doInBackground(Void... params) {
		String key = blobKey.substring(10, blobKey.length() - 1);		
		baseUrl += key;
		
		return savePrivateExternalFile(baseUrl, fileName, directory);
	}
	
	private Boolean savePrivateExternalFile(String fileURL, String fName, String directory) {
		   HttpURLConnection connection = null;
		   boolean success = false;
		    URL url = null;
		    try {

		        url = new URL(fileURL);
		        connection = (HttpURLConnection) url.openConnection();
		        /*connection.addRequestProperty(BConstant.WEB_SERVICES_COOKIES,
		                cookie);*/
		             connection.setDoOutput(true);
		        connection.connect();
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		    File folderDir = null;
		        //folderDir = new File(context.getExternalFilesDir(directory), "/download");
		    folderDir = new File(Environment.getExternalStorageDirectory() 
		    		+ "/MLearn/download/" + directory);

		    File file = new File(folderDir, fName);

		    if (file.exists()) {
		        file.delete();
		    }

		    if ((folderDir.mkdirs() || folderDir.isDirectory())) {
		        try {
		            InputStream inputStream = connection.getInputStream();
		            BufferedInputStream bufferedInputStream = null;

		                bufferedInputStream = new BufferedInputStream(inputStream,
		                        1024 * 5);

		            FileOutputStream fileOutputStream = new FileOutputStream(
		                    folderDir + "/" + fName);
		            byte[] buffer = new byte[1024];
		                int len1 = 0;
		                while ((len1 = inputStream.read(buffer)) != -1) {
		                    fileOutputStream.write(buffer, 0, len1);
		                }

		                bufferedInputStream.close();

		            fileOutputStream.close();
		            inputStream.close();
		            connection.disconnect();
		            success = true;
		            } catch (Exception e) {
		            e.printStackTrace();
		            }
		        }
		    return success;
	}
}
