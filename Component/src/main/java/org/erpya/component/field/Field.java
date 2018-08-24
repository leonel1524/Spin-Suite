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
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.erpya.component.R;
import org.erpya.base.model.InfoField;
import org.erpya.base.util.LogM;

/**
 * Main view for lookup in/out
 * It can be implemented for:
 * Text View
 * Image View
 * Numeric
 * Spinner
 * Search View
 * Check box
 */
public abstract class Field extends LinearLayout {

    /**
     * Default constructor
     * @param context
     */
    public Field(Context context) {
        super(context);
        initLayout(null, 0);
    }

    /**
     * Constructor From field
     * @param fieldDefinition
     */
    public Field(InfoField fieldDefinition) {
        super(fieldDefinition.getContext());
        setInfoField(fieldDefinition);
        initLayout(null, 0);
    }

    public Field(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(attrs, 0);
    }

    public Field(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout(attrs, defStyle);
    }

    /**
     * Init Layout
     * @param attrs
     * @param defStyle
     */
    private void initLayout(AttributeSet attrs, int defStyle) {
        //	Set Parameter
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        //	Set Orientation
        setOrientation(VERTICAL);
        //
        label = new TextView(getContext());
        label.setText(getResources().getString(R.string.None));
        //  Add to main
        addView(label);
        //  Call of parent for init child view
        init();
        initFromInfoField();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /** Field Definition for it */
    private InfoField fieldDefinition;
    /** Label for Component */
    private TextView label;
    /** Value   */
    private Object value = null;
    /** Old Value   */
    private Object oldValue = null;
    /**	Logger							*/
    protected transient LogM log = new LogM(getContext(), this.getClass());

    /**
     * Get label object from view
     * @return
     */
    public TextView getLabel() {
        return label;
    }

    /**
     * Get Main Layout
     * @return
     */
    public LinearLayout getLayout() {
        return this;
    }
    /**
     * Get Field Definition
     * @return
     */
    public InfoField getFieldDefinition() {
        return fieldDefinition;
    }

    /**
     * Verify if it not has label
     * @return
     */
    public boolean isFieldOnly() {
        if(getFieldDefinition() != null) {
            return getFieldDefinition().isFieldOnly();
        }
        //
        return false;
    }

    /**
     * Verify if is displayed
     * @return
     */
    public boolean isDisplayed() {
        return false;
    }

    /**
     * Get Sequence for display
     * @return
     */
    public int getSeqNo() {
        if(getFieldDefinition() != null) {
            return getFieldDefinition().getSeqNo();
        }
        return 0;
    }

    /**
     * Get Sorting number
     * @return
     */
    public int getSortNo() {
        if(getFieldDefinition() != null) {
            return getFieldDefinition().getSortNo();
        }
        return 0;
    }

    /**
     * Get Text of Label
     * @return
     */
    public String getFieldLabel() {
        if(getFieldDefinition() != null) {
            return getFieldDefinition().getColumnLabel();
        }
        //  Default
        return null;
    }

    /**
     * Get value from component
     * @return
     */
    public Object getValue() {
        return getFieldValue();
    }

    /**
     * Get Old Value
     * @return
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Set value for component
     * @param value
     */
    public void setValue(Object value) {
        oldValue = this.value;
        this.value = value;
        setFieldValue(value);
    }

    /**
     * Get display value
     * @return
     */
    public String getDisplayValue() {
        return getFieldDisplayValue();
    }

    /**
     * Set Field Value for child
     * @param value
     */
    protected abstract void setFieldValue(Object value);

    /**
     * Get Value from child
     * @return
     */
    protected abstract Object getFieldValue();

    /**
     * Get Display value from child
     * @return
     */
    protected abstract String getFieldDisplayValue();

    /**
     * Init child
     */
    protected abstract void init();

    /**
     * Init component from info field
     * It method can be called from parent after init
     */
    protected void initFromInfoField() {
        if(getFieldDefinition() != null) {
            //  Hide Label when is only field
            if(getFieldDefinition().isFieldOnly()) {
                getLabel().setVisibility(GONE);
            } else {
                getLabel().setVisibility(VISIBLE);
            }
            //  Enabled it
            setEnabled(getFieldDefinition().isUpdateable() && !getFieldDefinition().isReadOnly());
        }
    }

    /**
     * Set Info Field
     * @param fieldDefinition
     */
    public void setInfoField(InfoField fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    /**
     * Set enabled attribute
     * @param isEnabled
     */
    public void setEnabled(boolean isEnabled) {
        //  TODO Implement it for all
    }
}
