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
package org.erpya.base.arduino.supported;

import org.erpya.base.arduino.ArduinoBluetoothHandler;
import org.erpya.base.device.util.ConfigValue;
import org.erpya.base.device.util.IDeviceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Available Device for Bluetooth communication
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public class UNO implements IDeviceType {

    /** Device Type Configuration   */
    private Map<String, ConfigValue> deviceTypeConfig = new HashMap<>();

    @Override
    public String getDeviceTypeId() {
        return "UNO";
    }

    @Override
    public String getName() {
        return "Arduino UNO";
    }

    @Override
    public String getType() {
        return TYPE_ARDUINO;
    }

    @Override
    public Map<String, ConfigValue> getDeviceTypeConfig() {
        return deviceTypeConfig;
    }

    @Override
    public ConfigValue getConfigValue(String key) {
        return deviceTypeConfig.get(key);
    }

    @Override
    public void addConfigValue(String key, Object value) {
        deviceTypeConfig.put(key, new ConfigValue(value));
    }

    @Override
    public String getHandlerClass() {
        return ArduinoBluetoothHandler.class.getName();
    }

    @Override
    public String toString() {
        return "Arduino UNO{" +
                "getDeviceTypeId=" + getDeviceTypeId() +
                ", getName=" + getName() +
                ", getType=" + getType() +
                '}';
    }
}
