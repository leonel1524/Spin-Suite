/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.erpya.spinsuite.base.util;

import java.util.logging.Level;

import android.content.Context;
import android.util.Log;

import org.erpya.spinsuite.base.model.PO;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *
 */
public class LogM {
	/**	Trace Level Constant		*/
	private final static String 	TRACE_LEVEL_KEY = "#TraceLevel";

	/**
	 * Default contructor
	 * @param context
	 * @param clazz
	 */
	public LogM(Context context, Class clazz) {
		this.context = context;
		this.clazz = clazz;
	}

	/** Class for log	*/
	private Class clazz;
	/**	Context	*/
	private Context context;


	/**
	 * Log
	 * @param level
	 * @param msg
	 */
	public void log(Level level, String msg) {
		log(context, clazz.getName(), level, msg);
	}

	/**
	 * For Warning Log
	 * @param msg
	 */
	public void warning(String msg) {
		log(Level.WARNING, msg);
	}

	/**
	 * For Fine Log
	 * @param msg
	 */
	public void fine(String msg) {
		log(Level.FINE, msg);
	}

	/**
	 * For Error Log
	 * @param msg
	 */
	public void error(String msg) {
		log(Level.SEVERE, msg);
	}

	/**
	 * For Severe
	 * @param msg
	 */
	public void severe(String msg) {
		error(msg);
	}

	/**
	 * For finest
	 * @param msg
	 */
	public void finest(String msg) {
		log(Level.FINEST, msg);
	}

	/**
	 * Log
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 20:34:20
	 * @param ctx
	 * @param className
	 * @param level
	 * @param msg
	 * @return void
	 */
	public static void log(Context ctx, String className, Level level, String msg){
		if(msg == null)
			return;
		int m_TraceLevel = getTraceLevel(ctx);
		//	Level
		if(m_TraceLevel <=  level.intValue()){
			if(level == Level.FINE
					|| level == Level.FINER
					|| level == Level.FINEST)
				Log.v(className, msg);
			else if(level == Level.SEVERE)
				Log.e(className, msg);
			else if(level == Level.WARNING)
				Log.w(className, msg);
			else if(level == Level.INFO)
				Log.i(className, msg);
		}
	}
	
	/**
	 * Log
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 20:35:37
	 * @param ctx
	 * @param className
	 * @param level
	 * @param msg
	 * @param tr
	 * @return void
	 */
	public static void log(Context ctx, String className, Level level, String msg, Throwable tr) {
		if(msg == null)
			return;
		int m_TraceLevel = getTraceLevel(ctx);
		//	Level
		if(m_TraceLevel <=  level.intValue()){
			if(level == Level.FINE
					|| level == Level.FINER
					|| level == Level.FINEST)
				Log.v(className, msg, tr);
			else if(level == Level.SEVERE)
				Log.e(className, msg, tr);
			else if(level == Level.WARNING)
				Log.w(className, msg, tr);
			else if(level == Level.INFO)
				Log.i(className, msg, tr);
		}
	}
	
	/**
	 * Log
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 20:36:42
	 * @param ctx
	 * @param clazz
	 * @param level
	 * @param msg
	 * @param tr
	 * @return void
	 */
	public static void log(Context ctx, Class<?> clazz, Level level, String msg, Throwable tr) {
		log(ctx, clazz.getName(), level, msg, tr);
	}
	
	/**
	 * Log
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 20:38:27
	 * @param ctx
	 * @param clazz
	 * @param level
	 * @param msg
	 * @return void
	 */
	public static void log(Context ctx, Level level, Class<? extends PO> clazz, Exception ex) {
		log(ctx, clazz.getName(), level, ex.getLocalizedMessage());
	}
	/**
	 * Get current trace level
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 15:54:04
	 * @return
	 * @return int
	 */
	public static int getTraceLevel(Context ctx){
		return Env.getContextAsInt(TRACE_LEVEL_KEY);
	}
	
	/**
	 * Set Trace level
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 26/02/2014, 15:57:12
	 * @param traceLevel
	 * @return void
	 */
	public static void setTraceLevel(Context ctx, Level traceLevel) {
		Env.setContext(TRACE_LEVEL_KEY, traceLevel.intValue());
	}
}
