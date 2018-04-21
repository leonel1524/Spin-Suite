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

import android.content.Context;

import org.erpya.spinsuite.base.model.PO;

import java.math.BigDecimal;

/**
 * Metadata info for Table and column
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class MInfoColumn extends PO {

    /**
     * Default Constructor
     * @param context
     * @param tableName
     */
    public MInfoColumn(Context context, String tableName) {
        super(context);
        this.tableName = tableName;
    }

    /** Table Name  */
    private String tableName = null;

    public String getTableName() {
        return tableName;
    }

    public int getColumnId() {
        return getValueAsInt("AD_Column_ID");
    }

    public String getColumnName() {
        return getValueAsString("ColumnName");
    }

    public String getColumnSQL() {
        return getValueAsString("ColumnSQL");
    }

    public int getDisplayType() {
        return getValueAsInt("DisplayType");
    }

    public Class<?> getColumnClass() {
        return null;
    }

    public boolean isMandatory() {
        return getValueAsBoolean("IsMandatory");
    }

    public String getDefaultLogic() {
        return getValueAsString("DefaultLogic");
    }

    public boolean isUpdateable() {
        return getValueAsBoolean("IsUpdateable");
    }

    public boolean isAlwaysUpdateable() {
        return getValueAsBoolean("IsAlwaysUpdateable");
    }

    public String getColumnLabel() {
        return getValueAsString("Name");
    }

    public String getColumnDescription() {
        return getValueAsString("Description");
    }

    public String getColumnHelp() {
        return getValueAsString("Help");
    }

    public boolean isKey() {
        return getValueAsBoolean("IsKey");
    }

    public boolean isParent() {
        return getValueAsBoolean("IsParent");
    }

    public boolean isTranslated() {
        return getValueAsBoolean("IsTranslated");
    }

    public boolean isEncrypted() {
        return getValueAsBoolean("IsEncrypted");
    }

    public boolean isAllowLogging() {
        return getValueAsBoolean("IsAllowLogging");
    }

    public String getValidationCode() {
        return getValueAsString("ValidationCode");
    }

    public int getFieldLength() {
        return getValueAsInt("FieldLength");
    }

    public String getValueMin() {
        return getValueAsString("ValueMin");
    }

    public String getValueMax() {
        return getValueAsString("ValueMax");
    }

    public boolean isAllowCopy() {
        return getValueAsBoolean("IsAllowCopy");
    }
}
