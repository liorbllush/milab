package com.example.exercise_03;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReciever extends BroadcastReceiver  {
    private static final String TAG = "NotificationReciever";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "notification was tapped");
        QuoteNotificationService.startActionNotify(context);
    }
}
