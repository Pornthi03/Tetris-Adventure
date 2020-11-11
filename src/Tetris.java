import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Tetris extends JPanel implements ActionListener{
    private final Point[][][] Block = {
        {
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
	},
			
	// J-Piece
	{
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) }
	},
			
	// L-Piece
	{
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) }
	},
			
	// O-Piece
	{
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }
	},
			
	// S-Piece
	{
            { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
            { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }
	},
			
	// T-Piece
	{
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
	},
			
	// Z-Piece
	{
            { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
            { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }
	}
    
        
    };
    private final Color[] block = {
        Color.BLUE,Color.GREEN,Color.YELLOW,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.LIGHT_GRAY
    };
    protected final ImageIcon imgleaf = new ImageIcon(this.getClass().getResource("leaf//leaf.png"));
    protected final ImageIcon imglevi = new ImageIcon(this.getClass().getResource("levi//levi1.png"));//ประกาศรูปเลวี่
    protected final ImageIcon imgdg = new ImageIcon(this.getClass().getResource("Dimensional gate//Dimensional gate.png"));//ประตูมิติ
    protected Point firstpiece;
    protected int currentp;
    protected int rotate;
    protected ArrayList<Integer> nextpiece = new ArrayList<Integer>();
    int e=2,w=1;
    int score;int second = 150;
    Color[][] pp;
    //----------levi---------------//
    int l=0,r=0,u=0,d=0;
    int lleaf=0,rleaf=0,uleaf=0,dleaf=0;
    int xx;int yy;
    int xleaf;int yleaf;
    int i,j,x,y,countleft,countright,countleftleaf,countrightleaf;
    int [][] px = new int[12][23];
    int [][] py = new int[12][23];
    int levipositionx,levipositiony,levigameover;
    int leafpositionx,leafpositiony,leafgameover;
    ArrayList<Integer> dgpiecex = new ArrayList<Integer>();
    ArrayList<Integer> dgpiecey = new ArrayList<Integer>();
    int dgx,dgy;
    boolean st =false;
    boolean again = false;
    //--------------levi------------//
    ImageIcon feildwin = new ImageIcon(this.getClass().getResource("gameover_winner//winner.jpg"));
    ImageIcon feildover = new ImageIcon(this.getClass().getResource("gameover_winner//gameover.jpg"));
    ImageIcon exit = new ImageIcon(this.getClass().getResource("gameover_winner//exit.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("gameover_winner//start.png"));
    JButton BStart= new JButton(restart);
    JButton BExit  = new JButton(exit);
    //สุ่มรูปแบบบล็อกที่จะเกิด
    public void newpiece(){
        if(e>5){
            e=2;
        }
        firstpiece = new Point(e,w);
        rotate = 0;
        if(nextpiece.isEmpty()){
            Collections.addAll(nextpiece,0,1,2,3,4,5,6);
            Collections.shuffle(nextpiece);
        }
        currentp = nextpiece.get(0);
        nextpiece.remove(0);
        e++;
    }
    //กำหนดสีของพื้นหลัง
    void init(){
        dg();
        numfeild();
        checkpositionlevi();
        checkpositionleaf();
        if(!st){
            st = true;
         // playMusic("E:\\aomsin\\work\\OOP\\game\\src\\music\\okay.wav");
        }
        pp = new Color[12][24];
        for(int i=0;i<12;i++){
            for(int j=0;j<23;j++){
                if( i==0 || i == 11 || j==22){
                    pp[i][j] = Color.GRAY;
                }
                else {
                    pp[i][j] = Color.BLACK;
                }
            }
        }
        newpiece();
    }
    
    
    //ตรวจสอบกันชนกันของบล็อก
    protected boolean position(int x,int y,int rotate){
        for(Point p : Block[currentp][rotate]){
            if(pp[p.x + x][p.y + y] != Color.BLACK){
                return true;
            }
        }
        return false;
    }
    
    //การหมุน
    public void rot(int i){
        int newrotate = (rotate + i) %4;
        if(newrotate < 0){
            newrotate = 3;
        }
        if(!position(firstpiece.x,firstpiece.y,newrotate)){ //ถ้าเท่ากับสีดำ
            rotate = newrotate;//จะหมุนได้
        }
        repaint();
    }
    
    //เคลื่อนที่ซ้ายขวา
    public void move(int i){
        if(!position(firstpiece.x+i,firstpiece.y,rotate)){
            firstpiece.x+=i;
        }
        repaint();
    }
    
    public void deleterow(int row){
        for(int j = row-1;j >0;j--){
            for(int i = 1;i<11;i++){
                pp[i][j+1] = pp[i][j];
            }
        }
        d+=26;
    }
    
    public void clearrows(){
        boolean check;
        for(int j=21;j>0;j--){
            check = false;
            for(int i=1;i<11;i++){
                if(pp[i][j]==Color.BLACK){
                    check = true;
                    break;
                }
            }
            if(!check){
                deleterow(j);;
                j+=1;
                score+=100;
            }
        }
    }
    //เป็นการเปลี่ยนสีของตำแหน่งที่บล็อกหล่นมาถึงล่างสุดจากสีพื้นดำให้เป็นสีของบล็อก
    public void fix(){
        for(Point p:Block[currentp][rotate]){
            pp[firstpiece.x + p.x][firstpiece.y + p.y] = block[currentp];
        }
        clearrows();
        newpiece();
        checkleviover();
        checkleafover();
    }
    
    //การเคลื่อนที่ลงมาจนสุดชนกับบล็อกอื่นแล้ว
    public void dropdown(){
        checkdownlevi();
        checkdownleaf();
        checkgameover();
        if(!position(firstpiece.x,firstpiece.y+1,rotate)){
            firstpiece.y+=1;
        }
        else{
            fix();
        }
        repaint();
    }
    
    protected void drawpiece(Graphics g){
        g.setColor(block[currentp]);
        for(Point p:Block[currentp][rotate]){
             g.fillRect((p.x + firstpiece.x)*26,(p.y + firstpiece.y)*26, 25, 25);
        }
    }
    protected void checkgameover(){
        int newrotate = (rotate + i) %4;
        if(position(firstpiece.x,firstpiece.y,newrotate)){
            levigameover = 1;
        }
    }
    //---------------levi-----------//
    public void dg(){
        if(dgpiecex.isEmpty()){
            Collections.addAll(dgpiecex,104,130,156,182,208,234,260);
            Collections.shuffle(dgpiecex);
        }
        dgx = dgpiecex.get(0);
        dgpiecex.remove(0);
        if(dgpiecey.isEmpty()){
            Collections.addAll(dgpiecey,156,182,208,234,260);
            Collections.shuffle(dgpiecey);
        }
        dgy = dgpiecey.get(0);
        dgpiecey.remove(0);
    }
    protected void numfeild(){
        x = 0;y = 0;
        for(i=0;i<12;i++){
            for(j=0;j<23;j++){
                px[i][j] = x;
                py[i][j] = y;
                y=y+26;
            }
            y=0;
            x=x+26;
        }
    }
    void levtleft (){
                if(px[0][22] != xx-26){
                    if(pp[levipositionx-1][levipositiony] == Color.BLACK){
                        l+=26;
                    }
                }
                else{
                    l+=0;
                }
     
    }
    void leviright(){
            if(px[11][22] != xx+26){
                if(pp[levipositionx+1][levipositiony] == Color.BLACK){
                    r+=26;
                }
            }
            else{
                r+=0;
            }

    }
    void leviup(){
            if(py[0][0] != yy-26){
                if(pp[levipositionx][levipositiony-1] == Color.BLACK){
                    u+=26;
                    }
            }
            else{
                u+=0;
            }
           
    }
    void levidown(){
            if(py[11][22] != yy+26){
                if(pp[levipositionx][levipositiony+1] == Color.BLACK){
                    d+=26;
                }
            }
            else{
                d+=0;
            }
    }
    protected void checkpositionlevi(){
        for(i=1;i<12;i++){
            for(j=1;j<23;j++){
                if(xx == px[i][j] && yy == py[i][j]){
                    levipositionx = i;
                    levipositiony = j;
                }
            }
        }
        
    }
    void checkleviover(){
         if(pp[levipositionx][levipositiony] != Color.BLACK){
            levigameover = 1;
        }
         else {
             if(pp[levipositionx-1][levipositiony] != Color.BLACK){
                if(pp[levipositionx+1][levipositiony] != Color.BLACK){
                    if(pp[levipositionx][levipositiony-1] != Color.BLACK){
                        if(pp[levipositionx][levipositiony+1] != Color.BLACK){
                            levigameover = 1;
                        }
                    }
                }
            }
         }
    }
    int countheight = 0 ;
    void checkdownlevi(){
        if(pp[levipositionx][levipositiony+1]!=Color.BLACK){
            countheight = 0 ;
        }
        if(countheight<2){
            //System.out.println("before"+countheight);
            if(countheight  < 1){
            }
            else{
                if(pp[levipositionx][levipositiony-1]!=Color.BLACK){ 
                    countheight = 0;d+=26;
                    //System.out.println("after"+countheight);
                }
            }
        }
        else{
            //countheight = 0 ;
            checkdown(levipositionx,levipositiony);
        } 
        checkdownlevileft();
        checkdownleviright();
    }
    void checkdownlevileft(){
        checkpositionlevi();
        if(pp[levipositionx][levipositiony+1]!=Color.BLACK){
            countleft = 0;
        }
        if(countleft < 1){
        }
        else{
            //countheight = 0 ;
            d-=26;
            checkdown(levipositionx,levipositiony);
        }
    }
    void checkdownleviright(){
        checkpositionlevi();
        if(pp[levipositionx][levipositiony+1]!=Color.BLACK){
            countright = 0;
        }
        if(countright < 1){     
        }
        else{
            //countheight = 0 ;
            d-=26;
            checkdown(levipositionx,levipositiony);
        }
    }
    void checkdown(int n,int m){
        numfeild();
        checkpositionlevi();
        if(pp[n][m]==Color.BLACK){
            d += 26 ;
            countheight = 0;
            countleft = 0;
            countright = 0;
  
            checkdown(n,++m);
        }
        
    }
//----------------leaf-----------//
    void leafleft (){
        if(px[0][22] != xleaf-26){
            if(pp[leafpositionx-1][leafpositiony] == Color.BLACK){
                lleaf+=26;
            }
        }
        else{
            lleaf+=0;
        }
    }
    void leafright(){
            if(px[11][22] != xleaf+26){
                if(pp[leafpositionx+1][leafpositiony] == Color.BLACK){
                    rleaf+=26;
                }
            }
            else{
                rleaf+=0;
            }
    }
    void leafup(){
            if(py[0][0] != yleaf-26){
                if(pp[leafpositionx][leafpositiony-1] == Color.BLACK){
                    uleaf+=26;
                    }
            }
            else{
                uleaf+=0;
            }
           
    }
    void leafdown(){
            if(py[11][22] != yleaf+26){
                if(pp[leafpositionx][leafpositiony+1] == Color.BLACK){
                    dleaf+=26;
                }
            }
            else{
                dleaf+=0;
            }
    }
    protected void checkpositionleaf(){
        for(i=1;i<12;i++){
            for(j=1;j<23;j++){
                if(xleaf == px[i][j] && yleaf == py[i][j]){
                    leafpositionx = i;
                    leafpositiony = j;
                }
            }
        } 
    }
    void checkleafover(){
         if(pp[leafpositionx][leafpositiony] != Color.BLACK){
            levigameover = 1;
        }
         else {
             if(pp[leafpositionx-1][leafpositiony] != Color.BLACK){
                if(pp[leafpositionx+1][leafpositiony] != Color.BLACK){
                    if(pp[leafpositionx][leafpositiony-1] != Color.BLACK){
                        if(pp[leafpositionx][leafpositiony+1] != Color.BLACK){
                            levigameover = 1;
                        }
                    }
                }
            }
         }
    }
    int countheightleaf = 0 ;
    void checkdownleaf(){
        if(pp[leafpositionx][leafpositiony+1]!=Color.BLACK){
            countheightleaf = 0 ;
        }
        if(countheightleaf<2){
            //System.out.println("before"+countheight);
            if(countheightleaf  < 1){
            }
            else{
                if(pp[leafpositionx][leafpositiony-1]!=Color.BLACK){ 
                    countheightleaf = 0;dleaf+=26;
                    //System.out.println("after"+countheight);
                }
            }
        }
        else{
            //countheight = 0 ;
            checkdownleaf(leafpositionx,leafpositiony);
        } 
        checkdownleafleft();
        checkdownleafright();
    }
    void checkdownleafleft(){
        checkpositionleaf();
        if(pp[leafpositionx][leafpositiony+1]!=Color.BLACK){
            countleftleaf = 0;
        }
        if(countleftleaf < 1){
        }
        else{
            //countheight = 0 ;
            dleaf-=26;
            checkdownleaf(leafpositionx,leafpositiony);
        }
    }
    void checkdownleafright(){
        checkpositionleaf();
        if(pp[leafpositionx][leafpositiony+1]!=Color.BLACK){
            countrightleaf = 0;
        }
        if(countrightleaf < 1){     
        }
        else{
            //countheight = 0 ;
            dleaf-=26;
            checkdownleaf(leafpositionx,leafpositiony);
        }
    }
    void checkdownleaf(int z,int x){
        numfeild();
        checkpositionleaf();
        if(pp[z][x]==Color.BLACK){
            dleaf += 26 ;
            countheightleaf = 0;
            countleftleaf = 0;
            countrightleaf = 0;
  
            checkdownleaf(z,++x);
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public void playMusic(String filepath) {
        InputStream music;
            try {
                music = new FileInputStream(new File(filepath));
                AudioStream audio = new AudioStream(music);
                AudioPlayer.player.start(audio);
            } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error"); 
        }  
    }
}  



