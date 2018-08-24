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
package org.erpya.base.db;

import org.erpya.base.util.Criteria;

import java.util.Map;

/**
 * Interface of distint DB support
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public interface DBSupport {

    /**
     * Open Database for use
     */
    public void open() throws Exception;

    /**
     * Close Database
     * @throws Exception
     */
    public void close() throws Exception;

    /**
     * Save a Map from entity parameter
     * @param entity
     */
    public void saveMap(Map<String, Object> entity) throws Exception;

    /**
    * get Map with attributes and values from criteria
    */
    public Map<String, Object> getMap(Criteria condition) throws Exception;

    /**
     * Verify if it is open
     * @return
     */
    public boolean isOpen();
}
