package geekgit.userbootloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        String extra_boot_flag=intent.getStringExtra("extra_boot_flag");
        if(extra_boot_flag!=null) {
            if (extra_boot_flag.equals("startup")) {
                Working.WriteBootStartToLog();
                Context context = this.getBaseContext();
                Working.MakeNotification(context, 0, "UserBootLoader", "Boot Entrypoint");
            }
        }
        else
        {
            Working.WriteMainStartToLog();
            Context context=this.getBaseContext();
            Working.MakeNotification(context,1,"UserBootLoader","Manual Entrypoint");
        }
        //Working.WriteMainStartToLog();
        //Working.TrySuperAction();

    }
}

