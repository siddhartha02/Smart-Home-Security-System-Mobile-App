package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecuritySystemFragment extends Fragment {

public static String sys_status = "off";
    public SecuritySystemFragment() {
        // Required empty public constructor
    }
int trmp = fetchData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_security_system, container, false);
        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiogroup);
        RadioButton radioButtonAs = (RadioButton) v.findViewById(R.id.armedstay);
        RadioButton radioButtonAa = (RadioButton) v.findViewById(R.id.armedaway);
        RadioButton radioButtond = (RadioButton) v.findViewById(R.id.disarmed);

        Log.d("sec sys", sys_status);
        if(sys_status.equalsIgnoreCase("armed_away ")){
            Log.d("sec sys", sys_status);
            radioButtonAa.setChecked(true);

        }
        if(sys_status.equalsIgnoreCase("armed_stay ")){
            radioButtonAs.setChecked(true);
            Log.d("sec sys", sys_status);
        }
        if(sys_status.equalsIgnoreCase("disarmed ")){
            radioButtond.setChecked(true);
            Log.d("sec sys", sys_status);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Log.d("radio button", "not pressed");
                    String sys_status = "off";
                                    }
                if  (checkedId == R.id.armedaway){
                    Log.d("radio", "armedaway");
                    sys_status = "armed_away";

                    setSecurityData(sys_status);
                }
                if (checkedId == R.id.armedstay){
                    Log.d("radio","armedstay");
                    sys_status = "armed_stay";

                    setSecurityData(sys_status);
                }
                if (checkedId == R.id.disarmed){
                    Log.d("radio","disarmed");
                    sys_status = "disarmed";

                    setSecurityData(sys_status);
                }

            }
        });
        return v;
    }

    public int fetchData() {
        try {

            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/fetchSecurity.php";
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
            sys_status = result;



        } catch (Exception e) {

        }

        return 0;

    }

    public void setSecurityData(String x) {

        try {
            int temp_curr;
            String cId = "1";
            String url = "http://10.0.0.3/setSecurity.php";
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
        } catch (Exception e) {

        }
    }


}
















