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
package org.erpya.spinsuite.base.model;

import java.util.Map;

/**
 * Interface for determine methods and constants of POInfo
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public interface IPOInfo {

    /** Supported Attributes    */
    String ATTRIBUTE_AD_Table_ID = "AD_Table_ID";
    String ATTRIBUTE_TableName = "TableName";
    String ATTRIBUTE_Name = "Name";
    String ATTRIBUTE_Description = "Description";
    String ATTRIBUTE_Help = "Help";
    String ATTRIBUTE_IsDeleteable = "IsDeleteable";
    String ATTRIBUTE_IsReadOnly = "IsReadOnly";
    String ATTRIBUTE_IsDocument = "IsDocument";
    String ATTRIBUTE_IsView = "IsView";
    String ATTRIBUTE_Columns = "AD_Column";

    /**
     * Get Column Identifier if it exist
     * @return
     */
    public int getTableId();

    /**
     * Get Table Name for show
     * @return
     */
    public String getTableName();

    /**
     * Get table label
     * @return
     */
    public String getLabel();

    /**
     * Get Help from table
     * @return
     */
    public String getHelp();

    /**
     * Get description of table
     * @return
     */
    public String getDescription();

    /**
     * Verify if records are deleteables
     * @return
     */
    public boolean isDeleteable();

    /**
     * Verify if records are read only
     * @return
     */
    public boolean isReadOnly();

    /**
     * Verify if table is a document (Handle workflow and process like completeIt, closeIt, prepareIt)
     * @return
     */
    public boolean isDocument();

    /**
     * Verify if table is a View
     * @return
     */
    public boolean isView();

    /**
     * Get Columns of Table
     * @return
     */
    public Map<String, POInfoColumn> getColumns();
}
