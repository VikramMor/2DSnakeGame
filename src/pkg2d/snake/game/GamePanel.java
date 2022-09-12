/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2d.snake.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 *
 * @author MOR
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {
      private int [] snakeXlength = new int[750]; // to make sure that the snake does not cross the limit of the size of the window
    private int [] snakeYlength = new int[750];
    private int lengthOfSnake = 3; // initial size of the snake

    private int[] xPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] yPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
// all possible positions on which the enemy can appear

    // private int [] xPos = new int[851];
    // private int [] yPos = new int[625];

    // for(int i = 25; i < 851; i+=25){
    //     xPos[i] = 
    // }

    

    private Random random = new Random();
    private int enemyX, enemyY;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private int moves = 0;
    private int score = 0;
    private int maxScore = 0;
    private boolean gameOver = false;

    private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy.png"));

    private Timer timer;
    private int delay = 100;


    GamePanel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
        newEnemy();
    }

    private void newEnemy() {
        enemyX = xPos[random.nextInt(34)]; //position of enemy from the possible locations in the arrays
        enemyY = yPos[random.nextInt(23)];

//        for(int i = lengthOfSnake-1; i > 0; i++){ // to make sure that the new enemy does not appear on the body of the snake
//            if(snakeXlength[0] == enemyX && snakeYlength[0] == enemyY){
//                newEnemy();
//            }
//        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);



        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        snaketitle.paintIcon(this, g, 25,11);

        if(moves == 0){

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString("Press Any Direction Key to Start the Game", 100, 300);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.ITALIC, 25));
            g.drawString("Mode - 1 Snake can pass through the walls.", 100, 350);
            g.drawString("Snake will die when collides with own body.", 100, 400);


            snakeXlength[0] = 100;
            snakeXlength[1] = 75;
            snakeXlength[2] = 50;

            snakeYlength[0] = 100;
            snakeYlength[1] = 100;
            snakeYlength[2] = 100;
        }

        if(left){
            leftmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        }
        if(right){
            rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        }
        if(up){
            upmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        }
        if(down){
            downmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        }

        for(int i = 1; i < lengthOfSnake; i++){
            snakeimage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
        }

        enemy.paintIcon(this, g, enemyX, enemyY);

        if(gameOver){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", 300, 300);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.ITALIC, 25));
            g.drawString("Press SPACE to Restart", 300, 350);
        }

        maxScore = Math.max(maxScore, score);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibre",Font.PLAIN, 20));
        g.drawString("Score : " + score, 750, 30);
        g.drawString("Top Score : " + maxScore, 750, 60);
        // g.drawString("Length : " + lengthOfSnake, 750, 60);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = lengthOfSnake-1; i >= 1; i--){ // to make sure that all the body parts of snake moves with the head
            snakeXlength[i] = snakeXlength[i-1];
            snakeYlength[i] = snakeYlength[i-1];
        }

        if(left){
            snakeXlength[0] = snakeXlength[0] - 25;
        }
        if(right){
            snakeXlength[0] = snakeXlength[0] + 25;
        }
        if(up){
            snakeYlength[0] = snakeYlength[0] - 25;
        }
        if(down){
            snakeYlength[0] = snakeYlength[0] + 25;
        }

        if(snakeXlength[0] > 850){ // if the snake hits the right wall it will come from the left wall
            snakeXlength[0] = 25;
        }
        if(snakeXlength[0] < 25){ // if the snake hits the left wall it will come from the right wall
            snakeXlength[0] = 850;
        }
        if(snakeYlength[0] > 625){ // if the snake hits the lower wall it will come from the upper wall
            snakeYlength[0] = 75;
        }
        if(snakeYlength[0] < 75){ // if the snake hits the upper wall it will come from the lower wall
            snakeYlength[0] = 625;
        }

        collidesWithEnemy();
        collidesWithBody();
       if(collidesWithWall()){ // will end the game if the earlier condition is removed of the crossing of the wall
           gameOver = true;
       }

        repaint();

    }

    private boolean collidesWithWall() {

        if(snakeXlength[0] > 850){ // if the snake hits the right wall it will come from the left wall
            return true;
        }
        if(snakeXlength[0] < 25){ // if the snake hits the left wall it will come from the right wall
            return true;
        }
        if(snakeYlength[0] > 625){ // if the snake hits the lower wall it will come from the upper wall
            return true;
        }
        if(snakeYlength[0] < 75){ // if the snake hits the upper wall it will come from the lower wall
            return true;
        }
        return false;
    }

    private void collidesWithBody() {
        for(int i = lengthOfSnake-1; i > 0; i--){
            if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]){
                timer.stop();
                gameOver = true;
            }
        }
    }

    private void collidesWithEnemy() {
        if(snakeXlength[0] == enemyX && snakeYlength[0] == enemyY){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            restart();
        }


            if(e.getKeyCode() == KeyEvent.VK_LEFT && !right){
            left = true;
            right = false;
            up = false;
            down = false;
            moves++;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && !left){
            left = false;
            right = true;
            up = false;
            down = false;
            moves++;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && !down){
            left = false;
            right = false;
            up = true;
            down = false;
            moves++;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && !up){
            left = false;
            right = false;
            up = false;
            down = true;
            moves++;
        }

    }

    private void restart() {
        gameOver = false;
        moves = 0;
        score = 0;
        lengthOfSnake = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        timer.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
