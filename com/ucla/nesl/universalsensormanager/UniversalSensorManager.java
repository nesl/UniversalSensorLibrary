package com.ucla.nesl.universalsensormanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.ucla.nesl.aidl.Device;
import com.ucla.nesl.aidl.IUniversalManagerService;
import com.ucla.nesl.aidl.IUniversalManagerService.Stub;
import com.ucla.nesl.aidl.IUniversalSensorManager;
import com.ucla.nesl.aidl.SensorParcel;
import com.ucla.nesl.lib.UniversalEventListener;
import com.ucla.nesl.lib.UniversalSensor;
import com.ucla.nesl.lib.UniversalSensorEvent;

public class UniversalSensorManager {
	private String UNIVERSALServicePackage = "com.ucla.nesl.universalsensorservice";
	private String UNIVERSALServiceClass = "com.ucla.nesl.universalsensorservice.UniversalSensorService";
	private static UniversalSensorManager mManager = null;
	private UniversalManagerRemoteConnection remoteConnection;
	private Context context;
	private static String tag = UniversalSensorManager.class.getCanonicalName();
//	private ConnectionCallback cb = new ConnectionCallback();

	public static UniversalSensorManager create(Context context) {
		if (mManager != null)
			return mManager;
		mManager = new UniversalSensorManager(context);
		return mManager;
	}
	
	private UniversalSensorManager(Context context) {
		this.context = context;
		remoteConnection = new UniversalManagerRemoteConnection(this);
		connectRemote();
	}
	
	public Device[] listDevices()
	{
		try {
			return remoteConnection.listDevices();
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public boolean registerListener(UniversalEventListener mlistener, //UniversalSensor sensor, 
			String devID, int sType, int rateUs) {
		remoteConnection.registerListener(new ConnectionCallback(mlistener), "asd", 1, 1);
		return true;
	}
	
	void connectRemote()
	{
		Intent intent = new Intent("bindUniversalSensorService");
		intent.setClassName(UNIVERSALServicePackage, UNIVERSALServiceClass);
		context.bindService(intent, remoteConnection, Context.BIND_AUTO_CREATE);
	}
	
//	public UniversalSensor getDefaultSensor(int sType)
//	{
//		
//	}
	
	class ConnectionCallback extends IUniversalSensorManager.Stub {
		UniversalEventListener mlistener = null;
		ConnectionCallback(UniversalEventListener mlistener)
		{
			this.mlistener = mlistener;
		}
		@Override
		public void onSensorChanged(SensorParcel event) throws RemoteException {
			Log.i(tag, "Event received:" + event.values[0]);
		}

	}
}
