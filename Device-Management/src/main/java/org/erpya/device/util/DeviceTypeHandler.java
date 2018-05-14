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
package org.erpya.device.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.erpya.device.event.DeviceEvent;
import org.erpya.device.event.DeviceEventListener;
import org.erpya.spinsuite.base.exceptions.SpinSuiteException;


/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 2 ] Add Abstract Parent Class for handle currentDevice
 *		@see https://github.com/erpcya/DeviceManagement/issues/2
 */
public abstract class DeviceTypeHandler {

	/**
	 * Standard constructor
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param deviceType
	 * @return void
	 */
	public DeviceTypeHandler(IDeviceType deviceType) {
		if(deviceType == null)
			throw new SpinSuiteException("@AD_Device_ID@ @NotFound@");
		//	Set DeviceManager
		this.deviceType = deviceType;
	}
	
	/**	Device Type */
	private IDeviceType deviceType = null;
	/** Device  */
	private IDevice currentDevice = null;
	/**	Is Open			*/
	private boolean 	isConnected = false;
	/**	Event Listener	*/
	private List<DeviceEventListener> listeners = new ArrayList<DeviceEventListener>();
	
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
     * @return
     */
    public IDevice getCurrentDevice() {
        return currentDevice;
    }

    /**
     * Set Device Selected by default
     * @param device
     */
    public void setCurrentDevice(IDevice device) {
        currentDevice = device;
    }
	
	/**
	 * Get Available devices with optional Where Clause
	 * @param onlyAvailable
	 * @return
	 * @return List<MADDevice>
	 */
	public List<IDevice> getDeviceList(boolean onlyAvailable) {
		List<IDevice> devices = getDeviceList();
		List<IDevice> availables = new ArrayList<IDevice>();
		//  For all
		if(!onlyAvailable) {
            return devices;
        }
		if(devices == null) {
			return null;
		}
		//	Iterate
		for(IDevice device : devices) {
		    if(!device.isAvailable()) {
		        continue;
            }
            //  Add
            availables.add(device);
		}
		//	Convert to array
		return availables;
	}

	/**
	 * Get Device List connected to hardware
	 * @return
	 */
	public abstract List<IDevice> getDeviceList();
	
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
}
