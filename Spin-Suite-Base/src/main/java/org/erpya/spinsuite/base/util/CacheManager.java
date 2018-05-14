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

import java.util.HashMap;
import java.util.Map;

/**
 * Cache Manager class for implement cacheInstances of cache
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public class CacheManager {

    /** Singleton instance  */
    public static CacheManager instance = null;
    /** Instances of Cache  */
    private Map<String, ICache> cacheInstances = new HashMap<String, ICache>();

    /**
     * Private Constructor
     */
    private CacheManager() {
        //
    }

    /**
     * Get or create cache
     * @return
     */
    public static synchronized CacheManager get() {
        if (instance == null) {
            instance = new CacheManager();
        }
        //
        return instance;
    }	//	get


    /**************************************************************************
     * 	Register Cache Instance
     *	@param instance Cache
     *	@return true if added
     */
    public synchronized boolean register(ICache instance) {
        if (instance == null)
            return false;
        if(!cacheInstances.containsKey(instance.getName())) {
            cacheInstances.put(instance.getName(), instance);
        } else {
            instance.copyFrom(cacheInstances.get(instance.getName()));
        }
        //
        return true;
    }	//	register

    /**
     * 	Un-Register Cache Instance
     *	@param instance Cache
     *	@return true if removed
     */
    public boolean unregister (ICache instance) {
        if (instance == null)
            return false;
        boolean found = cacheInstances.containsKey(instance.getName());
        //  Remove
        if(found) {
            cacheInstances.remove(instance.getName());
        }
        //  Return
        return found;
    }	//	unregister

    /**************************************************************************
     * 	Reset All registered Cache
     * 	@return number of deleted cache entries
     */
    public int reset() {
        int total = 0;
        //  Iterate
        for (ICache cache : cacheInstances.values()) {
            total += cache.size();
            cache.clear();
        }
        return total;
    }	//	reset

    /**
     * 	Reset registered Cache from name
     * 	@param name table name
     */
    public void reset (String name) {
        reset (name, 0);
    }	//	reset

    /**
     * 	Reset registered Cache
     * 	@param name table name
     * 	@param recordId record if applicable or 0 for all
     */
    public void reset(String name, int recordId) {
        if (name == null)
            return;
        //
        ICache cache = cacheInstances.get(name);
        if(cache != null) {
            cache.remove(recordId);
        }
    }	//	reset

    /**
     * 	Total Cached Elements
     *	@return count
     */
    public int getElementCount() {
        int total = 0;
        for (ICache cache : cacheInstances.values()){
            total += cache.size();
        }
        return total;
    }	//	getElementCount
}
