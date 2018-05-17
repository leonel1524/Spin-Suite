package org.erpya.spinsuite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.erpya.database.support.CouchDBLite_2_0_Support;
import org.erpya.device.util.DeviceManager;
import org.erpya.device.util.DeviceTypeHandler;
import org.erpya.device.util.IDevice;
import org.erpya.printing.TestPrinter;
import org.erpya.printing.honeywell.supported.DatamaxApex2;
import org.erpya.spinsuite.base.model.GenericPO;
import org.erpya.spinsuite.base.model.PO;
import org.erpya.spinsuite.base.util.Env;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Env.setCurrentSupportedDatabase(CouchDBLite_2_0_Support.class.getName());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    PO po = new GenericPO(getApplicationContext(), "Test");
//                    po.setIsActive(true);
//                    po.setValue("Table", "Hola");
//                    po.saveEx();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                Env.getInstance(getApplicationContext());
                DeviceManager
                        .getInstance()
                        .addDeviceType(new DatamaxApex2());
                //
                TestPrinter printer = new TestPrinter(getApplicationContext());
                printer.run();
                Snackbar.make(view, "Replace with your own action: ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
