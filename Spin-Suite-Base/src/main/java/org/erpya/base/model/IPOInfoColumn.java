/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com				  		                 *
 * Contributor(s): Carlos Parada cparada@erpya.com				  		             *
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
package org.erpya.base.model;

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
    String ATTRIBUTE_FormatPattern = "FormatPattern";
    String ATTRIBUTE_ContextInfoScript = "ContextInfoScript";
    String ATTRIBUTE_ContextInfoFormatter = "ContextInfoFormatter";


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

    /**
     * Get Format Pattern
     * @return
     */
    public String getFormatPattern();

    /**
     * Get Context Info Script
     * @return
     */
    public String getContextInfoScript();

    /**
     * Get Context Info Formatter
     * @return
     */
    public String getContextInfoFormatter();
}
