/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * Contributor(s): Carlos Parada cparada@erpya.com                                   *
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
    String ARDUINO_TYPE_KEY = "#ARDUINO_TYPE";
    String INTERFACE_TYPE_KEY = "#INTERFACE_TYPE";
    /**Values   */
    String ARDUINO_UNO = "UNO";
    String INTERFACE_BLUETOOTH = "BLU";
    char STX_CHARACTER = 2;
    char ETX_CHARACTER = 3;
    /** Value Separator */
    char SEPARATOR = '|';

    /**
     * Send a message
     */
    void sendMessage(String message) throws Exception;

}
