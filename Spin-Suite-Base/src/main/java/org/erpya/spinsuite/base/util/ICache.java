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

import java.util.Collection;
import java.util.Set;

/**
 * Cache interface used as contract of a implemented cache
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public interface ICache <K, V> {

    /**
     * Put a value from a key, it can replace a existing value if has the same key
     * @param key
     * @param value
     */
    public void put(K key, V value);

    /**
     * Used for get a cached value from key
     * @param key
     * @return
     */
    public V get(K key);


    /**
     * Remove a cache entry
     * @param key
     */
    public void remove(K key);

    /**
     * Get all values
     * @return
     */
    public Collection<?> values();

    /**
     * Clear cache
     */
    public void clear();

    /**
     * Get Cache Size
     * @return
     */
    public int size();

    /**
     * Get Cache Name
     * @return
     */
    public String getName();

    /**
     * Copy cache from other
     * @param fromCache
     */
    public void copyFrom(ICache fromCache);

    /**
     * Get Key Set
     * @return
     */
    public Set<K> keySet();

    /**
     * Verify if is empty cache
     * @return
     */
    public boolean isEmpty();
}
