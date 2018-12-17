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
