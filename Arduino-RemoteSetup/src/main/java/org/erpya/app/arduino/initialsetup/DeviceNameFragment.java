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
package org.erpya.app.arduino.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.erpya.app.arduino.remotesetup.ArduinoDeviceContent;
import org.erpya.app.arduino.remotesetup.DeviceDetailActivity;
import org.erpya.app.arduino.remotesetup.DeviceListActivity;
import org.erpya.app.arduino.remotesetup.R;
import org.erpya.base.arduino.setup.WIFIAttribute;
import org.erpya.base.util.DisplayType;
import org.erpya.component.base.IWizardStepPage;
import org.erpya.component.factory.FieldFactory;
import org.erpya.component.field.Field;

/**
 * A fragment representing a single Device detail screen.
 * This fragment is either contained in a {@link DeviceListActivity}
 * in two-pane mode (on tablets) or a {@link DeviceDetailActivity}
 * on handsets.
 */
public class DeviceNameFragment extends Fragment implements IWizardStepPage {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy deviceName this fragment is presenting.
     */
    private ArduinoDeviceContent.DeviceItem mItem;
    private LinearLayout parent;
    private Field deviceName;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeviceNameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.device_initial_setup, container, false);
        parent = rootView.findViewById(R.id.parent);
        if(parent != null) {
            ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //  Device Name
            deviceName = FieldFactory
                    .createField(getContext())
                    .withColumnName("DeviceName")
                    .withName(getString(R.string.Name))
                    .withReadOnly(false)
                    .withUpdateable(true)
                    .withMandatory(true)
                    .withDisplayType(DisplayType.STRING)
                    .getFieldComponent();
            parent.addView(deviceName, layoutParams);
        }
        //
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.device_detail)).setText(mItem.details);
        }
        return rootView;
    }

    /**
     * Get WIFI Attribute
     * @return
     */
    public WIFIAttribute getWIFIAttribute() {
        if(parent == null) {
            return null;
        }
        deviceName.validateValue();
        String name = (String) deviceName.getValue();
        //
        WIFIAttribute attribute = new WIFIAttribute();
        attribute.withDeviceName(name);
        return attribute;
    }

    /**
     * Set WIFI Attribute
     * @param attribute
     */
    public void setWIFIAttribute(WIFIAttribute attribute) {
        if(parent == null) {
            return;
        }
        deviceName.setValue(attribute.getDeviceName());
    }

    @Override
    public boolean validateStep() {
        if(parent == null) {
            return false;
        }
        return deviceName.validateValue();
    }
}
