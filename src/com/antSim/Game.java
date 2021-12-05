package com.antSim;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Game extends JPanel implements ActionListener {
  
  private int antX;
  private int antY;
  private int antSpeed = 16;
  private Image ant;

  public Game() {
    initGame();
  }

  public void initGame() {
    addKeyListener(new KeyHandler());
    setFocusable(true);
    setBackground(Color.BLACK);

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
      
      repaint();
    }
  }
}
