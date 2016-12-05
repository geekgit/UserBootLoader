package geekgit.userbootloader;

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
}
