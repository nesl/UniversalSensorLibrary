package com.ucla.nesl.universaldrivermanager;

import com.ucla.nesl.aidl.IUniversalManagerService;
import com.ucla.nesl.aidl.SensorParcel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class UniversalDriverManager {
	private String UNIVERSALServicePackage = "com.ucla.nesl.universalsensorservice";
	private String UNIVERSALServiceClass = "com.ucla.nesl.universalsensorservice.UniversalSensorService";
	private Context context;
	private UniversalDriverRemoteConnection remoteConnection;
	private static UniversalDriverManager mManager = null;
	
	public static UniversalDriverManager create(Context context)
	{
		if (mManager != null)
			return mManager;
		mManager = new UniversalDriverManager(context);
		return mManager;
	}
	
	public UniversalDriverManager(Context context) {
		this.context = context;
		remoteConnection = new UniversalDriverRemoteConnection(this);
		connectRemote();
	}
	
	public Boolean push(int sType, float[] values, int valueSize, int accuracy, float timestamp)
	{
		SensorParcel sp = new SensorParcel(sType, values, valueSize, accuracy, timestamp);
		remoteConnection.push(sp);
		return true;
	}

	void connectRemote()
	{
		Intent intent = new Intent("bindUniversalSensorService");
		intent.setClassName(UNIVERSALServicePackage, UNIVERSALServiceClass);
		context.bindService(intent, remoteConnection, Context.BIND_AUTO_CREATE);
	}
}