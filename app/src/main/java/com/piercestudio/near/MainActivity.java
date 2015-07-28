package com.piercestudio.near;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.Signature;
//import android.util.Log;
//import android.content.pm.PackageManager.NameNotFoundException;
//import java.security.NoSuchAlgorithmException;
//import java.security.MessageDigest;
//import android.util.Base64;
//import android.view.View;
import android.util.Log;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.LoginResult;
import com.facebook.FacebookException;
import com.facebook.FacebookCallback;


public class MainActivity extends Activity
{
	CallbackManager callbackManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getApplicationContext());
		setContentView(R.layout.activity_main);

		final TextView text = (TextView) findViewById(R.id.textview);

//		text.setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				try
//				{
//					PackageInfo info = getPackageManager().getPackageInfo(
//							"com.piercestudio.near",
//							PackageManager.GET_SIGNATURES);
//					for (Signature signature : info.signatures)
//					{
//						MessageDigest md = MessageDigest.getInstance("SHA");
//						md.update(signature.toByteArray());
//						Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//					}
//				}catch (NameNotFoundException e)
//				{
//
//				}catch (NoSuchAlgorithmException e)
//				{
//
//				}
//
//			}
//		});

		callbackManager = CallbackManager.Factory.create();

		LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);

		loginButton.setReadPermissions("user_friends");
		loginButton.setReadPermissions("user_location");

		final Intent i = new Intent(this, GetFriendList.class);

		loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
		{
			@Override
			public void onSuccess(LoginResult loginResult)
			{
				// App code
				text.setText("access granted");
				Log.i("asdf", "access granted");

				startActivity(i);

			}

			@Override
			public void onCancel()
			{
				// App code
			}

			@Override
			public void onError(FacebookException exception)
			{
				// App code
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}
}
