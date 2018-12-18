/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com				  		                 *
 * Contributor(s): Carlos Parada cparada@erpya.com				  		             *
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
package org.erpya.base.print.honeywell;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.AssetManager;

import com.honeywell.mobility.print.LinePrinter;
import com.honeywell.mobility.print.LinePrinterException;

import org.erpya.base.device.util.ConfigValue;
import org.erpya.base.device.util.Device;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.device.util.IDevice;
import org.erpya.base.device.util.IDeviceType;
import org.erpya.base.print.util.IPrinter;
import org.erpya.base.exceptions.SpinSuiteException;
import org.erpya.base.util.LogM;
import org.erpya.base.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Honeywell connector
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class HoneywellPrinter extends DeviceTypeHandler implements IPrinter {
    /**
     * Standard constructor
     * @param context
     * @param deviceType
     * @return void
     * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
     */
    public HoneywellPrinter(Context context, IDeviceType deviceType) {
        super(context, deviceType);
        setBluetoothMode();
    }

    /** Class for print */
    private LinePrinter linePrinter = null;
    /** Printer URI */
    private String printerURI = null;
    /** Mandatory Attributes  */
    public static String PRINTER_TYPE = "#PRINTER_TYPE";
    public static String INTERFACE_TYPE = "#INTERFACE_TYPE";
    public static String INTERFACE_BLUETOOTH = "bt";
    public static String INTERFACE_SERIAL = "serial";
    public static String JSON_ATTRIBUE_FILE_VALUES = "#JSON_ATTRIBUE_FILE_VALUES";


    /**
     * Get Printer URI from MAC and Interface Type
     * @return
     */
    public String getPrinterURI() {
        if (!Util.isEmpty(printerURI)) {
            return printerURI;
        }
        //
        IDevice device = getCurrentDevice();
        if(device == null) {
            throw new SpinSuiteException("@Device@ @NotFound@");
        }
        //  Get Values
        ConfigValue interfaceType = device.getConfigValue(INTERFACE_TYPE);
        //  Validate
        if(Util.isEmpty(device.getAddress())
                || interfaceType == null
                || interfaceType.isEmpty()) {
            return null;
        }
        //  Get
        // The printer address should be a Bluetooth MAC address.
        if (device.getAddress().contains(":") == false
                && device.getAddress().length() == 12) {
            // If the MAC address only contains hex digits without the
            // ":" delimiter, then add ":" to the MAC address string.
            char[] cAddr = new char[17];

            for (int i = 0, j = 0; i < 12; i += 2) {
                device.getAddress().getChars(i, i + 2, cAddr, j);
                j += 2;
                if (j < 17) {
                    cAddr[j++] = ':';
                }
            }

            device.setAddress(new String(cAddr));
        }
        //
        printerURI = interfaceType.getValueAsString() + "://" + device.getAddress();
        //  Default return
        return printerURI;
    }

    /**
     * Set Bluetooth Mode
     */
    public void setBluetoothMode() {
        getDeviceType().addConfigValue(INTERFACE_TYPE, INTERFACE_BLUETOOTH);
    }

    /**
     * Set serial mode
     */
    public void setSerialMode() {
        getDeviceType().addConfigValue(INTERFACE_TYPE, INTERFACE_SERIAL);
    }

    @Override
    public List<IDevice> getAvailableDeviceList() {
        List<IDevice> deviceList = new ArrayList<IDevice>();
        ConfigValue connectionType = getDeviceType().getConfigValue(INTERFACE_TYPE);
        //  Validate
        if(connectionType == null
                || connectionType.isEmpty()) {
            return deviceList;
        }
        //  Validate Connection
        //  For Bluetooth
        if(connectionType.getValueAsString().equals(INTERFACE_BLUETOOTH)) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            //  Get list
            for(BluetoothDevice bluetooth : pairedDevices) {
                LogM.log(getContext(), "", Level.SEVERE,  "E" + bluetooth.getBluetoothClass());
                Device bluetoothDevice = new Device(getDeviceType());
                //  Set values
                bluetoothDevice.setName(bluetooth.getName());
                bluetoothDevice.setDeviceId(bluetooth.getAddress());
                bluetoothDevice.setAddress(bluetooth.getAddress());
                bluetoothDevice.setAvalilable(true);
                bluetoothDevice.addConfigValue(JSON_ATTRIBUE_FILE_VALUES, readAssetFile());
                bluetoothDevice.addConfigValue(PRINTER_TYPE, "PR2");
                bluetoothDevice.addConfigValue(INTERFACE_TYPE, INTERFACE_BLUETOOTH);
                deviceList.add(bluetoothDevice);
                addDevice(bluetoothDevice);
            }
        }
        return deviceList;
    }

    @Override
    public boolean isAvailable() throws Exception {
        return true;
    }

    /**
     * Read JSON file
     */
    private String readAssetFile() {
        String jsonAttribute = "";
        InputStream input = null;
        ByteArrayOutputStream output = null;
        AssetManager assetManager = getContext().getAssets();
        int initialBufferSize;

        try {
            input = assetManager.open("printer_profiles.JSON");
            initialBufferSize = 8000;
            output = new ByteArrayOutputStream(initialBufferSize);

            byte[] buf = new byte[1024];
            int len;
            while ((len = input.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
            jsonAttribute = output.toString();
            input.close();
            input = null;
            output.flush();
            output.close();
            output = null;
        } catch (Exception ex) {
            LogM.log(getContext(), getClass().getName(), Level.WARNING, "Error reading asset file: ");
        } finally {
            try {
                if (input != null) {
                    input.close();
                    input = null;
                }
                //
                if (output != null) {
                    output.close();
                    output = null;
                }
            } catch (IOException e){
                //
            }
        }
        //
        return jsonAttribute;
    }


    @Override
    public Object connect() throws Exception {
        ConfigValue jsonAttributeValues = getCurrentDevice().getConfigValue(JSON_ATTRIBUE_FILE_VALUES);
        ConfigValue printerType = getCurrentDevice().getConfigValue(PRINTER_TYPE);
        LinePrinter.ExtraSettings exSettings = new LinePrinter.ExtraSettings();
        exSettings.setContext(getContext());
        //  Validate
        if(jsonAttributeValues == null
                || jsonAttributeValues.isEmpty()
                || printerType == null
                || printerType.isEmpty()) {
            throw new SpinSuiteException("No Property value");
        }
        linePrinter = new LinePrinter(jsonAttributeValues.getValueAsString(), printerType.getValueAsString(), getPrinterURI(), exSettings);
        // Registers to listen for the print progress events.
        //lp.addPrintProgressListener(progressListener);

        //A retry sequence in case the bluetooth socket is temporarily not ready
        int numTries = 0;
        int maxRetry = 2;
        while (numTries < maxRetry) {
            try {
                linePrinter.connect();  // Connects to the printer
                break;
            } catch (LinePrinterException ex) {
                numTries++;
                Thread.sleep(1000);
            }
        }
        if (numTries == maxRetry) {
            linePrinter.connect();//Final retry
        }

        // Check the state of the printer and abort printing if there are
        // any critical errors detected.
        int[] results = linePrinter.getStatus();
        if (results != null) {
            for (int err = 0; err < results.length; err++) {
                String errorMsg = getErrorMsg(String.valueOf(err));
                if(!Util.isEmpty(errorMsg)) {
                    throw new SpinSuiteException(errorMsg);
                }
            }
        }
        return linePrinter;
    }

    @Override
    public void close() throws Exception {
        if(linePrinter == null) {
            return;
        }
        linePrinter.disconnect();  // Disconnects from the printer
        linePrinter.close();  // Releases resources
    }

    @Override
    public Object read() throws Exception {
        return null;
    }

    @Override
    public Object write(Object... value) throws Exception {
        //	Validate Value
        if(value == null
                || value.length == 0) {
            return null;
        }
        Object valueToPrint = value[0];
        if(valueToPrint instanceof String) {
            String printValue = (String) valueToPrint;
            linePrinter.write(printValue);
        }
        //
        return null;
    }

    @Override
    public void printLine(String line) throws Exception {
        write(line);
    }

    @Override
    public void addLine(int lineQty) throws Exception {
        linePrinter.newLine(lineQty);
    }

    /**
     * Set Compress property
     * @param value
     * @throws Exception
     */
    public void setCompress(boolean value) throws Exception {
        linePrinter.setCompress(value);
    }

    /**
     * Set Bold property
     * @param value
     * @throws Exception
     */
    public void setBold(boolean value) {
        try {
            linePrinter.setBold(value);
        } catch (LinePrinterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }

    @Override
    public String getErrorMsg(String error) {
        if(Util.isEmpty(error)) {
            return null;
        }
        //
        int errorCode = Integer.parseInt(error);
        String resultError = null;
        //  Convert
        switch (errorCode) {
            case 223:
                resultError = "Paper out";
                break;
            case 227:
                resultError = "Printer lid open";
                break;
            default:
                resultError = "No Error Found";
        }
        //
        return resultError;
    }
}
