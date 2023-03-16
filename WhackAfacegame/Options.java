import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.*;


public class Options implements ActionListener {
   
    private Home frame;
    JFrame newFrame;
    private String moleColor;
    private String name;

    public Options(Home frame) {
        this.frame = frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a new JFrame object to open the new window
        newFrame = new JFrame("Whack A Face");
        newFrame.setResizable(true);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        // Add background image
        ImageIcon backgroundImage = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/background2.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage) {
            // Override the paintComponent method to draw the image
            @Override
            public void paintComponent(Graphics g) {
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        newFrame.setContentPane(backgroundLabel);


        // Customize the appearance of the dialog box
        UIManager.put("OptionPane.background", java.awt.Color.PINK);
        UIManager.put("Panel.background", java.awt.Color.PINK);
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Algerian", java.awt.Font.BOLD, 50));
        UIManager.put("OptionPane.messageForeground", java.awt.Color.BLACK);


        // Show the dialog box to get the username
        while (true) {
            name = (String) JOptionPane.showInputDialog(
            newFrame,                                 // Parent component
            "Enter Player Name:",             // Message to display
            "Player name",                      // Title of the dialog
            JOptionPane.PLAIN_MESSAGE,                // Message type
            null,                               // Icon (default)
            null,                    // Options (default)
            "");                               // Default value

            if (name != null && !name.trim().isEmpty()) {
                JLabel nameLabel = new JLabel("Player Name: " + name);
                nameLabel.setFont(new Font("Algerian", Font.BOLD, 30));
                nameLabel.setForeground(Color.YELLOW);
                nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                nameLabel.setBounds(4, 40, 600, 130);
                newFrame.add(nameLabel);

                newFrame.setVisible(true);
                // Close the current window
                frame.dispose();
                break;
            } else {
                int option = JOptionPane.showConfirmDialog(newFrame, "Please enter your name.", "Player name", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.CANCEL_OPTION) {
                    continue;
                }
            }
        }
       
        

        //mole color selection
        String[] options = {"Purple", "Yellow", "Brown"};
        String selection = null;

        // Repeat the color selection block until a valid selection is made
        while (selection == null) {
            selection = (String) JOptionPane.showInputDialog(
                    newFrame,
                    "Choose Mole Color:",
                    "Color Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (selection == null) {
                // Show message and display color selection options again
                JOptionPane.showMessageDialog(newFrame, "Please select a mole color.", "Color Selection", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (selection) {
                    case "Purple":
                        moleColor = "purple";
                        break;
                    case "Yellow":
                        moleColor = "yellow";
                        break;
                    case "Brown":
                        moleColor = "brown";
                        break;
                }
            }
        }

            difficultySelection();
        }

    private void difficultySelection(){


       //select difficulty
        // Title Label
        JLabel SelectLabel = new JLabel("SELECT DIFFULTY");
        SelectLabel.setFont(new Font("Algerian", Font.BOLD, 50));
        SelectLabel.setForeground(Color.YELLOW);
        SelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SelectLabel.setBounds(690, 30, 800, 500);
        newFrame.add(SelectLabel);

        //instructions
        ImageIcon imageIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/My project.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(100, 85, 5000, 800);
        newFrame.add(imageLabel);

        //easybutton
        ImageIcon buttonIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/easy button.png");
        int newWidth = 300; // Replace with desired width
        int newHeight = 120; // Replace with desired height
        Image image = buttonIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image);
        JButton easyButton = new JButton(newIcon);
        easyButton.setBorder(null);
        easyButton.setBounds(950, 350, newWidth, newHeight);
        easyButton.setOpaque(false);
        easyButton.setContentAreaFilled(false);

        //event
        easyButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                disposition();
                Game g = new Game(newFrame, 60, name, moleColor, 3000);
               
            }    
        });

        //hover event
        easyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {     

                 ImageIcon easyBtnHoverIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/easy hover button.png");
                ImageIcon img = new ImageIcon(easyBtnHoverIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                easyButton.setIcon(img);            
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                easyButton.setIcon(newIcon);          
            }
        });

        newFrame.add(easyButton);


        //mediumbutton
        ImageIcon button2 = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/mediumbutton.png");
        int newWidth1 = 320; // Replace with desired width
        int newHeight1 = 130; // Replace with desired height
        Image image1 = button2.getImage().getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(image1);
        JButton mediumButton = new JButton(icon2);
        mediumButton.setBorder(null);
        mediumButton.setBounds(940, 490, newWidth1, newHeight1);
        mediumButton.setOpaque(false);
        mediumButton.setContentAreaFilled(false);
        newFrame.add(mediumButton);


        //add event to medium button
        mediumButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                disposition();
                Game g = new Game(newFrame, 40, name, moleColor, 2000);

               
            }    
        });

        //hover event
        mediumButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ImageIcon mediumBtnHoverIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/medium hover button.png");
                ImageIcon img = new ImageIcon(mediumBtnHoverIcon.getImage().getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH));
                mediumButton.setIcon(img);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mediumButton.setIcon(icon2);
            }
        });
        newFrame.add(mediumButton);


        //hardbutton
        ImageIcon button3 = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/hard button.png");
        int newWidth2 = 320; // Replace with desired width
        int newHeight2 = 160; // Replace with desired height
        Image image2 = button3.getImage().getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(image2);
        JButton hardButton = new JButton(icon3);
        hardButton.setBorder(null);
        hardButton.setBounds(940, 600, newWidth2, newHeight2);
        hardButton.setOpaque(false);
        hardButton.setContentAreaFilled(false);
        newFrame.add(hardButton);


        //add event to hard button
        hardButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                disposition();
                Game g = new Game(newFrame, 30, name, moleColor, 1000);

            }    
        });

        //hover event
        hardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {    
                ImageIcon hardBtnHoverIcon = new ImageIcon("C:/Users/kentl/Downloads/WHACKAFACEGAME/WHACKAFACEGAME/res/hard hover button.png");
                ImageIcon img = new ImageIcon(hardBtnHoverIcon.getImage().getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH));
                hardButton.setIcon(img);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hardButton.setIcon(icon3);
            }
        });
        newFrame.add(hardButton);
        newFrame.revalidate();
        newFrame.repaint();
    }

     void disposition(){
        newFrame.dispose();
}

}
