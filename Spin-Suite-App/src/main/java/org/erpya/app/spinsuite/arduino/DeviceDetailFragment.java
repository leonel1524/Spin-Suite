package org.erpya.app.spinsuite.arduino;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.erpya.app.spinsuite.R;
import org.erpya.base.arduino.setup.WIFIAttribute;
import org.erpya.base.util.DisplayType;
import org.erpya.base.util.Util;
import org.erpya.component.factory.FieldFactory;
import org.erpya.component.field.Field;

/**
 * A fragment representing a single Device detail screen.
 * This fragment is either contained in a {@link DeviceListActivity}
 * in two-pane mode (on tablets) or a {@link DeviceDetailActivity}
 * on handsets.
 */
public class DeviceDetailFragment extends Fragment {
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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeviceDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy deviceName specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load deviceName from a deviceName provider.
            mItem = ArduinoDeviceContent.getInstance().get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.deviceName);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.device_initial_setup, container, false);
        parent = rootView.findViewById(R.id.parent);
        if(parent != null) {
            ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //  Add SSID
            parent.addView(FieldFactory
                    .createField(getContext())
                    .withColumnName("SSID")
                    .withName(getString(R.string.SSID))
                    .withReadOnly(false)
                    .withUpdateable(true)
                    .withMandatory(true)
                    .withDisplayType(DisplayType.STRING)
                    .getFieldComponent(), layoutParams);
            //  Add PSK
            parent.addView(FieldFactory
                    .createField(getContext())
                    .withColumnName("PSK")
                    .withName(getString(R.string.Pass))
                    .withReadOnly(false)
                    .withUpdateable(true)
                    .withMandatory(true)
                    .withEncrypted(true)
                    .withDisplayType(DisplayType.STRING)
                    .getFieldComponent(), layoutParams);
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
        String ssid = null;
        String psk = null;
        int count = parent.getChildCount();
        for(int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            if(view instanceof Field) {
                Field field = (Field) view;
                if(field.getFieldDefinition() != null) {
                    if(!Util.isEmpty(field.getFieldDefinition().getColumnName())) {
                        Object value = field.getValue();
                        if(value != null) {
                            if(field.getFieldDefinition().getColumnName().equals("SSID")) {
                                ssid = (String) value;
                            } else if(field.getFieldDefinition().getColumnName().equals("PSK")) {
                                psk = (String) value;
                            }
                        }
                    }
                }
            }
        }
        //
        return new WIFIAttribute(ssid, psk);
    }

    /**
     * Set WIFI Attribute
     * @param attribute
     */
    public void setWIFIAttribute(WIFIAttribute attribute) {
        if(parent == null) {
            return;
        }
        int count = parent.getChildCount();
        for(int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            if(view instanceof Field) {
                Field field = (Field) view;
                if(field.getFieldDefinition() != null) {
                    if(!Util.isEmpty(field.getFieldDefinition().getColumnName())) {
                        if(field.getFieldDefinition().getColumnName().equals("SSID")) {
                            field.setValue(attribute.getSSID());
                        } else if(field.getFieldDefinition().getColumnName().equals("PSK")) {
                            field.setValue(attribute.getPSK());
                        }
                    }
                }
            }
        }
    }
}
