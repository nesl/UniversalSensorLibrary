package com.ucla.nesl.universalsensormanager;

import com.ucla.nesl.lib.UniversalEventListener;

public class UniversalSensorManager {

	private static UniversalSensorManager mManager = null;
	
	public static UniversalSensorManager create() {
		if (mManager != null)
			return mManager;
		mManager = new UniversalSensorManager();
		return mManager;
	}
	
	private UniversalSensorManager() {
		
	}
	
	public boolean registerListener(UniversalEventListener mlistener) {
		return true;
	}
}
