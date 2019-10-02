package cf.rittzyradio.ritzzyradio;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private DatabaseReference df;
    private RecyclerView rc;


    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        df = FirebaseDatabase.getInstance().getReference().child("RitzzyRadio").child("shows");
        rc = v.findViewById(R.id.rc_dashboard);
        rc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<Shows> options = new FirebaseRecyclerOptions.Builder<Shows>().setQuery(df,Shows.class).build();
        FirebaseRecyclerAdapter<Shows,Viewholder> adapter = new FirebaseRecyclerAdapter<Shows, Viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull Shows model) {
            holder.setImage(model.getLink());
            }

            @NonNull
            @Override
            public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v;
                v= LayoutInflater.from(getContext()).inflate(R.layout.show_card,viewGroup,false);
                return new Viewholder(v);
            }
        };
        rc.setAdapter(adapter);
        try{
            adapter.startListening();

        }catch (Exception e){
            Log.e("DASHBOARD","EXCEPTION IN START LISTENING");
            e.printStackTrace();
        }

        return v;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView image;
        private View v;
         Viewholder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            image = v.findViewById(R.id.showcardimage);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        void setImage(String link){
            Picasso.get().load(link).into(image);
        }
    }

}
