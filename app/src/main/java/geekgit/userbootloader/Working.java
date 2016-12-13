package geekgit.userbootloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.format.Time;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
    public static void ShowMessage(Context context, String string)
    {
        Toast.makeText(context,string, Toast.LENGTH_SHORT).show();
    }
    public static void CreateDummyDir(Context context)
    {
        final String LoadPath="/sdcard/UserBootLoader";
        File f=new File("/sdcard/UserBootLoader");
        if(f.exists())
        {
            Working.ShowMessage(context,"/sdcard/UserBootLoader' already exists");
        }
        else
        {
            try {
                f.mkdirs();
                File start_sh_file = new File(f, "00_start.sh");
                FileWriter fw = new FileWriter(start_sh_file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.append("#!/bin/sh"+"\n");
                bw.append("echo \"Hello, shell!\""+"\n");
                bw.append("echo \"User: $USER\""+"\n");
                bw.append("echo a > /sdcard/userbootloader.start.log.txt"+"\n");
                bw.close();
            }
            catch (Exception E)
            {
                Working.ShowMessage(context,E.getMessage());
            }
        }
    }
    public static void RemoveDir(Context context)
    {

        final String LoadPath="/sdcard/UserBootLoader";
        File f=new File("/sdcard/UserBootLoader");
        try {
            if(f.exists()&&f.isDirectory()) FileUtils.deleteDirectory(f);
            if(f.exists()&&!f.isDirectory()) f.delete();
        }
        catch (Exception E)
        {
            Working.ShowMessage(context,E.getMessage());
        }
    }
    public static void Procedure(Context context)
    {
        final String LoadPath="/sdcard/UserBootLoader";
        File f=new File("/sdcard/UserBootLoader");
        if(f.exists()&&f.isDirectory())
        {
            Working.ShowMessage(context,"Directory '/sdcard/UserBootLoader' - OK");
        }
        if(!f.exists())
        {
            Working.ShowMessage(context,"Directory '/sdcard/UserBootLoader' not found");
        }
        if(f.exists()&&!f.isDirectory())
        {
            Working.ShowMessage(context,"Error. '/sdcard/UserBoootLoader' != directory");
        }
    }
    public static String RootExecute(Context context, String cmd)
    {
        //throw new java.lang.UnsupportedOperationException();
        StringBuilder sb = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"su","-c",cmd});
            InputStream ip = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(ip);
            Reader r = (Reader) isr;
            BufferedReader br = new BufferedReader(r);

            while (true) {
                String chunk;
                chunk = br.readLine();
                if (chunk == null) break;
                sb.append(chunk + "\n");
            }
        } catch (Exception E) {
            sb.append(E.getMessage()+"\n");
        }
        String result=sb.toString();
        if(result.equals("")) return "N/A";
        else return result;
    }
    public static String UserExecute(Context context, String cmd)
    {
        //throw new java.lang.UnsupportedOperationException();
        StringBuilder sb = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            InputStream ip = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(ip);
            Reader r = (Reader) isr;
            BufferedReader br = new BufferedReader(r);

            while (true) {
                String chunk;
                chunk = br.readLine();
                if (chunk == null) break;
                sb.append(chunk + "\n");
            }
        } catch (Exception E) {
            sb.append(E.getMessage()+"\n");
        }
        String result=sb.toString();
        if(result.equals("")) return "N/A";
        else return result;
    }
}
