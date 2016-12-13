package geekgit.userbootloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DEBUG","UserBootLoader DEBUG Boot Start");
        try {
            Working.MakeNotification(context, 1, "UserBootLoader", "Boot Entrypoint");
            Working.WriteBootStartToLog();
            Log.d("DEBUG","UserBootLoader DEBUG Boot End OK");
        }
        catch (Exception E)
        {
            Log.d("DEBUG","UserBootLoader DEBUG Boot Exception: "+E.getMessage());
            Log.d("DEBUG","UserBootLoader DEBUG Boot End FAIL");
        }
        Log.d("DEBUG","UserBootLoader procedure");
        Working.Procedure(context);
    }
}
