package com.nextgened.dnd.diceroller;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class UserRESTDataService extends IntentService {

    public static final String ACTION_START_DATA_REST = "com.nextgened.dnd.diceroller.action.StartRestData";
    public static final String ACTION_STOP_DATA_REST = "com.nextgened.dnd.diceroller.action.StopRestData";

    public UserRESTDataService() {
        super("UserRESTDataService");
    }

    /**
     * Starts this service to begin getting data from the REST server. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionStartDataRest(Context context) {
        Intent intent = new Intent(context, UserRESTDataService.class);
        intent.setAction(ACTION_START_DATA_REST);
        context.startService(intent);
    }

    /**
     * Starts this service to stop getting data from the REST server. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionStopDataRest(Context context) {
        Intent intent = new Intent(context, UserRESTDataService.class);
        intent.setAction(ACTION_STOP_DATA_REST);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_DATA_REST.equals(action)) {
                handleActionStartDataRest();
            } else if (ACTION_STOP_DATA_REST.equals(action)) {
                handleActionStopDataRest();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStartDataRest() {
        Log.i(this.getClass().getName(), "Starting REST Data Service");

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
        LocalBroadcastManager.getInstance(this).sendBroadcast(dataFoundIntent);

        //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStopDataRest() {
        Log.i(this.getClass().getName(), "Stop REST Data Service");
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
