package com.antSim;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
  
  private int antX;
  private int antY;
  private int antSpeed = 16;
  private Image ant;
  private Map<Integer, ArrayList<Integer>> colony = new HashMap<>();

  public Game() {
    initGame();
  }

  public void initGame() {
    addKeyListener(new KeyHandler());
    setFocusable(true);
    setBackground(Color.BLACK);

    createColonyAnts();
    loadImages();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawImage(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    System.out.println(e);

    repaint();
  }

  private void drawImage(Graphics g) {
    g.drawImage(ant, antX, antY, this);

    for (int index = 0; index < colony.size(); index++){
      ArrayList<Integer> colonyAnt = colony.get(index);
      int colonyAntX = colonyAnt.get(0);
      int colonyAntY = colonyAnt.get(1);

      g.drawImage(ant, colonyAntX, colonyAntY, this);
    }
  }

  private void createColonyAnts() {
    int index = 0;

    while (index < 5) {
      colony.put(index, new ArrayList<Integer>(Arrays.asList(0, 0)));
      index ++;
    }
  }

  private void loadImages() {
    ImageIcon iia = new ImageIcon("src/assets/ant.png");
    ant = iia.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
  }

  private class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();

      if (key == KeyEvent.VK_RIGHT) {
        antX += antSpeed;
      }  
      if (key == KeyEvent.VK_UP) {
        antY -= antSpeed;
      }    
      if (key == KeyEvent.VK_DOWN) {
        antY += antSpeed;
      }    
      if (key == KeyEvent.VK_LEFT) {
        antX -= antSpeed;
      }  

      for (int index = 0; index < colony.size(); index++){
        Random rand = new Random();
        int upperbound = 450;

        int colonyAntX = rand.nextInt(upperbound);
        int colonyAntY = rand.nextInt(upperbound);
  
        colony.put(index, new ArrayList<Integer>(Arrays.asList(colonyAntX, colonyAntY)));
      }
      
      repaint();
    }
  }
}
