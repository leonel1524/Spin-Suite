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

import android.content.Context;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.erpya.spinsuite.base.util.LogM;

/**
 * Device Manager class is used for register each device to handle
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class DeviceManager {

    /**
     * Default Contructor
     * @param context
     */
	public DeviceManager(Context context) {
		this.context = context;
	}

	/** Instance for it */
	private static DeviceManager instance = null;
    /**	Logger							*/
    protected transient LogM log = new LogM(getContext(), this.getClass());

    /**
     * Get instance for it (Create new instance if it does not exist)
     * @param context
     * @return
     */
    public static DeviceManager getInstance(Context context) {
        if(instance == null) {
            instance = new DeviceManager(context);
        }
        //  Default return
        return instance;
    }

    /** Context */
	private Context context;
	/**	DeviceManager List	*/
	private Map<String, IDeviceType> deviceTypeMap = new HashMap<String, IDeviceType>();

    /**
     * Get Android Context
     * @return
     */
    public Context getContext() {
        return context;
    }

	/**
	 * Add new DeviceManager to list for availability
	 * @param deviceType
	 */
	public void addDeviceType(IDeviceType deviceType) {
		if(deviceType == null) {
			return;
		}
		//
		deviceTypeMap.put(deviceType.getDeviceTypeId(), deviceType);
    }


	/**
	 * Get DeviceManager Configuration List
	 * @return List<IDeviceType>
	 */
	private List<IDeviceType> getDeviceTypeList() {
		//	List
		return new ArrayList<IDeviceType>(deviceTypeMap.values());
	}


	
	/**
	 * Get DeviceManager Configuration from type
	 * @param deviceTypeId
	 * @return MADDeviceConfig
	 */
	public Map<String, Object> getDeviceTypeConfig(String deviceTypeId) {
		IDeviceType device = deviceTypeMap.get(deviceTypeId);
		if(device == null)
			return null;
		//	Load configuration if exists
		return device.getDeviceTypeConfig();
	}
	
	/**
	 * Get DeviceManager handler
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return DeviceTypeManagement
	 */
	public DeviceTypeHandler getDeviceHandler(String deviceId) {
	    IDeviceType device = deviceTypeMap.get(deviceId);
	    if(device == null) {
	        return null;
        }
		//	Get class from parent
		Class<?> clazz = getHandlerClass(device.getHandlerClass());
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using GenericDeviceHandler for " + device.getName());
			//	
			GenericDeviceHandler gdh = new GenericDeviceHandler(device);
			return gdh;
		}
		//	
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor(new Class[]{IDeviceType.class});
			//	new instance
			return (DeviceTypeHandler)constructor.newInstance(new Object[] {this});
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg == null)
				msg = e.toString();
			log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
		}
		//	default return
		return null;
	}

    /**
     * Get Class from device type, used for handler
     * @param className
     * @return Class<?>
     */
    private Class<?> getHandlerClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == DeviceTypeHandler.class) {
                    log.fine("Use: " + className);
                    return clazz;
                }
                //	Get Supert Class
                superClazz = superClazz.getSuperclass();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        //
        log.finest("Not found: " + className);
        return null;
    }	//	getHandlerClass
}
