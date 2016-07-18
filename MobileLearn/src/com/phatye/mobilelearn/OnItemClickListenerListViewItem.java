package com.phatye.mobilelearn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnItemClickListenerListViewItem implements OnItemClickListener {
	Context context;
	String directory;
	String fileName;
	String blobKey;
	String type;
	String category;
	
    public OnItemClickListenerListViewItem(String contentType, String category) {
		// TODO Auto-generated constructor stub
    	this.type = contentType;
    	this.category = category;
	}

	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        context = view.getContext();
        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewItem));

        // get the clicked item name
        fileName = textViewItem.getText().toString();
        // get the clicked item ID
        blobKey = textViewItem.getTag().toString();  
        
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Download...");
 
        // Setting Dialog Message
        alertDialog.setMessage("Download " + fileName);
 
        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) { 
            // Write your code here to invoke YES event
            	//chooseDirectory();
            	
            	directory = type + "/" + category;
                DownloadResourceAsyncTask asyncTask = new DownloadResourceAsyncTask
                		(blobKey, fileName, directory, context);
                asyncTask.execute();
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // Write your code here to invoke NO event
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
    
    /*private void chooseDirectory(){
    	
    	String m_chosenDir = "/";
        boolean m_newFolderEnabled = true;
        
    	// Create DirectoryChooserDialog and register a callback 
        DirectoryChooser directoryChooserDialog = 
        new DirectoryChooser(context, 
            new DirectoryChooser.ChosenDirectoryListener() 
        {
            @Override
            public void onChosenDir(String chosenDir) 
            {
                directory = chosenDir;
                DownloadResourceAsyncTask asyncTask = new DownloadResourceAsyncTask
                		(blobKey, fileName, directory, context);
                asyncTask.execute();
            }
        }); 
        // Toggle new folder button enabling
        directoryChooserDialog.setNewFolderEnabled(true);
        // Load directory chooser dialog for initial 'm_chosenDir' directory.
        // The registered callback will be called upon final directory selection.
        directoryChooserDialog.chooseDirectory(m_chosenDir);
        m_newFolderEnabled = ! m_newFolderEnabled;
    }*/
}