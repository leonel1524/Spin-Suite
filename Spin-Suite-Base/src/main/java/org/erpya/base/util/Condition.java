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
package org.erpya.base.util;

import java.util.Arrays;

/**
 * Condition class is used as component for Criteria class
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class Condition {

    /**
     * For from and to with between
     * @param keyAttribute
     * @param comparator
     * @param value
     * @param valueTo
     */
    public Condition(String keyAttribute, String comparator, Object value, Object valueTo) {
        this.keyAttribute = keyAttribute;
        this.comparator = comparator;
        this.value = value;
        this.valueTo = valueTo;
    }

    /**
     * Default constructor with mandatory values
     * @param keyAttribute
     * @param comparator
     * @param value
     */
    public Condition(String keyAttribute, String comparator, Object value) {
        this(keyAttribute, comparator, value, null);
    }

    /**
     * For IN(,) clause
     * @param keyAttribute
     * @param comparator
     * @param values
     */
    public Condition(String keyAttribute, String comparator, Object... values) {
        this(keyAttribute, comparator, null, null);
        this.values = values;
    }

    /** Key Attribute   */
    private String keyAttribute;
    /** Comparator  */
    private String comparator;
    /** Value for condition */
    private Object value;
    /** Value for condition */
    private Object valueTo;
    /** Array for values    */
    private Object[] values;

    /** Equal 			*/
    public static final String	EQUAL = "=";
    /** Not Equal		*/
    public static final String	NOT_EQUAL = "!=";
    /** Like			*/
    public static final String	LIKE = " LIKE ";
    /** Not Like		*/
    public static final String	NOT_LIKE = " NOT LIKE ";
    /** Greater			*/
    public static final String	GREATER = ">";
    /** Greater Equal	*/
    public static final String	GREATER_EQUAL = ">=";
    /** Less			*/
    public static final String	LESS = "<";
    /** Less Equal		*/
    public static final String	LESS_EQUAL = "<=";
    /** Between			*/
    public static final String	BETWEEN = " BETWEEN ";
    /** For 	*/
    public static final String 	NOT_NULL = " IS NOT NULL ";
    /** For 	*/
    public static final String 	NULL = " IS NULL ";
    /** For IN	*/
    public static final String 	IN = " IN ";
    /** For NOT IN	*/
    public static final String 	NOT_IN = " NOT IN ";

    public String getKeyAttribute() {
        return keyAttribute;
    }

    public String getComparator() {
        return comparator;
    }

    public Object getValue() {
        return value;
    }

    public Object getValueTo() {
        return valueTo;
    }

    public Object[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "keyAttribute='" + keyAttribute + '\'' +
                ", comparator='" + comparator + '\'' +
                ", value=" + value +
                ", valueTo=" + valueTo +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
