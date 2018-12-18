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

import android.content.Context;
import android.os.AsyncTask;

import org.erpya.base.arduino.util.IArduino;
import org.erpya.base.arduino.util.IArduinoStatus;
import org.erpya.base.device.util.DeviceManager;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.device.util.IDevice;
import org.erpya.base.device.util.IDeviceType;
import org.erpya.base.util.LogM;


/**
 * Class used for print a example document based on example of honeywell
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class TestArduinoSendMessage {
    //  Vars
    private Context context;
    private IArduinoStatus listener;
    private String result = null;
    private String message;
    private DeviceTypeHandler handler = null;
    private LogM log = null;

    /**
     * Default contructor
     * @param listener
     * @param context
     */
    public TestArduinoSendMessage(IArduinoStatus listener, Context context) {
        this.listener = listener;
        this.context = context;
        log = new LogM(context, this.getClass());
        handler = DeviceManager
                .getInstance()
                .getDefaultDeviceHandler(IDeviceType.TYPE_ARDUINO);
        //
        for(IDevice device: handler.getAvailableDeviceList()) {
            log.error(device.getAddress());
        }

    }

    /**
     * Print a Document
     * @param message
     * @return void
     */
    public void sendMessage(String message) {
        this.message = message;
        PrintTask task = new PrintTask();
        task.execute();
    }

    /**
     * This class demonstrates printing in a background thread and updates
     * the UI in the UI thread.
     */
    public class PrintTask extends AsyncTask<String, Integer, String> {


        /**
         * Runs on the UI thread before doInBackground(Params...).
         */
        @Override
        protected void onPreExecute() {
            //
        }

        /**
         * This method runs on a background thread. The specified parameters
         * are the parameters passed to the execute method by the caller of
         * this task. This method can call publishProgress to publish updates
         * on the UI thread.
         */
        @Override
        protected String doInBackground(String... args) {
            handler = DeviceManager
                    .getInstance()
                    .getDefaultDeviceHandler(IDeviceType.TYPE_ARDUINO);
            try {
                //	Connect
                publishProgress(IArduinoStatus.OPENING_CONNECTION);
                log.fine("Connecting...");
                handler.connect();
                IArduino arduinoDevice = ((IArduino) handler);
                //  send message
                arduinoDevice.sendMessage("Hola Mundo");
                log.fine("Document Sent");
            } catch (Exception e) {
                result = e.getLocalizedMessage();
                publishProgress(IArduinoStatus.ERROR);
                log.severe("Printing Error " + e.getLocalizedMessage());
            } finally {
                publishProgress(IArduinoStatus.CLOSING_CONNECTION);
                log.fine("Closing Connection");
                try {
                    handler.close();
                } catch (Exception e) {
                    result = e.getLocalizedMessage();
                    log.severe("Closing Connection Error " + e.getLocalizedMessage());
                }
            }
            // The result string will be passed to the onPostExecute method
            // for display in the the Progress and Status text box.
            return result;
        }

        /**
         * Runs on the UI thread after publishProgress is invoked. The
         * specified values are the values passed to publishProgress.
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            if(listener == null) {
                return;
            }
            // Access the values array.
            int progress = values[0];

            switch (progress) {
                /*case IArduinoStatus.OPENING_CONNECTION:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Opening_Connection));
                    break;
                case IArduinoStatus.CLOSING_CONNECTION:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Closing_Connection));
                    break;
                case IArduinoStatus.SENDING_TEXT:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Text));
                    break;
                case IArduinoStatus.CONNECTION_ERROR:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Connection_Error) + ": " + result);
                    break;
                case IArduinoStatus.ERROR:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Generic_Error) + ": " + result);
                    break;*/
            }
        }

        /**
         * Runs on the UI thread after doInBackground method. The specified
         * result parameter is the value returned by doInBackground.
         */
        @Override
        protected void onPostExecute(String result) {
            // Displays the result (number of bytes sent to the printer or
            // exception message) in the Progress and Status text box.
            if (result != null) {
                //textMsg.append(result);
            }
        }
    }
}
