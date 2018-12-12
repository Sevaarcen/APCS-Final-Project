package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.*;

public class GUIManager extends JFrame {
	private static final long serialVersionUID = 2052473337102841440L;

	JPanel mainView, content;
	Map map;
	CardLayout cardLayout;
	PlayerControls controls;
	Menu menu;

	public enum State {
		MAP, COMBAT, GAMEOVER
	};

	static JTextArea textOutput;

	public GUIManager(int xPos, int yPos, Map m) {
		super("Cole's APCS Final");
		setResizable(false);
		addKeyListener(new Controller());
		setFocusable(true);
		setLayout(new BorderLayout());
		setBounds(new Rectangle(xPos, yPos, 1166, 953));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		cardLayout = new CardLayout();
		map = m;
		mainView = new JPanel();
		mainView.setLayout(cardLayout);
		mainView.setPreferredSize(new Dimension(800, 800));
		mainView.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 5, false));

		textOutput = new JTextArea();
		textOutput.setEditable(false);
		JScrollPane out = new JScrollPane(textOutput);
		out.setPreferredSize(new Dimension(350, 100));
		menu = new Menu(this);
		setJMenuBar(menu);
		controls = new PlayerControls(this);
		add(mainView, BorderLayout.CENTER);
		add(out, BorderLayout.EAST);
		add(controls, BorderLayout.SOUTH);
		content = map;

		changeView(content, true);
		setVisible(true);
	}

	public void output(String outputText) {
		textOutput.append('\n' + outputText);
	}

	public void changeView(JPanel newView, boolean newDefault) {
		mainView.removeAll();
		mainView.add(newView, SwingConstants.CENTER);
		mainView.validate();
		if (newDefault)
			content = newView;
		repaint();
		requestFocus();
	}

	public void changeState(State s) {
		controls.change(s);
		repaint();
		requestFocus();
	}

	public JPanel getContent() {
		return content;
	}

	public Menu getMenu() {
		return menu;
	}

	public void clearConsole() {
		textOutput.setText("");
	}

	public PlayerControls getController() {
		return controls;
	}

	public String getLastOutput() {
		return textOutput.getText().substring(textOutput.getText().lastIndexOf('\n'));
	}
}
