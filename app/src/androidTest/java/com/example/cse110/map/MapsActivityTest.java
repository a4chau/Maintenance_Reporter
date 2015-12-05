package com.example.cse110.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.robotium.solo.Solo;
import junit.framework.Assert;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AndrewK on 12/4/2015.
 */
public class MapsActivityTest extends ActivityInstrumentationTestCase2<MapsActivity> {
    private Solo solo;

    public MapsActivityTest(){
        super(MapsActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    /**Test Scenario:
     Given I just opened up the app on my phone
     when I’m on the main activity page with the map
     then I should see pins in the locations of all buildings that have valid reports on them
     and when I click "New Report" button
     then I am directed to the report page
     and when I fill in all the fields but something is invalid and click report
     then the report won't go through and I will remain on that page
     and When I click the back button
     then I go back to Maps activity page
     and when I click "New Report" button
     then I am directed to the report page
     and when I fill in all the fields with valid input and click report
     then current page closes to maps activity
     when I am back on main activity page and I click on the pin where the building I reported is
     then I will see an information label that shows all rooms for that building that have valid reports on them
     and when I click on the information label
     then a new activity should come up with all the detailed reports for those rooms**/

    public void testMapsActivityFunctions() throws Exception {
        /* Given I just opened up the app on my phone when I’m on the main activity page with the map
        then I should see pins in the locations of all buildings that have valid reports on them*/
        solo.assertCurrentActivity("Current Activity", MapsActivity.class);

        //when I click "New Report" button then I am directed to the report page
        solo.clickOnButton("New Report");
        solo.assertCurrentActivity("Current Activity", MainActivity2Activity.class);

        /*when I fill in all the fields but something is invalid and click report then
        the report won't go through and I will remain on that page*/
        solo.enterText((EditText) solo.getView(R.id.buildingName), "Center Hall");
        solo.enterText((EditText) solo.getView(R.id.roomNumber), "1001");
        solo.enterText((EditText) solo.getView(R.id.problem), "projector doesn't work");
        solo.clickOnButton("Report");
        solo.assertCurrentActivity("Current Activity", MainActivity2Activity.class);

        /*When I click the back button then I go back to Maps activity page */
        solo.goBack();
        solo.assertCurrentActivity("Current Activity", MapsActivity.class);

        //when I click "New Report" button then I am directed to the report page
        solo.clickOnButton("New Report");
        solo.assertCurrentActivity("Current Activity", MainActivity2Activity.class);

        /*when I fill in all the fields with valid input and click report then
        current page closes to main activity*/
        solo.enterText((EditText) solo.getView(R.id.buildingName), "Center Hall");
        solo.enterText((EditText) solo.getView(R.id.roomNumber), "101");
        solo.enterText((EditText) solo.getView(R.id.problem), "projector doesn't work");
        solo.clickOnButton("Report");
        solo.waitForActivity(MapsActivity.class, 2000);
        solo.assertCurrentActivity("Current Activity", MapsActivity.class);
        /* when i am back on main activity page and I click on the pin where the building I reported
        is then I will see an information label that shows all rooms for that building that have
        valid reports on them*/
        solo.clickOnScreen(600,850);
        solo.sleep(5000);
       /* and when I click on the information label
        then a new activity should come up with all the detailed reports for those rooms*/
        solo.clickOnScreen(600, 700);
        solo.assertCurrentActivity("Current Activity", MainActivity3Activity.class);
        solo.sleep(5000);

    }
}
