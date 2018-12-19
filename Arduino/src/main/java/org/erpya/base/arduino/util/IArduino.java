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
package org.erpya.base.arduino.util;

/**
 * Arduino connection interface
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public interface IArduino {
    /** Mandatory Attributes  */
    public static final String ARDUINO_TYPE_KEY = "#ARDUINO_TYPE";
    public static final String INTERFACE_TYPE_KEY = "#INTERFACE_TYPE";
    /**Values   */
    public static final String ARDUINO_UNO = "UNO";
    public static final String INTERFACE_BLUETOOTH = "BLU";
    public static final String INTERFACE_WIRELESS = "WRL";
    public static final char START_CHARACTER = 1;
    public static final char END_CHARACTER = 2;

    /**
     * Send a message
     */
    public void sendMessage(String message) throws Exception;

}
