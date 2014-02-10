package org.computer.aman.misc.timekeeper;
import java.util.Observable;
import java.util.Observer;

/**
 * ���v���f��(�o�ߎ��Ԃ��J�v�Z����)
 * <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class TimeModel extends Observable
{
    /**
     * �o�ߎ��Ԃ̏������ƃI�u�U�[�o�I�u�W�F�N�g�̐ݒ�
     *
     * @param anObserver �I�u�U�[�o
     */
    public TimeModel( Observer anObserver )
    {
        addObserver( anObserver );
        elapsedTime = 0;
        setChanged();
        notifyObservers();
    }


    /**
     * 1 �P�ʎ��Ԃ̌o�߂𔽉f
     */
    public void count()
    {
        elapsedTime++;
        setChanged();
        notifyObservers();
    }


    /**
     * �o�ߎ��Ԃ�Ԃ�
     *
     * @return �o�ߎ���
     */
    public int getElapsedTime()
    {
        return elapsedTime;
    }

    
    /** �o�ߎ��� */
    private int elapsedTime;
}
