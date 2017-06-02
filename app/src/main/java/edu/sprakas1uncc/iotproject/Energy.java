package edu.sprakas1uncc.iotproject;

import android.os.Bundle;
import android.os.Handler;
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
import java.util.Timer;
import java.util.TimerTask;


public class Energy extends Fragment {

    TextView energy_consume_therm;
    TextView energy_consume_lights;
Timer updateTimertherm;
Timer updateTimerlight;
    public static int energy_cons_thermostat=0;


    public Energy() {
        // Required empty public constructor
    }

    int temp = fetchData();
    int temp1 = fetchLightData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1 = inflater.inflate(R.layout.fragment_energy, container, false);
        energy_consume_therm =(TextView)v1.findViewById(R.id.readingthermo);
        energy_consume_lights =(TextView)v1.findViewById(R.id.readinglight);
        updateTimertherm = new Timer();
        updateTimerlight = new Timer();
        return v1;
    }

    @Override
    public void onStart(){
        super.onStart();
        updateTimertherm.scheduleAtFixedRate(new updateTask(new Handler(),this),0, 1000);
        updateTimerlight.scheduleAtFixedRate(new updateTask(new Handler(),this),0, 1000);
    }
    @Override
    public void onStop(){
        super.onStop();
        updateTimertherm.cancel();
        updateTimerlight.cancel();


    }
    public  void update(){
        energy_consume_therm.setText(Integer.toString(energy_cons_thermostat));
    }
    public  void update_light(){
        energy_consume_lights.setText(Integer.toString(Lights.consumption_light));
    }

    private class  updateTask extends TimerTask {
        Handler handler1;
        Energy energy;
        public updateTask(Handler handler1, Energy energy){
            super();
            this.handler1=handler1;
            this.energy=energy;
        }
        @Override
        public void run(){
            handler1.post(new Runnable() {
                @Override
                public void run() {
                    energy.update();
                    energy.update_light();
                }
            });

        }


    }

    public int  fetchData(){
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchEner.php";
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
           // Log.d("fetchdata", "inside fetch data");

            in.close();
          //  Log.d("fetchdata", "value"+result);
            String[] numbersArray = result.split(" ");
            //energy_cons_thermostat = Integer.parseInt(numbersArray[0])+Integer.parseInt(numbersArray[1]);
            energy_cons_thermostat = Integer.parseInt(numbersArray[0])+Integer.parseInt(numbersArray[1]);
            //int temp1 = Integer.parseInt(numbersArray[1]);
            //energy_cons_thermostat = temp+temp1;

        }
        catch (Exception e){
            // Log.d("fetchdata",e.getMessage());

        }
        return 0;
    }



    public int  fetchLightData(){
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchLightEnergy.php";
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
            // Log.d("fetchdata", "inside fetch data");

            in.close();
            //  Log.d("fetchdata", "value"+result);
            String[] numbersArray = result.split(" ");
            //energy_cons_thermostat = Integer.parseInt(numbersArray[0])+Integer.parseInt(numbersArray[1]);
            Lights.consumption_light= Integer.parseInt(numbersArray[0])+Integer.parseInt(numbersArray[1]);
            //int temp1 = Integer.parseInt(numbersArray[1]);
            //energy_cons_thermostat = temp+temp1;

        }
        catch (Exception e){
            // Log.d("fetchdata",e.getMessage());

        }
        return 0;
    }

}


