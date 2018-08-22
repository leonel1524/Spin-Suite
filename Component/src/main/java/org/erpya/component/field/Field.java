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
import android.view.View;

import org.erpya.spinsuite.base.model.InfoField;

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
public class Field extends View {

    /**
     * Default constructor
     * @param context
     */
    public Field(Context context) {
        super(context);
        init(null, 0);
    }

    public Field(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Field(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        //
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /** Field Definition for it */
    private InfoField fieldDefinition;

    /**
     * Get Field Definition
     * @return
     */
    public InfoField getFieldDefinition() {
        return fieldDefinition;
    }

}
