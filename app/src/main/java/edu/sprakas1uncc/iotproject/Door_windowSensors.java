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
public class Door_windowSensors extends Fragment {
    public static  String main_sensor_status = "off";
    public static  String upstair_sensor_status = "off";


    public Door_windowSensors() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fetchData();
        View v = inflater.inflate(R.layout.fragment_door_window_sensors, container, false);
        ToggleButton toggle = (ToggleButton) v.findViewById(R.id.dwsensormain);
        ToggleButton toggle1 = (ToggleButton) v.findViewById(R.id.dwsensorup);
        if(main_sensor_status.equalsIgnoreCase("on")){
            toggle.setChecked(true);
        }
        else
        toggle.setChecked(false);
        if(upstair_sensor_status.equalsIgnoreCase("on")){
            toggle1.setChecked(true);
        }
        else
            toggle.setChecked(false);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("toggle button","Button is ON");
                    main_sensor_status = "on";
                    setMainData(main_sensor_status);

                } else {
                    // The toggle is disabled
                    Log.d("toggle button", "Button is OFF");
                    main_sensor_status = "off";
                    setMainData(main_sensor_status);
                }
            }
        });


        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.d("toggle button","Button is ON");
                    upstair_sensor_status= "on";
                    setUpstairData(upstair_sensor_status);
                } else {
                    // The toggle is disabled
                    Log.d("toggle button", "Button is OFF");
                    upstair_sensor_status= "off";
                    setUpstairData(upstair_sensor_status);
                }
            }
        });

        return v;
    }

    public void  fetchData(){
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchDoorSensor.php";
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
            main_sensor_status = numbersArray[0];
            upstair_sensor_status = numbersArray[1];
            //lock_status_garage = numbersArray[2];





        }
        catch (Exception e){
            // Log.d("fetchdata",e.getMessage());

        }

    }


    public void setUpstairData(String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setUpDoorStatus.php";
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



    public void setMainData(String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setMainDoorSensor.php";
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
