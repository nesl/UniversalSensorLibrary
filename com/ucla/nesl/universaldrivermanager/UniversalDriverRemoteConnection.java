package com.ucla.nesl.universaldrivermanager;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.ucla.nesl.aidl.IUniversalManagerService;
import com.ucla.nesl.aidl.SensorParcel;

public class UniversalDriverRemoteConnection implements ServiceConnection {
	private IUniversalManagerService service;
	private UniversalDriverManager parent;
	
	UniversalDriverRemoteConnection(UniversalDriverManager parent) {
		this.parent = parent;
	}
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		this.service = IUniversalManagerService.Stub.asInterface(service);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}		
	
	public void push(SensorParcel sp)
	{
		try {
			service.onSensorChanged(sp);
		} catch(RemoteException e) {}
	}
}

