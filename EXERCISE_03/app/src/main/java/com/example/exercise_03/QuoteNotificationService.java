package com.example.exercise_03;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class QuoteNotificationService extends IntentService {
    private static final String ACTION_CREATE = "ACTION_CREATE";
    private static final String ACTION_NOTIFY = "ACTION_NOTIFY";
    private static final String CHANNEL_ID = "NotifyChannel";
    public static int mn_id = 1;


    public QuoteNotificationService() {
        super("QuoteNotificationService");
    }

    protected void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    private String getQuote(String[] quotes){
        return quotes[(int) (Math.random() * quotes.length)];
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CREATE.equals(action)) {
                handleActionCreate();
            } else if (ACTION_NOTIFY.equals(action)) {
                handleActionNotify();
            }
        }
    }

    public static void startActionCreate(Context context) {
        Intent intent = new Intent(context, QuoteNotificationService.class);
        intent.setAction(ACTION_CREATE);
        context.startService(intent);
    }

    private void handleActionCreate() {
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        registerNotificationChannel();
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_HOUR /(60), pendingIntent);
    }

    public static void startActionNotify(Context context) {
        Intent intent = new Intent(context, QuoteNotificationService.class);
        intent.setAction(ACTION_NOTIFY);
        context.startService(intent);
    }

    private void handleActionNotify() {
        String[] quotes = getResources().getStringArray(R.array.quotes);
        String randomQuote =getQuote(quotes);
        NotificationCompat.Builder notification= new NotificationCompat.Builder(this, CHANNEL_ID);
                notification.setContentTitle("MY QUOTE")
                        .setColor(Color.YELLOW)
                .setContentText(randomQuote).setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground).setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(mn_id++,notification.build());

    }

}

