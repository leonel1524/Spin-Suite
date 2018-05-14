/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, write to the Free Software Foundation, Inc.,            *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.erpya.printing.honeywell;

import com.honeywell.mobility.print.LinePrinter;
import com.honeywell.mobility.print.LinePrinterException;

import org.erpya.device.util.DeviceTypeHandler;
import org.erpya.device.util.IDevice;
import org.erpya.device.util.IDeviceType;
import org.erpya.spinsuite.base.exceptions.SpinSuiteException;
import org.erpya.spinsuite.base.util.Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Honeywell connector
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class HWPrinter extends DeviceTypeHandler {
    /**
     * Standard constructor
     *
     * @param deviceType
     * @return void
     * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
     */
    public HWPrinter(IDeviceType deviceType) {
        super(deviceType);
    }

    /** Class for print */
    private LinePrinter linePrinter = null;
    /** Printer URI */
    private String printerURI = null;
    /** Mandatory Attributes  */
    public static String MAC_ADDRESS = "#MAC_ADDRESS";
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
        //
        Map<String, Object> attributes = device.getDeviceTypeConfig();
        if(attributes == null) {
            return null;
        }
        //  Get Values
        String macAddress = (String) attributes.get(MAC_ADDRESS);
        String interfaceType = (String) attributes.get(INTERFACE_TYPE);
        //  Validate
        if(Util.isEmpty(macAddress)
                || Util.isEmpty(interfaceType)) {
            return null;
        }
        //  Get
        // The printer address should be a Bluetooth MAC address.
        if (macAddress.contains(":") == false && macAddress.length() == 12) {
            // If the MAC address only contains hex digits without the
            // ":" delimiter, then add ":" to the MAC address string.
            char[] cAddr = new char[17];

            for (int i = 0, j = 0; i < 12; i += 2) {
                macAddress.getChars(i, i + 2, cAddr, j);
                j += 2;
                if (j < 17) {
                    cAddr[j++] = ':';
                }
            }

            macAddress = new String(cAddr);
        }
        //
        printerURI = interfaceType + "://" + macAddress;
        //  Default return
        return printerURI;
    }

    @Override
    public List<IDevice> getDeviceList() {
        return null;
    }

    @Override
    public boolean isAvailable() throws Exception {
        return false;
    }

    @Override
    public Object connect() throws Exception {
        String uri = getPrinterURI();
        Map<String, Object> attributes = getCurrentDevice().getDeviceTypeConfig();
        if(attributes == null) {
            return null;
        }
        String jsonAttributeValues = (String) attributes.get(JSON_ATTRIBUE_FILE_VALUES);
        String printerId = (String) attributes.get(PRINTER_TYPE);
        LinePrinter.ExtraSettings exSettings = new LinePrinter.ExtraSettings();
        exSettings.setContext(null);
        linePrinter = new LinePrinter(jsonAttributeValues, printerId, printerURI, exSettings);
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

    /**
     * Add a new line
     * @param lineQty
     * @throws Exception
     */
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
    public void setBold(boolean value) throws Exception {
        linePrinter.setBold(value);
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
