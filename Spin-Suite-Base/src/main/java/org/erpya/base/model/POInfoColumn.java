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
package org.erpya.base.model;

import android.content.Context;

import org.erpya.base.util.DisplayType;
import org.erpya.base.util.Util;
import org.erpya.base.util.ValueUtil;

import java.util.Map;

/**
 * Metadata info for Table and column
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public class POInfoColumn implements org.erpya.base.model.IPOInfoColumn {

    /**
     * Simple constructor
     * @param context
     */
    public POInfoColumn(Context context) {
        this.context = context;
    }

    public POInfoColumn(Context context, Map<String, Object> attributes) {
        this.context = context;
        if(attributes != null
                && !attributes.isEmpty()) {
            columnId = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_AD_Column_ID));
            columnName = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ColumnName));
            columnSQL = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ColumnSQL));
            displayType = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_DisplayType));
            isMandatory = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsMandatory));
            defaultLogic = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_DefaultLogic));
            isUpdateable = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsUpdateable));
            isAlwaysUpdateable = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsAlwaysUpdateable));
            name = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Name));
            description = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Description));
            help = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Help));
            isKey = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsKey));
            isParent = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsParent));
            isTranslated = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsTranslated));
            isEncrypted = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsEncrypted));
            isAllowLogging = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsAllowLogging));
            validationCode = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ValidationCode));
            fieldLength = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_FieldLength));
            valueMin = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ValueMin));
            valueMax = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ValueMax));
            isAllowCopy = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsAllowCopy));
            formatPattern = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_FormatPattern));
            contextInfoScript = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ContextInfoScript));
            contextInfoFormatter = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_ContextInfoFormatter));
        }
    }

    /**
     * Set attribute
     * @param attributeKey
     * @param value
     */
    public void setAttribute(String attributeKey, Object value) {
        if(Util.isEmpty(attributeKey)) {
            return;
        }
        //  Add
        switch (attributeKey) {
            case ATTRIBUTE_AD_Column_ID:
                columnId = ValueUtil.getValueAsInt(value);
                break;
            case ATTRIBUTE_ColumnName:
                columnName = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_ColumnSQL:
                columnSQL = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_DisplayType:
                displayType = ValueUtil.getValueAsInt(value);
                break;
            case ATTRIBUTE_IsMandatory:
                isMandatory = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_DefaultLogic:
                defaultLogic = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_IsUpdateable:
                isUpdateable = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_IsAlwaysUpdateable:
                isAlwaysUpdateable = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_Name:
                name = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_Description:
                description = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_Help:
                help = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_IsKey:
                isKey = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_IsParent:
                isParent = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_IsTranslated:
                isTranslated = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_IsEncrypted:
                isEncrypted = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_IsAllowLogging:
                isAllowLogging = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_ValidationCode:
                validationCode = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_FieldLength:
                fieldLength = ValueUtil.getValueAsInt(value);
                break;
            case ATTRIBUTE_ValueMin:
                valueMin = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_ValueMax:
                valueMax = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_IsAllowCopy:
                isAllowCopy = ValueUtil.getValueAsBoolean(value);
                break;
            case ATTRIBUTE_FormatPattern:
                formatPattern = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_ContextInfoScript:
                contextInfoScript = ValueUtil.getValueAsString(value);
                break;
            case ATTRIBUTE_ContextInfoFormatter:
                contextInfoFormatter = ValueUtil.getValueAsString(value);
                break;
        }
    }

    /** Context */
    private Context context;
    /** Table Name  */
    private String tableName = null;
    /** Column ID   */
    private int columnId = 0;
    /** Column Name */
    private String columnName;
    /** Column SQL */
    private String columnSQL;
    /** Display Type */
    private int displayType;
    /** Column Name */
    private boolean isMandatory;
    /** Default Value or logic */
    private String defaultLogic;
    /** Is Updateable Column */
    private boolean isUpdateable;
    /** Is Always Updateable */
    private boolean isAlwaysUpdateable;
    /** Name for Show   */
    private String name;
    /** Description */
    private String description;
    /** Help */
    private String help;
    /** Context Info Script */
    private String contextInfoScript;
    /** Context Info Message    */
    private String contextInfoFormatter;
    /** Is A Key Column */
    private boolean isKey;
    /** Is a Parent Column */
    private boolean isParent;
    /** Is a Translated Column */
    private boolean isTranslated;
    /** Ia a Envrypted column */
    private boolean isEncrypted;
    /** Is Allow Logging */
    private boolean isAllowLogging;
    /** Validation Code */
    private String validationCode;
    /** Field Length */
    private int fieldLength;
    /** Minimum Value */
    private String valueMin;
    /** Maximum Value */
    private String valueMax;
    /** Allows Copy */
    private boolean isAllowCopy;
    /** Format Pattern  */
    private String formatPattern;

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public int getColumnId() {
        return columnId;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getColumnSQL() {
        return columnSQL;
    }

    @Override
    public int getDisplayType() {
        return displayType;
    }

    @Override
    public Class<?> getColumnClass() {
        return null;
    }

    @Override
    public boolean isMandatory() {
        return isMandatory;
    }

    @Override
    public String getDefaultLogic() {
        return defaultLogic;
    }

    @Override
    public boolean isUpdateable() {
        return isUpdateable;
    }

    @Override
    public boolean isAlwaysUpdateable() {
        return isAlwaysUpdateable;
    }

    @Override
    public String getColumnLabel() {
        return name;
    }

    @Override
    public String getColumnDescription() {
        return description;
    }

    @Override
    public String getColumnHelp() {
        return help;
    }

    @Override
    public boolean isKey() {
        return isKey;
    }

    @Override
    public boolean isParent() {
        return isParent;
    }

    @Override
    public boolean isTranslated() {
        return isTranslated;
    }

    @Override
    public boolean isEncrypted() {
        return isEncrypted;
    }

    @Override
    public boolean isAllowLogging() {
        return isAllowLogging;
    }

    @Override
    public String getValidationCode() {
        return validationCode;
    }

    @Override
    public int getFieldLength() {
        return fieldLength;
    }

    @Override
    public String getValueMin() {
        return valueMin;
    }

    @Override
    public String getValueMax() {
        return valueMax;
    }

    @Override
    public boolean isAllowCopy() {
        return isAllowCopy;
    }

    @Override
    public String getFormatPattern() {
        return formatPattern;
    }

    /**
     * Get Context
     * @return
     */
    public Context getContext() {
        return context;
    }

    public boolean isId (int displayType) {
        return DisplayType.isId(getDisplayType());
    }	//	isID

    /**
     * Verify if is a Boolean
     * @return boolean
     */
    public boolean isBoolean() {
        return DisplayType.isBoolean(getDisplayType());
    }

    /**
     * Is Integer
     * @return
     */
    public boolean isInteger() {
        return DisplayType.isInteger(getDisplayType());
    }	//	isInteger

    /**
     *	Returns true, if DisplayType is numeric (Amount, Number, Quantity, Integer).
     *  (stored as BigDecimal)
     *  @return true if numeric
     */
    public boolean isNumeric() {
        return DisplayType.isNumeric(getDisplayType());
    }	//	isNumeric

    /**
     * Is a BigDecimal
     * @return
     * @return boolean
     */
    public boolean isBigDecimal() {
        return DisplayType.isBigDecimal(getDisplayType());
    }	//	isBigDecimal

    /**
     *	Returns true, if DisplayType is text (String, Text, TextLong, Memo).
     *  @return true if text
     */
    public boolean isText() {
        return DisplayType.isText(getDisplayType());
    }	//	isText

    /**
     *	Returns true if DisplayType is a Date.
     *  (stored as Timestamp)
     *  @return true if date
     */
    public boolean isDate () {
        return DisplayType.isDate(getDisplayType());
    }	//	isDate

    /**
     * Verify if is only date supported
     * @return
     */
    public boolean isDateOnly() {
        return DisplayType.DATE == getDisplayType();
    }

    /**
     * Verify if is only time supported
     * @return
     */
    public boolean isTimeOnly() {
        return DisplayType.TIME == getDisplayType();
    }

    /**
     *	Returns true if DisplayType is a VLookup (List, Table, TableDir, Search).
     *  (stored as Integer)
     *  @return true if Lookup
     */
    public boolean isLookup(int displayType) {
        return DisplayType.isLookup(getDisplayType());
    }	//	isLookup

    /**
     * 	Returns true if DisplayType is a Large Object
     *	@return true if Binary
     */
    public boolean isBinary () {
        return DisplayType.isBinary(getDisplayType());
    }	//	isBinary

    /**
     * Get Context Info Message
     * @return
     */
    public String getContextInfoFormatter() {
        return contextInfoFormatter;
    }

    /**
     * Get Context Info script
     * @return
     */
    public String getContextInfoScript() {
        return contextInfoScript;
    }

    @Override
    public String toString() {
        return "POInfoColumn{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
