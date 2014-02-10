package org.computer.aman.misc.timekeeper;
import java.util.TimerTask;

/**
 * 時刻の更新を通知するタスク <br>
 *
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class TimeUpdateTask extends TimerTask
{
    /**
     * 時計モデルを設定
     *
     * @param aModel 時計モデル(経過時間をカプセル化)
     */
    public TimeUpdateTask( TimeModel aModel )
    {
        timeModel = aModel;
    }

    
    /**
     * 時計モデルに対して 1 単位時間が経過したことを通知
     */
    public void run()
    {
        timeModel.count();
    }

    /** 時計モデル */
    private TimeModel timeModel; 
}
