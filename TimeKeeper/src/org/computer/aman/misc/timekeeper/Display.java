package org.computer.aman.misc.timekeeper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * �^�C���L�[�p�[�V�X�e���̕\����
 * <br>
 * (C) 2004 Hirohisa AMAN (aman@ehime-u.ac.jp)
 * 
 * @author Hirohisa AMAN (aman@ehime-u.ac.jp)
 * @version 1.2
 */
public class Display 
extends JFrame 
implements Observer, WindowListener
{
	private static final long serialVersionUID = -6131271582793986310L;

	/**
     * �E�B���h�E�̊e��ݒ�
     */
    public Display()
    {
        super("�^�C���L�[�p�[�V�X�e��");
        
        // �E��́~�{�^���N���b�N�ŏI�� or ���Z�b�g��I������_�C�A���O���J���悤�ݒ�
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(this);
        
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

	@Override
	public void windowActivated(WindowEvent e) 
	{
	}


	@Override
	public void windowClosed(WindowEvent e) 
	{
	}


	@Override
	public void windowClosing(WindowEvent e) 
	{
		Object[] options = {"���Z�b�g���čăX�^�[�g", "�I��" };
		int n = JOptionPane.showOptionDialog(this, "���Z�b�g���čăX�^�[�g���܂����H����Ƃ��I�����܂����H", "���Z�b�g or �I��",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if ( n == 0 ){
			System.out.println("���Z�b�g");
		}
		else{
			System.exit(0);
		}
	}


	@Override
	public void windowDeactivated(WindowEvent e) 
	{
	}


	@Override
	public void windowDeiconified(WindowEvent e) 
	{
	}


	@Override
	public void windowIconified(WindowEvent e) 
	{
	}


	@Override
	public void windowOpened(WindowEvent e) 
	{
	}
}
