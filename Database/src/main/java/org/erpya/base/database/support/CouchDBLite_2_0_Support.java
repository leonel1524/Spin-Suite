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
package org.erpya.base.database.support;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.Expression;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.couchbase.lite.URLEndpoint;

import org.erpya.base.db.DBSupport;
import org.erpya.base.util.Condition;
import org.erpya.base.util.Criteria;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * DB class, used for handle connection with Database
 *
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [  ]
 * @see https://github.com/erpcya/Spin-Suite/issues/
 */
public class CouchDBLite_2_0_Support implements DBSupport {
    /** Constant with Database Name */
    private static final String DATABASE_NAME = "spin-suite";
    /** Android Context */
    /** Database Configurator   */
    private DatabaseConfiguration databaseConfiguration = null;
    /** Database object */
    private Database database = null;
    /** Context */
    private Context context;

    /**
     * Default constructor, context is mandatory
     * @param context
     */
    public CouchDBLite_2_0_Support(Context context) {
        this.context = context;
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
    public void saveMap(Map<String, Object> values) throws Exception {
        if(values == null) {
            return;
        }
        //
        MutableDocument mutableDocument = new MutableDocument();
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
            } else if(entry.getValue() instanceof Boolean) {
                mutableDocument.setBoolean(entry.getKey(), (Boolean) entry.getValue());
            } else if(entry.getValue() instanceof Date) {
                mutableDocument.setDate(entry.getKey(), (Date) entry.getValue());
            } else if(entry.getValue() instanceof Double) {
                mutableDocument.setDouble(entry.getKey(), (Double) entry.getValue());
            } else if(entry.getValue() instanceof Float) {
                mutableDocument.setFloat(entry.getKey(), (Float) entry.getValue());
            } else if(entry.getValue() instanceof Integer) {
                mutableDocument.setInt(entry.getKey(), (Integer) entry.getValue());
            } else if(entry.getValue() instanceof Long) {
                mutableDocument.setLong(entry.getKey(), (Long) entry.getValue());
            } else if(entry.getValue() instanceof Number) {
                mutableDocument.setNumber(entry.getKey(), (Number) entry.getValue());
            } else {
                mutableDocument.setValue(entry.getKey(), entry.getValue());
            }
        }
        //  Save document
        database.save(mutableDocument);
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
    public Map<String, Object> getMap(Criteria criteria) throws Exception {
        //  Validate metadata for query
        if(criteria == null) {
            return null;
        }
        //
        Query query;
        //  Add to where
        Expression expression = getExpressionFromCriteria(criteria);
        if(expression == null) {
            query = QueryBuilder
                    .select(SelectResult.all())
                    .from(DataSource.database(database));
        } else {
            query = QueryBuilder
                    .select(SelectResult.all())
                    .from(DataSource.database(database))
                    .where(expression);
        }
        //  Populate map
        ResultSet rs = query.execute();
        Result result = rs.next();
        if(result != null) {
            Dictionary attributes = result.getDictionary(DATABASE_NAME);
            Log.i("Sample", String.format("name -> %s", attributes.getString("name")));
            Log.i("Sample", String.format("type -> %s", attributes.getString("type")));
            return attributes.toMap();
        }
        //  Default
        return null;
    }

    private Expression getExpressionFromCriteria(Criteria criteria) {
        List<Condition> conditionList = criteria.getCriteriaList();
        if(conditionList.isEmpty()) {
            return null;
        }
        Expression expression = null;
        //  Add condition
        for(Condition condition : conditionList) {
            if(condition.getComparator().equals(Condition.EQUAL)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .equalTo(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .equalTo(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.LESS)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .lessThan(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .lessThan(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.LESS_EQUAL)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .lessThanOrEqualTo(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .lessThanOrEqualTo(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.GREATER)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .greaterThan(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .greaterThan(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.GREATER_EQUAL)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .greaterThanOrEqualTo(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .greaterThanOrEqualTo(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.BETWEEN)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .between(getExpressionValue(condition.getValue()), getExpressionValue(condition.getValueTo()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .between(getExpressionValue(condition.getValue()), getExpressionValue(condition.getValueTo())));
                }
            } else if(condition.getComparator().equals(Condition.GREATER_EQUAL)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .greaterThanOrEqualTo(getExpressionValue(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .greaterThanOrEqualTo(getExpressionValue(condition.getValue())));
                }
            } else if(condition.getComparator().equals(Condition.IN)) {
                if(expression == null) {
                    expression = Expression.property(condition.getKeyAttribute())
                            .in(getExpressionValueAsArray(condition.getValue()));
                } else {
                    expression.and(Expression.property(condition.getKeyAttribute())
                            .in(getExpressionValueAsArray(condition.getValue())));
                }
            }
            //  TODO: add support to like operator
        }
        //  Return
        return expression;
    }

    /**
     * Get expression values array
     * @param values
     * @return
     */
    private Expression[] getExpressionValueAsArray(Object... values) {
        if (values == null) {
            return null;
        }
        //  For all
        List<Expression> expressionValues = new ArrayList<Expression>();
        for (Object value : values) {
            expressionValues.add(getExpressionValue(value));
        }
        //  Convert
        return expressionValues.toArray(new Expression[expressionValues.size()]);
    }

    /**
     * Get expression for distinct values
     * @param value
     * @return
     */
    private Expression getExpressionValue(Object value) {
        Expression expression = null;
        //  Validate value
        if(value == null) {
            return expression;
        }
        //  For String
        if(value instanceof String)  {
            expression = Expression.string((String) value);
        } else if(value instanceof Boolean) {
            expression = Expression.booleanValue((Boolean) value);
        } else if(value instanceof Date) {
            expression = Expression.date((Date) value);
        } else if(value instanceof Double) {
            expression = Expression.doubleValue((Double) value);
        } else if(value instanceof Float) {
            expression = Expression.floatValue((Float) value);
        } else if(value instanceof Integer) {
            expression = Expression.intValue((Integer) value);
        } else if(value instanceof Long) {
            expression = Expression.longValue((Long) value);
        } else if(value instanceof Number) {
            expression = Expression.number((Number) value);
        } else {
            expression = Expression.value(value);
        }
        //  Default return
        return expression;
    }

    @Override
    public boolean isOpen() {
        return database != null;
    }
}
