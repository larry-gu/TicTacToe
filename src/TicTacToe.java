import java.awt.*; // Abstract Window Toolkit for GUI/window-based apps
import java.awt.event.*; // Provides interfaces/classes for different types of fired events
import java.util.*; // contains collections framework, legacy collection classes, misc utility classes
import javax.swing.*;// extension of the AWT 

public class TicTacToe implements ActionListener{

    Random random = new Random(); // Determines whose turn it is randomly
    JFrame frame = new JFrame(); // Create a new window
    JPanel title_panel = new JPanel(); // Creates panel
    JPanel button_panel = new JPanel(); // Button panel
    JLabel textfield = new JLabel(); // Creates label with text
    JButton[] buttons = new JButton[9]; // Playing field
    boolean player1_turn; // determines which player's turn

    TicTacToe(){
        
        /*
         * --------Frame------------
         */
        // Ensures the program exits when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the width and height of the window to 800x800 pixels
        frame.setSize(800, 800);

        // Changes the background color of the window to a dark gray (RGB: 50, 50, 50)
        frame.getContentPane().setBackground(new Color(50, 50, 50));

        // Sets the layout manager to BorderLayout, which helps arrange components in five regions
        // North, South, East, West, and Center
        frame.setLayout(new BorderLayout());

        // Makes the window visible to the user (must be called last after setting up the frame)
        frame.setVisible(true);


        /*
         * -----------Textfield----------
         */

         // Sets the background color of the text field
         textfield.setBackground(new Color(25, 25, 25));
         
         // Sets the text color to bright green
         textfield.setForeground(new Color(25, 255, 0));

         // Sets the font style to "Ink Free", makes it bold, and sets the font size to 75
         textfield.setFont(new Font("Ink Free", Font.BOLD, 75));

         // Aligns the text to the center of the textfield
         textfield.setHorizontalAlignment(JLabel.CENTER);

         // Sets the text displated in the textfield to "Tic-Tac-Toe"
         textfield.setText("Tic-Tac-Toe");
         
         // Makes the background color of the text field visible
         textfield.setOpaque(true);


         /*
          * ---------Title Panel-------
          */

          // Sets layout manager of the panel to BorderLayout, allowing components to be placed
          // in different regions: North, South, East, West, Center
          title_panel.setLayout(new BorderLayout());
          
          // Defines the position and size of the panel:
          // (x = 0, y = 0) -> Positioned at the top-left corner of the frame
          // Width = 800, Height = 100 -> The panel spans the full width of the frame and has a height of 100 pixels
          title_panel.setBounds(0, 0, 800, 100);

          // Sets layout manager of the panel to GridLayout with 3 rows/3cols
          // This arranges components in a grid-like structure with 9 cells (3x3)
          button_panel.setLayout(new GridLayout(3, 3));
          // Sets the bg color of the panel to a light gray (RGB: 150, 150, 150)
          button_panel.setBackground(new Color(150, 150, 150));

          // for loop to create 9 buttons
          for(int i = 0; i < 9; i++) {
            // Creates a new button and stores it in buttons array at index i
            buttons[i] = new JButton();
            // Adds the newly created button to the button_panel, making it visible on the panel
            button_panel.add(buttons[i]);
            // Sets the font of the button text
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            // Makes sure the button cannot be focused (prevents focus border when being clicked)
            buttons[i].setFocusable(false);
            // Make buttons opaque
            buttons[i].setOpaque(true);
            // Adds an ActionListener to each button, so the 'actionPerformed' method in the class
            // (implenmenting ActionListner) is triggered when the button is clicked
            buttons[i].addActionListener(this);
          }


          // Adds the textfield to the panel, so it appears inside this section of the window
          title_panel.add(textfield);
          // Adds panel to the frame, making it a visible part of the GUI
          frame.add(title_panel, BorderLayout.NORTH);
          // Adds 3x3 grid to the frame
          frame.add(button_panel);

          firstTurn();

    }

    @Override
    // This method is called whenevre a button is clicked, since they are registered with ActionListener
    public void actionPerformed(ActionEvent e) {
        // Loops through all 9 buttons to find out which one was clicked
        for(int i = 0; i < 9; i++) {
            // If button clicked matched current button in the loop
            if(e.getSource()==buttons[i]) {
                // If it's player1's turn
                if(player1_turn) {
                    // Check if button is empty
                    if(buttons[i].getText()=="") {
                        // set text color to red for player 1
                        buttons[i].setForeground(new Color(255, 0, 0));
                        // set text to "X"
                        buttons[i].setText("X");
                        // switch to player 2's turn
                        player1_turn = false;
                        // update textfield to show player 2's turn
                        textfield.setText("O turn");
                        // calls 'check()' to see if game has been won
                        check();
                    }
                } else {
                    if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {

        // Adds delay of 2000ms before displaying first player's turn
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // if random == 0, X goes first
        if(random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
            // else O goes first
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }

    }

    public void check() {
        // Check X win conditions
        if( (buttons[0].getText() == "X") && (buttons[1].getText() == "X") &&
            (buttons[2].getText() == "X")) {
                xWins(0, 1, 2);
        }

        if( (buttons[3].getText() == "X") && (buttons[4].getText() == "X") &&
            (buttons[5].getText() == "X")) {
                xWins(3, 4, 5);
        }

        if( (buttons[6].getText() == "X") && (buttons[7].getText() == "X") &&
            (buttons[8].getText() == "X")) {
                xWins(6, 7, 8);
        }

        if( (buttons[0].getText() == "X") && (buttons[3].getText() == "X") &&
        (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }

        if( (buttons[1].getText() == "X") && (buttons[4].getText() == "X") &&
        (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }

        if( (buttons[2].getText() == "X") && (buttons[5].getText() == "X") &&
        (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }

        if( (buttons[0].getText() == "X") && (buttons[4].getText() == "X") &&
        (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }

        if( (buttons[2].getText() == "X") && (buttons[4].getText() == "X") &&
        (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }

        // Check O win conditons
        if( (buttons[0].getText() == "O") && (buttons[1].getText() == "O") &&
        (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
        }

        if( (buttons[3].getText() == "O") && (buttons[4].getText() == "O") &&
        (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
        }

        if( (buttons[6].getText() == "O") && (buttons[7].getText() == "O") &&
            (buttons[8].getText() == "O")) {
                oWins(6, 7, 8);
        }

        if( (buttons[0].getText() == "O") && (buttons[3].getText() == "O") &&
        (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
        }

        if( (buttons[1].getText() == "O") && (buttons[4].getText() == "O") &&
        (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
        }

        if( (buttons[2].getText() == "O") && (buttons[5].getText() == "O") &&
        (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
        }

        if( (buttons[0].getText() == "O") && (buttons[4].getText() == "O") &&
        (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
        }

        if( (buttons[2].getText() == "O") && (buttons[4].getText() == "O") &&
        (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
        }

    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i <9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins!");


    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0; i <9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins!");
    }    
}

