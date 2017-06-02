package edu.sprakas1uncc.iotproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

/**
 * Created by harshvardhan on 10/19/16.
 */

public class LIghtQueryThread implements Runnable {
    Thread thread = new Thread(this);
    public LIghtQueryThread()
    {
        thread.start();
    }



    public void run()
    {

        if(Lights.status){
            Lights.status = false;

            Log.d ("Light","Lights are OFF!!");
        }
        else {
            Lights.status = true;

            Log.d("Light", "Lights are On!!");
        }

    }
}
