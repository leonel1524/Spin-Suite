package org.erpya.app.spinsuite;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.erpya.base.database.support.CouchDBLite_2_0_Support;
import org.erpya.component.factory.FieldFactory;
import org.erpya.base.util.DisplayType;
import org.erpya.base.util.Env;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Env.getInstance(getApplicationContext());
        Env.setCurrentSupportedDatabase(CouchDBLite_2_0_Support.class.getName());
        //  Add dynamic
        ConstraintLayout parent = (ConstraintLayout) (ConstraintLayout) findViewById(R.id.parent);
        if(parent != null) {
            parent.addView(FieldFactory
                    .createField(this)
                    .withName("Test Label")
                    .withReadOnly(false)
                    .withUpdateable(true)
                    .withMandatory(true)
                    .withDisplayType(DisplayType.DATE)
                    .getFieldComponent(), 0, parent.getLayoutParams());
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
