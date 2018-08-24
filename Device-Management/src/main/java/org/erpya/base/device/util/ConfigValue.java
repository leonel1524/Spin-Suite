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
package org.erpya.base.device.util;

import org.erpya.base.util.Util;
import org.erpya.base.util.ValueUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Class for save Configuration Value
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ConfigValue {

    /**
     * Unique Constructor for value
     * @param value
     */
    public ConfigValue(Object value) {
        this.value = value;
    }

    /** Value   */
    private Object value;

    /**************************************************************
     * Helper Methods
     *************************************************************/

    /**
     * Set Value
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get a value from map
     * @return
     */
    public Object getValueAsObject() {
        return value;
    }


    /**
     *  Get Value as int
     *  @return int value or 0
     */
    public int getValueAsInt() {
        return ValueUtil.getValueAsInt(value);
    }   //  getValueAsInt

    /**
     * 	Get Column Value
     *	@return value or ""
     */
    public String getValueAsString() {
        return ValueUtil.getValueAsString(value);
    }	//	get_ValueAsString

    /**
     * Get value as Boolean
     * @return boolean value
     */
    public boolean getValueAsBoolean() {
        return ValueUtil.getValueAsBoolean(value);
    }

    /**
     * Get value as Timestamp
     * @return boolean value
     */
    public Date getValueAsDate() {
        return ValueUtil.getValueAsDate(value);
    }

    /**
     * For BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getValueAsBigDecimal() {
        return ValueUtil.getValueAsBigDecimal(value);
    }

    /**
     * Get Database value as list
     * @return
     */
    public List<?> getValueAsList() {
        return ValueUtil.getValueAsList(value);
    }

    /**
     * Verify if is empty
     * @return
     */
    public boolean isEmpty() {
        if(value == null) {
            return true;
        }
        //
        if(value instanceof String) {
            return Util.isEmpty(getValueAsString());
        }
        //
        return true;
    }

    @Override
    public String toString() {
        return getValueAsString();
    }
}
