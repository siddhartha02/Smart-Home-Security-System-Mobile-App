package edu.sprakas1uncc.iotproject;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
        //implements NavigationView.OnNavigationItemSelectedListener {
        DrawerLayout drawer;
    Toolbar toolbar;
    FragmentTransaction fra;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      //  FragmentTransaction fra;

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.openDrawer(GravityCompat.START);
        fra = getSupportFragmentManager().beginTransaction();
        fra.add(R.id.mainContainer,new HomeFragment());
        fra.commit();
        getSupportActionBar().setTitle("Home");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

         ;

            //@SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

                switch (id){
                    case (R.id.home):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new HomeFragment());

                        fra.commit();
                        getSupportActionBar().setTitle("Security System");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case (R.id.security_system):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new SecuritySystemFragment());
                        fra.commit();
                        getSupportActionBar().setTitle("Security System");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
//                    case (R.id.images):
//                        fra = getSupportFragmentManager().beginTransaction();
//                        fra.replace(R.id.mainContainer,new Image());
//                        fra.commit();
//                        getSupportActionBar().setTitle("Images");
//                        item.setChecked(true);
//                        drawer.closeDrawers();
//                        break;
                    case (R.id.locks):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Locks());
                        fra.commit();
                        getSupportActionBar().setTitle("Locks");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case (R.id.garage_doors):
                        Log.d("garade","garage doors");
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new garageDoors());
                        fra.commit();
                        getSupportActionBar().setTitle("Garage Doors");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case (R.id.lights):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Lights());
                        fra.commit();
                        getSupportActionBar().setTitle("Lights");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case (R.id.thermostats):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Thermostats());
                        fra.commit();
                        getSupportActionBar().setTitle("Thermostats");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case(R.id.energy):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Energy());
                        fra.commit();
                        getSupportActionBar().setTitle("Energy");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case(R.id.doorwindowsensor):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Door_windowSensors());
                        fra.commit();
                        getSupportActionBar().setTitle("Door/Window Sensors");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case(R.id.motionsensor):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new MotionSensor());
                        fra.commit();
                        getSupportActionBar().setTitle("Motion Sensor");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case(R.id.geo_services):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new GeoServices());
                        fra.commit();
                        getSupportActionBar().setTitle("Geo Service");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case(R.id.video):
                        fra = getSupportFragmentManager().beginTransaction();
                        fra.replace(R.id.mainContainer,new Video());
                        fra.commit();
                        getSupportActionBar().setTitle("Video");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                        default:
                            return false;


                }

                return false;


       //     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
           // drawer.closeDrawer(GravityCompat.START);

        }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
