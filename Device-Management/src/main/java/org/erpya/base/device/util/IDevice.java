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
 * Device Interface for add new device
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public interface IDevice extends IDeviceType {

    /**
     * Get Name of Device
     * @return
     */
    public String getName();

    /**
     * Set Name of Device
     * @param name
     */
    public void setName(String name);

    /**
     * Get Device Type ID for classification
     * @return
     */
    public String getDeviceTypeId();

    /**
     * Get Device ID
     * @return
     */
    public String getDeviceId();

    /**
     * Set Device ID
     * @param deviceId
     */
    public void setDeviceId(String deviceId);

    /**
     * Set Device Type ID
     * @param deviceTypeId
     */
    public void setDeviceTypeId(String deviceTypeId);

    /**
     * Get DeviceManager Config for use, example:
     * <li>Coonection Type::Bluetooh</li>
     * <li>Page::A4</li>
     * @return
     */
    public Map<String, ConfigValue> getDeviceConfig();

    /**
     * Verify if it is available
     * @return
     */
    public boolean isAvailable();

    /**
     * Set Available Status
     * @param isAvailable
     */
    public void setAvalilable(boolean isAvailable);

    /**
     * Get MAC Address
     * @return
     */
    public String getAddress();


    /**
     * Set MAC Address
     * @param address
     */
    public void setAddress(String address);
}
