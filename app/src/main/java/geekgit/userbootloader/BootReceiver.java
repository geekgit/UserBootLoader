package geekgit.userbootloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i=new Intent(context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("extra_boot_flag","startup");
            context.startActivity(i);
            //Working.MakeNotification(context, 1, "UserBootLoader", "Boot Entrypoint");

        }
    }
}
