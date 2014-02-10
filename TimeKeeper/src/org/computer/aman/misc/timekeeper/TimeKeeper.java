package org.computer.aman.misc.timekeeper;
import java.util.Date;
import java.util.Timer;

/**
 * タイムキーパーシステムの制御クラス <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * <p>
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.3
 */
public class TimeKeeper
{
    /**
     * 1 秒ごとに表示時間を変更するタスクと
     * (引数で指定された時間に従い)ベルを鳴らすタスクとを設定．
     *
     * @param args ベルタスク(BellTask)を
     *             実行するタイミング(開始からの経過時間:秒)
     */
    public TimeKeeper(String[] args, String aSoundFile)
    {
        // タイマーオブジェクトを用意
        Timer timer = new Timer();
        
        // 表示オブジェクトを用意
        Display display = new Display();
        
        // 時計モデルを用意
        TimeModel timeModel = new TimeModel(display);
        
        // 測定ならびにカウントの開始
        Date startTime = new Date();
        timer.scheduleAtFixedRate( new TimeUpdateTask(timeModel), 0, 1000 );
        
        // コマンドライン引数に従い，ベルタスクを設定
        for ( int i = 0; i < args.length; i++ ){
            long t = startTime.getTime();
            t += Integer.parseInt(args[i]) * 1000;
            timer.schedule( new BellTask(i+1, aSoundFile), new Date(t) );
        }
    }


    public static void main(String[] args)
    {
        System.err.println("TimeKeeper Ver.1.3 (C) 2004-2014 Hirohisa AMAN <aman@ehime-u.ac.jp>");
        
        try{
            if ( args[0].equals("-f") ){
                String[] subArgs = new String[args.length-2];
                for ( int i = 2; i < args.length; i++ ){
                    subArgs[i-2] = args[i];
                }
                new TimeKeeper(subArgs, args[1]);
            }
            else{
                new TimeKeeper(args, null);
            }
        }
        catch( ArrayIndexOutOfBoundsException e ){
            System.err.println("(Usage)");
            System.err.println(" java -jar TimeKeeper.jar [-f soundfile] 1st [2nd 3rd ...]");
            System.err.println();
            System.err.println("(options) ");
            System.err.println("   -f \"soundfile\" : the sound file to be used as \"bell\".");
            System.err.println("                    the default is your machine beep.");
            System.err.println("   1st : waiting seconds for the 1st ring.");
            System.err.println("   2nd : waiting seconds for the 2nd ring.");
            System.err.println("   3rd : waiting seconds for the 3rd ring.");
        }
	catch( NumberFormatException nfe ){
	    System.err.print("*** Invalid time parameter specifed : ");
	    System.err.println(nfe.getMessage());
	    System.exit(1);
	}
    }
}
