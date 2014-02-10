package org.computer.aman.misc.timekeeper;
import java.util.Date;
import java.util.Timer;

/**
 * �^�C���L�[�p�[�V�X�e���̐���N���X <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * <p>
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.3
 */
public class TimeKeeper
{
    /**
     * 1 �b���Ƃɕ\�����Ԃ�ύX����^�X�N��
     * (�����Ŏw�肳�ꂽ���Ԃɏ]��)�x����炷�^�X�N�Ƃ�ݒ�D
     *
     * @param args �x���^�X�N(BellTask)��
     *             ���s����^�C�~���O(�J�n����̌o�ߎ���:�b)
     */
    public TimeKeeper(String[] args, String aSoundFile)
    {
        // �^�C�}�[�I�u�W�F�N�g��p��
        Timer timer = new Timer();
        
        // �\���I�u�W�F�N�g��p��
        Display display = new Display();
        
        // ���v���f����p��
        TimeModel timeModel = new TimeModel(display);
        
        // ����Ȃ�тɃJ�E���g�̊J�n
        Date startTime = new Date();
        timer.scheduleAtFixedRate( new TimeUpdateTask(timeModel), 0, 1000 );
        
        // �R�}���h���C�������ɏ]���C�x���^�X�N��ݒ�
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
