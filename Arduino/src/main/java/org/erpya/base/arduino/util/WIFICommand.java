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

/**
 * Class for send wireless command for setup
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
//  TODO: Add support to Receive command
public class WIFICommand implements ICommand {

    /**
     * Private contructor
     * use singleton
     */
    private WIFICommand() {
        ssid = "";
        psk = "";
    }

    /**
     * Instance
     * @return
     */
    public static WIFICommand getInstance() {
        if(wLLCommand == null) {
            wLLCommand = new WIFICommand();
        }
        //
        return wLLCommand;
    }

    /** Singleton   */
    private static WIFICommand wLLCommand;
    /** Wireless SSID   */
    private String ssid;
    /** PSK */
    private String psk;
    /** Send command    */
    private ISendCommand sendCommand;

    /**
     * Set a send command for it
     * @param sendCommand
     * @return
     */
    public WIFICommand withSendCommand(ISendCommand sendCommand) {
        this.sendCommand = sendCommand;
        return this;
    }

    /**
     * Set SSID
     * @param ssid
     * @return
     */
    public WIFICommand withSSID(String ssid) {
        this.ssid = ssid;
        return this;
    }

    /**
     * Set Psk
     * @param psk
     * @return
     */
    public WIFICommand withPSK(String psk) {
        this.psk = psk;
        return this;
    }

    public String getSSID() {
        return ssid;
    }

    public String getPSK() {
        return psk;
    }

    /**
     * Send Wireless values
     * @return
     */
    public boolean send() throws Exception {
        if(sendCommand == null) {
            return false;
        }
        //  for others
        sendCommand.initCommand(ICommand.WIFI);
        sendCommand.sendValue(getSSID());
        sendCommand.sendValue(getPSK());
        sendCommand.endCommand();
        return true;
    }

    @Override
    public boolean request() throws Exception {
        if(sendCommand == null) {
            return false;
        }
        //  for others
        sendCommand.requestCommand(ICommand.WIFI);
        return false;
    }
}
