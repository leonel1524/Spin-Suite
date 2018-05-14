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

import org.erpya.spinsuite.base.db.DBManager;
import org.erpya.spinsuite.base.util.Condition;
import org.erpya.spinsuite.base.util.Criteria;
import org.erpya.spinsuite.base.util.LogM;
import org.erpya.spinsuite.base.util.Util;
import org.erpya.spinsuite.base.util.ValueUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Metadata info for Table and column
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public class POInfo implements IPOInfo {

    /**
     *
     * @param context
     * @param tableName
     */
    public POInfo(Context context, String tableName) {
        this.context = context;
        this.tableName = tableName;
        loadInfo();
    }

    /** Static constant for ID */
    public static final String ID_KEY = "_id";
    /** Static constant for Revision */
    public static final String REVISION_KEY = "_rev";
    /** Static constant for ID */
    public static final String ATTACHMENT_KEY = "_attachments";
    /** Criteria for search */
    private Criteria criteria;
    /** Table Name to find */
    private String tableName = null;
    /** Is a View   */
    private boolean isView;
    /** Is a Document   */
    private boolean isDocument;
    /** Is Read Only    */
    private boolean isReadOnly;
    /** Is Deleteable   */
    private boolean isDeleteable;
    /** Description */
    private String description;
    /** Help    */
    private String help;
    /** Name    */
    private String name;
    /** Table Identifier    */
    private int tableId;
    /** Context */
    private Context context = null;
    /** Columns */
    private Map<String, POInfoColumn> columnns = new HashMap<String, POInfoColumn>();
    /** Table Name  */
    public static final String TABLE_NAME = "AD_Table";
    /** Attribute for table Search  */
    public static final String METADATA_TABLE_NAME = "AD_Table_TableName";

    /**
     * Get Context
     * @return
     */
    private Context getContext() {
        return context;
    }

    /**
     * Load PO information
     */
    private void loadInfo() {
        if(Util.isEmpty(tableName)) {
            return;
        }
        try {
            criteria = new Criteria().addCriteria(METADATA_TABLE_NAME, Condition.EQUAL, tableName);
            Map<String, Object> attributes = DBManager.getInstance(getContext()).getMap(criteria);
            //  Load
            if(attributes != null
                    && !attributes.isEmpty()) {
                tableName = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_TableName));
                isView = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsView));
                isDocument = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsDocument));
                isReadOnly = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsReadOnly));
                isDeleteable = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsDeleteable));
                name = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Name));
                description = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Description));
                help = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_Help));
                tableId = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_AD_Table_ID));

            }
        } catch (Exception e) {
            LogM.log(getContext(), this.getClass(), Level.WARNING, "Error Loading Data for PO Info", e);
        }
    }

    /**
     * Get Key and value for distinct type of documents
     * @return
     */
    public Criteria getCriteria() {
        return criteria;
    }

    @Override
    public int getTableId() {
        return tableId;
    }

    /**
     * Get Table Name of PO
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getLabel() {
        return name;
    }

    @Override
    public String getHelp() {
        return help;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isDeleteable() {
        return isDeleteable;
    }

    @Override
    public boolean isReadOnly() {
        return isReadOnly;
    }

    @Override
    public boolean isDocument() {
        return isDocument;
    }

    @Override
    public boolean isView() {
        return isView;
    }

    @Override
    public Map<String, POInfoColumn> getColumns() {
        return columnns;
    }
}
