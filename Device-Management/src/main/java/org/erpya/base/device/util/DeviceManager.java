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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.erpya.base.util.Env;
import org.erpya.base.util.LogM;
import org.erpya.base.util.Util;

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
	/** Context */
	private final String CONTEXT_DEFAULT_DEVICE_HANDLER = "#DeviceTypeHandler|";
    /**	Logger							*/
    protected transient LogM log = new LogM(getContext(), this.getClass());

    /**
     * Get instance for it (Create new instance if it does not exist)
     * @return
     */
    public static DeviceManager getInstance() {
        getInstance(Env.getContext());
        //  Default return
        return instance;
    }

    /**
     * Get instance with context
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
	/**	DeviceManager Map	*/
	private Map<String, IDeviceType> deviceTypeMap = new HashMap<String, IDeviceType>();
    /**	Handler Map	*/
    private Map<String, DeviceTypeHandler> handlerMap = new HashMap<String, DeviceTypeHandler>();

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
	public Map<String, ConfigValue> getDeviceTypeConfig(String deviceTypeId) {
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
	public DeviceTypeHandler getDeviceHandler(String deviceTypeId) {
	    IDeviceType deviceType = deviceTypeMap.get(deviceTypeId);
	    if(deviceType == null) {
	        return null;
        }
		//	default return
		return getDeviceHandler(deviceType);
	}

    /**
     * Get Default device handler from type
     * @param type
     * @return configured device or first on searched
     */
    public DeviceTypeHandler getDefaultDeviceHandler(String type) {
        if(Util.isEmpty(type)) {
            return null;
        }
        //
        DeviceTypeHandler handler = null;
        //  Get ID from context
        String deviceTypeId = Env.getContext(CONTEXT_DEFAULT_DEVICE_HANDLER + "|" + type);
        if(!Util.isEmpty(deviceTypeId)) {
            handler = getDeviceHandler(deviceTypeId);
        }
        //  Validate
        if(handler == null) {
            List<IDeviceType> deviceList = getDeviceTypeList();
            if(deviceList != null) {
                for(IDeviceType deviceType : deviceList) {
                    if(deviceType.getType().equals(type)) {
                        Env.setContext(CONTEXT_DEFAULT_DEVICE_HANDLER + "|" + type, deviceType.getDeviceTypeId());
                        return getDeviceHandler(deviceType);
                    }
                }
            }
        }
        //  Default Return
        Env.setContext(CONTEXT_DEFAULT_DEVICE_HANDLER + "|" + type, (String) null);
        return null;
    }

    /**
     * Get Device Type Handler
     * @param deviceType
     * @return
     */
	public DeviceTypeHandler getDeviceHandler(IDeviceType deviceType) {
	    DeviceTypeHandler handler = handlerMap.get(deviceType.getDeviceTypeId());
	    //  Get
        if(handler != null) {
            return handler;
        }
        //	Get class from parent
        Class<?> clazz = getHandlerClass(deviceType.getHandlerClass());
        //	Not yet implemented
        if (clazz == null) {
            log.log(Level.INFO, "Using GenericDeviceHandler for " + deviceType.getName());
            //
            handler = new GenericDeviceHandler(context, deviceType);
        } else {
            //
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getDeclaredConstructor(new Class[]{Context.class, IDeviceType.class});
                //	new instance
                handler = (DeviceTypeHandler)constructor.newInstance(new Object[] {context, deviceType});
            } catch (Exception e) {
                String msg = e.getMessage();
                if (msg == null)
                    msg = e.toString();
                log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
            }
        }
        //  Save on map
        handlerMap.put(deviceType.getDeviceTypeId(), handler);
        //
        return handler;
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
