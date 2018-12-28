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

import java.util.HashMap;

/**
 * Contract for setup attributes
 */
public class SetupAttribute {

    /**
     * Constructor
     */
    public SetupAttribute() {
        attributes = new HashMap<>();
    }
    /** Attributes  */
    private HashMap<String, String> attributes;

    /**
     * Get Attributes added
     * @return
     */
    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Add Attribute
     * @param key
     * @param value
     */
    public void addAttribute(String key, String value) {
        if(Util.isEmpty(key)) {
            return;
        }
        if(Util.isEmpty(value)) {
            attributes.remove(key);
        } else {
            attributes.put(key, value);
        }
    }

    /**
     * Get attribute from key
     * @param key
     * @return
     */
    public String getAttribute(String key) {
        return attributes.get(key);
    }
}
