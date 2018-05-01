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
import android.util.Log;

import org.erpya.spinsuite.base.db.DBManager;
import org.erpya.spinsuite.base.exceptions.SpinSuiteException;
import org.erpya.spinsuite.base.util.LogM;
import org.erpya.spinsuite.base.util.ValueUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Persistence Object for data
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public abstract class PO {

    /**
     * Load from Context and Table Name
     * @param context
     * @param tableName
     */
    public PO(Context context, String tableName) {
        this.context = context;
        this.info = new POInfo(context, tableName);
    }

    /**
     * From Parent
     * @param parent
     */
    public PO(PO parent) {
        this(parent.getContext());
        this.parent = parent;
    }

    /**
     * Only context the Meta-Data is populate after
     * @param context
     */
    public PO(Context context) {
        this.context = context;
        this.info = new POInfo(context, getTableName());
    }

    /** Map for key and values  */
    private Map<String, Object> attributes = new HashMap<String, Object>();
    /** Map for old key and values  */
    private Map<String, Object> oldAttributes = new HashMap<String, Object>();
    /** Context */
    private Context context = null;
    /** Parent  */
    private PO parent;
    /** PO Info */
    private POInfo info = null;

    /**
     * Get Table Name if it is not give from contructor
     * @return
     */
    protected abstract String getTableName();

    /**
     * Get Map with key and values, used for save data
     * @return
     */
    public Map<String, Object> getMap() {
        return attributes;
    }

    /**
     * Set Map (Used only for populate from DB)
     * @param map
     */
    public void setMap(Map<String, Object> map) {
        attributes.putAll(map);
        oldAttributes.putAll(map);
    }

    /**
     * Get Identifier for this object
     * @return
     */
    public String getId() {
        return (String) attributes.get(POInfo.ID_KEY);
    }

    /**
     * Get PO Information
     * @return
     */
    public POInfo getInfo() {
        return info;
    }

    /**
     * Get Revision for this object
     * @return
     */
    public String getRevision() {
        return (String) attributes.get(POInfo.REVISION_KEY);
    }

    /**
     * Get Attachment for this object
     * @return
     */
    public Object getAttachment() {
        return attributes.get(POInfo.ATTACHMENT_KEY);
    }

    /**
     * 	Get Context
     * 	@return context
     */
    public Context getContext() {
        return context;
    }


    /**************************************************************
     * Helper Methods
     *************************************************************/

    /**
     * Get a value from map
     * @param key
     * @return
     */
    public Object getValueAsObject(String key) {
        return attributes.get(key);
    }


    /**
     *  Get Value as int
     *  @param key or column
     *  @return int value or 0
     */
    public int getValueAsInt(String key) {
        return ValueUtil.getValueAsInt(attributes.get(key));
    }   //  getValueAsInt

    /**
     * 	Get Column Value
     *	@param key name
     *	@return value or ""
     */
    public String getValueAsString (String key) {
        return ValueUtil.getValueAsString(attributes.get(key));
    }	//	get_ValueAsString

    /**
     * Get value as Boolean
     * @param key
     * @return boolean value
     */
    public boolean getValueAsBoolean(String key) {
        return ValueUtil.getValueAsBoolean(attributes.get(key));
    }

    /**
     * Get value as Timestamp
     * @param key
     * @return boolean value
     */
    public Date getValueAsDate(String key) {
        return ValueUtil.getValueAsDate(attributes.get(key));
    }

    /**
     * Get Database value as list
     * @param key
     * @return
     */
    public List<?> getValueAsList(String key) {
        return ValueUtil.getValueAsList(attributes.get(key));
    }

    /**************************************************************************
     * 	Set AD_Client
     * 	@param clientId client
     */
    protected final void setClientId(int clientId) {
        setValueNoCheck("AD_Client_ID", new Integer(clientId));
    }	//	setAD_Client_ID

    /**
     * 	Get AD_Client
     * 	@return AD_Client_ID
     */
    public final int getClientId() {
        Integer ii = getValueAsInt("AD_Client_ID");
        if (ii == null)
            return 0;
        return ii.intValue();
    }	//	getClientId

    /**
     * 	Set AD_Org
     * 	@param orgId org
     */
    public final void setOrgId(int orgId) {
        setValueNoCheck("AD_Org_ID", new Integer(orgId));
    }	//	setOrgId

    /**
     * 	Get AD_Org
     * 	@return AD_Org_ID
     */
    public int getOrgId() {
        Integer ii = getValueAsInt("AD_Org_ID");
        if (ii == null)
            return 0;
        return ii.intValue();
    }	//	getOrgId

    /**
     * 	Overwrite Client Org if different
     *	@param clientId client
     *	@param orgId org
     */
    protected void setClientOrg (int clientId, int orgId) {
        if (clientId != getClientId())
            setClientId(clientId);
        if (orgId != getOrgId())
            setOrgId(orgId);
    }	//	setClientOrg

    /**
     * 	Overwrite Client Org if different
     *	@param entity persistent object
     */
    protected void setClientOrg (PO entity) {
        setClientOrg(entity.getClientId(), entity.getOrgId());
    }	//	setClientOrg

    /**
     * 	Set Active
     * 	@param active active
     */
    public final void setIsActive (boolean active) {
        setValue("IsActive", new Boolean(active));
    }	//	setActive

    /**
     *	Is Active
     *  @return is active
     */
    public final boolean isActive() {
        return getValueAsBoolean("IsActive");
    }	//	isActive

    /**
     * 	Get Created
     * 	@return created
     */
    final public Date getCreated() {
        return getValueAsDate("Created");
    }	//	getCreated

    /**
     * 	Get Updated
     *	@return updated
     */
    final public Date getUpdated() {
        return getValueAsDate("Updated");
    }	//	getUpdated

    /**
     * 	Get CreatedBy
     * 	@return AD_User_ID
     */
    public final int getCreatedBy() {
        return getValueAsInt("CreatedBy");
    }	//	getCreateddBy

    /**
     * 	Get UpdatedBy
     * 	@return AD_User_ID
     */
    public final int getUpdatedBy() {
        return getValueAsInt("UpdatedBy");
    }	//	getUpdatedBy

    /**
     * 	Set UpdatedBy
     * 	@param userId user
     */
    protected final void setUpdatedBy (int userId) {
        setValueNoCheck("UpdatedBy", new Integer(userId));
    }	//	setAD_User_ID

    /**
     * Set Value and not check it
     * @param key
     * @param value
     */
    protected final void setValueNoCheck(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * Set value for all
     * @param key
     * @param value
     */
    public final void setValue(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * 	Called before Save for Pre-Save Operation
     * 	@param newRecord new record
     *	@return true if record can be saved
     */
    protected boolean beforeSave(boolean newRecord) {
        return true;
    }	//	beforeSave

    /**
     * 	Called after Save for Post-Save Operation
     * 	@param newRecord new record
     *	@param success true if save operation was success
     *	@return if save was a success
     */
    protected boolean afterSave (boolean newRecord, boolean success) {
        return success;
    }	//	afterSave

    /**
     * 	Is there a Change to be saved?
     *	@return true if record changed
     */
    public boolean isValueChanged() {
        //  TODO Implement it
        return false;
    }	//	isValueChanged

    /**
     * 	Is new record
     *	@return true if new
     */
    public boolean isNewRecord() {
        return true;
    }	//	is_new

    public void saveEx() throws SpinSuiteException {
        boolean newRecord = false;
        boolean success = true;
        //  Before Save trigger
        if (!beforeSave(newRecord)) {
            throw new SpinSuiteException("Error After Save");
        } else {
            try {
                DBManager.getInstance(context).save(this);
            } catch (Exception e) {
                LogM.log(getContext(), this.getClass(), Level.SEVERE, "Save Error", e);
                success = false;
            }
            Log.i("saveEx", "Record Saved");
        }
        //  After Save
        if(!afterSave(newRecord, success)) {
            throw new SpinSuiteException("Error After Save");
        }
    }
}
