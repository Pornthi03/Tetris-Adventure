import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
 
public class gameover extends JPanel{
    private ImageIcon feildover = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\gameover_winner\\gameover.jpg");
    private ImageIcon exit = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\gameover_winner\\exit.jpg");
    private ImageIcon rs = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\gameover_winner\\start.png");
    public JButton BStart= new JButton(rs);
    public JButton BExit = new JButton(exit);
    modegame modegame = new modegame();
    public gameover(){
	setLayout(null);
	BExit.setBounds(150,500,150,62);
	add(BExit);
	BStart.setBounds(150,430,150,62);
        add(BStart);
    }
    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(feildover.getImage(),0,0,12*26+10,26*23+25,this);
    }
}