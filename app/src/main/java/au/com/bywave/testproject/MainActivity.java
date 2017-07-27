package au.com.bywave.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class);
        intent.putExtra("KEY1", "Value to be used by the service");

        myReceiver = new MyReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        startService(intent);Intent intent = new Intent(Intent.ACTION_SEND);

//        IntentFilter intentFilter = new IntentFilter();
//        registerReceiver(myReceiver, intentFilter);

        startActivity(new Intent(MainActivity.this, ItemActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
//        stopService(intent);
//        unregisterReceiver(myReceiver);
    }
}
