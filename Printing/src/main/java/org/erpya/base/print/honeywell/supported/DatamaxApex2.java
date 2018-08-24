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
package org.erpya.base.print.honeywell.supported;

import org.erpya.base.device.util.ConfigValue;
import org.erpya.base.device.util.IDeviceType;
import org.erpya.base.print.honeywell.HoneywellPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Available Device for printing
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public class DatamaxApex2 implements IDeviceType {

    /** Device Type Configuration   */
    private Map<String, ConfigValue> deviceTypeConfig = new HashMap<>();

    @Override
    public String getDeviceTypeId() {
        return "PR2";
    }

    @Override
    public String getName() {
        return "Datamax O'neil Apex 2";
    }

    @Override
    public String getType() {
        return TYPE_PRINTER;
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
        return HoneywellPrinter.class.getName();
    }

    @Override
    public String toString() {
        return "DatamaxApex2{" +
                "getDeviceTypeId=" + getDeviceTypeId() +
                ", getName=" + getName() +
                ", getType=" + getType() +
                '}';
    }
}
