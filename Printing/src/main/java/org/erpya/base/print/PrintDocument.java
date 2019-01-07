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

package org.erpya.base.print;

import org.erpya.base.print.util.IPrintDocument;
import org.erpya.base.print.util.IPrintLine;

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
