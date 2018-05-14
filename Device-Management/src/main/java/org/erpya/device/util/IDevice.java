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

import java.util.Map;

/**
 * Device Interface for add new device
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public interface IDevice extends IDeviceType {

    /**
     * Get Id configured for DeviceManager Type, example:
     * <li>ID::#PR1</li>
     * <li>Name::Printer</li>
     * @return
     */
    public String getTypeId();

    /**
     * Get Name of Device
     * @return
     */
    public String getName();

    /**
     * Get Device Type ID for classification
     * @return
     */
    public String getDeviceTypeId();

    /**
     * Get DeviceManager Config for use, example:
     * <li>Coonection Type::Bluetooh</li>
     * <li>Page::A4</li>
     * @return
     */
    public Map<String, Object> getDeviceConfig();

    /**
     * Verify if it is available
     * @return
     */
    public boolean isAvailable();
}
