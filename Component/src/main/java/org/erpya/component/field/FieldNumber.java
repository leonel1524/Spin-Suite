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
package org.erpya.component.field;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;

import org.erpya.base.model.InfoField;
import org.erpya.base.util.DisplayType;
import org.erpya.base.util.Env;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Edit Text component for Number values, it can be decimal and integer
 */
public class FieldNumber extends FieldText {

    /**
     * Standard constructor
     * @param context
     */
    public FieldNumber(Context context) {
        super(context);
    }

    /**
     * Init from parent
     * @param context
     * @param attrs
     */
    public FieldNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * From field definition
     * @param fieldDefinition
     */
    public FieldNumber(InfoField fieldDefinition) {
        super(fieldDefinition);
    }

    public FieldNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**	Column Decimal Format   */
    private DecimalFormat decimalFormat;

    @Override
    protected void setFieldValue(Object value) {
        BigDecimal internalValue = null;
        if(value == null) {
            getEditText().setText("");
            internalValue = Env.ZERO;
        } else {
            if(getFieldDefinition() != null
                && getFieldDefinition().getDisplayType() == DisplayType.INTEGER) {
                internalValue = new BigDecimal((Integer) value);
            } else {
                internalValue = (BigDecimal) value;
            }
        }
        //	Set Edit Text
        if(internalValue != null
                && decimalFormat!= null) {
            getEditText().setText(decimalFormat.format(internalValue));
        }
    }

    @Override
    protected Object getFieldValue() {
        if(getFieldDefinition() == null) {
            return null;
        }
        BigDecimal internalValue = DisplayType.getNumber(getEditText().getText().toString(), getFieldDefinition().getDisplayType());
        //	Valid Null Value
        if(internalValue == null) {
            internalValue = Env.ZERO;
        }
        if(getFieldDefinition().getDisplayType() == DisplayType.INTEGER) {
            return internalValue.intValue();
        }
        //	Default
        return internalValue;
    }

    @Override
    protected void initFromInfoField() {
        super.initFromInfoField();
        getEditText().setSingleLine(true);
        getEditText().setGravity(Gravity.RIGHT);
        if(getFieldDefinition() != null) {
            //	Set Format
            decimalFormat = DisplayType.getNumberFormat(getContext(), getFieldDefinition().getDisplayType(),
                    getFieldDefinition().getFormatPattern());
        } else {
            decimalFormat = DisplayType.getNumberFormat(getContext(), DisplayType.AMOUNT);
            getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }
}
