package Backtracking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Graphics extends JFrame {

    ImageIcon image = new ImageIcon("src/queen.png)");
    ImageIcon logo = new ImageIcon("src/logo.png");
    public JLabel[][] board;
    public int dimension, rowp3, columnp3;
    public int index = 0;
    boolean pr4 = false;
    ArrayList<boolean[][]> solutions;
    String num = "8";
    JPanel window;
    JMenuBar outMenu, outButtons;
    JMenu Menu;
    JButton back, forward, resolves;
    JMenuItem Size, Project3, Project4;
    JTextField info, nSols;
    JOptionPane size;
    Dimension dim;

    public void initialize(int dimension) {
        this.dimension = dimension;
        window = new JPanel();
        window.setLayout(new GridLayout(dimension, dimension));
        this.setTitle("Practice 2 Backtracking");
        this.setIconImage(logo.getImage());
        outMenu = new JMenuBar();
        outButtons = new JMenuBar();
        solutions = new ArrayList<>();


        Menu = new JMenu("Menu");
        Size = new JMenuItem("Choose the board size...");
        Project3 = new JMenuItem("Project 3");
        Project4 = new JMenuItem("Project 4");
        resolves = new JButton("Solve");
        back = new JButton("<");
        forward = new JButton(">");

        initializeBoard(dimension);

        Size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeListener();
                num = JOptionPane.showInputDialog(null, "Enter the number of rows and columns", 0);
                initializeBoard(Integer.parseInt(num));
            }
        });

        Project3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectSquare();
                pr4 = false;
            }
        });

        Project4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeListener();
                pr4 = true;
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index - 1 >= 0) {
                    index--;
                    paintSolution(solutions.get(index));
                }
            }
        });

        forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index + 1 < solutions.size()) {
                    index++;
                    paintSolution(solutions.get(index));
                }
            }
        });

        resolves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pr4) {
                    Project4 p4 = new Project4(board.length);
                    solutions = p4.findSolution();
                    index = 0;
                    paintSolution(solutions.get(index));
                } else {
                    Project3 p3 = new Project3(board.length, rowp3, columnp3);
                    paintSolution(p3.findSolution());
                }
            }
        });

        Menu.add(Size);
        Menu.add(Project3);
        Menu.add(Project4);
        outMenu.add(Menu);

        outButtons.add(back);
        outButtons.add(resolves);
        outButtons.add(forward);
        outButtons.setLayout(new GridBagLayout());

        dim = new Dimension(dimension * 80, dimension * 80 + 30);

        this.getContentPane().add(outMenu, BorderLayout.NORTH);
        this.getContentPane().add(window, BorderLayout.CENTER);
        this.getContentPane().add(outButtons, BorderLayout.SOUTH);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(dim);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeBoard(int dimension) {
        this.dimension = dimension;
        window.removeAll();
        board = new JLabel[dimension][dimension];
        window.setLayout(new GridLayout(dimension, dimension));
        dim = new Dimension(dimension * 80, dimension * 80 + 30);
        window.setSize(dim);
        initSquares(dimension, board);
        window.updateUI();
    }

    private void initSquares(int dimension, JLabel[][] board) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = new JLabel();
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                paintSquares(i, j);
                window.add(board[i][j]);
            }
        }
    }

    private void paintSquares(int i, int j) {
        if ((i + j) % 2 == 0) {
            board[i][j].setBackground(Color.WHITE);
        } else {
            board[i][j].setBackground(Color.BLACK);
        }
        board[i][j].setOpaque(true);
    }

    private void paintSolution(boolean[][] solution) {
        if (solution != null) {
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution.length; j++) {
                    board[i][j].setIcon(null);
                    if (solution[i][j]) {
                        ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(board[i][j].getWidth(), board[i][j].getHeight(), Image.SCALE_DEFAULT));
                        board[i][j].setIcon(icon);
                    }
                }
            }
        }
    }

    private void selectSquare() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j].addMouseListener(new MouseListener() {
                                                   @Override
                                                   public void mouseClicked(MouseEvent me) {
                                                   }

                                                   @Override
                                                   public void mousePressed(MouseEvent me) {
                                                       for (int i = 0; i < dimension; i++) {
                                                           for (int j = 0; j < dimension; j++) {
                                                               paintSquares(i, j);
                                                           }
                                                       }
                                                       for (int i = 0; i < dimension; i++) {
                                                           for (int j = 0; j < dimension; j++) {
                                                               if (me.getSource() == board[i][j]) {
                                                                   setQueen(i, j);
                                                                   rowp3 = i;
                                                                   columnp3 = j;
                                                               }
                                                           }
                                                       }
                                                   }

                                                   @Override
                                                   public void mouseReleased(MouseEvent me) {
                                                   }

                                                   @Override
                                                   public void mouseEntered(MouseEvent me) {
                                                   }

                                                   @Override
                                                   public void mouseExited(MouseEvent me) {
                                                   }
                                               }
                );
            }
        }
    }

    private void setQueen(int i, int j) {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (board[row][col].getIcon() != null) {
                    board[row][col].setIcon(null);
                    board[row][col].revalidate();
                    paintSquares(row, col);
                }
            }
        }
        ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(board[i][j].getWidth(), board[i][j].getHeight(), Image.SCALE_DEFAULT));
        board[i][j].setIcon(icon);
    }

    public void removeListener() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                for (MouseListener ml : board[i][j].getMouseListeners()) {
                    board[i][j].removeMouseListener(ml);
                }
            }
        }
    }
}
