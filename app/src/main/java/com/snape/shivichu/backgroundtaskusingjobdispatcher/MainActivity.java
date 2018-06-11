package com.snape.shivichu.backgroundtaskusingjobdispatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {

    private static final String job_Tag = "bg_task";

    FirebaseJobDispatcher dispatcher;

    private Button mStart, mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStart = (Button) findViewById(R.id.btn_start);
        mStop = (Button) findViewById(R.id.btn_stop);


        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(MainActivity.this));

                Job myJob = dispatcher.newJobBuilder()
                        .setService(MyService.class) // the JobService that will be called
                        .setTag(job_Tag)
                        .setLifetime(Lifetime.FOREVER)
                        .setTrigger(Trigger.executionWindow(10, 15))
                        .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                        .setRecurring(true)
                        .setReplaceCurrent(false)
                        .setConstraints(Constraint.ON_ANY_NETWORK)
                        .build();

                dispatcher.mustSchedule(myJob);

                Toast.makeText(getApplicationContext(), "Job Scheduled.. PLease Wait", Toast.LENGTH_SHORT).show();


            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatcher.cancel(job_Tag);

            }
        });

    }
}
