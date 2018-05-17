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
package org.erpya.printing;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.honeywell.mobility.print.PrintProgressEvent;

import org.erpya.device.util.DeviceManager;
import org.erpya.device.util.DeviceTypeHandler;
import org.erpya.device.util.IDevice;
import org.erpya.device.util.IDeviceType;
import org.erpya.printing.honeywell.supported.DatamaxApex2;
import org.erpya.printing.util.IPrinter;
import org.erpya.spinsuite.base.util.LogM;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;

/**
 * DB class, used for handle connection with Database
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class TestPrinter {

    private String jsonAttribute = null;
    private Context context;
    private String base64LogoPng = null;
    private DeviceTypeHandler handler = null;

    public TestPrinter(Context context) {
        this.context = context;
    }

    public void run() {
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
            // Clears the Progress and Status text box.
            // textMsg.setText("");

            // Disables the Print button.
            //buttonPrint.setEnabled(false);
            // Disables the Sign button.
            //buttonSign.setEnabled(false);

            // Shows a progress icon on the title bar to indicate
            // it is working on something.
            //setProgressBarIndeterminateVisibility(true);
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
                    .getDefaultDeviceHandler(IDeviceType.TYPE_PRINTER);
            try {
                handler.connect();
                IPrinter printer = ((IPrinter) handler);
                printer.printLine("Hi");
                printer.addLine(10);
                printer.printLine("Epale");
                handler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Runs on the UI thread after publishProgress is invoked. The
         * specified values are the values passed to publishProgress.
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            // Access the values array.
            int progress = values[0];

            switch (progress) {
                case PrintProgressEvent.MessageTypes.CANCEL:
                    //textMsg.append(PROGRESS_CANCEL_MSG);
                    break;
                case PrintProgressEvent.MessageTypes.COMPLETE:
                    //textMsg.append(PROGRESS_COMPLETE_MSG);
                    break;
                case PrintProgressEvent.MessageTypes.ENDDOC:
                    //textMsg.append(PROGRESS_ENDDOC_MSG);
                    break;
                case PrintProgressEvent.MessageTypes.FINISHED:
                    //textMsg.append(PROGRESS_FINISHED_MSG);
                    break;
                case PrintProgressEvent.MessageTypes.STARTDOC:
                    //textMsg.append(PROGRESS_STARTDOC_MSG);
                    break;
                default:
                    //textMsg.append(PROGRESS_NONE_MSG);
                    break;
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

            // Dismisses the progress icon on the title bar.
            //setProgressBarIndeterminateVisibility(false);

            // Enables the Print button.
            //buttonPrint.setEnabled(true);
            // Enables the Sign button.
            //buttonSign.setEnabled(true);
        }
    }
}
