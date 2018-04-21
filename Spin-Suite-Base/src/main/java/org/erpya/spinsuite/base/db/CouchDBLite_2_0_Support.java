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
package org.erpya.spinsuite.base.db;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;
import org.erpya.spinsuite.base.model.PO;

import java.net.URI;
import java.util.Map;


/**
 * DB class, used for handle connection with Database
 *
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class CouchDBLite_2_0_Support implements DB_Support {
    /** Constant with Database Name */
    private static final String DATABASE_NAME = "spin-suite";
    /** Android Context */
    /** Database Configurator   */
    private DatabaseConfiguration databaseConfiguration = null;
    /** Database object */
    private Database database = null;

    /**
     * Default constructor, context is mandatory
     * @param context
     */
    public CouchDBLite_2_0_Support(Context context) {
        databaseConfiguration = new DatabaseConfiguration(context);
    }

    @Override
    public void open() throws Exception {
        if(database != null) {
            return;
        }
        database = new Database(DATABASE_NAME, databaseConfiguration);
    }

    @Override
    public void close() throws Exception {
        if(database != null) {
            database.close();
        }
    }

    @Override
    public void savePO(PO entity) throws Exception {
        if(entity == null) {
            return;
        }
        //
        MutableDocument mutableDocument = new MutableDocument();
        //  Get data from
        Map<String, Object> values = entity.getMap();
        // validate
        if(values == null
                || values.isEmpty()) {
            return;
        }
        //  do it
        for(Map.Entry<String, Object> entry : values.entrySet()) {
            if(entry.getValue() instanceof Integer) {
                mutableDocument.setInt(entry.getKey(), (Integer) entry.getValue());
            } else if(entry.getValue() instanceof String) {
                mutableDocument.setString(entry.getKey(), (String) entry.getValue());
            }
            //  TODO add support to all value types
        }
        //  Save document
        database.save(mutableDocument);
        Log.d("Epale", "--- " + mutableDocument.getId());
        // Create replicators to push and pull changes to and from the cloud.
        Endpoint targetEndpoint = new URLEndpoint(new URI("ws://impala:55084/spin-suite"));
        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(database, targetEndpoint);
        replConfig.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);

        // Add authentication.
        replConfig.setAuthenticator(new BasicAuthenticator("rMunoz", "rMunoz"));

        // Create replicator.
        Replicator replicator = new Replicator(replConfig);

        // Listen to replicator change events.
        replicator.addChangeListener(new ReplicatorChangeListener() {
            @Override
            public void changed(ReplicatorChange change) {
                if (change.getStatus().getError() != null)
                    Log.i("Prueba de Listener", "Error code ::  " + change.getStatus().getError().getCode());
            }
        });

        // Start replication.
        replicator.start();
    }

    @Override
    public boolean isOpen() {
        return database != null;
    }
}
