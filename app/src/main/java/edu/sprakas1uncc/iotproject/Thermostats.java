package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.net.URLEncoder;



/**
 * A simple {@link Fragment} subclass.
 */
public class Thermostats extends Fragment {
int counter=0;
    public static String temp_mode = "";
    public static  String fan_mode = "";
    public static String temp_mode_main = "";
    public static  String fan_mode_main = "";
    public static int control_temp_upstairs = 0;
    public static int control_temp_main = 0;
    public static int current_temp_upstairs;
    public static int current_temp_main;

    public static String mode = "";

    TextView textViewcurrent;
    Timer updateTimer;
TextView textViewcurrent1;
    public Thermostats() {
        // Required empty public constructor
    }

    int temp = fetchData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thermostats, container, false);

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiogroupthermmode);
        RadioGroup radioGroupfan = (RadioGroup) v.findViewById(R.id.radiogroupthermfan);
        RadioGroup radioGroupmain = (RadioGroup) v.findViewById(R.id.radiogroupthermmodemain);
        RadioGroup radioGroupfanmain = (RadioGroup) v.findViewById(R.id.radiogroupthermfanmain);
        RadioButton radioButtonUpHeat = (RadioButton) v.findViewById(R.id.heat);
        RadioButton radioButtonUpCool = (RadioButton) v.findViewById(R.id.cool);
        RadioButton radioButtonUpOff = (RadioButton) v.findViewById(R.id.off);
        RadioButton radioButtonMainHeat = (RadioButton) v.findViewById(R.id.heatmain);
        RadioButton radioButtonMainCool = (RadioButton) v.findViewById(R.id.coolmain);
        RadioButton radioButtonMainOff = (RadioButton) v.findViewById(R.id.offmain);
        final TextView textViewupstair = (TextView) v.findViewById(R.id.controltemp);
        final SeekBar myseekbarupstair = (SeekBar) v.findViewById(R.id.seekbarcurrenttemp);
        final TextView textViewmain = (TextView) v.findViewById(R.id.controltempmain);
        final SeekBar myseekbarmain = (SeekBar) v.findViewById(R.id.seekbarcurrenttempmain);
         textViewcurrent = (TextView) v.findViewById(R.id.currenttempval);
        textViewcurrent1 =(TextView)v.findViewById(R.id.currenttempvalmain) ;
        if(temp_mode.equalsIgnoreCase("heat")){
            radioButtonUpHeat.setChecked(true);
            Log.d("rb up","heatmode up");
            Log.d("rb up",temp_mode);



        }
        if(temp_mode.equalsIgnoreCase("cool")){
            radioButtonUpCool.setChecked(true);

        }
        if(temp_mode.equalsIgnoreCase("off")){
            radioButtonUpOff.setChecked(true);

        }
        if(temp_mode_main.equalsIgnoreCase("heat")){
            radioButtonMainHeat.setChecked(true);
            Log.d("rb main","heatmode main");
            Log.d("rb main",temp_mode_main);

        }
        if(temp_mode_main.equalsIgnoreCase("cool")){
            radioButtonMainCool.setChecked(true);

        }
        if(temp_mode_main.equalsIgnoreCase("off")){
            radioButtonMainOff.setChecked(true);

        }

    updateTimer = new Timer();

        //radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                if (null != rb && checkedId > -1) {
                    temp_mode = "undefined mode upstairs";
                }
                if  (checkedId == R.id.heat){
                    temp_mode = "heat";
                    mode = temp_mode;
                    setThermModeup(temp_mode);

                }
                if (checkedId == R.id.cool){
                    temp_mode = "cool";
                    mode = temp_mode;
                    setThermModeup(temp_mode);
                    Log.d ("temp mode", "cool");
                }
                if (checkedId == R.id.off){
                    temp_mode = "off";
                    setThermModeup(temp_mode);
                }
             //   textViewcurrent.setText(Integer.toString(current_temp_upstairs));

            }
        });


        //radioGroupfan.clearCheck();
        radioGroupfan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    fan_mode = "undefined mode upstairs fan";
                }
                if  (checkedId == R.id.auto){
                    temp_mode = "auto";
                    Log.d("fan mode", "auto");
                }
                if (checkedId == R.id.fanoff){
                    temp_mode = "off";
                }

            }
        });


        //radioGroupmain.clearCheck();
        radioGroupmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    temp_mode_main = "undefined mode upstairs";
                }
                if  (checkedId == R.id.heatmain){
                    temp_mode_main = "heat";
                    setThermModemain(temp_mode_main);
                }
                if (checkedId == R.id.coolmain){
                    temp_mode_main = "cool";
                    Log.d ("temp mode main", "coolmain");
                    setThermModemain(temp_mode_main);
                }
                if (checkedId == R.id.offmain){
                    temp_mode_main = "off";
                    setThermModemain(temp_mode_main);
                }



            }
        });


       // radioGroupfanmain.clearCheck();
        radioGroupfanmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    fan_mode_main = "undefined mode upstairs fan";
                }
                if  (checkedId == R.id.automain){
                    temp_mode_main = "auto";
                    Log.d("fan mode main", "auto");
                }
                if (checkedId == R.id.fanoffmain){
                    temp_mode_main = "off";
                }

            }
        });

        myseekbarupstair.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //if (status) {
                control_temp_upstairs = progress;

                Log.d("seekbar", "reached pregress change");
                textViewupstair.setText(Integer.toString(control_temp_upstairs));
                setThermDataup(control_temp_upstairs);
                //  }
                // else
                // textView.setText("");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        myseekbarmain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //if (status) {
                control_temp_main = progress;

                Log.d("seekbar", "reached pregress change");
                textViewmain.setText(Integer.toString(control_temp_main));
                setThermData(control_temp_main);
                //  }
                // else
                // textView.setText("");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        return v;
    }

@Override
public void onStart(){
    super.onStart();
    updateTimer.scheduleAtFixedRate(new updateTask(new Handler(),this),0, 1000);
}
    @Override
    public void onStop(){
        super.onStop();
        updateTimer.cancel();

    }


    public  void update(){
       textViewcurrent.setText(Integer.toString(current_temp_upstairs));
        textViewcurrent1.setText(Integer.toString(current_temp_main));



      //  textViewcurrent.setText(String.valueOf(++counter));
    }
private class  updateTask extends TimerTask{
   Handler handler;
    Thermostats thermostats;
    public updateTask(Handler handler, Thermostats thermostats){
        super();
        this.handler=handler;
        this.thermostats=thermostats;
    }
    @Override
    public void run(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                thermostats.update();
            }
        });

    }


}

    public int  fetchData(){
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchTher.php";
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
            current_temp_upstairs = Integer.parseInt(numbersArray[0]);
            current_temp_main = Integer.parseInt(numbersArray[1]);
            temp_mode = numbersArray[2];
            temp_mode_main = numbersArray[3];




        }
        catch (Exception e){
           // Log.d("fetchdata",e.getMessage());

        }
        return 0;
    }

    public void setThermData(int x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setTher.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            String data1 = " " + java.net.URLEncoder.encode(Integer.toString(x), "UTF-8");
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


    public void setThermDataup (int x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setTherup.php";
            URL urlObj = new URL(url);
            String result = "";
            String data = "cId=" + java.net.URLEncoder.encode(cId, "UTF-8");
            String data1 = " " + java.net.URLEncoder.encode(Integer.toString(x), "UTF-8");
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
    public void setThermModeup (String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setTherModeup.php";
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

    public void setThermModemain (String x){

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setTherModemain.php";
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
