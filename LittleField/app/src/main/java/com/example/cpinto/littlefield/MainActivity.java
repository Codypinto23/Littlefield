package com.example.cpinto.littlefield;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    //For map functionalty
    public static FragmentManager fragmentManager;
    private String mTitle="Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager(). You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getFragmentManager();

        //Create view of fragment
        displayView(0);
    }

    protected void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (position) {
            case 0:
                mTitle = "LittleField";
                fragment = new MainActivityFragment();
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .add(R.id.frame_container_1, fragment, "Welcome")
                        .addToBackStack("Welcome")
                        .commit();
                break;
        }
    }// end display view


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


    //BACK LISTENER
    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }

    }// end back Pressed
}
