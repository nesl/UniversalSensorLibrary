package com.ucla.nesl.aidl;
interface IUniversalSensorManagerCallback {
	void pushDouble(int deviceID, int sensor, in double data, long timestamp);
}