package com.snape.shivichu.backgroundtaskusingjobdispatcher;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobService;

/**
 * Created by Shivichu on 11-06-2018.
 */

public class MyService extends JobService {


    BackgroundTask backgroundTask;

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {


        backgroundTask = new BackgroundTask() {

            @Override
            protected void onPostExecute(Void aVoid) {

                Toast.makeText(getApplicationContext(), "Snape Reached ON Post", Toast.LENGTH_SHORT).show();
                jobFinished(job, false);
            }
        };

        backgroundTask.execute();


        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return false;
    }


    public static class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("BG TAsk", "Do in BG");

            return null;
        }
    }

}
