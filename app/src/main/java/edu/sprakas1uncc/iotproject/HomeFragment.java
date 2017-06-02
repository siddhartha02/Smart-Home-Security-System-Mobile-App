package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView garage_status = (TextView) v.findViewById(R.id.statusgarage);
        final TextView secsys_status = (TextView) v.findViewById(R.id.statussecsys);
        final TextView lock_status = (TextView) v.findViewById(R.id.statuslocked);

        if(Locks.lock_status_garage.equalsIgnoreCase("unlocked"))
            garage_status.setText("unlocked");
        else if(Locks.lock_status_garage.equalsIgnoreCase("locked"))
        garage_status.setText("locked");
        else
        garage_status.setText("no status");

        return v;
    }

}
