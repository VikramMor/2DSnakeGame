/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2d.snake.game;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author MOR
 */
public class SnakeGame {

      /**
       * @param args the command line arguments
       */
      public static void main(String[] args) {
            JFrame frame = new JFrame("Snake 2D");
            frame.setBounds(10, 10, 905, 700);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GamePanel panel = new GamePanel();
            frame.add(panel);// it will show error when adding another class as we can only add components in JFrame
            // and not another class, so we will extend the GamePanel Class to JPanel which will remove the error.

            panel.setBackground(Color.DARK_GRAY);

            frame.setVisible(true);
      }

}
