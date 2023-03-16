import java.awt.*;
import javax.swing.*;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Home extends JFrame {


    public JLabel backgroundLabel;
    public int screenwidth, screenHeight;

    public Home() {
        setTitle("Whack A Face");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


    // Create background label with GridBagLayout
    ImageIcon backgroundImage = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/bg.gif");
    backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setLayout(new GridBagLayout());


    // Create constraints object to specify component's layout
    GridBagConstraints c = new GridBagConstraints();


    // Title Label
    JLabel titleLabel = new JLabel("Welcome to \n Whack A Face Game!");
    titleLabel.setFont(new Font("Algerian", Font.BOLD, 95));
    titleLabel.setForeground(Color.YELLOW);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    String[] lines = titleLabel.getText().split("\n");
    String splitText = String.join("<br>", lines);
    titleLabel.setText("<html><div style='text-align: center;'>" + splitText + "</div></html>");
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 0, 0, 0); // Top margin
    backgroundLabel.add(titleLabel, c);


    // Second Title Label
    JLabel titleLabel2 = new JLabel("Created By: \n Eiron Clark David, Kent Nicholas Dela Cruz, \n Kate Bernadette Rosaldo, Kyla Bridget Rosaldo");
    titleLabel2.setFont(new Font("Algerian", Font.BOLD, 25));
    titleLabel2.setForeground(Color.PINK);
    titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    String[] lines2 = titleLabel2.getText().split("\n");
    String splitText2 = String.join("<br>", lines2);
    titleLabel2.setText("<html><div style='text-align: center;'>" + splitText2 + "</div></html>");
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(350, 0, 0, 0); // Top margin
    backgroundLabel.add(titleLabel2, c);


    // Start Button
    ImageIcon startIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/startbutton-removebg-preview.png");
    JButton startButton = new JButton(startIcon);
    startButton.setBorder(null);
    startButton.setContentAreaFilled(false);
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(50, 0, 0, 0); // Top margin
    backgroundLabel.add(startButton, c);


    //start button event
     startButton.addActionListener(new Options(this));

      //Start button Hover Event
      startButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            startButton.setIcon(new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/start hover button.png"));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            startButton.setIcon(new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/startbutton-removebg-preview.png"));
        }
    });

    // Exit Button
    ImageIcon exitIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/exitbutton-removebg-preview.png");
    JButton exitButton = new JButton(exitIcon);
    exitButton.setBorder(null);
    exitButton.setContentAreaFilled(false);
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(20, 0, 0, 0); // Top margin
    backgroundLabel.add(exitButton, c);


    //exit button event
    exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0); // exit the program when the button is clicked
        }
    });
    //hover event
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            exitButton.setIcon(new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/exit hover button.png"));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            exitButton.setIcon(new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/exitbutton-removebg-preview.png"));
        }
    });


    // Add background label to the frame
    add(backgroundLabel);
    pack();
    setLocationRelativeTo(null);

    //background music method call
    playBackgroundMusic();  


}
private Clip clip;

    //background music
    private void playBackgroundMusic() {
        try {
            // Open an audio input stream from the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/Backgroundmusic.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void stopBackgroundMusic() {
        clip.stop();
    }
}
