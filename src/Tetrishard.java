import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

public class Tetrishard extends Tetris {
    Tetrishard(){
        init();
            this.setFocusable(true);
            this.setLayout(null);
            BExit.setBounds(150,500,150,62);
            BStart.setBounds(150,430,150,62);
            BExit.addActionListener(this);
            BStart.addActionListener(this);
            this.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_UP){
                        rot(-1);
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                        rot(+1);
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                        move(-1);
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                        move(+1);
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        dropdown();
                        score+=1;
                    }
                    if(e.getKeyCode() == KeyEvent.VK_W){
                        leviup();
                        ++countheight;

                    }
                    else if(e.getKeyCode() == KeyEvent.VK_A){
                        levtleft();
                        countleft++;
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_S){
                        --countheight;
                        levidown();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_D){
                        leviright();
                        countright++;

                    }
                    if(e.getKeyCode() == KeyEvent.VK_T){
                       leafup();
                       ++countheightleaf;

                    }
                    else if(e.getKeyCode() == KeyEvent.VK_F){
                        leafleft();
                        countleftleaf++;
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_G){
                       --countheightleaf;
                        leafdown();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_H){
                        leafright();
                        countrightleaf++;
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }

            });
             new Thread(){
                    @Override public void run(){
                        while(true){
                            try{
                                Thread.sleep(300);
                                if(!st){
                                    dropdown();
                                }
                            }catch(InterruptedException e){}
                        }
                    }
                }.start();
            time.start();
            }
    Thread time = new Thread(new Runnable(){
            @Override
            public void run(){
		while(true){
                    try{
			Thread.sleep(1000);
                        if(!st){
                            second--;
                        }
                    }catch(Exception e){ }
		}
            }
	});
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	g.fillRect(0, 0, 26*12, 26*23);
	for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {	
		g.setColor(pp[i][j]);	
		g.fillRect(26*i, 26*j, 25, 25);
            }
	}
	g.setColor(Color.WHITE);
	g.drawString("" + score, 19*12, 25);
	drawpiece(g);
        xx=208-l+r;yy=546-u+d;
        g.setColor(Color.WHITE);
        g.drawString("" + second,19*14, 25);
        if(score >= 1000){
            g.drawImage(imgdg.getImage(), dgx, dgy, 25,25 , this);
        }
        g.drawImage(imglevi.getImage(),xx , yy, 25, 25, this);
        repaint();
       if((xx == dgx)&&(yy == dgy)){
            this.setLayout(null);
            g.drawImage(feildwin.getImage(),0,0,337,640,this);
        }
        if(second <= 0 || levigameover == 1){
           this.setLayout(null);
            g.drawImage(feildover.getImage(),0,0,337,640,this);
            setLayout(null);
            BExit.setBounds(150,500,150,62);
            add(BExit);
            BStart.setBounds(150,430,150,62);
            add(BStart);
        
        }   
    }
    @Override
    public void actionPerformed(ActionEvent e){
            if(e.getSource()== BStart){
                if(!again){
                    again = true;
                    new playgame1();
                }
            }
            else if(e.getSource() == BExit){
                System.exit(0);
            }
        }
}
