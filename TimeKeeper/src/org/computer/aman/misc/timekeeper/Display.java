package org.computer.aman.misc.timekeeper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * �^�C���L�[�p�[�V�X�e���̕\����
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
     * �E�B���h�E�̊e��ݒ�
     */
    public Display()
    {
        super("�^�C���L�[�p�[�V�X�e��");
        
        // �E��́~�{�^���N���b�N�ŏI������悤�ݒ�
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // �w�i�F�𔒂ɐݒ�
        getContentPane().setBackground(Color.white);
        
        // �S��ʕ\��
        setSize( Toolkit.getDefaultToolkit().getScreenSize() );
        
        // �������(0�F00)��ݒ�
        timeText = new JLabel("0:00", JLabel.CENTER);
        timeText.setFont(new Font("Serif", Font.BOLD, getWidth()/3));
        getContentPane().add(timeText);
        
        setVisible(true);
    }


    /**
     * ���v���f���ł̕ύX�����ʒm���󂯂āC�\�����X�V
     *
     * @param aModel ���v���f��
     * @param args �ʒm�ɂ��������
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

    /** �\�������镶����(�o�ߎ���) */
    private JLabel timeText;
}
