package dk.lott.VPPS2015;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by sdc on 7/15/14.
 */
public class PotatoService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public PotatoService() {
        super("potato");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
