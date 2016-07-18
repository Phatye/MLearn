package com.phatye.mobilelearn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.phatye.mobilelearn.dummy.DummyContent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LocalResourceFragment extends Fragment{
	 /* The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;
	String category;
	ProgressDialog dialog;
	View rootView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public LocalResourceFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
			category = getArguments().getString("category");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_resource_detail,
				container, false);

		if(mItem != null){
			getFileList();
		}

		return rootView;
	}

	private void getFileList() {
		ArrayList<String> files = getLocalFiles();
		
		if(files != null){
			ListView listView = ((ListView) rootView.findViewById(R.id.resource_list));
			
			String[] filesArr = new String[files.size()];
			filesArr = files.toArray(filesArr);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>
			(getActivity(), android.R.layout.simple_list_item_1, filesArr);
			
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
			                             long id) {
			         // We know the View is a TextView so we can cast it
			         TextView clickedView = (TextView) view;
			         
			         String selectedFile = (String)clickedView.getText();
			         
			         if(selectedFile != null && !selectedFile.isEmpty())
			        	 viewFile(selectedFile);
			     }
			});						
		}
	}

	private void viewFile(String selectedFile) {
		// TODO Auto-generated method stub
		String directory = mItem.contentType + "/" + category;
		String folder = Environment.getExternalStorageDirectory() 
	    		+ "/MLearn/download/" + directory;		
		String format = mItem.contentFormat;
		
		File file = new File(folder + "/" + selectedFile);
		Intent i = new Intent();
		i.setAction(android.content.Intent.ACTION_VIEW);
		i.setDataAndType(Uri.fromFile(file), format);
		startActivity(i);
	}

	private ArrayList<String> getLocalFiles() {
		// TODO Auto-generated method stub
		String fileNameFilterPattern = null;
		String directory = mItem.contentType + "/" + category;
		String folder = Environment.getExternalStorageDirectory() 
	    		+ "/MLearn/download/" + directory;
		
		ArrayList<String> myData = new ArrayList<String>();
        File fileDir = new File(folder);
        if(!fileDir.exists() || !fileDir.isDirectory()){
            return null;
        }

        String[] files = fileDir.list();

        if(files.length == 0){
            return null;
        }
        for (int i = 0; i < files.length; i++) {
            if(fileNameFilterPattern == null ||
                    files[i].matches(fileNameFilterPattern))
            myData.add(files[i]);
        }
        if (myData.size() == 0)
          return null;

        Collections.sort(myData, String.CASE_INSENSITIVE_ORDER);
        
        /*if (sort != 0)
        {
          Collections.sort(myData, String.CASE_INSENSITIVE_ORDER);
          if (sort < 0)
            Collections.reverse(myData);
        }*/

        return myData;
	}
}
