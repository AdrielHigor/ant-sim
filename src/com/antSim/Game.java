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
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
  
  private final int DELAY = (1000/8);

  private int antX;
  private int antY;
  private int antSpeed = 16;
  private Image ant;
  private Map<Integer, ArrayList<Integer>> colony = new HashMap<>();
  private Timer timer;

  public Game() {
    initGame();
  }

  public void initGame() {
    addKeyListener(new KeyHandler());
    setFocusable(true);
    setBackground(Color.BLACK);

    createColonyAnts();
    loadImages();

    timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawImage(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    colonyUpdate();
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

  private int getNewColonyAntPosition(int direction, int oldPosition) {
    int newPos = 0;

    switch (direction) {
      case 0:
        if ((oldPosition + antSpeed) <= 434) {
          newPos = oldPosition + antSpeed;
        } else {
          newPos = oldPosition;
        }
        break;
      case 1:
        if ((oldPosition - antSpeed) >= -16) {
          newPos = oldPosition - antSpeed;
        } else {
          newPos = oldPosition;
        }
        break;
      default:
        break;
    }

    return newPos;
  }

  private void loadImages() {
    ImageIcon iia = new ImageIcon("src/assets/ant.png");
    ant = iia.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
  }

  private void colonyUpdate() {
    for (int index = 0; index < colony.size(); index++){
      ArrayList<Integer> colonyAnt = colony.get(index);
      int oldColonyAntX = colonyAnt.get(0);
      int oldColonyAntY = colonyAnt.get(1);
     
      Random rand = new Random();
      int upperbound = 2;

      int colonyAntX = getNewColonyAntPosition(rand.nextInt(upperbound), oldColonyAntX);
      int colonyAntY = getNewColonyAntPosition(rand.nextInt(upperbound), oldColonyAntY);

      colony.put(index, new ArrayList<Integer>(Arrays.asList(colonyAntX, colonyAntY)));
    }
  }

  private class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();

      if (key == KeyEvent.VK_RIGHT && (antX + antSpeed) <= 434) {
        antX += antSpeed;
      }  
      if (key == KeyEvent.VK_UP && (antY - antSpeed) >= -16) {
        antY -= antSpeed;
      }    
      if (key == KeyEvent.VK_DOWN && antY + antSpeed <= 434) {
        antY += antSpeed;
      }    
      if (key == KeyEvent.VK_LEFT && (antX - antSpeed) >= -16) {
        antX -= antSpeed;
      }  
    }
  }
}
