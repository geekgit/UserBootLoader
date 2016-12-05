package geekgit.userbootloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.format.Time;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Working {
    private static final String LogFileName="/sdcard/userbootloader.log.txt";
    public static void TrySuperAction()
    {
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean CheckSDCard()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    private static BufferedWriter GetLogWriter()
    {
        BufferedWriter result=null;
        try
        {
        File f=new File(LogFileName);
        FileWriter fw=new FileWriter(f,true);
        BufferedWriter bw=new BufferedWriter(fw);
        result=bw;
        }
        catch (Exception e)
        {

        }
        return result;
    }
    private static void LogWrite(String data)
    {
        BufferedWriter bw=GetLogWriter();
        try {
            bw.append(data+"\n");
            bw.close();
        }
        catch (Exception e)
        {

        }
    }
    public static void WriteMainStartToLog()
    {
        LogWrite(CurrentTimeString()+" [main] start...");
    }

    public static void WriteBootStartToLog()
    {
        LogWrite(CurrentTimeString()+" [boot] start...");
    }
    private static String CurrentTimeString()
    {
    Time t=new Time();
    t.setToNow();
    return t.toString();
    }

    public static void MakeNotification(Context context, int notification_id, String notificaiton_title,String notificaiton_text)
    {
        final int NID=notification_id;
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(context,NID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder nb=new Notification.Builder(context);
        nb=nb.setContentTitle(notificaiton_title);
        nb=nb.setContentText(notificaiton_text);
        nb=nb.setContentIntent(pi);
        nb=nb.setSmallIcon(R.drawable.ic_notification_icon_00);
        Notification n=nb.build();
        n.flags=n.flags|Notification.FLAG_NO_CLEAR|Notification.FLAG_ONGOING_EVENT;
        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID,n);
    }
}
