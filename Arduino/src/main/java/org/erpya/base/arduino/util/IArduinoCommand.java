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
public interface IArduinoCommand {

    /** Supported commands  */
    /** Maintenance commands    */
    int RESET_DEVICE = 1;
    int CLEAR_EEPROM = 2;
    int DEVICE_INFO = 3;
    int GET_RESULT = 4;
    /** Wireless setup / info   */
    int WIFI_SSID = 5;
    int WIFI_PSK = 6;
    int WIFI_IP = 7;
    int WIFI_INFO = 8;
    int WIFI_STATUS = 9;
    int WIFI_SAVE_INFO = 10;
    /** MQTT setup / info   */
    int MQTT_BROKER = 11;
    int MQTT_PORT = 12;
    int MQTT_PUBLISH_TOPIC = 13;
    int MQTT_SUBSCRIBE_TOPIC = 14;
    int MQTT_USER = 15;
    int MQTT_PASS = 16;
    int MQTT_STATUS = 17;
    int MQTT_INFO = 18;
    int MQTT_SAVE_INFO = 19;

    /**
     * Sen a command with additional info
     * Example 1: send WIFI ssid
     * Complete Stream: STX5|Spin-GroupETX
     * Example 2: send MQTT broker info
     * Complete Stream: STX11|test.mosquitto.orgETX
     * @param command
     * @param message
     * @throws Exception
     */
    void sendCommand(int command, String message) throws Exception;

    /**
     * Send simple command
     * Example 1: send a statement for reset device
     * Complete Stream: STX1ETX
     * Example 2: send a statement for device info
     * Complete Stream: STX3ETX
     * @param command
     * @throws Exception
     */
    void sendCommand(int command) throws Exception;

    /**
     * Get result of request command
     * Example: Run WIFI IP command
     * Complete Stream: STX7ETX
     * After send previous command just run get Result method
     * and it should be retrieve some like STX7|192.168.1.2ETX
     * @return
     */
    String getResult();
}
