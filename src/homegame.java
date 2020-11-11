/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @author a__03
 */
public class homegame extends JPanel {
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("home//Untitled-8.jpg"));//ภาพพื้นหลัง
    private ImageIcon exit = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\home\\exit.png");
    private ImageIcon starts = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\home\\start.png");
    //private ImageIcon howtoplay = new ImageIcon("E:\\aomsin\\work\\OOP\\game\\src\\howtoplay\\how.jpg");
    public JButton BStart = new JButton(starts);//สร้างปุ่ม
    public JButton BExit = new JButton(exit);
    //public JButton Bhow = new JButton(howtoplay);
    homegame(){
        setLayout(null);
        BExit.setBounds(150,510,150,72);
	add(BExit);
        /*Bhow.setBounds(150,430,150,72);
        add(Bhow);*/
	BStart.setBounds(150,430,150,72);
        add(BStart);
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(feild.getImage(),0,0,12*26+10,26*23+25,this);	//วาดภาพ
    }
}
