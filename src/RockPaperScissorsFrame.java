import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    //JPanels
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel statsPanel;
    JPanel resultsPanel;

    //Button Panel( three buttons with images and quit)
    JLabel titleLabel;
    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton quitButton;

    //Stats Panel( three Labels with text fields)
    JLabel playWinsLabel;
    JTextField playerWinsField;

    JLabel compWinsLabel;
    JTextField compWinsField;

    JLabel tiesLabel;
    JTextField tiesField;

    //Results Panel ( TextArea inside ScrollPane)
    JTextArea resultsArea;
    JScrollPane scroller;

    //initialized variables
    int compWins = 0, playerWins = 0, ties = 0;

    public RockPaperScissorsFrame(){

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        //methods
        createButtonPanel();
        mainPanel.add(buttonPanel,BorderLayout.NORTH);
        createStatsPanel();
        mainPanel.add(statsPanel,BorderLayout.CENTER);
        createResultsPanel();
        mainPanel.add(resultsPanel,BorderLayout.SOUTH);


        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createButtonPanel(){

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBorder(new EtchedBorder());

        titleLabel = new JLabel("Lets Play Rock Paper Scissors!");
        //alignment of text with button/icons
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        buttonPanel.add(titleLabel);

        //rock button with image
        rockButton = new JButton(new ImageIcon("src/rock.png"));
        rockButton.addActionListener(e ->playGame("Rock"));

        //paper button with image
        paperButton = new JButton(new ImageIcon("src/paper.png"));
        paperButton.addActionListener(e ->playGame("Paper"));

        //scissors button with image
        scissorsButton = new JButton(new ImageIcon("src/scissors.png"));
        scissorsButton.addActionListener(e ->playGame("Scissors"));

        //quit button with image
        quitButton = new JButton(new ImageIcon("src/quit.png"));
        quitButton.addActionListener(e -> System.exit(0));

        //add buttons to panel
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

    }
    private void createStatsPanel(){

        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1,3));
        statsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Game Stats:" ));

        //player wins display set to non-editable
        playWinsLabel = new JLabel("Player Wins");
        playerWinsField = new JTextField("0",5);
        playerWinsField.setEditable(false);

        //computer wins display set to non-editable
        compWinsLabel = new JLabel("Computer Wins");
        compWinsField = new JTextField("0",5);
        compWinsField.setEditable(false);

        //ties display set to non-editable
        tiesLabel = new JLabel("Ties");
        tiesField = new JTextField("0",5);
        tiesField.setEditable(false);

        //labels and text fields are added to panel
        statsPanel.add(playWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(compWinsLabel);
        statsPanel.add(compWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);
    }
    private void createResultsPanel(){

        resultsPanel = new JPanel();
        resultsPanel.setBorder((new TitledBorder(new EtchedBorder(),"Game Results:" )));

        //results text area non-editable
        resultsArea = new JTextArea(12,40);
        resultsArea.setEditable(false);

        // Scroll Pane added into the text area
        scroller = new JScrollPane(resultsArea);
        resultsPanel.add(scroller);
    }
     // method determines computer moves
    public void playGame(String playersChoice){
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(3)];
        String result;
        //conditions
        if (playersChoice.equals(computerChoice)) {
            ties++; // counter increases
            result = "It's a Tie!";
        } else if ((playersChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playersChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playersChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerWins++; // counter increases
            result = playersChoice + " beats " + computerChoice + " (Player Wins)";
        } else {
            compWins++; // counter increases
            result = computerChoice + " beats " + playersChoice + " (Computer Wins)";
        }
        // Update results area
        resultsArea.append(result + "\n");

        // Update stats fields
        playerWinsField.setText(String.valueOf(playerWins));
        compWinsField.setText(String.valueOf(compWins));
        tiesField.setText(String.valueOf(ties));

    }


}
