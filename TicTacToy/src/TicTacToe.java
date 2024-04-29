import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe{
    int boardwidth = 600;
    int boardHeight = 700; //50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel reset = new JPanel();
    JButton resetButton = new JButton("Reset");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O"; 
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardwidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.gray);
        textLabel.setForeground(Color.yellow);
        textLabel.setFont(new Font("Arial",Font.BOLD,45));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH );
        frame.add(reset, BorderLayout.SOUTH);
        reset.add(resetButton);
        reset.setBackground(Color.gray);
        resetButton.setBounds(0,600,600,600);
        resetButton.setBackground(Color.gray);
        resetButton.setForeground(Color.white);
        resetButton.setFont(new Font("arial",Font.BOLD,25));
        resetButton.setHorizontalAlignment(JLabel.CENTER);
        resetButton.setSize(400,400);
        resetButton.setFocusable(false);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        for (int r = 0; r < 3; r++){
            for(int c =0; c<3;c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("serif", Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText()== ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkwinner();
                            if(!gameOver){
                             currentPlayer = currentPlayer == playerX ?  playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
         

                            }
                            
                        }
                       
                    }
                });

                resetButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (gameOver) return;
                        JButton resetButton = (JButton) e.getSource();
                        resetGame();
                    }
                
                });


            }
        }

    }

    void checkwinner(){
        //horizontal
        for(int r = 0; r< 3; r++){
           if(board[r][0].getText() == "") continue;
           
           if (board[r][0].getText() == board[r][1].getText() &&
           board[r][1].getText() == board[r][2].getText()) {
            for(int i =0; i<3; i++){
                setWinner(board[r][i]);
            }
            gameOver = true;
            return;
           }
        }

        //vertical
        for(int c=0;c<3;c++){
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()) {
                for(int i =0; i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonal
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() && 
            board[0][0].getText() != ""){
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
         }
         
         //anti-diagonaly
         if(board[0][2].getText() == board[1][1].getText() && 
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != ""){
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
            }
            if (turns==9){
                for (int r=0; r<3; r++ ){
                    for(int c =0; c<3; c++){
                        setTie(board[r][c]);
                    }
                }
                gameOver = true;
            }
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.white);
        tile.setBackground(Color.black);
        textLabel.setText(currentPlayer + " Is The Winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");

   }
   void resetGame(){
    for(int r = 0; r < 3; r++)
        for (int c=0;c<3;c++)
            board[r][c].setText("");

        gameOver = false;
        turns = 0;
        checkwinner();
   }
   
}
