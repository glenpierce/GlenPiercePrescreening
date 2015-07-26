package com.piercestudio.near;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.facebook.FacebookSdk;



public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FacebookSdk.sdkInitialize(getApplicationContext());

	}

}
