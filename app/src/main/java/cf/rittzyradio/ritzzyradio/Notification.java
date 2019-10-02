package cf.rittzyradio.ritzzyradio;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {
    private String desc ="Do you ever feel that you are getting bored and have nothing to do, do you ever feel in the need of doing something interesting then you are in the right place.\n" +
            " RITZZY RADIO  is the one spot to take all your boredom away, be it your interest in poetry, songs, stories or pranks. We've got it all. With us 'The Voice Is All Yours'.\n";
    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        Element e = new Element();
        e.setTitle("Version 1.3 \n\nMade With Love in LPU");
        View about = new AboutPage(getContext()).isRTL(false).setDescription(desc).addEmail("ritzzyradio@gmail.com").addInstagram("ritzzy_radio").addFacebook("ritzzyradio").setImage(R.drawable.rr).addItem(e).create();
        return about;

    }

}
