package org.computer.aman.misc.timekeeper;
import java.util.TimerTask;

/**
 * �����̍X�V��ʒm����^�X�N <br>
 *
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class TimeUpdateTask extends TimerTask
{
    /**
     * ���v���f����ݒ�
     *
     * @param aModel ���v���f��(�o�ߎ��Ԃ��J�v�Z����)
     */
    public TimeUpdateTask( TimeModel aModel )
    {
        timeModel = aModel;
    }

    
    /**
     * ���v���f���ɑ΂��� 1 �P�ʎ��Ԃ��o�߂������Ƃ�ʒm
     */
    public void run()
    {
        timeModel.count();
    }

    /** ���v���f�� */
    private TimeModel timeModel; 
}
