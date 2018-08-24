/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.erpya.base.print;

import android.content.Context;
import android.os.AsyncTask;

import org.erpya.print.util.IPrintDocument;
import org.erpya.print.util.IPrintLine;
import org.erpya.print.util.IPrintStatus;
import org.erpya.base.device.util.DeviceManager;
import org.erpya.base.device.util.DeviceTypeHandler;
import org.erpya.base.device.util.IDeviceType;
import org.erpya.base.util.LogM;

import java.util.List;

/**
 * Class used for print a example document based on example of honeywell
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class TestPrintDocument {
    //  Vars
    private Context context;
    private IPrintDocument document;
    private IPrintStatus listener;
    private String result = null;
    private DeviceTypeHandler handler = null;
    private LogM log = null;

    /**
     * Default contructor
     * @param listener
     * @param context
     */
    public TestPrintDocument(IPrintStatus listener, Context context) {
        this.listener = listener;
        this.context = context;
        log = new LogM(context, this.getClass());
    }

    /**
     * Print a Document
     * @param document
     * @return void
     */
    public void printDocument(IPrintDocument document) {
        this.document = document;
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
                    .getDefaultDeviceHandler(IDeviceType.TYPE_PRINTER);
            try {
                //	Connect
                publishProgress(IPrintStatus.OPENING_CONNECTION);
                log.fine("Connecting...");
                handler.connect();
                org.erpya.print.util.IPrinter printer = ((org.erpya.print.util.IPrinter) handler);
                int lineSpacing = document.getLineSpacing();
                if(lineSpacing <= 0) {
                    lineSpacing = 1;
                }
                //	Head Space
                if(document.getHeadSpace() > 0) {
                    printer.addLine(document.getHeadSpace());
                }
                publishProgress(IPrintStatus.SENDING_HEADER);
                log.fine("Sending Header");
                //	Print header
                if(document.getHeader() != null
                        && document.getHeader().trim().length() > 0) {
//                    printer.setBold(true);
                    //
                    printer.printLine(document.getHeader());
                    printer.addLine(lineSpacing);
                    //
//                    printer.setBold(false);
                }
                //	For custom fields
                if(document.getFields() != null
                        && document.getFields().size() > 0) {
                    for(String text : document.getFields()) {
                        if(text == null
                                || text.trim().length() == 0) {
                            continue;
                        }
                        printer.printLine(text);
                        printer.addLine(lineSpacing);
                    }
                }
                //	For Description
                if(document.getDescription() != null
                        && document.getDescription().trim().length() > 0) {
//                    printer.setBold(true);
                    //
                    printer.printLine(document.getDescription());
                    printer.addLine(lineSpacing);
                    //
//                    printer.setBold(false);
                }
                publishProgress(IPrintStatus.SENDING_LINE);
                log.fine("Sending Line");
                //	Get Lines
                List<IPrintLine> lines = document.getPrintLines();
                if(lines != null
                        && lines.size() > 0) {
                    for(IPrintLine line : lines) {
                        if(line != null
                                && line.getLine() != null
                                && line.getLine().trim().length() > 0) {
                            printer.printLine(line.getLine());
                        }
                        printer.addLine(lineSpacing);
                    }
                }
                publishProgress(IPrintStatus.SENDING_FOOTER);
                log.fine("Sending Footer");
                //	Footer Space
                if(document.getFooterSpace() > 0) {
                    printer.addLine(document.getFooterSpace());
                }
                //	Footer
                if(document.getFooter() != null
                        && document.getFooter().trim().length() > 0) {
//                    printer.setBold(true);
                    //
                    printer.printLine(document.getFooter());
                    printer.addLine(lineSpacing);
                    //
//                    printer.setBold(false);
                }
                //	Add last Space
                if(document.getPageFooterSpace() > 0) {
                    printer.addLine(document.getPageFooterSpace());
                }
                publishProgress(IPrintStatus.DOCUMENT_SENT);
                log.fine("Document Sent");
            } catch (Exception e) {
                result = e.getLocalizedMessage();
                publishProgress(IPrintStatus.ERROR);
                log.severe("Printing Error " + e.getLocalizedMessage());
            } finally {
                publishProgress(IPrintStatus.CLOSING_CONNECTION);
                log.fine("Closing Connection");
                try {
                    handler.close();
                } catch (Exception e) {
                    result = e.getLocalizedMessage();
                    log.severe("Closing Connection Error " + e.getLocalizedMessage());
                }
                publishProgress(IPrintStatus.PRINTING_FINISHED);
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
                case IPrintStatus.OPENING_CONNECTION:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Opening_Connection));
                    break;
                case IPrintStatus.CLOSING_CONNECTION:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Closing_Connection));
                    break;
                case IPrintStatus.SENDING_TEXT:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Text));
                    break;
                case IPrintStatus.SENDING_DOCUMENT:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Document));
                    break;
                case IPrintStatus.SENDING_HEADER:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Header));
                    break;
                case IPrintStatus.SENDING_LINE:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Line));
                    break;
                case IPrintStatus.SENDING_IMAGE:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Image));
                    break;
                case IPrintStatus.SENDING_FOOTER:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Sending_Footer));
                    break;
                case IPrintStatus.PRINTING_FINISHED:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Finished));
                    break;
                case IPrintStatus.CONNECTION_ERROR:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Connection_Error) + ": " + result);
                    break;
                case IPrintStatus.PRINTING_ERROR:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Error) + ": " + result);
                    break;
                case IPrintStatus.ERROR:
                    listener.publishStatus(context.getResources().getString(R.string.Printing_Generic_Error) + ": " + result);
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
        }
    }
}
