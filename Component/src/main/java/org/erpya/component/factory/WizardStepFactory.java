/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
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
package org.erpya.component.factory;

import android.content.Context;

import org.erpya.base.model.InfoField;
import org.erpya.component.base.IWizardStep;
import org.erpya.component.wizard.GenericWizardStep;


/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Factory for Wizard Step
 */
public class WizardStepFactory {
    /**
     * Standard Constructor for it,
     * @param context is mandatory
     */
    private WizardStepFactory(Context context) {
        step = new GenericWizardStep();
    }

    /** Dynamic Step for load   */
    private GenericWizardStep step;

    /**
     * Create Field
     * @param context
     * @return
     */
    public static WizardStepFactory createStep(Context context) {
        return new WizardStepFactory(context);
    }

    /**
     * Set Title
     * @param title
     * @return
     */
    public WizardStepFactory withTitle(String title) {
        step.setTitle(title);
        return this;
    }

    /**
     * Set Table
     * @param tableName
     * @return
     */
    public WizardStepFactory withTableName(String tableName) {
        step.setTableName(tableName);
        return this;
    }

    /**
     * Set Help
     * @param help
     * @return
     */
    public WizardStepFactory withHelp(String help) {
        step.setHelp(help);
        return this;
    }

    /**
     * Set mandatory property
     * @param isMandatory
     * @return
     */
    public WizardStepFactory withMandatory(boolean isMandatory) {
        step.setMandatory(isMandatory);
        return this;
    }

    /**
     * Add field to Step
     * @param field
     * @return
     */
    public WizardStepFactory withAdditionalField(InfoField field) {
        step.addField(field);
        return this;
    }

    /**
     * Get Step definition
     * @return
     */
    public IWizardStep getStep() {
        return step;
    }
}
