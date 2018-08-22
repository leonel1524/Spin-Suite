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

import org.erpya.spinsuite.base.util.ValueUtil;

import java.util.Map;

/**
 * Metadata info for Table and column
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2 ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public final class InfoField extends POInfoColumn implements IInfoField {

    /**
     * Default constructor for info field
     * @param context
     * @param attributes
     */
    public InfoField(Context context, Map<String, Object> attributes) {
        super(context, attributes);
        if(attributes != null
                && !attributes.isEmpty()) {
            isFieldOnly = ValueUtil.getValueAsBoolean(attributes.get(ATTRIBUTE_IsFieldOnly));
            displayLogic = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_DisplayLogic));
            displayLength = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_DisplayLength));
            seqNo = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_SeqNo));
            sortNo = ValueUtil.getValueAsInt(attributes.get(ATTRIBUTE_SortNo));
            infoFactoryClass = ValueUtil.getValueAsString(attributes.get(ATTRIBUTE_InfoFactoryClass));
        }
    }

    /** Field Only  */
    private boolean isFieldOnly = false;
    /** Display Logic   */
    private String displayLogic = null;
    /** Display Length  */
    private int displayLength = 0;
    /** Sequence    */
    private int seqNo = 0;
    /** Sorting No  */
    private int sortNo = 0;
    /** Factory Class   */
    private String infoFactoryClass = null;

    @Override
    public boolean isFieldOnly() {
        return isFieldOnly;
    }

    @Override
    public String getDisplayLogic() {
        return displayLogic;
    }

    @Override
    public int getDisplayLength() {
        return displayLength;
    }

    @Override
    public int getSeqNo() {
        return seqNo;
    }

    @Override
    public int getSortNo() {
        return sortNo;
    }

    @Override
    public String getInfoFactoryClass() {
        return infoFactoryClass;
    }
}
