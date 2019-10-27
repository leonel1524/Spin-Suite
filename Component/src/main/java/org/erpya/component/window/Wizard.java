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
package org.erpya.component.window;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.erpya.component.R;

public class Wizard extends WindowManager {

    /** Previous Action */
    private Button previousAction;
    /** Next Action */
    private Button nextAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_window);
//        CustomPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.container);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void setupTabs() {

    }

    @Override
    public void setupActions() {
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
    }

    @Override
    protected void changeTabAction(int position) {
        //  for previous action
        if(position == 0) {
            previousAction.setVisibility(View.INVISIBLE);
        } else {
            previousAction.setVisibility(View.VISIBLE);
        }
        //  For next action
        if(isLastAction()) {
            nextAction.setText(R.string.Action_Finish);
        } else {
            nextAction.setText(R.string.Action_Next);
        }
    }
}