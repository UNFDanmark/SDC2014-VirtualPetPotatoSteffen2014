package dk.lott.VPPS2015;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

public class PotatoService extends IntentService {

    public PotatoService() {
        super("potato");
    }
    //public NotificationReceiver;

    @Override
    protected void onHandleIntent(Intent intent) {

        long endTime = System.currentTimeMillis() + 5 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
    }
/**
* notificationer
*/

    NotificationManager notificationManager = (NotificationManager)
        getSystemService(NOTIFICATION_SERVICE);

    Intent intent = new Intent(this, NotificationReceiver.class);
    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Potato potato = new Potato();
        potato.load(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if(potato.hunger == 200){

        }

        return super.onStartCommand(intent, flags, startId);
    }

/**
* * alarm
*/
    public static void setAlarm(Context context) {
        Intent intent = new Intent(context, PotatoService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*30, pintent);
    }
}
