package dk.lott.VPPS2015;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

/**
 * Created by sdc on 7/15/14.
 */
public class PotatoService extends IntentService {

    public PotatoService() {
        super("potato");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
/*
        long endTime = System.currentTimeMillis() + 5 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }*/
    }

    /**
* notifikationer
*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Potato potato = new Potato();
        potato.load(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if(potato.hunger <= 200){
            showNotification("So hungry. Please feed me");
        }
        else if(potato.energy <= 200){
            showNotification("So tired.....");
        }
        else if(potato.thirst<= 200){
            showNotification("No water.The end is near");
        }
        else if(potato.happiness <= 200){
            showNotification("So lonely... U y no love me anymore!?!");
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void showNotification(String besked){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setContentText(besked)
                .setSmallIcon(R.drawable.trist)
                .setContentIntent(contentIntent)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(7, notification);

    }

/**
* * alarm
*/
    public static void setAlarm(Context context) {
        Intent intent = new Intent(context, PotatoService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60, pintent);
    }
}
