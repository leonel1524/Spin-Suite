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
package org.erpya.base.arduino;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Build;

import org.erpya.base.arduino.util.ICommand;
import org.erpya.base.arduino.util.ISendCommand;
import org.erpya.base.device.util.ConfigValue;
import org.erpya.base.device.util.Device;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.device.util.IDevice;
import org.erpya.base.device.util.IDeviceType;
import org.erpya.base.util.Env;
import org.erpya.base.util.LogM;
import org.erpya.base.util.Util;
import org.erpya.base.util.ValueUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Arduino connector
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ArduinoBluetoothHandler extends DeviceTypeHandler implements ISendCommand {

    /** Mandatory Attributes  */
    String ARDUINO_TYPE_KEY = "#ARDUINO_TYPE";
    String INTERFACE_TYPE_KEY = "#INTERFACE_TYPE";
    /**Values   */
    String ARDUINO_UNO = "UNO";
    String INTERFACE_BLUETOOTH = "BLU";

    /**
     * Standard constructor
     *
     * @param context
     * @param deviceType
     * @return void
     * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
     */
    public ArduinoBluetoothHandler(Context context, IDeviceType deviceType) {
        super(context, deviceType);
        setBluetoothMode();
    }

    /** Bluetooth Socket    */
    private BluetoothSocket bluetoothSocket = null;
    //SPP UUID. Look for it
    static final UUID deviceUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /**
     * Set Bluetooth Mode
     */
    public void setBluetoothMode() {
        getDeviceType().addConfigValue(INTERFACE_TYPE_KEY, INTERFACE_BLUETOOTH);
    }

    @Override
    public List<IDevice> getAvailableDeviceList() {
        List<IDevice> deviceList = new ArrayList<IDevice>();
        ConfigValue connectionType = getDeviceType().getConfigValue(INTERFACE_TYPE_KEY);
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
                bluetoothDevice.addConfigValue(ARDUINO_TYPE_KEY, ARDUINO_UNO);
                bluetoothDevice.addConfigValue(INTERFACE_TYPE_KEY, INTERFACE_BLUETOOTH);
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

    @Override
    public Object connect() throws Exception {
        Device bluetooth = (Device) getCurrentDevice();
        if(bluetooth == null) {
            return null;
        }
        if(bluetoothSocket == null
                || !isConnected()) {
            BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bluetooth.getAddress());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
                bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(deviceUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                bluetoothSocket.connect();//start connection
                setIsConnected(true);
            }
        }
        //
        return bluetoothSocket;
    }

    @Override
    public void close() throws Exception {
        if(bluetoothSocket != null) {
            bluetoothSocket.close();
            setIsConnected(false);
        }
    }

    @Override
    public Object read() throws Exception {
        return null;
    }

    @Override
    public Object write(Object... value) throws Exception {
        //  Validate Value
        if(value == null) {
            return null;
        }
        //  Write
        OutputStream outputStream = getOutputStream();
        if(outputStream != null) {
            for(Object valueToWrite : value) {
                if(valueToWrite == null) {
                    continue;
                }
                outputStream.write(ValueUtil.getValueAsString(valueToWrite).getBytes());
            }
        }
        return null;
    }

    @Override
    public InputStream getInputStream() {
        if(bluetoothSocket != null
                && isConnected()) {
            try {
                return bluetoothSocket.getInputStream();
            } catch (IOException e) {
                LogM.log(Env.getContext(), this.getClass(), Level.WARNING, "Error getting InputStream", e.getCause());
            }
        }
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        if(bluetoothSocket != null
                && isConnected()) {
            try {
                return bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                LogM.log(Env.getContext(), this.getClass(), Level.WARNING, "Error getting OutputStream", e.getCause());
            }
        }
        return null;
    }

    @Override
    public String getErrorMsg(String error) {
        return null;
    }

    @Override
    public void initCommand(int command) throws Exception {
        write(ICommand.SOH_CHARACTER + String.valueOf(command));
    }

    @Override
    public void sendValue(String message) throws Exception {
        if(Util.isEmpty(message)) {
            message = "";
        }
        write(ICommand.STX_CHARACTER + message + ICommand.ETX_CHARACTER);
    }

    @Override
    public void endCommand() throws Exception {
        write(ICommand.EOT_CHARACTER);
    }

    @Override
    public void requestCommand(int command) throws Exception {
        write(ICommand.SOH_CHARACTER + String.valueOf(command) + ICommand.EOT_CHARACTER);
    }
}
