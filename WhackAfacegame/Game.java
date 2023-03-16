import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game {

    private String playerName;
    private JLabel panel;
    private JLabel[] holes = new JLabel[16];
    private int[]  board = new int[16];

    private int score = 0;
    private int timeLeft = 30;
    
    private int iniTime;
    private int misses = -1; 
    private int holeHeight = 150;
    private int holeWidth =150;
    public int screenwidth, screenHeight;

    private JLabel lblScore;
    private JLabel lblTimeLeft;
  

    private JButton btnStart;
    private Timer timer;
    private Timer moleTimer;

    private JFrame home;
    public JLabel contentPane;

    boolean smash = true;
    Timer cursorAnimTimer;

    private String moleColor;

    private void gameOver(String difficulty){
        btnStart.setEnabled(true);
        moleTimer.stop();
        timer.stop();

            int option = JOptionPane.showOptionDialog(home, "Your final score is: " + score, "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Try Again", "Home"}, "Try Again");
            if (option == JOptionPane.YES_OPTION) {
                score = 0;
                lblScore.setText("Score: 0");
                clearBoard();
                misses = -1;
                moleTimer.restart();  
                btnStart.setEnabled(false);
                timer.start();
                timeLeft = iniTime;
                lblTimeLeft.setText("Time: " + timeLeft);
                
    

            } else {
             
                Home interf = new Home();  
                interf.setVisible(true);
                interf.stopBackgroundMusic(); 
                home.dispose();
            }
        }


    private void pressedButton(int id){
        int val = board[id];
        misses--;
        moleTimer.restart();
        //if val is 1 = mole
        //if val is 0 = empty hole
        if(val==1){
            score++;
        }else{ //val==0
            score--;
            misses++;
        }

        lblScore.setText("Score: " + score); //update the score
        clearBoard();

    }


    private void initEvents(){
        for(int i = 0; i < holes.length; i++){
            holes[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    JLabel lbl = (JLabel)e.getSource();
                    int id = Integer.parseInt(lbl.getName());
                    pressedButton(id);
                    smash = true;
                    CursorSmash();
                }
            });

        cursorAnimTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cursorAnimSwap();
            }
        });

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    
                btnStart.setEnabled(false);
                clearBoard();
                moleTimer.start();
                timer.start();
                
            }
        });


        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(timeLeft == 0){
                    lblTimeLeft.setText("Time:" + timeLeft);
                    timer.stop();
                    //timer.restart();
                    String difficulty = "";
                    gameOver(difficulty);
                }
                lblTimeLeft.setText("Time:" + timeLeft);
                timeLeft--;
            }
        });}

        //mole timer  
        moleTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearBoard();
                genRandMole();

                misses++;
                if (misses >= 3) {
                    String difficulty = "";
                    gameOver(difficulty);
                    return;
                }

            }
        });

    }


    private void initGUI(){

         // Create the panel for the mole holes
        panel = new JLabel();
        int panelHeight = 600;
        int panelWidth = 600;
        int panelPosY = 205;
        panel.setBounds((screenwidth/2)-(panelWidth/2), panelPosY, panelWidth, panelHeight);
        panel.setLayout(null); 
       
        
        // Create the content pane with the background image
        contentPane = new JLabel(new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/cyber.gif"));
        contentPane.setBounds(0, 0, 500, 500);
        contentPane.setLayout(null);
        
        //Add the panel to the content pane
        contentPane.add(panel);
        
    
        // Create the title label
        JLabel lblTitle = new JLabel("Whack A Face");
        lblTitle.setForeground(Color.YELLOW);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Algerian", Font.BOLD, 50));
        int lblTitleHeight = 40;
        int lblTitleWidth = 600;
        int lblTitlePosY = 20;
        lblTitle.setBounds((screenwidth/2)-(lblTitleWidth/2), lblTitlePosY,lblTitleWidth, lblTitleHeight);
        contentPane.add(lblTitle);
    
        // Add the panel to the content pane
        contentPane.add(panel);
    
        // Set the cursor for the panel
        panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                loadImage("/res/hammer.png").getImage(),
                new Point(0,0),"custom cursor1"));

                //putting holes on the panel
                for(int i = 0; i < holes.length; i++){
                    holes[i] = new JLabel("", SwingConstants.CENTER);
                    //holes[i] = new JLabel(Integer.toString(i));
                    holes[i].setName(Integer.toString(i));
                    panel.add(holes[i]);
                }
                for(int i = 0; i < Math.sqrt(holes.length); i++){
                    for(int j = 0; j < Math.sqrt(holes.length); j++){
                        int holeIndex = (int) (i*Math.sqrt(holes.length))+j;
                        holes[holeIndex].setBounds(j*holeWidth, i*holeHeight, holeWidth, holeHeight);
                    }
                }
        
    
           // Create the start button as an image button 
           ImageIcon startIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/gameStartbtn.png");
           btnStart = new JButton(startIcon);
           btnStart.setBounds(655, 100, startIcon.getIconWidth(), startIcon.getIconHeight());
           btnStart.setBorder(null);
           btnStart.setOpaque(false);
           btnStart.setContentAreaFilled(false);
           contentPane.add(btnStart);


    
        // Add the score label to the content pane
        lblScore = new JLabel("Score: 0");
        lblScore.setForeground(Color.YELLOW);
        lblScore.setFont(new Font("Algerian", Font.BOLD, 30));
        lblScore.setBounds(1200, 90, 300, 100);
        contentPane.add(lblScore);

        //player name
        JLabel playerNameLabel = new JLabel("Player Name: " + playerName);
        playerNameLabel.setForeground(Color.YELLOW);
        playerNameLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        playerNameLabel.setBounds(90, 100, 602, 60);
        contentPane.add(playerNameLabel);
    
        // Add the time left label to the content pane
        lblTimeLeft = new JLabel("Time:00");
        lblTimeLeft.setForeground(Color.YELLOW);
        lblTimeLeft.setFont(new Font("Algerian", Font.BOLD, 30));
        lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        int lblTimeLeftHeight = 50;
        int lblTimeLeftWidth = 200;
        int lblTimeLeftPosY = 70;
        lblTimeLeft.setBounds((screenwidth/2)-(lblTimeLeftWidth/2), lblTimeLeftPosY,lblTimeLeftWidth, lblTimeLeftHeight);
        contentPane.add(lblTimeLeft);
    
        // Add the content pane to the frame
        home.setContentPane(contentPane);
    }


    private void clearBoard(){
        for(int i = 0; i < 16; i++){
            holes[i].setIcon(loadImage("/res/hole-removebg-preview.png"));
            board[i] = 0;
        }
    }

    public void genRandMole(){

        ImageIcon[] moleImages = new ImageIcon[4];
        moleImages[0] = loadImage("/res/" + moleColor + " eron.png");
        moleImages[1] = loadImage("/res/" + moleColor + " kate.png");
        moleImages[2] = loadImage("/res/" + moleColor + " kent.png");
        moleImages[3] = loadImage("/res/" + moleColor + " kyla.png");
    
        Random rand = new Random();
        int moleIndex = rand.nextInt(moleImages.length);
        ImageIcon moleIcon = moleImages[moleIndex];
        int holeIndex = rand.nextInt(holes.length);
        holes[holeIndex].setIcon(moleIcon);
        board[holeIndex] = 1;

    }

    private ImageIcon loadImage(String path){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    
    private void CursorSmash(){
        panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            loadImage("/res/hammer3.png").getImage(),
            new Point(0,0),"custom cursor1"));
            smash = false;
        cursorAnimTimer.start();
    }


    private void cursorAnimSwap(){
        panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                loadImage("/res/hammer2-removebg-preview.png").getImage(),
                new Point(0,0),"custom cursor1"));
                smash = true;
                cursorAnimTimer.stop();
    }

    public Game(JFrame home, int time, String playerName, String moleColor) {
        
        timeLeft = time;
        iniTime = time;
        this.home = home;
        this.playerName = playerName;
        this.moleColor = moleColor;
        home.setVisible(true);
        screenwidth = home.getSize().width;
        screenHeight = home.getSize().height;
        initGUI();
        clearBoard();
        initEvents();
    
    }

}