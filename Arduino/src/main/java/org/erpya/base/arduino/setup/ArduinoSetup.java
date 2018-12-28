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
package org.erpya.base.arduino.setup;

import android.os.Handler;

import org.erpya.base.arduino.util.ICommand;
import org.erpya.base.arduino.util.ISendCommand;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.util.Util;

import java.io.InputStream;

/**
 * Class for send wireless command for setup
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ArduinoSetup extends Thread implements ICommand {

    /**
     * Private constructor
     * use singleton
     */
    public ArduinoSetup(DeviceTypeHandler deviceHandler, Handler eventHandler) {
        this.deviceHandler = deviceHandler;
        this.eventHandler = eventHandler;
        commandReaded = new StringBuffer();
    }

    /** Send command    */
    private DeviceTypeHandler deviceHandler;
    private Handler eventHandler;
    private SetupAttribute attribute;
    // #defines for identifying shared types between calling functions
    public final static int MESSAGE_READED = 1;
    /** Complete Command    */
    private StringBuffer commandReaded;

    /**
     * Add Attributes for send
     * @param attribute
     * @return
     */
    public ArduinoSetup withAttribute(SetupAttribute attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Send Wireless values
     * @return
     */
    public boolean send() throws Exception {
        if(deviceHandler == null
                || attribute == null) {
            return false;
        }
        //  for others
        if(attribute.getAttributes().size() > 0) {
            ISendCommand sendCommand = (ISendCommand) deviceHandler;
            sendCommand.initCommand(ICommand.REMOTE_SETUP);
            for(String key: attribute.getAttributes().keySet()) {
                sendCommand.sendValue(key, attribute.getAttribute(key));
            }
            sendCommand.endCommand();
        }
        return true;
    }

    @Override
    public boolean request() throws Exception {
        if(deviceHandler == null) {
            return false;
        }
        //  for others
        ISendCommand sendCommand = (ISendCommand) deviceHandler;
        sendCommand.requestValue(ICommand.REMOTE_SETUP);
        return true;
    }

    @Override
    public boolean request(String key) throws Exception {
        if(deviceHandler == null
                || Util.isEmpty(key)) {
            return false;
        }
        //  for others
        ISendCommand sendCommand = (ISendCommand) deviceHandler;
        sendCommand.requestValue(ICommand.REMOTE_SETUP, key);
        //
        return true;
    }

    /**
     * Run thread
     */
    public void run() {
        // Keep listening to the InputStream until an exception occurs
        while (true) {
            InputStream inputStream = deviceHandler.getInputStream();
            if(inputStream == null
                    || eventHandler == null) {
                continue;
            }
            try {
                boolean read = false;
                if(inputStream.available() > 0) {
                    Thread.sleep(1000);
                    do {
                        int bit = inputStream.read();
                        if(bit == ICommand.SOH_CHARACTER
                                && !read) {
                            read = true;
                            commandReaded = new StringBuffer();
                        }
                        if(read) {
                            commandReaded.append((char)bit);
                        }
                        if(bit == ICommand.EOT_CHARACTER) {
                            read = false;
                            if(commandReaded.length() > 0) {
                                String command = commandReaded.toString();
                                if(command.contains(String.valueOf(ICommand.SOH_CHARACTER))
                                        && command.contains(String.valueOf(ICommand.EOT_CHARACTER))) {
                                    // Send the obtained bytes to the UI activity
                                    eventHandler.obtainMessage(MESSAGE_READED, getAttributesFromCommand(command))
                                            .sendToTarget();
                                    commandReaded = new StringBuffer();
                                }
                            }
                        }
                    } while(inputStream.available() > 0);
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Convert command to Setup Attribute
     * @param command
     * @return
     */
    private SetupAttribute getAttributesFromCommand(String command) {
        SetupAttribute attributes = new SetupAttribute();
        if (!hasValue(command)) {
            return attributes;
        }
        //
        String token;
        String evaluateCommand = new String(command);
        int startTokenIndex = evaluateCommand.indexOf(ICommand.STX_CHARACTER);
        while (startTokenIndex != -1) {
            evaluateCommand = evaluateCommand.substring(startTokenIndex + 1, evaluateCommand.length());    // from first STX_CHARACTER
            //
            int endTokenIndex = evaluateCommand.indexOf(ICommand.ETX_CHARACTER);                        // next ETX_CHARACTER
            if (endTokenIndex < 0) {
                System.err.println("No second tag: " + evaluateCommand);
            }
            //	Get individual command
            token = evaluateCommand.substring(0, endTokenIndex);
            //
            if (token.contains(String.valueOf(ICommand.VALUE_SEPARATOR))) {
                String key = token.substring(0, token.indexOf(VALUE_SEPARATOR));
                String value = token.substring(token.indexOf(ICommand.VALUE_SEPARATOR) + 1, token.length());
                //  Add to command
                attributes.addAttribute(key, value);
            }
            //
            evaluateCommand = evaluateCommand.substring(endTokenIndex + 1, evaluateCommand.length());    // from second STX_CHARACTER
            startTokenIndex = evaluateCommand.indexOf(ICommand.STX_CHARACTER);
        }
        //  Default
        return attributes;
    }

    /**
     * Verify if it command has value
     * @param command
     * @return
     */
    private boolean hasValue(String command) {
        if(Util.isEmpty(command)) {
            return false;
        }
        //
        return command.indexOf(ICommand.STX_CHARACTER) > 0;
    }
}
