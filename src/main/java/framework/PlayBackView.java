package framework;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayBackView {
	public static JLabel userLabel = new JLabel("Start");

	public static void main(String[] args)
	{

	}

	PlayBackView()
	{
		JFrame frame = new JFrame("Playback");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);

		frame.setVisible(true);
		panel.setLayout(null);
		userLabel.setSize(300, 150);
		userLabel.setBounds(80, 10, 200, 25);
		panel.add(userLabel);
	}

	public void placeComponents(String sAction)
	{
		userLabel.setText(sAction);
	}
}
