package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.GUIManager.State;
import GUI.Tile.Terrain;
import base.Game;
import base.Player;

public class PlayerControls extends JPanel {
	private static final long serialVersionUID = 8041281435028234951L;

	boolean inCombat, inMainView;
	private Player player;
	JButton rest, interact, lookAround, attack, run, playerInfo;
	ConsumablesMenu potions;
	GUIManager GUI;

	public PlayerControls(GUIManager GUI) {
		this.GUI = GUI;
		player = Game.player;
		setPreferredSize(new Dimension(1080, 85));
		setLayout(new GridLayout(1, 5));
		rest = new JButton("Rest");
		interact = new JButton("Interact");
		lookAround = new JButton("Look Around");
		attack = new JButton("Attack");
		run = new JButton("Run");
		playerInfo = new JButton("Toggle Player Info");

		rest.addActionListener(new ControlListener());
		interact.addActionListener(new ControlListener());
		lookAround.addActionListener(new ControlListener());
		attack.addActionListener(new ControlListener());
		run.addActionListener(new ControlListener());
		playerInfo.addActionListener(new ControlListener());

		potions = new ConsumablesMenu(GUI, player.getInventory().getConsumables());

		inCombat = false;
		inMainView = true;
		change(State.MAP);
		player = Game.player;
	}

	public void change(State s) {
		removeAll();
		if (s == State.COMBAT)
			combatState();
		else
			mapState();
		validate();
	}

	public void combatState() {
		inCombat = true;
		add(attack);
		add(run);
		add(potions);
		add(playerInfo);
	}

	public void mapState() {
		add(rest);
		add(interact);
		add(potions);
		add(playerInfo);
		inCombat = false;
	}

	private class ControlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if (src == rest) {
				if (Game.map.getTypeAtPlayerPos() == Terrain.PLAIN)
					player.fieldRest();
				else
					player.rest();
				GUI.repaint();
			}
			if (src == interact)
				Game.map.interact();
			if (e.getSource() == playerInfo) {
				if (inMainView)
					GUI.changeView(new PlayerInfoGUI(Game.player), false);
				else
					GUI.changeView(GUI.getContent(), false);
				inMainView = !inMainView;
			}

			if (e.getSource() == attack) {
				if (Game.encounter != null)
					Game.encounter.combatTurn();
			}
			if (e.getSource() == run) {
				if (Game.encounter != null)
					Game.encounter.runAway();
			}
			GUI.requestFocus();
		}

	}
}
