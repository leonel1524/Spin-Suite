package org.erpya.app.spinsuite.arduino;

import android.content.Context;

import org.erpya.base.device.util.DeviceManager;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.device.util.IDevice;
import org.erpya.base.device.util.IDeviceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample deviceName for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class ArduinoDeviceContent {



    /**
     * An array of sample (dummy) items.
     */
    private List<DeviceItem> deviceItemList;

    /**
     * A map of sample (dummy) items, by ID.
     */
    private Map<String, DeviceItem> deviceItemMap;

    /**
     * Get instance for it (Create new instance if it does not exist)
     * @return
     */
    public static ArduinoDeviceContent getInstance() {
        getInstance(null);
        //  Default return
        return instance;
    }

    /**
     * Get instance with context
     * @param context
     * @return
     */
    public static ArduinoDeviceContent getInstance(Context context) {
        if(instance == null) {
            instance = new ArduinoDeviceContent(context);
        } else if(context != null) {
            instance = new ArduinoDeviceContent(context);
        }
        //  Default return
        return instance;
    }

    /** Instance    */
    private static ArduinoDeviceContent instance;

    /**
     * Get item from ID
     * @param id
     * @return
     */
    public DeviceItem get(String id) {
        return deviceItemMap.get(id);
    }

    /**
     * Get item list
     * @return
     */
    public List<DeviceItem> getItemList() {
        return deviceItemList;
    }

    /**
     * Load device
     * @param context
     */
    private ArduinoDeviceContent(Context context) {
        DeviceTypeHandler handler = DeviceManager
                .getInstance()
                .getDefaultDeviceHandler(IDeviceType.TYPE_ARDUINO);
        deviceItemMap = new HashMap<String, DeviceItem>();
        deviceItemList = new ArrayList<DeviceItem>();
        // Add some sample items.
        if(handler != null) {
            for (IDevice device : handler.getAvailableDeviceList()) {
                addItem(new DeviceItem(device.getDeviceId(), device.getName(), device.getAddress()));
            }
        }
    }

    /**
     * Add item to list
     * @param item
     */
    private void addItem(DeviceItem item) {
        deviceItemList.add(item);
        deviceItemMap.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of deviceName.
     */
    public static class DeviceItem {
        public final String id;
        public final String deviceName;
        public final String details;

        public DeviceItem(String id, String deviceName, String details) {
            this.id = id;
            this.deviceName = deviceName;
            this.details = details;
        }

        @Override
        public String toString() {
            return deviceName;
        }
    }
}
