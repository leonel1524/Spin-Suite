/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.erpya.printing;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Base64;

import com.honeywell.mobility.print.LinePrinter;
import com.honeywell.mobility.print.LinePrinterException;
import com.honeywell.mobility.print.PrintProgressEvent;

import org.erpya.spinsuite.base.exceptions.SpinSuiteException;
import org.erpya.spinsuite.base.util.LogM;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * DB class, used for handle connection with Database
 *
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class TestPrinter {

    private String jsonCmdAttribStr = null;
    private Context context;
    private String base64LogoPng = null;
    public TestPrinter(Context context) {
        this.context = context;
    }

    private void readAssetFiles()
    {
        InputStream input = null;
        ByteArrayOutputStream output = null;
        AssetManager assetManager = context.getAssets();
        String[] files = { "printer_profiles.JSON", "honeywell_logo.bmp" };
        int fileIndex = 0;
        int initialBufferSize;

        try
        {
            for (String filename : files)
            {
                input = assetManager.open(filename);
                initialBufferSize = (fileIndex == 0) ? 8000 : 2500;
                output = new ByteArrayOutputStream(initialBufferSize);

                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0)
                {
                    output.write(buf, 0, len);
                }
                input.close();
                input = null;

                output.flush();
                output.close();
                switch (fileIndex)
                {
                    case 0:
                        jsonCmdAttribStr = output.toString();
                        break;
                    case 1:
                        base64LogoPng = Base64.encodeToString(output.toByteArray(), Base64.DEFAULT);
                        break;
                }

                fileIndex++;
                output = null;
            }
        }
        catch (Exception ex)
        {
            LogM.log(context, getClass().getName(), Level.WARNING, "Error reading asset file: ");
        }
        finally
        {
            try
            {
                if (input != null)
                {
                    input.close();
                    input = null;
                }

                if (output != null)
                {
                    output.close();
                    output = null;
                }
            }
            catch (IOException e){}
        }
    }

    public void run() {
        // Create a PrintTask to do printing on a separate thread.
        PrintTask task = new PrintTask();

        // Executes PrintTask with the specified parameter which is passed
        // to the PrintTask.doInBackground method.
        //task.execute("P6824BT", "00:12:F3:1B:AE:51");
        //task.execute("PR2", "00:12:F3:1B:AE:51");
        //task.execute("PR3", "00:12:F3:1B:AE:51");
        task.execute("PR2", "00:12:F3:1B:AE:51");
    }

    /**
     * This class demonstrates printing in a background thread and updates
     * the UI in the UI thread.
     */
    public class PrintTask extends AsyncTask<String, Integer, String> {
        private static final String PROGRESS_CANCEL_MSG = "Printing cancelled\n";
        private static final String PROGRESS_COMPLETE_MSG = "Printing completed\n";
        private static final String PROGRESS_ENDDOC_MSG = "End of document\n";
        private static final String PROGRESS_FINISHED_MSG = "Printer connection closed\n";
        private static final String PROGRESS_NONE_MSG = "Unknown progress message\n";
        private static final String PROGRESS_STARTDOC_MSG = "Start printing document\n";


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
            LinePrinter lp = null;
            String sResult = null;
            String sPrinterID = args[0];
            String sPrinterAddr = args[1];
            String sDocNumber = "1234567890";
            String sPrinterURI = null;

            /*if(connectionTypes.getSelectedItem().toString().equals(
                    getResources().getString(R.string.bluetooth_connection)))
            {
                // The printer address should be a Bluetooth MAC address.
                if (sPrinterAddr.contains(":") == false && sPrinterAddr.length() == 12)
                {
                    // If the MAC address only contains hex digits without the
                    // ":" delimiter, then add ":" to the MAC address string.
                    char[] cAddr = new char[17];

                    for (int i=0, j=0; i < 12; i += 2)
                    {
                        sPrinterAddr.getChars(i, i+2, cAddr, j);
                        j += 2;
                        if (j < 17)
                        {
                            cAddr[j++] = ':';
                        }
                    }

                    sPrinterAddr = new String(cAddr);
                }

                sPrinterURI = "bt://" + sPrinterAddr;
            }
            else if(connectionTypes.getSelectedItem().toString().equals(
                    getResources().getString(R.string.serial_connection)))
            {
                // The printer address should be a serial port name.
                sPrinterURI = "serial://" + sPrinterAddr;
            }*/

            // The printer address should be a Bluetooth MAC address.
            if (sPrinterAddr.contains(":") == false && sPrinterAddr.length() == 12) {
                // If the MAC address only contains hex digits without the
                // ":" delimiter, then add ":" to the MAC address string.
                char[] cAddr = new char[17];

                for (int i = 0, j = 0; i < 12; i += 2) {
                    sPrinterAddr.getChars(i, i + 2, cAddr, j);
                    j += 2;
                    if (j < 17) {
                        cAddr[j++] = ':';
                    }
                }

                sPrinterAddr = new String(cAddr);
            }

            sPrinterURI = "bt://" + sPrinterAddr;

            LinePrinter.ExtraSettings exSettings = new LinePrinter.ExtraSettings();

            exSettings.setContext(context);

            /*PrintProgressListener progressListener =
                    new PrintProgressListener()
                    {
                        @Override
                        public void receivedStatus(PrintProgressEvent aEvent)
                        {
                            // Publishes updates on the UI thread.
                            publishProgress(aEvent.getMessageType());
                        }
                    };*/

            try {
                readAssetFiles();
                lp = new LinePrinter(
                        jsonCmdAttribStr,
                        sPrinterID,
                        sPrinterURI,
                        exSettings);

                // Registers to listen for the print progress events.
                //lp.addPrintProgressListener(progressListener);

                //A retry sequence in case the bluetooth socket is temporarily not ready
                int numtries = 0;
                int maxretry = 2;
                while (numtries < maxretry) {
                    try {
                        lp.connect();  // Connects to the printer
                        break;
                    } catch (LinePrinterException ex) {
                        numtries++;
                        Thread.sleep(1000);
                    }
                }
                if (numtries == maxretry) lp.connect();//Final retry

                // Check the state of the printer and abort printing if there are
                // any critical errors detected.
                int[] results = lp.getStatus();
                if (results != null) {
                    for (int err = 0; err < results.length; err++) {
                        if (results[err] == 223) {
                            // Paper out.
                            throw new SpinSuiteException("Paper out");
                        } else if (results[err] == 227) {
                            // Lid open.
                            throw new SpinSuiteException("Printer lid open");
                        }
                    }
                }

                // Prints the Honeywell logo graphic.
//                lp.writeGraphicBase64(base64LogoPng,
//                        LinePrinter.GraphicRotationDegrees.DEGREE_0,
//                        72,  // Offset in printhead dots from the left of the page
//                        200, // Desired graphic width on paper in printhead dots
//                        40); // Desired graphic height on paper in printhead dots
//                lp.newLine(1);

                // Set font style to Bold + Double Wide + Double High.
                lp.newLine(2);
                lp.setBold(true);
                //lp.setDoubleWide(true);
                //lp.setDoubleHigh(true);
                lp.setCompress(true);
                lp.write("SALES ORDER");
                //lp.setDoubleWide(false);
                //lp.setDoubleHigh(false);
                lp.setCompress(false);
                lp.newLine(2);

                // The following text shall be printed in Bold font style.
                lp.write("CUSTOMER: Casual Step");
                //lp.setBold(false);  // Returns to normal font.
                lp.newLine(2);

                // Set font style to Compressed + Double High.
                //lp.setDoubleHigh(true);
                lp.setCompress(true);
                lp.write("DOCUMENT#: " + sDocNumber);
                lp.setCompress(false);
                //lp.setDoubleHigh(false);
                lp.newLine(2);

                // The following text shall be printed in Normal font style.
                lp.write(" PRD. DESCRIPT.   PRC.  QTY.    NET.");
                lp.newLine(2);

                lp.write(" 1501 Timer-Md1  13.15     1   13.15");
                lp.newLine(1);
                lp.write(" 1502 Timer-Md2  13.15     3   39.45");
                lp.newLine(1);
                lp.write(" 1503 Timer-Md3  13.15     2   26.30");
                lp.newLine(1);
                lp.write(" 1504 Timer-Md4  13.15     4   52.60");
                lp.newLine(1);
                lp.write(" 1505 Timer-Md5  13.15     5   65.75");
                lp.newLine(1);
                lp.write("                        ----  ------");
                lp.newLine(1);
                lp.write("              SUBTOTAL    15  197.25");
                lp.newLine(2);

                lp.write("          5% State Tax          9.86");
                lp.newLine(2);

                lp.write("                              ------");
                lp.newLine(1);
                lp.write("           BALANCE DUE        207.11");
                lp.newLine(1);
                lp.newLine(1);

                lp.write(" PAYMENT TYPE: CASH");
                lp.newLine(2);

                lp.setDoubleHigh(true);
                lp.write("       SIGNATURE / STORE STAMP");
                lp.setDoubleHigh(false);
                lp.newLine(2);

                // Prints the captured signature if it exists.
                /*if (base64SignaturePng != null)
                {
                    lp.writeGraphicBase64(base64SignaturePng,
                            LinePrinter.GraphicRotationDegrees.DEGREE_0,
                            72,   // Offset in printhead dots from the left of the page
                            220,  // Desired graphic width on paper in printhead dots
                            100); // Desired graphic height on paper in printhead dots
                }*/
                lp.newLine(1);

                lp.setBold(true);

                lp.write("          ORIGINAL");
                lp.setBold(false);
                lp.newLine(2);

                // Print a Code 39 barcode containing the document number.
                /*lp.writeBarcode(LinePrinter.BarcodeSymbologies.SYMBOLOGY_CODE39,
                        sDocNumber,   // Document# to encode in barcode
                        60,           // Desired height of the barcode in printhead dots
                        40);          // Offset in printhead dots from the left of the page

                lp.newLine(4);*/

                sResult = "Number of bytes sent to printer: " + lp.getBytesWritten();
            } catch (Exception ex) {
                // Stop listening for printer events.
                //lp.removePrintProgressListener(progressListener);
                sResult = "Printer error detected: " + ex.getMessage() + ". Please correct the error and try again.";
                LogM.log(context, getClass().getName(), Level.WARNING, sResult);
            }
            /*catch (LinePrinterException ex)
            {
                sResult = "LinePrinterException: " + ex.getMessage();
            }
            catch (Exception ex)
            {
                if (ex.getMessage() != null)
                    sResult = "Unexpected exception: " + ex.getMessage();
                else
                    sResult = "Unexpected exception.";
            }*/ finally {
                if (lp != null) {
                    try {
                        lp.disconnect();  // Disconnects from the printer
                        lp.close();  // Releases resources
                    } catch (Exception ex) {
                    }
                }
            }

            // The result string will be passed to the onPostExecute method
            // for display in the the Progress and Status text box.
            return sResult;
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
