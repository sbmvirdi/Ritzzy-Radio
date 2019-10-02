package cf.rittzyradio.ritzzyradio;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Stream_Link extends AppCompatActivity {
    private WebView wb;
    private static final String URL = "https://www.ritzzyradio.tk/public/ritzzy";
    private static final String URL1 = "https://www.ritzzyradio.tk/public/ritzzy_sitapur";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle  =  getIntent().getExtras();
        assert bundle != null;
        boolean v = bundle.getBoolean("stream");
        setContentView(R.layout.activity_stream__link);
        wb = findViewById(R.id.streamweb);
        WebSettings wbs = wb.getSettings();
        wbs.setJavaScriptEnabled(true);
        wbs.setMediaPlaybackRequiresUserGesture(false);
        if (v){
        wb.loadUrl(URL);}
        else{
            wb.loadUrl(URL1);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        wb.onPause();
        wb.pauseTimers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wb.onResume();
        wb.resumeTimers();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wb.destroy();
        wb = null;

    }
}
