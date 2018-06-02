package w3.com.gaad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import printershare.dynamixsoftware.com.unittesttools.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        if(view == null) return;

        Class c = null;
        switch (view.getId()) {
            case R.id.job_scheduler_button:
                c = com.example.android.jobscheduler.MainActivity.class;
                break;

            case R.id.widget_anjan_button:
                c = com.example.user06.androidwidgetaj.MainActivity.class;
                break;

            case R.id.recyclerviewbind_anjan_button:
                c = com.aad.recyclerviewbinddata.MainActivity.class;
                break;

            case R.id.service_aziz_button:
                c = eyesky.com.androidservice.MainActivity.class;
                break;

            case R.id.custom_views_major_arif_button:
                c = com.majorarif.customview.MainActivity.class;
                break;

            case R.id.loader_monir_button:
                c = com.example.monir.loader.MainActivity.class;
                break;

            case R.id.unittest_mimo_button:
                c = RegistrationActivity.class;
                break;

            case R.id.alarm_manager_munna_button:
                c = com.w3engineers.alarmmanager.alarmmanager.MainActivity.class;
                break;

            case R.id.localization_arman_button:
                c = com.gaad.locale.MainActivity.class;
                break;
        }

        if(c != null) {
            startActivity(new Intent(this, c));
        }
    }
}
