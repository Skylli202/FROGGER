package menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scorebord.Scoreboard;

public class AskScoreboard implements ActionListener{
	private JFrame frame;
	private JPanel panelLabel;
	private JPanel panelButton;
	
	private JLabel label;
	private JButton score;
	private JButton ladder;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public AskScoreboard() {
		frame = new JFrame("Board Confirmation");
		panelLabel = new JPanel();
		panelButton = new JPanel();
		
		label = new JLabel("Que voulez-vous faire ?");
		ladder = new JButton("Ladderboard");
		ladder.addActionListener(this);
		ladder.setActionCommand("ladder");
		score = new JButton("Scoreboard");
		score.addActionListener(this);
		score.setActionCommand("score");
		
		frame.setLayout(new FlowLayout());
		panelLabel.setLayout(new FlowLayout());
		panelButton.setLayout(new FlowLayout());
		
		panelLabel.add(label);
		panelButton.add(ladder);
		panelButton.add(score);
		
		frame.add(panelLabel);
		frame.add(panelButton);
		frame.setResizable(false);
		frame.setSize(new Dimension(300,110));
		frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Scoreboard scoreboard = new Scoreboard();
		if("ladder".equals(e.getActionCommand())) {
			scoreboard.showRanking();
			frame.dispose();
		}
		if("score".equals(e.getActionCommand())){
			scoreboard.showAllScore();
			frame.dispose();
		}
	}
}
