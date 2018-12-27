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
 * Arduino Key and Value Util, used para define constant
 * for send message command for Arduino serial communication.
 * Note: When a command is send with a additional value then the command is for set,
 * else is for run some or get info
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public interface ISendCommand {

    /**
     * Send a command for start transmission
     * @param command
     */
    void initCommand(int command) throws Exception;

    /**
     * Send a command with additional info
     * Example: send REMOTE_SETUP ssid
     * Complete Stream: STX + SSID + | + Spin-Group + ETX
     * @param key
     * @param value
     * @throws Exception
     */
    void sendValue(String key, String value) throws Exception;

    /**
     * Send a value without key
     * Example: send REMOTE_SETUP ssid
     * Complete Stream: STX + Spin-Group + ETX
     * @param value
     * @throws Exception
     */
    void sendValue(String value) throws Exception;

    /**
     * End command transmission
     */
    void endCommand() throws Exception;

    /**
     * Request for get info from device
     * @param command
     * @throws Exception
     */
    void requestValue(int command) throws Exception;

    /**
     * Request for get info from device
     * @param command
     * @throws Exception
     */
    void requestValue(int command, String key) throws Exception;
}
