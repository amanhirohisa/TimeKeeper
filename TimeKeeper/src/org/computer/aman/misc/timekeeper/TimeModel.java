package org.computer.aman.misc.timekeeper;
import java.util.Observable;
import java.util.Observer;

/**
 * 時計モデル(経過時間をカプセル化)
 * <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class TimeModel extends Observable
{
    /**
     * 経過時間の初期化とオブザーバオブジェクトの設定
     *
     * @param anObserver オブザーバ
     */
    public TimeModel( Observer anObserver )
    {
        addObserver( anObserver );
        elapsedTime = 0;
        setChanged();
        notifyObservers();
    }


    /**
     * 1 単位時間の経過を反映
     */
    public void count()
    {
        elapsedTime++;
        setChanged();
        notifyObservers();
    }


    /**
     * 経過時間を返す
     *
     * @return 経過時間
     */
    public int getElapsedTime()
    {
        return elapsedTime;
    }

    
    /** 経過時間 */
    private int elapsedTime;
}
