/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.erpya.base.device.event;

import org.erpya.base.device.util.DeviceTypeHandler;

import java.util.EventObject;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 5 ] Add event listener for read data from hardware
 *		@see https://github.com/erpcya/DeviceManagement/issues/5
 */
public class DeviceEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8272351839762608028L;

	/**
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 *		<li> FR [ 5 ] Add event listener for read data from hardware
	 *		@see https://github.com/erpcya/DeviceManagement/issues/5
	 * @param source
	 * @param eventType
	 */
	public DeviceEvent(DeviceTypeHandler source, int eventType) {
		super(source);
		this.eventType = eventType;
	}
	
	/**	Read Data event		*/
	public static final int	READ_DATA = 1;

	/**	Event Type			*/
	private int eventType = 0;
	
	/**
	 * Get Event Type
	 * @return int
	 */
	public int getEventType() {
		return eventType;
	}
}
