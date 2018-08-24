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

import org.erpya.print.util.IPrintDocument;
import org.erpya.print.util.IPrintLine;

import java.util.List;

/**
 * Class used for print a example document based on example of honeywell
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class PrintDocument implements IPrintDocument {
    @Override
    public String getHeader() {
        return "Hola";
    }

    @Override
    public String getFooter() {
        return "Hasta Luego";
    }

    @Override
    public String getDescription() {
        return "Epale Description";
    }

    @Override
    public List<String> getFields() {
        return null;
    }

    @Override
    public int getHeadSpace() {
        return 0;
    }

    @Override
    public int getFooterSpace() {
        return 0;
    }

    @Override
    public int getLineSpacing() {
        return 0;
    }

    @Override
    public int getPageFooterSpace() {
        return 0;
    }

    @Override
    public List<IPrintLine> getPrintLines() {
        return null;
    }

    @Override
    public boolean isAllowsPrint() {
        return false;
    }
}
