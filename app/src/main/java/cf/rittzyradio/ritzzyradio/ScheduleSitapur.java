package cf.rittzyradio.ritzzyradio;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScheduleSitapur extends AppCompatActivity {
    private RecyclerView rc;
    private DatabaseReference rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scedule_sitapur);

        rc = findViewById(R.id.sch_lpu_sita);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rf = FirebaseDatabase.getInstance().getReference().child("RitzzyRadio").child("schedule_sita");
        FirebaseRecyclerOptions<schsitamodel> options = new FirebaseRecyclerOptions.Builder<schsitamodel>().setQuery(rf, schsitamodel.class).build();
       FirebaseRecyclerAdapter<schsitamodel,sitaholder> adapter = new FirebaseRecyclerAdapter<schsitamodel,sitaholder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull sitaholder holder, int position, @NonNull schsitamodel model) {
               holder.setname(model.getName());
               holder.setTiming(model.getTiming());
           }

           @NonNull
           @Override
           public sitaholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
               View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sch_row_sita,viewGroup,false);
               return new sitaholder(v) ;
           }
       };
        rc.setAdapter(adapter);


        try {
            adapter.startListening();
        } catch (Exception e) {
            Log.e("SCHEDULELPU", "ADAPTER CRASH");
        }


    }

    public class sitaholder extends RecyclerView.ViewHolder{
        TextView name,timing;
        View v;
        public sitaholder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            name = v.findViewById(R.id.nameofshow);
            timing = v.findViewById(R.id.timing);
        }

        public void setname(String Name){
            name.setText(Name);
        }
        public void setTiming(String Tim){
            timing.setText(Tim);
        }
    }


}
