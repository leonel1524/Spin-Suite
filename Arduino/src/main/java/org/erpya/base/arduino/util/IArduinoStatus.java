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
 * Interface used for represent a status of message for Arduino Bluetooth connection
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public interface IArduinoStatus {
    /**	Open Printer connection	*/
    public final int OPENING_CONNECTION = 0;
    /**	Close Printer connection	*/
    public final int CLOSING_CONNECTION = 1;
    /**	Sending a Text	*/
    public final int SENDING_TEXT = 2;
    /**	Connection Error	*/
    public final int CONNECTION_ERROR = 10;
    /**	Generic Error	*/
    public final int ERROR = 12;
    /**	Close Printer connection	*/
    public final int CONNECTED = 3;
    /**
     * Used for publish print status
     */
    public void publishStatus(String message);
}
