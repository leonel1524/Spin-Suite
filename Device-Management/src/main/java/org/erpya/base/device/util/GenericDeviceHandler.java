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
package org.erpya.base.device.util;

import android.content.Context;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Generic implementation of Device Type Handler
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class GenericDeviceHandler extends DeviceTypeHandler {

	public GenericDeviceHandler(Context context, IDeviceType device) {
		super(context, device);
	}

	@Override
	public List<IDevice> getDeviceList() {
		return null;
	}

	@Override
	public List<IDevice> getAvailableDeviceList() {
		return null;
	}

	@Override
	public Object connect() {
		//	Not yet implemented
		return null;
	}

	@Override
	public void close() {
		//	Not yet implemented
	}

	@Override
	public Object read() {
		//	Not yet implemented
		return null;
	}

	@Override
	public Object write(Object... value) {
		//	Not yet implemented
		return null;
	}

	@Override
	public InputStream getInputStream() {
		//	Not yet implemented
		return null;
	}

	@Override
	public OutputStream getOutputStream() {
		//	Not yet implemented
		return null;
	}

    @Override
    public String getErrorMsg(String error) {
        return null;
    }

    @Override
	public boolean isAvailable() throws Exception {
		//	Not yet implemented
		return false;
	}
}
