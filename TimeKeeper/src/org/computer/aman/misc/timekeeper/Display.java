package org.computer.aman.misc.timekeeper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * タイムキーパーシステムの表示部
 * <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class Display extends JFrame implements Observer
{
	private static final long serialVersionUID = -6131271582793986310L;

	/**
     * ウィンドウの各種設定
     */
    public Display()
    {
        super("タイムキーパーシステム");
        
        // 右上の×ボタンクリックで終了するよう設定
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // 背景色を白に設定
        getContentPane().setBackground(Color.white);
        
        // 全画面表示
        setSize( Toolkit.getDefaultToolkit().getScreenSize() );
        
        // 初期画面(0：00)を設定
        timeText = new JLabel("0:00", JLabel.CENTER);
        timeText.setFont(new Font("Serif", Font.BOLD, getWidth()/3));
        getContentPane().add(timeText);
        
        setVisible(true);
    }


    /**
     * 時計モデルでの変更発生通知を受けて，表示を更新
     *
     * @param aModel 時計モデル
     * @param args 通知における引数
     */
    public void update( Observable aModel, Object args )
    {
        int time = ((TimeModel)aModel).getElapsedTime();
        StringBuffer text = new StringBuffer();
        if ( time/60 < 10 ){
            text.append("0");
        }
        text.append((time/60) + ":");
        if ( time%60 < 10 ){
            text.append("0");
        }
        text.append((time%60));
        timeText.setText(new String(text));
    }

    /** 表示させる文字列(経過時間) */
    private JLabel timeText;
}
