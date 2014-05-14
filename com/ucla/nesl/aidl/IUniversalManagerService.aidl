package com.ucla.nesl.aidl;
import com.ucla.nesl.aidl.IUniversalSensorManagerCallback;
import com.ucla.nesl.aidl.Device;


interface IUniversalSensorManager {
	void sendDevice(in Device mdevice);
	Devices[] listDevices();
	Boolean registerListener(IUniversalSensorManager mManager, String devID, int sType, int rateUs);
}