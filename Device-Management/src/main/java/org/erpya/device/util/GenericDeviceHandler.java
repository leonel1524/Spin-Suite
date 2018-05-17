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
package org.erpya.device.util;

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
