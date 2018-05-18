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

import java.util.List;

/**
 * Interface for printing document
 */
public interface IPrintDocument {

	/**
	 * Get header to print
	 * @return String
	 */
	public String getHeader();

	/**
	 * Get Footer document
	 * @return String
	 */
	public String getFooter();


	/**
	 * Get description to print
	 * @return String
	 */
	public String getDescription();

	/**
	 * Get Additional fields
	 * @return List<String>
	 */
	public List<String> getFields();

	/**
	 * Get head Space (Example for 3 space)
	 * |------------------|
	 * |         1        |
	 * |         2        |
	 * |         3        |
	 * |Document Text     |
	 * |                  |
	 * |                  |
	 * |------------------|
	 * @return int
	 */
	public int getHeadSpace();

	/**
	 * Get footer Space (Example for 3 space)
	 * |------------------|
	 * |Document Text     |
	 * |         1        |
	 * |         2        |
	 * |         3        |
	 * |Document Footer   |
	 * |------------------|
	 * @return int
	 */
	public int getFooterSpace();

	/**
	 * Get Line Spacing
	 * @return int
	 */
	public int getLineSpacing();

	/**
	 * Get footer Space (Example for 3 space)
	 * |------------------|
	 * |Document Footer   |
	 * |         1        |
	 * |         2        |
	 * |         3        |
	 * |------------------|
	 * @return int
	 */
	public int getPageFooterSpace();

	/**
	 * Get Lines of document
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 16 may. 2018, 7:43:59 p. m.
	 * @return
	 * @return List<PrintLine>
	 */
	public List<IPrintLine> getPrintLines();


	/**
	 * Validate this document can be printed
	 * @return boolean
	 */
	public boolean isAllowsPrint();
}
