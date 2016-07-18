package com.phatye.mobilelearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_main);
	}
	
	public void btnRegisterUser_Click (View view){
		Intent intent = new Intent(AdminMainActivity.this, RegisterUserActivity.class);
		startActivity(intent);		
	}
	
	public void btnRegisterCategory_Click (View view){
		
	}
}
