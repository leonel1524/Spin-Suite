/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com				  		                 *
 * Contributor(s): Carlos Parada cparada@erpya.com				  		             *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
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
