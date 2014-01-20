package org.iiis.buzhidao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.google.android.maps.*;

public class BigActivity extends MapActivity {

	private MapView map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big);
		initMapView();
		initZoomControls();
		initMyLocation();
	}

	private void initMyLocation() {
		// TODO Auto-generated method stub
		final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
		overlay.enableMyLocation();
		overlay.enableCompass();
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				map.getController().setZoom(8);
				map.getController().animateTo(overlay.getMyLocation());
			}
		});
	}

	private void initZoomControls() {
		// TODO Auto-generated method stub
		map.setBuiltInZoomControls(true);
	}

	private void initMapView() {
		// TODO Auto-generated method stub
		map = (MapView) findViewById(R.id.mapView1);
		map.setSatellite(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.big, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
