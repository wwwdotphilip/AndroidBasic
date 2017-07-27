package au.com.bywave.testproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("MyService", "on start is called.");
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("MyService", "on destroy is called.");
    }
}
