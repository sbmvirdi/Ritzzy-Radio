package cf.rittzyradio.ritzzyradio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.Random;


public class SplashScreen extends AppCompatActivity {
    private RoundedHorizontalProgressBar pb;
    private RelativeLayout root;
    private SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = getSharedPreferences("cf.rittzyradio.ritzzyradio", MODE_PRIVATE);
        pb = findViewById(R.id.pb);
        root = findViewById(R.id.splashroot);
        int[] i = {R.drawable.note1,R.drawable.note2,R.drawable.note3,R.drawable.note4};

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                pb.animateProgress(3900,0,100);
            }
        });
        //Thread t = new Thread(new Runnable() {
         //   @Override
          //  public void run() {
          //      v.start();
          //  }
        //});
       // t.start();
        t1.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_in,R.transition.slide_out);
                finish();
            }
        },3800);
    }

}
