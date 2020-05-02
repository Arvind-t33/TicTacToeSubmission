// ============================================================================
//     Taken and adapted from: http://programmingnotes.org/
// ============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
    // setting up ALL the variables
    JFrame window = new JFrame("Tic Tac Toe");

    JMenuBar menuMain = new JMenuBar();
    JMenuItem menuNewGame = new JMenuItem("  New Game"), 
    menuGameTitle = new JMenuItem("|Tic Tac Toe|  "),
    menuStartingPlayer = new JMenuItem(" Starting Player"),
    menuExit = new JMenuItem("    Quit");

    JButton [][] square = new JButton[3][3];

    JPanel panelNewGame = new JPanel(),
    panelNorth = new JPanel(),
    panelSouth = new JPanel(),
    panelTop = new JPanel(),
    panelBottom = new JPanel(),
    panelPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();

    private JRadioButton selectX = new JRadioButton("User Plays X", false);
    private JRadioButton selectO = new JRadioButton("User Plays O", false);
    private ButtonGroup radioGroup;
    private String startingPlayer= "";
    final int X = 800, Y = 480, color = 190; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean squareClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Rufscript", Font.BOLD, 100);
    private int movesMade = 0;
    private int maxMoves = square.length * square[0].length;

    //===============================  GUI  ========================================//
    public GUI() //This is the constructor
    {
        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(0, 0);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelNorth.setBackground(new Color(70, 70, 70));
        panelSouth.setBackground(new Color(color, color, color));

        panelTop.setBackground(new Color(color, color, color));
        panelBottom.setBackground(new Color(color, color, color));

        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(color, color, color));
        panelBottom.setBackground(new Color(color, color, color));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));

        // adding menu items to menu bar
        menuMain.add(menuGameTitle);
        menuGameTitle.setEnabled(false);
        menuGameTitle.setFont(new Font("Purisa",Font.BOLD,18));
        menuMain.add(menuNewGame);
        menuNewGame.setFont(new Font("Purisa",Font.BOLD,18));
        menuMain.add(menuStartingPlayer);
        menuStartingPlayer.setFont(new Font("Purisa",Font.BOLD,18));
        menuMain.add(menuExit);
        menuExit.setFont(new Font("Purisa",Font.BOLD,18));//---->Menu Bar Complete

        // adding X & O options to menu
        selectX.setFont(new Font("Purisa",Font.BOLD,18));
        selectO.setFont(new Font("Purisa",Font.BOLD,18));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(selectX); // add plain to group
        radioGroup.add(selectO);
        radioPanel.add(selectX);
        radioPanel.add(selectO);

        // adding Action Listener to all the Buttons and Menu Items
        menuNewGame.addActionListener(this);
        menuExit.addActionListener(this);
        menuStartingPlayer.addActionListener(this);

        // setting up the playing field
        panelPlayingField.setLayout(new GridLayout(3, 3, 2, 2));
        panelPlayingField.setBackground(Color.black);
        for(int r = 0; r < square.length; r++)   
            for(int c = 0; c < square[0].length; c++)
            {
                //creates button for each possible X/O location
                square[r][c] = new JButton();
                square[r][c].setBackground(new Color(220, 220, 220));
                square[r][c].addActionListener(this);
                panelPlayingField.add(square[r][c]);
                square[r][c].setEnabled(setTableEnabled);
            }

        // adding everything needed to panelNorth and panelSouth
        panelNorth.add(menuMain);
        Logic.showGame(panelSouth,panelPlayingField);

        // adding to window and Showing window
        window.add(panelNorth, BorderLayout.NORTH);
        window.add(panelSouth, BorderLayout.CENTER);
        window.setVisible(true);
    }// End GUI
    public boolean col(int col)
    {
        if(square[0][col].getText().equals(square[1][col].getText()) && 
        square[1][col].getText().equals(square[2][col].getText()) && square[0][col].getText().equals("X"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "X wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else
        if(square[0][col].getText().equals(square[1][col].getText()) && 
        square[1][col].getText().equals(square[2][col].getText()) && square[0][col].getText().equals("O"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "O wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean row(int row)
    {
        if(square[row][0].getText().equals(square[row][1].getText()) && 
        square[row][1].getText().equals(square[row][2].getText()) && square[row][0].getText().equals("X"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "X wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else
        if(square[row][0].getText().equals(square[row][1].getText()) && 
        square[row][1].getText().equals(square[row][2].getText()) && square[row][0].getText().equals("O"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "O wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean dia()
    {
        if(square[0][0].getText().equals(square[1][1].getText()) && 
        square[1][1].getText().equals(square[2][2].getText()) && square[0][0].getText().equals("X"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "X wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else
        if(square[0][0].getText().equals(square[1][1].getText()) && 
        square[1][1].getText().equals(square[2][2].getText()) && square[0][0].getText().equals("O"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "O wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else
        if(square[0][2].getText().equals(square[1][1].getText()) && 
        square[1][1].getText().equals(square[2][0].getText()) && square[0][2].getText().equals("X"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "X wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else
        if(square[0][2].getText().equals(square[1][1].getText()) && 
        square[1][1].getText().equals(square[2][0].getText()) && square[0][2].getText().equals("O"))
        {
            System.out.println("Game over");
            int option = JOptionPane.showConfirmDialog(null, "O wins! Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            redrawGameBoard();
            return true;
        }
        else{
            return false; 
        }
    }
    // ===========  Start Action Performed  ===============//
    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user
        Object source = click.getSource();

        // check if a button was clicked on the gameboard
        for(int rowMove=0; rowMove < square.length; rowMove++) 
            for(int colMove = 0; colMove < square[0].length; colMove++)
            {
                if(source == square[rowMove][colMove] && square[rowMove][colMove].getText().length()==0 && movesMade < maxMoves)  
                {
                    squareClicked = true;
                    Logic.getMove(rowMove, colMove, movesMade, font, 
                        square, startingPlayer);          
                    panelPlayingField.requestFocus();

                    movesMade++;
                    if(startingPlayer.equals("X"))
                        startingPlayer="O";
                    else
                        startingPlayer="X";
                    col(colMove);
                    row(rowMove);
                    dia();
                }
            }

        //if a button was clicked on the gameboard, check for a winner
        // if(squareClicked) 
        // {
            // // inGame = true;
            // // squareClicked = false;
            
        // }

        // check if the user clicks on a menu item
        if(source == menuNewGame)    
        {
            Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
                panelPlayingField,panelBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                Logic.showGame(panelSouth,panelPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " your current game will be lost..." + "\n" +"Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        Logic.showGame(panelSouth,panelPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    redrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == menuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        // select X or O player 
        else if(source == menuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                Logic.showGame(panelSouth,panelPlayingField);
            }
            else
            {
                setTableEnabled = true;
                Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
                    panelPlayingField,panelBottom,radioPanel);

                selectX.addActionListener(new RadioListener());
                selectO.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(selectX);
                radioPanel.add(selectO);
                panelSouth.setLayout(new GridLayout(2, 1, 2, 1));
                panelSouth.add(radioPanel);
                panelSouth.add(panelBottom);
            }
        }
        panelSouth.setVisible(false); 
        panelSouth.setVisible(true);  
    }// End Action Performed

    // ===========  Start RadioListener  ===============//  
    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton) event.getSource();
            if(theButton.getText().equals("User Plays X")) 
            {
                startingPlayer = "X";
            }
            if(theButton.getText().equals("User Plays O"))
            {
                startingPlayer = "O";
            }

            // redisplay the gameboard to the screen
            panelSouth.setVisible(false); 
            panelSouth.setVisible(true);          
            redrawGameBoard();
        }
    }// End RadioListener
    /*
    ----------------------------------
    Start of all the other methods.
    ----------------------------------
     */
    private void redrawGameBoard()  
    {
        Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
            panelPlayingField,panelBottom,radioPanel);
        Logic.showGame(panelSouth,panelPlayingField);       

        movesMade = 0;

        for(int row = 0; row < square.length; row++)
            for(int col = 0; col < square[0].length; col++)
            {
                square[row][col].setText("");
                square[row][col].setEnabled(setTableEnabled);
            }

        win = false;        
    }
}   
