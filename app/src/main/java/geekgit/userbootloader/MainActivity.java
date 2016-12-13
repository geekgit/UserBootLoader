package geekgit.userbootloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG","UserBootLoader Main Main Main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        String extra_boot_flag=intent.getStringExtra("extra_boot_flag");
        if(extra_boot_flag!=null) {
            if (extra_boot_flag.equals("startup")) {
                Working.WriteBootStartToLog();
                Context context = this.getBaseContext();
                Working.MakeNotification(context, 1, "UserBootLoader", "Boot Entrypoint");
            }
        }
        else
        {
            Working.WriteMainStartToLog();
            Context context=this.getBaseContext();
            Working.MakeNotification(context,1,"UserBootLoader","Manual Entrypoint");
        }
        final Context context=getBaseContext();

        Button ManualStartButton=(Button)findViewById(R.id.ManualStartButton);
        ManualStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Working.ShowMessage(context,"Click...");
                Working.Procedure(context);
            }
        });
        Button RemoveButton=(Button)findViewById(R.id.RemoveButton);
        RemoveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Working.ShowMessage(context,"Remove...");
                Working.RemoveDir(context);
            }
        });
        Button CreateButton=(Button)findViewById(R.id.CreateButton);
        CreateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Working.ShowMessage(context,"Create...");
                Working.CreateDummyDir(context);
            }
        });
        Button BootStartShUserButton=(Button)findViewById(R.id.BootStartShUserButton);
        BootStartShUserButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Working.ShowMessage(context,"Booting 00_start.sh (user)...");
                String start_sh_string=Working.UserExecute(context,"sh /sdcard/UserBootLoader/00_start.sh");
                Working.ShowMessage(context,start_sh_string);
            }
        });
        Button BootStartShRootButton=(Button)findViewById(R.id.BootStartShRootButton);
        BootStartShRootButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Working.ShowMessage(context,"Booting 00_start.sh (root)...");
                String start_sh_string=Working.RootExecute(context,"sh /sdcard/UserBootLoader/00_start.sh");
                Working.ShowMessage(context,start_sh_string);
            }
        });
    }
}

