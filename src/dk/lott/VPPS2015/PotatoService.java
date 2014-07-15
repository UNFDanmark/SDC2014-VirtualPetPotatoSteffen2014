package dk.lott.VPPS2015;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by sdc on 7/15/14.
 */
public class PotatoService extends IntentService {

    public PotatoService() {
        super("potato");
    }

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
}
