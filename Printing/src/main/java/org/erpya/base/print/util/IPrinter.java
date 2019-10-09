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
package org.erpya.base.print.util;

/**
 * Device Interface for handle printers
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 */
public interface IPrinter {

    /**
     * Print Line
     */
    public void printLine(String line) throws Exception;


    /**
     * add lines
     * @param lineQuantity
     */
    public void addLine(int lineQuantity) throws Exception;

    /**
     * Se if is printed a bold font for next printing
     * @param isBold
     */
    public void setBold(boolean isBold);
}
