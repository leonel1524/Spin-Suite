/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                                                *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
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
package org.erpya.component.wizard;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.erpya.component.R;
import org.erpya.component.base.CustomPagerAdapter;
import org.erpya.component.base.CustomViewPager;
import org.erpya.component.base.IWizardStep;

/**
 * Dynamic wizard
 */
public abstract class Wizard extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private CustomPagerAdapter sectionsPagerAdapter;
    /** Previous Action */
    private Button previousAction;
    /** Next Action */
    private Button nextAction;
    /** Is last action  */
    private boolean isLastAction;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private CustomViewPager viewPagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wizard);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.setArguments(getIntent().getExtras());
        //  Get Previous Action
        previousAction = findViewById(R.id.PreviousAction);
        previousAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousAction();
            }
        });
        //  Get next Action
        nextAction = findViewById(R.id.NextAction);
        nextAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAction();
            }
        });
        //  Add Steps
        initWizard();
        // Set up the ViewPager with the sections adapter.
        viewPagerController = findViewById(R.id.container);
        viewPagerController.setAdapter(sectionsPagerAdapter);
        viewPagerController.setEnableScroll(false);
        //  Add listener
        viewPagerController.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeViewText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //  Default
        changeViewText(0);
    }

    /**
     * Change view text from position
     * @param position
     */
    private void changeViewText(int position) {
        //  for previous action
        if(position == 0) {
            previousAction.setVisibility(View.INVISIBLE);
        } else {
            previousAction.setVisibility(View.VISIBLE);
        }
        //  For next action
        if((position + 1) == sectionsPagerAdapter.getCount()) {
            nextAction.setText(R.string.Action_Finish);
            isLastAction = true;
        } else {
            nextAction.setText(R.string.Action_Next);
            isLastAction = false;
        }
    }

    /**
     * Previous Action
     */
    private void previousAction() {
        viewPagerController.setCurrentItem(viewPagerController.getCurrentItem() - 1);
    }

    /**
     * Next Action
     */
    private void nextAction() {
        int currentStep = viewPagerController.getCurrentItem();
        GenericWizardStep step = (GenericWizardStep) sectionsPagerAdapter.getStepDefinition(currentStep);
        boolean isValid = step.validateIt();
        if(step.isMandatory()) {
            if(isValid) {
                viewPagerController.setCurrentItem(viewPagerController.getCurrentItem() + 1);
            }
        } else {
            viewPagerController.setCurrentItem(viewPagerController.getCurrentItem() + 1);
        }
        //  Close if is last
        if((currentStep + 1) == sectionsPagerAdapter.getCount()
                && isLastAction) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wizard, menu);
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

    /**
     * Add step for view
     * @param step
     */
    public void addStep(IWizardStep step) {
        sectionsPagerAdapter.addStep(step);
    }

    /**
     * Initialize here child
     * Add all Steps
     */
    public abstract void initWizard();
}
