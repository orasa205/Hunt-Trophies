/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.ImageIcon;
import static javax.swing.Spring.width;
/**
 *
 * @author DELL
 */

public class Obstacle {
    private int x, y;
    private int speed;
    private static final Random random = new Random(); // สุ่มตำแหน่ง
    private int position; // ตำแหน่งของอุปสรรค
    private int obstacleSpeed; // ความเร็วของอุปสรรค
    private int diameter = 50;
    private Image[] obstacleImages;  // อาเรย์ของรูปภาพสำหรับอุปสรรคหลายประเภท
    private Image currentObstacleImage; // รูปภาพอุปสรรคที่กำลังใช้งาน
    private int initialPosition;
    private int width;

    public Obstacle(int x) {
        this.x = x;
        this.y = random.nextBoolean() ? 200 : 300; // สุ่มตำแหน่ง Y (300 = ด้านล่าง, 250 = ด้านบน)
     
        // โหลดรูปภาพสำหรับอุปสรรคแต่ละประเภท
        obstacleImages = new Image[] {
            new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\volleyball.png").getImage(),   // ลูกวอลเล่ย์บอล
            new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\rucbe.png").getImage(),        // รักบี้
            new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\football.png").getImage(),     // ฟุตบอล
            new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Tennis.png").getImage(),       // เทนนิส
            new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\basketball.png").getImage()    // บาสเก็ตบอล
        };

        // สุ่มเลือกรูปภาพอุปสรรคที่จะแสดงผล
        currentObstacleImage = obstacleImages[random.nextInt(obstacleImages.length)];
        this.speed = 10; // ความเร็วเริ่มต้น
        this.width = 50; // กำหนดความกว้างของอุปสรรค
    }
    

    public void update() {
        x -= speed; // อัปเดตตำแหน่งอุปสรรค
       

        if (x < -width) {
            x = 800 + random.nextInt(800);  // รีเซ็ตตำแหน่งอุปสรรคแบบสุ่ม
            y = random.nextBoolean() ? 200 : 300;  // สุ่มตำแหน่ง Y ใหม่

            // สุ่มเลือกรูปภาพอุปสรรคใหม่เมื่ออุปสรรคเคลื่อนที่ออกจากจอ
            currentObstacleImage = obstacleImages[random.nextInt(obstacleImages.length)];
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        // สร้าง Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        int diameter = width; 

        // สร้างรูปวงกลม
        g2d.setClip(new Ellipse2D.Double(x, y, diameter, diameter)); // ตั้งค่า clipping ให้เป็นวงกลม

        // วาดรูปภาพอุปสรรคที่ตำแหน่ง x และ y
        g2d.drawImage(currentObstacleImage, x, y, diameter, diameter, null); // วาดรูปภาพ

        // ยกเลิก clipping
        g2d.setClip(null);
    }
    
    public void setSpeed(int speed) {
    this.speed = speed; // ตั้งค่าความเร็ว
}

 public void setSpeedByLevel(int level) {
        switch (level) {
            case 1: setSpeed(10); break;
            case 2: setSpeed(15); break;
            case 3: setSpeed(18); break;
            default: setSpeed(10); break; // กำหนดความเร็วเริ่มต้น
        }
    }
}