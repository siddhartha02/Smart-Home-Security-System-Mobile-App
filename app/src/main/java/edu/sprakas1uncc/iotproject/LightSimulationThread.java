package edu.sprakas1uncc.iotproject;

/**
 * Created by harshvardhan on 10/23/16.
 */

public class LightSimulationThread extends Lights implements Runnable {

    Thread thread = new Thread(this);
    public LightSimulationThread()
    {
        thread.start();
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Lights.status){
              Lights.consumption_light++;

            }
        }


    }




}
