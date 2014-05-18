package com.ucla.nesl.aidl;

import com.ucla.nesl.aidl.IUniversalSensorManager;
import com.ucla.nesl.aidl.IUniversalDriverManager;
import com.ucla.nesl.aidl.Device;
import com.ucla.nesl.aidl.SensorParcel;


interface IUniversalManagerService {
	Device[] listDevices();
	boolean registerListener(IUniversalSensorManager mManager, String devID, int sType, int rateUs);
//	boolean registerDriver(IUniversalDriverManager mDriver, in Device device);
	void onSensorChanged(in SensorParcel event);
}