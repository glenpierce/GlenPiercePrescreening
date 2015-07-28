package com.piercestudio.near;

import android.app.ListActivity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import java.util.List;

import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.AccessTokenTracker;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetFriendList extends ListActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getApplicationContext());

		AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(
					AccessToken oldAccessToken,
					AccessToken currentAccessToken) {
				// Set the access token using
				// currentAccessToken when it's loaded or set.
			}
		};
		// If the access token is available already assign it.
		AccessToken accessToken = AccessToken.getCurrentAccessToken();

		new GraphRequest(
				AccessToken.getCurrentAccessToken(), "/{friendlist-id}", null, HttpMethod.GET, new GraphRequest.Callback() {
					public void onCompleted(GraphResponse response) {
            /* handle the result */
						JSONObject jsonObject = response.getJSONObject();
						JSONArray jarray = response.getJSONArray();

						for(int i = 0; i < jarray.length(); i++){
							JSONObject oneAlbum = jarray.getJSONObject(i);
							//get your values
							oneAlbum.getJSONString("name"); // this will return you the album's name.
						}
					}
				}
		).executeAsync();

		displayFriendList();

	}

	public void displayFriendList(Array jarray){

		ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.get_friend_layout, R.id.friend_list_text);
		for (int i = 0; i < jarray.length(); ++i) {
			arrayAdapter.add(jarray[i]);
		}

		setListAdapter(arrayAdapter);

	}
}
