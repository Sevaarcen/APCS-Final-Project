package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import base.Encounter;
import base.Game;
import base.MonsterDB;
import base.MonsterDB.Monsters;
import base.ThreadController;
import consumableTypes.banana;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = 7257452992895589856L;

	JMenu file, edit, debugTools;
	JMenuItem newGame, help, clearOutput, DBCombat, DBGodmode, DBLevel, DBImmortal, DBCBTurns, DBItem;
	JCheckBox sound, debugging;
	GUIManager GUI;

	// EXTRA make a help menu!
	public Menu(GUIManager gui) {
		GUI = gui;
		file = new JMenu("FILE");
		edit = new JMenu("EDIT");
		debugTools = new JMenu("Debug Tools");

		DBCombat = new JMenuItem("Combat");
		DBCombat.addActionListener(new MenuListener());
		DBLevel = new JMenuItem("Level Up");
		DBLevel.addActionListener(new MenuListener());
		DBCBTurns = new JMenuItem("Combat Turns x25");
		DBCBTurns.addActionListener(new MenuListener());
		DBGodmode = new JMenuItem("Level Up x10");
		DBGodmode.addActionListener(new MenuListener());
		DBImmortal = new JMenuItem("Immortality");
		DBImmortal.addActionListener(new MenuListener());
		DBItem = new JMenuItem("New Item");
		DBItem.addActionListener(new MenuListener());
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new MenuListener());
		// help = new JMenuItem("Help");
		// help.addActionListener(new MenuListener());
		clearOutput = new JMenuItem("Clear Output");
		clearOutput.addActionListener(new MenuListener());
		sound = new JCheckBox("Sound on");
		sound.addActionListener(new MenuListener());
		sound.setSelected(false);
		Game.soundEnabled = sound.isSelected();
		debugging = new JCheckBox("Debugging");
		debugging.addActionListener(new MenuListener());
		debugging.setSelected(false);

		file.add(newGame);
		// file.add(help);
		file.add(clearOutput);
		edit.add(sound);
		edit.add(debugging);
		debugTools.add(DBCombat);
		debugTools.add(DBCBTurns);
		debugTools.add(DBLevel);
		debugTools.add(DBGodmode);
		debugTools.add(DBImmortal);
		debugTools.add(DBItem);
		add(file);
		add(edit);
		if (debugging.isSelected())
			add(debugTools);

		// This performs an actionevent to override Game's soundEnabled = to
		// sound enabled here
		MenuListener plz = new MenuListener();
		plz.actionPerformed(new ActionEvent(sound, 0, null));
	}

	private class MenuListener implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == newGame)
				Game.newGame();
			if (e.getSource() == clearOutput)
				GUI.clearConsole();
			if (e.getSource() == sound) {
				Game.soundEnabled = sound.isSelected();
				if (!sound.isSelected()) {
					ThreadController.stopSound();
				} else if (sound.isSelected()) {
					ThreadController.resumeSound();
				}
			}
			if (e.getSource() == debugging)
				if (debugging.isSelected()) {
					add(debugTools);
					// V John Cena V
//					Game.playSFX("testSFX.wav");
				} else
					remove(debugTools);
			if (e.getSource() == DBCombat) {
				Encounter encounter = new Encounter(Game.player, MonsterDB.createNew(Monsters.ASSASSINVINE));
				Game.enterEncounter(encounter);
			}
			if (e.getSource() == DBCBTurns)
				if (Game.encounter != null)
					Game.encounter.combatTurnAmountSim(25);
			if (e.getSource() == DBGodmode) {
				Game.player.gainXP(250);
			}
			if (e.getSource() == DBLevel) {
				Game.player.gainXP(25);
			}
			if (e.getSource() == DBImmortal) {
				Game.player.setMaxHP(1000000);
			}
			if (e.getSource() == DBItem) {
				Game.player.getInventory().add(new banana());
			}
			validate();
			GUI.repaint();
			GUI.requestFocus();
		}

	}

	public boolean isSoundEnabled() {
		return sound.isSelected();
	}
}
