package org.iiis.buzhidao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.location.*;

public class PositionService extends Service {
	private LocationManager manager;
	private String best;
	
	public PositionService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		best = manager.getBestProvider(criteria, true);
		
		Location location = manager.getLastKnownLocation(best);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
}
