package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeoServices extends Fragment {
    public static String status;
    public static String temp;


    public GeoServices() {
        // Required empty public constructor
    }
int temp1 =fetchData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_geo_services, container, false);
        final TextView wstatus = (TextView) v.findViewById(R.id.weatherstatus);
        final TextView wtemp = (TextView) v.findViewById(R.id.weathertemp);
        wstatus.setText(status);
        wtemp.setText(temp);

        return v;
    }



    public int fetchData() {
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchWeather.php";
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
            while ((g = in.readLine()) != null) {

                result += g;

            }
            Log.d("fetchdata", "inside fetch data");

            in.close();
            Log.d("fetchdata", "value" + result);
            String[] numbersArray = result.split(" ");
            temp = numbersArray[0];
            status = numbersArray[1];



        } catch (Exception e) {

        }

        return 0;

    }


}
