package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class garageDoors extends Fragment {


    public garageDoors() {
        // Required empty public constructor
    }

    public static String lock_status_1door = "unlocked";
    public static String lock_status_2door = "unlocked";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fetchData();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_garage_doors, container, false);

        ToggleButton toggle1 = (ToggleButton) v.findViewById(R.id.garagelockbutton);
        ToggleButton toggle = (ToggleButton) v.findViewById(R.id.backdoorbutton);
        //ToggleButton toggle2 = (ToggleButton) v.findViewById(R.id.frontButton);

Log.d("lock1 stat",lock_status_1door);
        Log.d("lock2 stat",lock_status_2door);

        if(lock_status_1door.equalsIgnoreCase("locked")){
            toggle.setChecked(true);
        }
        if(lock_status_2door.equalsIgnoreCase("locked")){
            toggle1.setChecked(true);
        }


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("toggle button","Button is ON");
                    lock_status_1door = "locked";
                   setGarage1doorData(lock_status_1door);

                } else {
                    // The toggle is disabled
                    Log.d("toggle button", "Button is OFF");
                    lock_status_1door = "unlocked";
                    setGarage1doorData(lock_status_1door);
                }
            }
        });


        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("toggle button","Button is ON");
                    lock_status_2door= "locked";
                    setBack2doorData(lock_status_2door);
                } else {
                    // The toggle is disabled
                    Log.d("toggle button", "Button is OFF");
                    lock_status_2door= "unlocked";
                    setBack2doorData(lock_status_2door);
                }
            }
        });




        return v;

    }

    public void  fetchData(){
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchGarage.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            //1
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            //2
            DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
            dataOut.writeBytes(data);
            //3
            dataOut.flush();
            dataOut.close();
            DataInputStream in = new DataInputStream(conn.getInputStream());

            String g;
            while((g = in.readLine()) != null){

                result += g;

            }
            Log.d("fetchdata", "inside fetch data");

            in.close();
            Log.d("fetchdata", "value"+result);
            String[] numbersArray = result.split(" ");
            lock_status_2door = numbersArray[0];
            lock_status_1door = numbersArray[1];
            //lock_status_garage = numbersArray[2];





        }
        catch (Exception e){
            // Log.d("fetchdata",e.getMessage());

        }

    }



    public void setGarage1doorData(String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/set1door.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            String data1 = " " + java.net.URLEncoder.encode(x, "UTF-8");
            //1
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            //2
            DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
            dataOut.writeBytes(data);
            dataOut.writeBytes(data1);
            //3
            dataOut.flush();
            dataOut.close();
            DataInputStream in = new DataInputStream(conn.getInputStream());

            String g;
            while ((g = in.readLine()) != null) {

                result += g;

            }
            Log.d("fetchdata", "inside fetch data");

            in.close();
            //Log.d("fetchdata", "value" + result);
            //String[] numbersArray = result.split(" ");
            // current_temp_upstairs = Integer.parseInt(numbersArray[0]);
            // current_temp_main = Integer.parseInt(numbersArray[1]);
        }
        catch (Exception e){

        }


    }



    public void setBack2doorData(String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/set2door.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            String data1 = " " + java.net.URLEncoder.encode(x, "UTF-8");
            //1
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            //2
            DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
            dataOut.writeBytes(data);
            dataOut.writeBytes(data1);
            //3
            dataOut.flush();
            dataOut.close();
            DataInputStream in = new DataInputStream(conn.getInputStream());

            String g;
            while ((g = in.readLine()) != null) {

                result += g;

            }
            Log.d("fetchdata", "inside fetch data");

            in.close();
            //Log.d("fetchdata", "value" + result);
            //String[] numbersArray = result.split(" ");
            // current_temp_upstairs = Integer.parseInt(numbersArray[0]);
            // current_temp_main = Integer.parseInt(numbersArray[1]);
        }
        catch (Exception e){

        }


    }

    public void setFrontData(String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setLocks.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            String data1 = " " + java.net.URLEncoder.encode(x, "UTF-8");
            //1
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            //2
            DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
            dataOut.writeBytes(data);
            dataOut.writeBytes(data1);
            //3
            dataOut.flush();
            dataOut.close();
            DataInputStream in = new DataInputStream(conn.getInputStream());

            String g;
            while ((g = in.readLine()) != null) {

                result += g;

            }
            Log.d("fetchdata", "inside fetch data");

            in.close();
            //Log.d("fetchdata", "value" + result);
            //String[] numbersArray = result.split(" ");
            // current_temp_upstairs = Integer.parseInt(numbersArray[0]);
            // current_temp_main = Integer.parseInt(numbersArray[1]);
        }
        catch (Exception e){

        }


    }



    }

