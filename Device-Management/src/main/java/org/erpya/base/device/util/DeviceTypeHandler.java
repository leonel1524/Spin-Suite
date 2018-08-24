/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, write to the Free Software Foundation, Inc.,            *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.erpya.base.device.util;

import android.content.Context;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.erpya.base.device.event.DeviceEvent;
import org.erpya.base.device.event.DeviceEventListener;
import org.erpya.base.exceptions.SpinSuiteException;
import org.erpya.base.util.Env;
import org.erpya.base.util.Util;


/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 2 ] Add Abstract Parent Class for handle currentDevice
 *		@see https://github.com/erpcya/DeviceManagement/issues/2
 */
public abstract class DeviceTypeHandler {

	/**
	 * Standard constructor
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param context
	 * @param deviceType
	 * @return void
	 */
	public DeviceTypeHandler(Context context, IDeviceType deviceType) {
		if(deviceType == null)
			throw new SpinSuiteException("@AD_Device_ID@ @NotFound@");
		//	Set DeviceManager
		this.deviceType = deviceType;
		this.context = context;
	}
	
	/**	Device Type */
	private IDeviceType deviceType = null;
	/** Device  */
	private IDevice currentDevice = null;
	/**	Is Open			*/
	private boolean isConnected = false;
	/**	Context	*/
	private Context context = null;
	/**	Event Listener	*/
	private List<DeviceEventListener> listeners = new ArrayList<DeviceEventListener>();
	/** Device Map  */
    private Map<String, IDevice> deviceMap = new HashMap<String, IDevice>();
    /** Context */
    private final String CONTEXT_DEFAULT_DEVICE = "#Device|";

	/**
	 * Get Context
	 * @return
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * Get DeviceManager
	 * @return
	 * @return MADDevice
	 */
	public IDeviceType getDeviceType() {
		return deviceType;
	}

    /**
     * Get Current Device
     * @param reload reload device
     * @return
     */
    protected IDevice getCurrentDevice(boolean reload) {
        if(currentDevice == null
                || reload) {
            currentDevice = getDefaultDevice();
        }
        return currentDevice;
    }

    /**
     * Get Current device configured
     * @return
     */
    public IDevice getCurrentDevice() {
        return getCurrentDevice(false);
    }

    /**
     * Set Device Selected by default
     * @param device
     */
    public void setCurrentDevice(IDevice device) {
        currentDevice = device;
    }

    /**
     * Add Device to list
     * @param device
     */
    protected void addDevice(IDevice device) {
        if(device == null) {
            return;
        }
        deviceMap.put(device.getDeviceId(), device);
    }

    /**
     * Get Device from ID if exist
     * @param deviceId
     * @return
     */
    public IDevice getDevice(String deviceId) {
        return deviceMap.get(deviceId);
    }

    /**
     * Get Device List from cache map
     * @return
     */
    public List<IDevice> getDeviceList() {
        //	List
        if(deviceMap.isEmpty()) {
            getAvailableDeviceList();
        }
        return new ArrayList<IDevice>(deviceMap.values());
    }

    /**
     * Get Default device selected previously
     * @return
     */
    public IDevice getDefaultDevice() {
        //  Get ID from context
        String deviceId = Env.getContext(CONTEXT_DEFAULT_DEVICE + "|" + getDeviceType().getDeviceTypeId());
        IDevice device = null;
        if(!Util.isEmpty(deviceId)) {
			device = getDevice(deviceId);
        }
        //
        if(device == null){
            List<IDevice> deviceList = getDeviceList();
            if(deviceList != null) {
				for(IDevice deviceSearched : deviceList) {
					Env.setContext(CONTEXT_DEFAULT_DEVICE + "|" + getDeviceType().getDeviceTypeId(), deviceSearched.getDeviceId());
					return deviceSearched;
				}
            }
        }
        //  Device not found
        Env.setContext(CONTEXT_DEFAULT_DEVICE + "|" + getDeviceType().getDeviceTypeId(), (String) null);
        return device;
    }

	/**
	 * Get Device List connected to hardware
	 * @return
	 */
	public abstract List<IDevice> getAvailableDeviceList();
	
	/**
	 * Add Listener
	 * @param listener
	 * @return void
	 */
	public synchronized void addDeviceListener(DeviceEventListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove Listener
	 * @param listener
	 * @return void
	 */
	public synchronized void removeDeviceListener(DeviceEventListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Fire currentDevice event
	 * @param eventType
	 * @return void
	 */
	protected synchronized void fireDeviceEvent(int eventType) {
		DeviceEvent ev = new DeviceEvent(this, eventType);
		//	Get Iterator
		Iterator<DeviceEventListener> iterator = listeners.iterator();
        //	Iterate
		while(iterator.hasNext()) {
            ((DeviceEventListener) iterator.next()).deviceEvent(ev);
        }
	}
	
	/**
	 * Verify if is connected
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 
	 * @return
	 * @return boolean
	 */
	public boolean isConnected() {
		return isConnected;
	}
	
	/**
	 * Set Is connected flag
	 * @param isConnected
	 * @return void
	 */
	protected void setIsConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	/**
	 * Get Available devices from a array
	 * @throws Exception
	 * @return boolean
	 */
	public abstract boolean isAvailable() throws Exception;
	
	/**
	 * Connect with DeviceManager, it method is used for establish a connection
	 * with a currentDevice
	 * @throws Exception
	 * @return Object connection
	 */
	public abstract Object connect() throws Exception;
	
	/**
	 * Close connection with currentDevice, it method is used for close the connection with currentDevice
	 * @throws Exception
	 * @return void
	 */
	public abstract void close() throws Exception;
	
	/**
	 * Used for read data from currentDevice
	 * @throws Exception
	 * @return Object
	 */
	public abstract Object read() throws Exception;
	
	/**
	 * Used for write data to currentDevice
	 * @throws Exception
	 * @return Object
	 */
	public abstract Object write(Object... value) throws Exception;
	
	/**
	 * Get Input Stream
	 * @return InputStream
	 */
	public abstract InputStream getInputStream();
	
	/**
	 * Get Output Stream
	 * @return InputStream
	 */
	public abstract OutputStream getOutputStream();

    /**
     * Get Error Message from error code
     * @param error
     * @return
     */
	public abstract String getErrorMsg(String error);

    @Override
    public String toString() {
        return "DeviceTypeHandler{" +
                "deviceType=" + deviceType +
                ", currentDevice=" + currentDevice +
                '}';
    }
}
