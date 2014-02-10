package org.computer.aman.misc.timekeeper;

import java.io.File;
import java.util.TimerTask;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * ベルを鳴らすタスク <br>
 *
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class BellTask extends TimerTask
{
    /**
     * ベルを鳴らす回数を設定(ベル音にはビープ音を使用)
     *
     * @param aCount 回数
     */
    public BellTask( int aCount )
    {
        this( aCount, null );
    }

    
    /**
     * ベルを鳴らす回数とベル音に使うサウンドファイルを設定
     *
     * @param aCount 回数
     * @param aSoundFile サウンドファイル
     */
    public BellTask( int aCount, String aSoundFile )
    {
        count = aCount;
        line = new Clip[count];
        for ( int i = 0; i < count; i++ ){
            try{
                AudioInputStream audioInputStream 
                	= AudioSystem.getAudioInputStream( new File(aSoundFile) );
                AudioFormat format = audioInputStream.getFormat();
                
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                line[i] = (Clip)AudioSystem.getLine(info);
                line[i].open(audioInputStream);
            }
            catch( Exception e ){
                line[i] = null;
            }
        }
    }


    /**
     * Timer によって実行されるタスク: ベル音を鳴らす
     */
    public void run()
    {
        for ( int c = 0; c < count; c++ ){
            if ( line == null || line[c] == null ){
                java.awt.Toolkit.getDefaultToolkit().beep();
                java.awt.Toolkit.getDefaultToolkit().beep();
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
            else{
                line[c].start();
            }
            
            try{
                Thread.sleep(700);
            }
            catch( InterruptedException e ){}
        }
    }


    /** ベルを鳴らす回数 */
    private int count;

    /** サウンドファイルのクリップ */
    private Clip[] line;
}
