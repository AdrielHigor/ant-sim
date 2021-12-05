package com.antSim;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame{

  public Main() {
    initUI();
  }

  private void initUI() {
    add(new Game());

    setResizable(false);
    setSize(450, 450);
    setTitle("Ant Sim");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      JFrame game = new Main();
      game.setVisible(true);
    });
  }
}