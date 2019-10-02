package cf.rittzyradio.ritzzyradio;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private RecyclerView rc,crc;
   // private ScaleTextView sc;
    //private LineBarVisualizer vc;
    //private RadioManager mRadioManager;
    private ImageView mplaypause;
    private boolean readylpu,readysitapur,start;
    private Button lpuradio,sitapurradio,sch_lpu,sch_sita;
    private static final String URL ="https://www.ritzzyradio.tk/radio/8000/radio.mp3";
    private static final String URL1 ="https://www.ritzzyradio.tk/radio/8010/radio.mp3";
    private DatabaseReference mQuery,mQuery1;
    private MediaPlayer mplpu;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_home2, container, false);

        rc = layout.findViewById(R.id.recycler_ads);
        //vc = layout.findViewById(R.id.visualizer);
        //sc = layout.findViewById(R.id.ani_text);
       // crc = layout.findViewById(R.id.crc);
        lpuradio = layout.findViewById(R.id.buttonradiolpu);
        sitapurradio = layout.findViewById(R.id.buttonradiositapur);
        sch_lpu =layout.findViewById(R.id.scheduleoflpu);
        sch_sita = layout.findViewById(R.id.scheduleofsita);
        sch_lpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasConnection())
                startActivity(new Intent(getContext(),ScheduleLPU.class));
                else
                    startActivity(new Intent(getContext(),NoInternet.class));
            }
        });
        sch_sita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasConnection())
                startActivity(new Intent(getContext(),ScheduleSitapur.class));
                else
                    startActivity(new Intent(getContext(),NoInternet.class));
            }
        });
        lpuradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasConnection()){
                Intent intent = new Intent(getContext(),Stream_Link.class);
                intent.putExtra("stream",true);
                startActivity(intent);
            }
            else{
                    startActivity(new Intent(getContext(),NoInternet.class));
            }}
        });

      /*        sitapurradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasConnection()) {
                    Intent intent = new Intent(getContext(), Stream_Link.class);
                    intent.putExtra("stream", false);
                    startActivity(intent);
                }
                else{
                    startActivity(new Intent(getContext(),NoInternet.class));
                }
            }
        });*/
       // mplaypause = layout.findViewById(R.id.playpause);
//        crc.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        //rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mQuery = FirebaseDatabase.getInstance().getReference().child("RitzzyRadio").child("ads");
        mQuery1 = FirebaseDatabase.getInstance().getReference().child("RitzzyRadio").child("Circular_Sponsors");
        mQuery.keepSynced(true);
        mQuery1.keepSynced(true);
        //sc.animateText("Ritzzy Radio is LPU's Online Radio!");
       // sc.animate();
        FirebaseRecyclerOptions<Sponsors> options = new FirebaseRecyclerOptions.Builder<Sponsors>().setQuery(mQuery,Sponsors.class).build();
        FirebaseRecyclerAdapter<Sponsors,SponsorsHolder> Adapter = new FirebaseRecyclerAdapter<Sponsors,SponsorsHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SponsorsHolder holder, int position, @NonNull Sponsors model) {
                holder.setlink(model.getLink());
            }

            @NonNull
            @Override
            public SponsorsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.ads_card,viewGroup,false);
                return new SponsorsHolder(v);
            }
        };
        rc.setAdapter(Adapter);
        try{
            Adapter.startListening();
        }catch (Exception ex){
            Toast.makeText(getContext(), ""+ex, Toast.LENGTH_SHORT).show();
        }

        /*FirebaseRecyclerOptions<Circular_Sponsors> options1 = new FirebaseRecyclerOptions.Builder<Circular_Sponsors>().setQuery(mQuery1,Circular_Sponsors.class).build();
        FirebaseRecyclerAdapter<Circular_Sponsors,CircularSponsorsViewHolder> adapter1 = new FirebaseRecyclerAdapter<Circular_Sponsors, CircularSponsorsViewHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull CircularSponsorsViewHolder holder, int position, @NonNull Circular_Sponsors model) {
                holder.setlink(model.getLink());
            }

            @NonNull
            @Override
            public CircularSponsorsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.circular_sponsors,viewGroup,false);
                return new CircularSponsorsViewHolder(v);
            }
        };
         crc.setAdapter(adapter1);
        try {
            adapter1.startListening();
        }catch (Exception e){
            e.printStackTrace();
        }*/


/*        mplaypause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (start) {
                    if (!ready) {
                        mplaypause.setImageResource(R.drawable.pause);
                        if (firsttime) {
                            mp.start();

                        } else {
                            if (!mp.isPlaying())
                                mp.start();
                        }
                        ready = true;
                    } else {
                        mplaypause.setImageResource(R.drawable.playmobile);
                        if (mp.isPlaying())
                            mp.pause();
                        ready = false;
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please Wait while Stream is Loading!", Toast.LENGTH_SHORT).show();
                }
            }

            });

*/

               //Toast.makeText(getActivity().getApplicationContext(),"Thread runs", Toast.LENGTH_SHORT).show();


              /* mplpu.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   @Override
                   public void onPrepared(MediaPlayer mediaPlayer) {
                       Toast.makeText(getContext(), "ready", Toast.LENGTH_SHORT).show();
                       start=true;
                       //ready=true;
                       lpuradio.setText("Play Now");
                       readylpu=true;
                   }
               });

               mplpu.setOnErrorListener(new MediaPlayer.OnErrorListener(){
                   @Override
                   public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {

                       Toast.makeText(getContext(), "Try Preparing The stream again", Toast.LENGTH_SHORT).show();
                       return false;
                   }
               });
*/


                return layout;



    }



    public class SponsorsHolder extends RecyclerView.ViewHolder{
        private ImageView _image_ad;
        private View v;
        SponsorsHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            _image_ad = v.findViewById(R.id.image_ad);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        private void setlink(String link){
            Picasso.get().load(link).placeholder(R.drawable.placeholder).into(_image_ad);
        }
    }

    public class CircularSponsorsViewHolder extends RecyclerView.ViewHolder{
        private ImageView _image_ad;
        private View v;
        CircularSponsorsViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            _image_ad = v.findViewById(R.id.circular_sponsors);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        private void setlink(String link){
            Picasso.get().load(link).into(_image_ad);
        }
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }






}
