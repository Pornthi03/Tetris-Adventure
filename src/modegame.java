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
public class modegame extends JPanel{
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("mode//Untitled-9.jpg"));//ภาพพื้นหลัง
    private ImageIcon easy = new ImageIcon(this.getClass().getResource("mode//easy.png"));//ภาพปุ่ม easy
    private ImageIcon normal = new ImageIcon(this.getClass().getResource("mode//normal.png"));//ภาพปุ่ม normal
    private ImageIcon hard = new ImageIcon(this.getClass().getResource("mode//hard.png"));//ภาพปุ่ม hard
    public JButton Beasy = new JButton(easy);
    public JButton Bnormal = new JButton(normal);
    public JButton Bhard = new JButton(hard);
    modegame(){
        setLayout(null);
        Beasy.setBounds(85,130,150,82);
        add(Beasy);
        Bnormal.setBounds(85,250,150,82);
        add(Bnormal);
        Bhard.setBounds(85,380,150,82);
        add(Bhard);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(feild.getImage(),0,0,12*26+10,26*23+25,this);	
    }

}
