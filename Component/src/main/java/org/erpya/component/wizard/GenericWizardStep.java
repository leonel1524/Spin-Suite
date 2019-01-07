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
package org.erpya.component.wizard;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.erpya.base.model.InfoField;
import org.erpya.component.base.IWizardStep;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Generic Wizard Step
 */
public class GenericWizardStep implements IWizardStep {

    public GenericWizardStep() {
        nextStep = -1;
        fields = new ArrayList<InfoField>();
    }

    /** Mandatory property  */
    private boolean isMandatory;
    /** Next Step   */
    private int nextStep;
    /** Title of Step   */
    private String title;
    /** Help to show for Wizard Step    */
    private String help;
    /** Fields  */
    private List<InfoField> fields;
    /** Step    */
    private WizardStep step;

    @Override
    public Fragment newInstance(Bundle savedInstanceState) {
        step = new WizardStep();
        step.setName(title);
        step.setHelp(help);
        step.setFields(fields);
        if(savedInstanceState != null) {
            step.setArguments(savedInstanceState);
        }
        return step;
    }

    @Override
    public int getNextItem() {
        return nextStep;
    }

    @Override
    public boolean validateIt() {
        return step.validateStep();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isMandatory() {
        return isMandatory;
    }

    /**
     * Set title for Step
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set help for step
     * @param help
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * Set mandatory property
     * @param isMandatory
     */
    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    /**
     * Add field to Step
     * @param field
     */
    public void addField(InfoField field) {
        fields.add(field);
    }

    /**
     * Get Step fragment
     * @return
     */
    public WizardStep getStep() {
        return step;
    }
}
