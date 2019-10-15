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
package org.erpya.base.arduino.setup;

import org.erpya.base.util.Util;

/**
 * This class have WIFI attributes for setup
 */
public class WIFIAttribute extends SetupAttribute {


    public WIFIAttribute() {
        super();
    }

    /**
     * From default attribute
     * @param attribute
     */
    public WIFIAttribute(SetupAttribute attribute) {
        this();
        withSSID(attribute.getAttribute(SSID_KEY));
        withPSK(attribute.getAttribute(PSK_KEY));
    }

    /**
     * Constructor for default values
     * @param ssid
     * @param psk
     */
    public WIFIAttribute(String ssid, String psk) {
        this();
        withSSID(ssid);
        withPSK(psk);
    }

    /** Key Commands    */
    public static final String SSID_KEY = "SSID";
    public static final String PSK_KEY = "PSK";
    public static final String TEST_WIFI_KEY = "TestWifi";

    /**
     * Set SSID
     * @param ssid
     * @return
     */
    public WIFIAttribute withSSID(String ssid) {
        addAttribute(SSID_KEY, ssid);
        return this;
    }

    /**
     * Set Psk
     * @param psk
     * @return
     */
    public WIFIAttribute withPSK(String psk) {
        addAttribute(PSK_KEY, psk);
        return this;
    }

    /**
     * Get SSID
     * @return
     */
    public String getSSID() {
        return getAttribute(SSID_KEY);
    }

    /**
     * Send test WIFI
     * @param testWifi
     * @return
     */
    public WIFIAttribute withTestWifi(boolean testWifi) {
        addAttribute(TEST_WIFI_KEY, testWifi? "Y": "N");
        return this;
    }

    /**
     * Return true when is ok
     * @return
     */
    public boolean getTestWifi() {
        String isOkAsString = getAttribute(TEST_WIFI_KEY);
        return Util.isEmpty(isOkAsString) && isOkAsString.equals("Y");
    }

    /**
     * Get PSK
     * @return
     */
    public String getPSK() {
        return getAttribute(PSK_KEY);
    }
}
