package org.computer.aman.misc.timekeeper;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;

/**
 * タイムキーパーシステムの制御クラス <br>
 * (C) 2004-2026 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * <p>
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 2.0
 */
public class TimeKeeper
extends WindowAdapter
{
    /**
     * 1 秒ごとに表示時間を変更するタスクと
     * (引数で指定された時間に従い)ベルを鳴らすタスクとを設定．
     *
     * @param args ベルタスク(BellTask)を
     *             実行するタイミング(開始からの経過時間:秒)
     */
    public TimeKeeper(final int[] aBellTimes, final String aSoundFile)
    {
    	bellTimes = new int[aBellTimes.length];
    	System.arraycopy(aBellTimes, 0, bellTimes, 0, aBellTimes.length);
        
        soundFileName = aSoundFile;
        
        reset();
        start();
    }

    /**
     * 右上の×ボタンがクリックされた際にダイアログを表示し，
     * リセットか終了かを選ばせる．
     */
	@Override
	public void windowClosing(WindowEvent e) 
	{
		display.setVisible(false);
		Object[] options = {"リセット", "終了" };
		int n = JOptionPane.showOptionDialog(display, "リセットしますか？それとも終了しますか？", "リセット or 終了",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if ( n == 0 ){
			reset();
			start();
		}
		else{
			System.exit(0);
		}
	}
	
	/** 
	 * タイマーをスタートさせる．
	 */
	private void start()
	{		
		JOptionPane.showMessageDialog(display, "OK をクリックするとスタートします");

        // 時計モデルを用意
        TimeModel timeModel = new TimeModel(display);
        
        // 測定ならびにカウントの開始
        Date startTime = new Date();
        timer.scheduleAtFixedRate( new TimeUpdateTask(timeModel), 0, 1000 );
        
        // コマンドライン引数に従い，ベルタスクを設定
        for ( int i = 0; i < bellTimes.length; i++ ){
            long t = startTime.getTime();
            t += bellTimes[i] * 1000;
            timer.schedule( new BellTask(i+1, soundFileName), new Date(t) );
        }        
	}
	
	/**
	 * タイマーをリセットする．
	 */
	private void reset()
	{
		if ( timer != null ){
			timer.cancel();
			timer = null;
		}
        timer = new Timer();       
        
        if ( display != null ){
        	display.dispose();
        }
        display = new Display();
        display.addWindowListener(this);
	}
	
	
    public static void main(String[] args)
    {
        System.err.println("TimeKeeper Ver.2.0 (C) 2004-2026 Hirohisa AMAN <aman@ehime-u.ac.jp>");
        
        try{
            String soundFile = null;
            int startIndex = 0;
            boolean inMinute = true;
            if ( args[startIndex].equals("-f") ){
            	startIndex = 2;
            	soundFile = args[1];
            }
            if ( args[startIndex].equals("-s") ) {
            	startIndex++;
            	inMinute = false;
            }
            
            int[] bellTimes = new int[args.length-startIndex];
            for ( int i = startIndex; i < args.length; i++ ){
            	bellTimes[i-startIndex] = Integer.parseInt(args[i]) * (inMinute ? 60 : 1);
            }
            new TimeKeeper(bellTimes, soundFile);
        }
        catch( ArrayIndexOutOfBoundsException e ){
            System.err.println("(Usage)");
            System.err.println(" java -jar TimeKeeper.jar [-f soundfile] [-s] 1st [2nd 3rd ...]");
            System.err.println();
            System.err.println("(options) ");
            System.err.println("   -f \"soundfile\" : the sound file to be used as \"bell\".");
            System.err.println("                    the default is your machine beep.");
            System.err.println("   -s  : time unit is a second (default: minute).");
            System.err.println("   1st : waiting time for the 1st ring.");
            System.err.println("   2nd : waiting time for the 2nd ring.");
            System.err.println("   3rd : waiting time for the 3rd ring.");
        }
        catch( NumberFormatException nfe ){
        	System.err.print("*** Invalid time parameter specifed : ");
        	System.err.println(nfe.getMessage());
        	System.exit(1);
        }
    }
    
    /** ベルを鳴らすまでの秒数 */
    int[] bellTimes;
    
    /** 表示オブジェクト */
    Display display;
    
    /** ベル音のファイル名 */
    String soundFileName;
    
    /** タイマーオブジェクト */
    Timer timer;
}
