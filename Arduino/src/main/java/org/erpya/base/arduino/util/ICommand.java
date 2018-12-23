/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
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

public interface ICommand {
    /** Start of Header */
    char SOH_CHARACTER = 1;
    /** Start of Text   */
    char STX_CHARACTER = 2;
    /** End of Text */
    char ETX_CHARACTER = 3;
    /** End of Transmission */
    char EOT_CHARACTER = 4;
    /** Supported commands  */
    int MESSAGE = 0;
    /** Maintenance commands    */
    int RESET_DEVICE = 1;
    int CLEAR_EEPROM = 2;
    int DEVICE_INFO = 3;
    /** Wireless setup / info   */
    int WIFI = 4;
    /** MQTT setup / info   */
    int MQTT = 5;

    /**
     * Method used for send command, can be used for send complete command or parse received command
     * @return
     * @throws Exception
     */
    boolean send() throws Exception;

    /**
     * Request info from Arduino
     * @return
     * @throws Exception
     */
    boolean request() throws Exception;
}
