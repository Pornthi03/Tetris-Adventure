
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class playgame extends JFrame implements ActionListener {
        Tetriseasy teeasy = new Tetriseasy();
        Tetrisnormal tenormal = new Tetrisnormal();
        Tetrishard tehard = new Tetrishard();
        homegame homegame = new homegame();
        modegame modegame = new modegame();
        gameover gameover = new gameover();
        boolean again = true;
	//gameover gover = new gameover();
	public playgame(){
		this.setSize(337, 640);
		this.add(homegame);
                homegame.BStart.addActionListener(this);//หน้าเกม
                homegame.BExit.addActionListener(this);
                modegame.Beasy.addActionListener(this);
                modegame.Bnormal.addActionListener(this);
                modegame.Bhard.addActionListener(this);
                gameover.BStart.addActionListener(this);
                gameover.BExit.addActionListener(this);
	}
        @Override
         public void actionPerformed(ActionEvent e){
            if(e.getSource()==homegame.BStart){
                this.remove(homegame);
		this.add(modegame);//โหมดเกม
                modegame.requestFocusInWindow(); 
            }
            else if(e.getSource() == homegame.BExit){
               System.exit(0);
                }
            else if(e.getSource()==modegame.Beasy){
                this.remove(modegame);
                teeasy.st = false;
                this.add(teeasy);//โหมดง่าย
                teeasy.requestFocusInWindow(); 
            }
            else if(e.getSource()==modegame.Bnormal){
                this.remove(modegame);
                tenormal.st = false;
                this.add(tenormal);//โหมดกลาง
                tenormal.requestFocusInWindow(); 
            }
            else if(e.getSource()==modegame.Bhard){
                this.remove(modegame);
                tehard.st = false;
                this.add(tehard);//โหมดยาก
                tehard.requestFocusInWindow(); 
            }
            this.validate();
            this.repaint();
        }
        /*public static void main(String[] args) {
		JFrame f = new playgame();
                f.setTitle("Tetis Adventure");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(337, 640);
		f.setVisible(true);
                f.setLocationRelativeTo(null);
	}*/
}
