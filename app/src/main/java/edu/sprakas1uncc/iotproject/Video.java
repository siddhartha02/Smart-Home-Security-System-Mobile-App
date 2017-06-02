package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
//import android.media.session.MediaController;


/**
 * A simple {@link Fragment} subclass.
 */
public class Video extends Fragment {


    public Video() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentharsh
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        VideoView vidView = (VideoView)v.findViewById(R.id.myVideo);

        String vidAddress = "http://10.0.0.3/v1.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
       MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(vidView);
        vidView.setMediaController(mediaController);
        vidView.start();
        return  v;
    }

}
