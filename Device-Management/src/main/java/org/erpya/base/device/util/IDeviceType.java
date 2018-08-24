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

import java.util.Map;

/**
 * DeviceManager Interface for add new device to list
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public interface IDeviceType {

    /** Device Type List    */
    public String TYPE_PRINTER = "PRT";
    public String TYPE_BARCODE_SCAN = "BCS";
    public String TYPE_RFID_SCAN = "RFI";
    public String TYPE_CUSTOM = "CUS";

    /**
     * Get Id configured for DeviceManager Type, example:
     * <li>ID::#PR1</li>
     * <li>Name::Printer</li>
     * @return
     */
    public String getDeviceTypeId();

    /**
     * Get Name of Device
     * @return
     */
    public String getName();

    /**
     * Get Device Type for classification
     * <li>TYPE_PRINTER</li>
     * <li>TYPE_BARCODE_SCAN</li>
     * <li>TYPE_RFID_SCAN</li>
     * <li>TYPE_CUSTOM</li>
     * @return
     */
    public String getType();

    /**
     * Get DeviceManager Config for use, example:
     * <li>Coonection Type::Bluetooh</li>
     * <li>Page::A4</li>
     * @return
     */
    public Map<String, ConfigValue> getDeviceTypeConfig();

    /**
     * Get a Configuration Value
     * @param key
     * @return
     */
    public ConfigValue getConfigValue(String key);

    /**
     * Add device config value
     * @param key
     * @param value
     */
    public void addConfigValue(String key, Object value);

    /**
     * Get DeviceManager Handler class (Used for connect with device)
     * @return
     */
    public String getHandlerClass();
}
