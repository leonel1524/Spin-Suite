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

/**
 * Interface for determine methods and constants of POInfoColumn
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public interface IPOInfoColumn {

    /** Supported Attributes    */
    String ATTRIBUTE_AD_Column_ID = "AD_Column_ID";
    String ATTRIBUTE_ColumnName = "ColumnName";
    String ATTRIBUTE_ColumnSQL = "ColumnSQL";
    String ATTRIBUTE_DisplayType = "DisplayType";
    String ATTRIBUTE_IsMandatory = "IsMandatory";
    String ATTRIBUTE_DefaultLogic = "DefaultLogic";
    String ATTRIBUTE_IsUpdateable = "IsUpdateable";
    String ATTRIBUTE_IsAlwaysUpdateable = "IsAlwaysUpdateable";
    String ATTRIBUTE_Name = "Name";
    String ATTRIBUTE_Description = "Description";
    String ATTRIBUTE_Help = "Help";
    String ATTRIBUTE_IsKey = "IsKey";
    String ATTRIBUTE_IsParent = "IsParent";
    String ATTRIBUTE_IsTranslated = "IsTranslated";
    String ATTRIBUTE_IsEncrypted = "IsEncrypted";
    String ATTRIBUTE_IsAllowLogging = "IsAllowLogging";
    String ATTRIBUTE_ValidationCode = "ValidationCode";
    String ATTRIBUTE_FieldLength = "FieldLength";
    String ATTRIBUTE_ValueMin = "ValueMin";
    String ATTRIBUTE_ValueMax = "ValueMax";
    String ATTRIBUTE_IsAllowCopy = "IsAllowCopy";


    /**
     * Get Table Name
     * @return
     */
    public String getTableName();

    /**
     * Get Column Identifier if it exist
     * @return
     */
    public int getColumnId();

    /**
     * Get Column Name for show
     * @return
     */
    public String getColumnName();

    /**
     * Get SQL of Column
     * @return
     */
    public String getColumnSQL();

    /**
     * Get Display Type
     * @return
     */
    public int getDisplayType();

    /**
     * Get Column Class (Used for know if is Integer, Boolean, String, and other)
     * @return
     */
    public Class<?> getColumnClass();

    /**
     * Verify if it is mandatory before save
     * @return
     */
    public boolean isMandatory();

    /**
     * Get a default logic for new event listener
     * @return
     */
    public String getDefaultLogic();

    /**
     * Show if it can be updated
     * @return
     */
    public boolean isUpdateable();

    /**
     * show if it can be always updated
     * @return
     */
    public boolean isAlwaysUpdateable();

    /**
     * Get column label
     * @return
     */
    public String getColumnLabel();

    /**
     * Get description of column
     * @return
     */
    public String getColumnDescription();

    /**
     * Get column help
     * @return
     */
    public String getColumnHelp();

    /**
     * Verify it it is a key
     * @return
     */
    public boolean isKey();

    /**
     * verify it it have a parent table
     * @return
     */
    public boolean isParent();

    /**
     * Verify if it a translated column
     * @return
     */
    public boolean isTranslated();

    /**
     * Show if it is a encrypted column
     * @return
     */
    public boolean isEncrypted();

    /**
     * Show if is a allow logging
     * @return
     */
    public boolean isAllowLogging();

    /**
     * Get a validation code for it column
     * @return
     */
    public String getValidationCode();

    /**
     * Get field length
     * @return
     */
    public int getFieldLength();

    /**
     * Ver minimum value
     * @return
     */
    public String getValueMin();

    /**
     * Get Maximum value
     * @return
     */
    public String getValueMax();

    /**
     * Verifi if allow copy
     * @return
     */
    public boolean isAllowCopy();

}
