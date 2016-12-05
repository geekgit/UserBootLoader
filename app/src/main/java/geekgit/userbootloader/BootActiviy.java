package geekgit.userbootloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootActiviy extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Working.WriteBootStartToLog();

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent _intent = new Intent(context, MainActivity.class);
            _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _intent.putExtra("extra_boot_flag","startup");
            context.startActivity(_intent);
        }
    }
}
