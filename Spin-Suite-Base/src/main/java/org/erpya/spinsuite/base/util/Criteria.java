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

import java.util.ArrayList;
import java.util.List;

/**
 * Used for handle Criteria Query
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class Criteria {

    /**
     * Instance of Criteria with table name or alias
     */
    public Criteria() {
    }

    /** Map for old key and values  */
    private List<Condition> conditionList = new ArrayList<Condition>();

    /**
     * Add criteria value
     * @param attributeKey
     * @param comparator
     * @param value
     * @return
     */
    public Criteria addCriteria(String attributeKey, String comparator, Object value) {
        conditionList.add(new Condition(attributeKey, comparator, value));
        return this;
    }

    /**
     * Add criteria for IN
     * @param attributeKey
     * @param values
     * @return
     */
    public Criteria addCriteriaIN(String attributeKey, Object... values) {
        conditionList.add(new Condition(attributeKey, Condition.IN, values));
        return this;
    }

    /**
     * Add criteria for NOT IN
     * @param attributeKey
     * @param values
     * @return
     */
    public Criteria addCriteriaNOTIN(String attributeKey, Object... values) {
        conditionList.add(new Condition(attributeKey, Condition.NOT_IN, values));
        return this;
    }

    /**
     * Get Criteria List
     * @return
     */
    public List<Condition> getCriteriaList() {
        return conditionList;
    }

}
