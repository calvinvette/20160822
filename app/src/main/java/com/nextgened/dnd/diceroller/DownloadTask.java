package com.nextgened.dnd.diceroller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;

/**
 * Created by Administrator on 8/25/2016.
 */
// Params, Progress, Result
public class DownloadTask extends AsyncTask<String, String, String> {
    String status;
    Context context;

    public DownloadTask() {}
    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MockUserDAO mock = new MockUserDAO();

        User neville = new User();
        neville.setEmail("neville@hogwarts.ac.uk");
        neville.setFirstName("Neville Longbottom");
        neville.setCreatedDate(new Date());
        mock.insert(neville);

        User dean = new User();
        dean.setEmail("dean@hogwarts.ac.uk");
        dean.setFirstName("Dean Thomas");
        dean.setCreatedDate(new Date());
        mock.insert(dean);

        User seamus = new User();
        seamus.setEmail("seamus@hogwarts.ac.uk");
        seamus.setFirstName("Seamus Finnegan");
        seamus.setCreatedDate(new Date());
        mock.insert(seamus);

        Intent dataFoundIntent = new Intent();
        dataFoundIntent.setAction("DATA_RECEIVED_EVENT"); // Make this a Constant later on
        // Broadcast receivers can filter for just this event
        // Otherwise they'll get too many intents from other sources
//        dataFoundIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(dataFoundIntent);
        LocalBroadcastManager.getInstance(context).sendBroadcast(dataFoundIntent);



        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, new Intent(context, UserListActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification note = new Notification.Builder(context)
                .setContentTitle("New Users!")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.flag_of_canada)
                .setContentText("You've got Users @" + new Date())
                .setAutoCancel(true)
                .build();
        notificationManager.notify(
                new Long(Math.round(Math.random() * 4 * 1024 * 1024 * 1024)).intValue(),
                note);


        return "Done";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.i(this.getClass().getName(), "Progress Update: " + values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        status = s;
        Log.i(this.getClass().getName(), "Post-Execute: " + s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(this.getClass().getName(), "Pre-Execute");
    }
}
