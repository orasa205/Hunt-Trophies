/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

/**
 * /**
 *
 * @author DELL
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Obstacle obstacle;
    private ArrayList<Trophy> trophies;
    private Timer timer;
    private int score = 0;  // คะแนนสำหรับการเก็บถ้วยรางวัล
    private boolean gameStarted = false;
    private JButton startButton;
    private JLabel titleLabel; // เพิ่ม JLabel สำหรับข้อความ
    private boolean winState = false;
    private boolean gameOver = false;
    private ArrayList<Obstacle> obstacles; // เก็บอุปสรรคหลายอัน
    private Image backgroundImage;
    private int backgroundX1;
    private int backgroundX2;
    private final int backgroundWidth;
    private final int backgroundHeight;
    private Person Person;
    private Graphics g;
    private JLabel winLabel;
    private JLabel loseLabel;
    private Image trophyImage0;
    private Image trophyImage1;
    private Image trophyImage2;
    private Image trophyImage3;
    private int currentLevel = 1; // ตั้งค่าเริ่มต้นสำหรับ currentLevel
    private final int totalLevels = 3; // หรือจำนวนด่านที่คุณมี
    private ImageIcon backgroundIcon;  // Declare as a class-level variable
    private int speed = 0;
    private int level = 1;  // ระดับของเกม
    private int obstacleSpeed = 10;  // ความเร็วของอุปสรรคเริ่มต้น
    private final int[] trophiesRequired = {3, 5,7};
    int lastTrophyX = 0;
    private Clip backgroundMusic;
    private Clip trophySound;
    private Clip loseSound;
    private Clip winSound;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 400));
        setBackgroundImage();
//        loadSounds();
        
        // โหลดรูปภาพที่แสดงเมื่อชนะและแพ้
        ImageIcon winIcon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Win.png");
        winLabel = new JLabel(winIcon);
        winLabel.setBounds(250, 125, 300, 150);  // รูปขนาด 300x150 ตรงกลางหน้าจอ
        winLabel.setVisible(false);  // ซ่อนไว้ก่อน
        this.add(winLabel);

        ImageIcon loseIcon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Lose.png");
        loseLabel = new JLabel(loseIcon);
        loseLabel.setBounds(250, 125, 300, 150);  // รูปขนาด 300x150 ตรงกลางหน้าจอ
        loseLabel.setVisible(false);  // ซ่อนไว้ก่อน
        this.add(loseLabel);

        // ตรวจสอบความกว้างของภาพพื้นหลัง
        backgroundWidth = 800; // ความกว้างของภาพพื้นหลัง
        backgroundHeight = 400; // ความสูงของภาพพื้นหลัง

        // ตั้งค่าตำแหน่งเริ่มต้นของพื้นหลัง
        backgroundX1 = 0;  // เริ่มต้นที่ตำแหน่ง 0
        backgroundX2 = backgroundIcon.getIconWidth();

        Person = new Person(50, 250);
        obstacles = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            obstacles.add(new Obstacle(800 + i * 300));  // อุปสรรคแต่ละอันจะมีระยะห่างกัน
        }

        // สร้างถ้วยรางวัล 3 อัน
        trophies = new ArrayList<>();

        for (int i = 0; i < 8; i++) { // เพิ่มถ้วยรางวัล 8 ถ้วย
            Trophy trophy = new Trophy(lastTrophyX);
            trophies.add(trophy);
            lastTrophyX = trophy.getX(); // ใช้ตำแหน่ง X ของถ้วยรางวัลก่อนหน้าเพื่อกำหนดถ้วยรางวัลถัดไป
        }

        titleLabel = new JLabel("Hunt Trophies");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // Use Verdana font with size 40
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 80, 800, 50);

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.setBounds(350, 200, 100, 50);
        startButton.setBackground(Color.YELLOW);  // พื้นหลังสีเหลือง
        startButton.setBorder(new RoundedBorder(20, 4));  // ขอบดำหนา 2px

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();  // เริ่มเกมเมื่อกดปุ่ม Start
            }
        });

        this.setLayout(null);  // ตั้งค่า layout เป็น null เพื่อให้จัดตำแหน่งปุ่มได้เอง
        this.add(titleLabel);
        this.add(startButton);  // เพิ่มปุ่ม Start ลงใน JPanel

        addKeyListener(this);
        setFocusable(true);
        loadSounds();
    }

    private void setBackgroundImage() {
        switch (currentLevel) {
            case 1:
                backgroundIcon = new ImageIcon("Background1.jpg");

                break;
            case 2:
                backgroundIcon = new ImageIcon("Background2.jpg");

                break;
            case 3:
                backgroundIcon = new ImageIcon("Background3.jpg");

                break;
            default:
                backgroundIcon = new ImageIcon("Background1.jpg"); // ค่าเริ่มต้น

        }
        backgroundImage = backgroundIcon.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดพื้นหลังให้มีขนาดตามที่ต้องการ
        g.drawImage(backgroundImage, backgroundX1, 0, backgroundWidth, backgroundHeight, null);
        g.drawImage(backgroundImage, backgroundX2, 0, backgroundWidth, backgroundHeight, null);

        Person.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        for (Trophy trophy : trophies) {
            trophy.draw(g);
        }
        drawLevelText(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Trophies Collected: " + score, 10, 15);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameStarted) {
            return;  // หากเกมยังไม่เริ่ม ไม่ทำอะไร
        }
        // เลื่อนตำแหน่งพื้นหลังให้ขยับไปทางซ้ายเมื่อเกมเริ่ม
        backgroundX1 -= 5;  // ปรับค่าความเร็วการเลื่อน
        backgroundX2 -= 5;

        // ตรวจสอบว่าเมื่อไหร่พื้นหลังจะหลุดจอ และรีเซ็ตตำแหน่ง
        if (backgroundX1 + backgroundWidth <= 0) {
            backgroundX1 = backgroundX2 + backgroundWidth;
        }

        if (backgroundX2 + backgroundWidth <= 0) {
            backgroundX2 = backgroundX1 + backgroundWidth;
        }

        // อัปเดตสถานะไดโนเสาร์และอุปสรรค
        Person.update();

        for (Obstacle obstacle : obstacles) {
            obstacle.setSpeed(obstacleSpeed); // ตั้งค่าความเร็ว
            obstacle.update(); // อัปเดตการเคลื่อนที่ของอุปสรรค
        }

        // อัปเดตถ้วยรางวัล
        for (Trophy trophy : trophies) {
            trophy.update();
        }

        if (checkCollisionWithObstacle()) {
            timer.stop(); // หยุดเกมก่อน
            showLoseDialog();  // แสดงรูปภาพ LOSE!
            return;
        }
        // ตรวจสอบการเก็บถ้วยรางวัล
        checkCollisionWithTrophy();

        // ตรวจสอบการชนะ
        if (winState && Person.isOnGround()) {
            timer.stop();
            showWinDialog();
            return;
        }

        repaint();
    }

    // แก้ไข method showWinDialog ให้แสดงรูปภาพ WIN! 
    private void showWinDialog() {
        stopBackgroundMusic(); // หยุดเสียง background music
        playSound(winSound);
        winLabel.setVisible(true);  // แสดงรูปภาพ WIN!
        timer.stop();  // หยุดเกม
    }

// เพิ่ม method showLoseDialog ให้แสดงรูปภาพ LOSE!
    private void showLoseDialog() {
        stopBackgroundMusic(); // หยุดเสียง background music
        playSound(loseSound); // เล่นเสียงแพ้
        loseLabel.setVisible(true);
        timer.stop();

        Timer hideLoseTimer = new Timer(3000, e -> {
            loseLabel.setVisible(false);
            restartGame();
        });
        hideLoseTimer.setRepeats(false);
        hideLoseTimer.start();
    }

    private void restartGame() {        
        // เลื่อนตำแหน่งไดโนเสาร์หลังจากรีสตาร์ทเกม
        Person = new Person(50, 250);  // รีเซ็ตตำแหน่งของไดโนเสาร์เป็นค่าเริ่มต้น
        obstacles.clear(); // ล้างอุปสรรคเก่าออกก่อนสร้างใหม่

        for (int i = 0; i < 3; i++) {
            obstacles.add(new Obstacle(800 + i * 300));  // สร้างอุปสรรคใหม่และตั้งค่าให้อยู่ห่างกัน
        }

        trophies.clear();  // ล้างถ้วยรางวัลเก่าออกก่อนสร้างใหม่
        int lastTrophyX = 500; // ตำแหน่งเริ่มต้นของถ้วยรางวัลแรก
        for (int i = 0; i < 10; i++) { // สร้างถ้วยรางวัล 5 อัน
            Trophy trophy = new Trophy(lastTrophyX);
            trophies.add(trophy);
            lastTrophyX = trophy.getX() + 150; // อัปเดตตำแหน่งล่าสุดและเว้นระยะห่าง
        }

        score = 0;  // รีเซ็ตคะแนน
        winState = false;  // รีเซ็ตสถานะการชนะ
        gameStarted = true; // ทำให้เกมเริ่มใหม่

        repaint();
        if (timer == null) {
            timer = new Timer(18, this);
        }
        stopBackgroundMusic(); // หยุดเสียงพื้นหลัง
        startBackgroundMusic(); // เริ่มเสียงพื้นหลังใหม่หลังจากรีสตาร์ทเกม
        timer.start();  // เริ่ม timer ใหม่
    }

    public boolean checkCollisionWithObstacle() {
        Rectangle personRect = new Rectangle(Person.getX(), Person.getY(), 50, 50);  // พื้นที่ตัวละคร
        for (Obstacle obstacle : obstacles) {
            Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), 30, 30);  // พื้นที่อุปสรรค
            if (personRect.intersects(obstacleRect)) {
                return true;  // ตรวจพบการชน ไม่ว่าจะชนจากด้านหน้า, ด้านหลัง, หรือด้านบน
            }
        }
        return false;  // ไม่มีการชน
    }

    public void checkCollisionWithTrophy() {
        Rectangle dinoRect = new Rectangle(Person.getX(), Person.getY(), 50, 50);
        for (Trophy trophy : trophies) {
            Rectangle trophyRect = new Rectangle(trophy.getX(), trophy.getY(), 30, 30);
            if (dinoRect.intersects(trophyRect) && !trophy.isCollected()) {
                trophy.collect();
                score++;
                playTrophySound();
            }
        }

        if (score >= trophiesRequired[currentLevel - 1]) {
            if (currentLevel < totalLevels) {
                askToProceedToNextLevel();  // ถามว่าจะไปยังด่านถัดไปหรือไม่
            } else {
                winState = true;  // ชนะเมื่อครบทุกด่าน
            }
        }
    }
    
    private void loadSounds() {
        backgroundMusic = loadSound("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\game.wav"); // เสียงพื้นหลัง
        trophySound = loadSound("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\purchase.wav");         // เสียงถ้วยรางวัล
        loseSound = loadSound("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\lose.wav");             // เสียงแพ้
        winSound = loadSound("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\winning.wav");               // เสียงชนะ
    }
    
    private Clip loadSound(String filePath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void playSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0); // เล่นเสียงตั้งแต่ต้น
            clip.start();
        }
    }
    
    private void startBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // เล่นซ้ำไปเรื่อยๆ
            backgroundMusic.start();
        }
    }
    
    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
    
    public void playTrophySound() {
        playSound(trophySound); // เล่นเสียงถ้วยรางวัลเมื่อเก็บได้
    }
    
    
    public void drawLevelText(Graphics g) {
        String levelText = "Level " + currentLevel;
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);

        // หาตำแหน่งกลางหน้าจอ
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(levelText);
        int x = (getWidth() - textWidth) / 2;  // คำนวณตำแหน่ง X ให้อยู่ตรงกลาง
        int y = 50;  // กำหนดตำแหน่ง Y อยู่ตรงบนของหน้าจอ

        g.drawString(levelText, x, y);  // วาดข้อความ
    }

    private void askToProceedToNextLevel() {
        stopBackgroundMusic(); // หยุดเสียงเพลงพื้นหลังก่อนแสดงกล่องยืนยัน
        // ถ้าผู้เล่นไปยังด่านถัดไป
        
        int response = JOptionPane.showConfirmDialog(this,
            "Go To Next Level?",
            "Next Level",
            JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            currentLevel++;
            setBackgroundImage(); // เปลี่ยนภาพพื้นหลังตามด่าน
            obstacleSpeed = 10 + (currentLevel - 1) * 5;  // เพิ่มความเร็ว 5 หน่วยต่อระดับ
            restartGame(); // เริ่มด่านใหม่
            startBackgroundMusic(); // เล่นเสียงเพลงพื้นหลังใหม่หลังจากเริ่มด่าน
        } else {
            restartGame(); // หากไม่ต้องการไปด่านต่อ ให้ชนะเกมปัจจุบันแทน
             startBackgroundMusic(); // เล่นเสียงเพลงพื้นหลังใหม่หลังจากเริ่มด่าน
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Person.jump();  // เรียกใช้ฟังก์ชัน jump() เมื่อกด Space bar
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void startGame() {
        gameStarted = true;  // เปลี่ยนสถานะเกมเป็นเริ่มต้นแล้ว
        startButton.setVisible(false);  // ซ่อนปุ่ม Start
        startBackgroundMusic(); // เริ่มเสียงพื้นหลัง
        setBackgroundImage();
        timer = new Timer(18, this);  // เริ่ม Timer สำหรับเกม
        titleLabel.setVisible(false);
        timer.start();  // เริ่มการอัปเดตของเกม
    }

    private void drawShadowedText(Graphics g, String text, int x, int y, Font font) {
        g.setFont(font);
        g.setColor(Color.GRAY);  // เงาสีเทา
        g.drawString(text, x + 2, y + 2);  // วาดเงาที่ตำแหน่งเลื่อนออกมาเล็กน้อย
        g.setColor(Color.BLACK);  // ข้อความสีดำ
        g.drawString(text, x, y);  // วาดข้อความหลัก
    }

    private void updateLevelDisplay() {
        repaint();
    }

    // คลาสสำหรับขอบมน
    private class RoundedBorder implements Border {

        private int radius;
        private int thickness;  // เพิ่มตัวแปรความหนาของขอบ

        public RoundedBorder(int radius, int thickness) {
            this.radius = radius;
            this.thickness = thickness;  // กำหนดค่าความหนา
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + thickness, this.radius + thickness, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;  // แปลงเป็น Graphics2D
            g2d.setStroke(new BasicStroke(thickness));  // ตั้งค่าความหนาของเส้น
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
