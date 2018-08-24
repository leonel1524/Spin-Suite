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
package org.erpya.base.util;

import android.support.v4.util.LruCache;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Cache class for implement
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/adempiere/spin-suite/issues/2
 */
public class Cache<K, V> implements ICache<K, V> {

    /**
     * Create Cache
     * @param maxSize
     */
    public Cache(String name, int maxSize, int duration) {
        cache = new LruCache<K, V>(maxSize);
        this.name = name;
        org.erpya.base.util.CacheManager.get().register(this);
    }

    /** Implemented Cache   */
    private LruCache<K, V> cache;
    /** Name    */
    private String name;

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public Collection<V> values() {
        return cache.snapshot().values();
    }

    @Override
    public void clear() {
        //
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void copyFrom(ICache fromCache) {
        if(fromCache == null) {
            return;
        }
        //
        Iterator<K> iterator = fromCache.keySet().iterator();
        while(iterator.hasNext()) {
            K key = iterator.next();
            V value = (V) fromCache.get(key);
            put(key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        return cache.snapshot().keySet();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
