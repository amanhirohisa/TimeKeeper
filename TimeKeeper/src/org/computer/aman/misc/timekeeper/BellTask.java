package org.computer.aman.misc.timekeeper;

import java.io.File;
import java.util.TimerTask;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * �x����炷�^�X�N <br>
 *
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class BellTask extends TimerTask
{
    /**
     * �x����炷�񐔂�ݒ�(�x�����ɂ̓r�[�v�����g�p)
     *
     * @param aCount ��
     */
    public BellTask( int aCount )
    {
        this( aCount, null );
    }

    
    /**
     * �x����炷�񐔂ƃx�����Ɏg���T�E���h�t�@�C����ݒ�
     *
     * @param aCount ��
     * @param aSoundFile �T�E���h�t�@�C��
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
     * Timer �ɂ���Ď��s�����^�X�N: �x������炷
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


    /** �x����炷�� */
    private int count;

    /** �T�E���h�t�@�C���̃N���b�v */
    private Clip[] line;
}
