package com.phatye.mobilelearn;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	String userName, userType;
	ListView listView;
	TextView userText;
	
	String[] categories = {"Computing", "Business", "Finance", "Architecture", "Economics",
			"Management", "Estate Management"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		listView = (ListView)findViewById(R.id.listView);
		userText = (TextView)findViewById(R.id.userText);
		
		Intent intent = getIntent();
		userName = intent.getStringExtra("UserName");
		userType = intent.getStringExtra("UserType");
		
		userText.setText("Hello " + userName);			
		
		Arrays.sort(categories);
		ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
		routeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		
		listView.setAdapter(routeAdapter);
		
		// React to user clicks on item
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
		                             long id) {
		         // We know the View is a TextView so we can cast it
		         TextView clickedView = (TextView) view;
		         
		         String selectedCategory = (String)clickedView.getText();
		         
		         if(userType.equals("Student")){
		        	Intent intent = new Intent(HomeActivity.this, ResourceListActivity.class);
					intent.putExtra("category", selectedCategory);
					startActivity(intent);
		         }
		         else{
			        	Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
						intent.putExtra("category", selectedCategory);
						startActivity(intent);		        	 
		         }

		         //Toast.makeText(HomeActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Category ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		     }
		});		
	}	
}
