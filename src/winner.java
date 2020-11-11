import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
public class winner extends JPanel{
    private ImageIcon feildwin = new ImageIcon("gameover_winner//winner.jpg");
    private ImageIcon exitwin = new ImageIcon("gameover_winner//exit.jpg");
    private ImageIcon restart = new ImageIcon("gameover_winner//Start.jpg");
    public JButton BStartwin = new JButton(restart);
    public JButton BExitwin  = new JButton(exitwin);
    
    winner(){
        this.setLayout(null);
        BExitwin.setBounds(500, 650, 150,90);
        add(BExitwin);
	add(BStartwin);
	BStartwin.setBounds(750,650,150,90);
        add(BStartwin);
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(feildwin.getImage(),0,0,337,640,this);
    }
}
