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
package org.erpya.printing.util;

/**
 * Interface for publish result
 */
public interface IPrintStatus {

	/**	Open Printer connection	*/
	public final int OPENING_CONNECTION = 0;
	/**	Close Printer connection	*/
	public final int CLOSING_CONNECTION = 1;
	/**	Sending a Text	*/
	public final int SENDING_TEXT = 2;
	/**	Sending document	*/
	public final int SENDING_DOCUMENT = 3;
	/**	Document Sent	*/
	public final int DOCUMENT_SENT = 4;
	/**	Sending a header document	*/
	public final int SENDING_HEADER = 5;
	/**	Sending a Line Document	*/
	public final int SENDING_LINE = 6;
	/**	Sending Image	*/
	public final int SENDING_IMAGE = 7;
	/**	Sending Footer	*/
	public final int SENDING_FOOTER = 8;
	/**	Printing Finished	*/
	public final int PRINTING_FINISHED = 9;
	/**	Connection Error	*/
	public final int CONNECTION_ERROR = 10;
	/**	Printing Error	*/
	public final int PRINTING_ERROR = 11;
	/**	Generic Error	*/
	public final int ERROR = 12;

	/**
	 * Used for publish print status
	 */
	public void publishStatus(String message);
}
