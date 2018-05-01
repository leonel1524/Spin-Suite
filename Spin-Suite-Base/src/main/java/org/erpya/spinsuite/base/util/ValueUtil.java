/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.erpya.spinsuite.base.util;

import android.util.Log;

import java.util.Date;
import java.util.List;

/**
 * Value Util allows cast class
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public class ValueUtil {

    /**************************************************************
     * Helper Methods
     *************************************************************/

    /**
     *  Get Value as int
     *  @param Value for cast
     *  @return int value or 0
     */
    public static int getValueAsInt(Object value) {
        //
        if (value instanceof Integer)
            return ((Integer)value).intValue();
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException ex) {
            Log.w("getValueAsInt", value + " - " + ex.getMessage());
            return 0;
        }
    }   //  getValueAsInt

    /**
     * 	Get Column Value
     *	@param key name
     *	@return value or ""
     */
    public static String getValueAsString (Object value) {
        if (value == null)
            return "";
        return value.toString();
    }	//	get_ValueAsString

    /**
     * Get value as Boolean
     * @param Value for cast
     * @return boolean value
     */
    public static boolean getValueAsBoolean(Object value) {
        if (value != null) {
            if (value instanceof Boolean)
                return ((Boolean)value).booleanValue();
            return "Y".equals(value);
        }
        return false;
    }

    /**
     * Get value as Timestamp
     * @param Value for cast
     * @return boolean value
     */
    public static Date getValueAsDate(Object value) {
        if (value != null) {
            if (value instanceof Date)
                return (Date) value;
        }
        return null;
    }

    /**
     * Get Database value as list
     * @param Value for cast
     * @return
     */
    public static List<?> getValueAsList(Object value) {
        if (value != null) {
            if (value instanceof List)
                return (List<?>) value;
        }
        return null;
    }
}
