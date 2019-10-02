package cf.rittzyradio.ritzzyradio;
/* copyright shubam virdi */

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class RitzzyRadio extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
