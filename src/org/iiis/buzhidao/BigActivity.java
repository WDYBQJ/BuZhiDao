package org.iiis.buzhidao;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class BigActivity extends Activity {
	private SharedPreferences prefs;
	private boolean hasMapSelected;
	private static final String PREF_HAS_MAP_SELECTED = "PREF_HAS_MAP_SELECTED";
	private static final String PREF_MAP_URI = "PREF_MAP_URI";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big);
		prefs = getPreferences(MODE_PRIVATE);
	}
	
	@Override
	protected void onStart() {
		hasMapSelected = prefs.getBoolean(PREF_HAS_MAP_SELECTED, false);
		if (hasMapSelected) {
			try {
				Uri uri = Uri.parse(prefs.getString(PREF_MAP_URI, null));
				ImageView imageView = (ImageView) findViewById(R.id.mapID);
				imageView.setImageURI(uri);
			} catch (Exception e) {
				// TODO: what should we do if we cannot open it?
				e.printStackTrace();
				hasMapSelected = false;
			}
		}
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		Cursor c = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		String[] columns = new String[] {
			MediaStore.Images.Media.DISPLAY_NAME,
			MediaStore.Images.Media._ID
		};
		int[] views = new int[] {
			R.id.mapName, R.id.mapID
		};
		SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.list_item, c, columns, views);
		spinner.setAdapter(ca);
	}
	
	@Override
	protected void onPause() {
		SharedPreferences.Editor prefEdit = prefs.edit();
		prefEdit.putBoolean(PREF_HAS_MAP_SELECTED, hasMapSelected);
		prefEdit.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.big, menu);
		return true;
	}

}
