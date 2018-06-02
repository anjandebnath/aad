package w3.com.gaad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startJobScheduler(View view) {

        startActivity(new Intent(this, com.example.android.jobscheduler.MainActivity.class));

    }

    public void startWidget(View view) {

        startActivity(new Intent(this, com.example.user06.androidwidgetaj.MainActivity.class));

    }
}
