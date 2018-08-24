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
import android.widget.EditText;

import org.erpya.base.model.InfoField;
import org.erpya.base.util.DisplayType;
import org.erpya.base.util.ValueUtil;

/**
 * Edit Text component for in / out
 */
public class FieldText extends Field {

    /**
     * Standard constructor
     * @param context
     */
    public FieldText(Context context) {
        super(context);
    }

    /**
     * From field definition
     * @param fieldDefinition
     */
    public FieldText(InfoField fieldDefinition) {
        super(fieldDefinition);
    }

    /**
     * Init from parent
     * @param context
     * @param attrs
     */
    public FieldText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FieldText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void setFieldValue(Object value) {
        editText.setText(ValueUtil.getValueAsString(value));
    }

    @Override
    protected Object getFieldValue() {
        return editText.getText().toString();
    }

    @Override
    protected String getFieldDisplayValue() {
        return (String) getFieldValue();
    }

    /** Edit text   */
    private EditText editText;

    /**
     * Get Edit text used
     * @return
     */
    protected EditText getEditText() {
        return editText;
    }

    /**
     * Init it from parent
     */
    protected void init() {
        editText = new EditText(getContext());
        //  Add to parent
        addView(editText);
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        //  For it
        editText.setEnabled(isEnabled);
    }

    @Override
    protected void initFromInfoField() {
        super.initFromInfoField();
        if(getFieldDefinition() != null) {
            //  For Input Type
            if(getInputType() > 0) {
                editText.setInputType(getInputType());
            }
            //	Set Hint
            editText.setHint(getFieldDefinition().getColumnLabel());
            setEnabled(!getFieldDefinition().isReadOnly());
            //	Selected All on Focus
            editText.setSelectAllOnFocus(true);
            //	Set Multi-line
            if((getFieldDefinition().getDisplayType() == DisplayType.TEXT
                    || getFieldDefinition().getDisplayType() == DisplayType.TEXT_LONG
                    || getFieldDefinition().getDisplayType() == DisplayType.MEMO)
                    && !getFieldDefinition().isEncrypted()) {
                editText.setSingleLine(false);
            }
        }
    }

    /**
     * Get input Type with is Encrypted
     * @return
     * @return int
     */
    protected int getInputType() {
        int inputType = 0;
        if(getFieldDefinition() == null) {
            return inputType;
        }
        int displayType = getFieldDefinition().getDisplayType();

        if(getFieldDefinition().isText()) {
            //	Encrypted
            if(getFieldDefinition().isEncrypted()) {
                inputType = InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD;
            } else if(displayType == DisplayType.TEXT
                    || displayType == DisplayType.TEXT_LONG
                    || displayType == DisplayType.MEMO) {
                inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE
                        | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
            } else if(displayType == DisplayType.URL) {
                inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI;
            } else {
                inputType = InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
            }
        } else if(displayType == DisplayType.INTEGER) {
            inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED;
        } else if(getFieldDefinition().isNumeric()) {
            inputType = InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL
                    | InputType.TYPE_NUMBER_FLAG_SIGNED
                    | InputType.TYPE_CLASS_PHONE;
        }
        else if(getFieldDefinition().isDate()) {
            inputType = InputType.TYPE_CLASS_DATETIME;
        }
        //	Default
        return inputType;
    }
}
