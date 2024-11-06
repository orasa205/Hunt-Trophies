/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 *
 * @author DELL */

public class Trophy {
    private int x, y;
    private boolean collected;
    private boolean isCollected = false;
    private static final Random random = new Random();
    private Image trophyImage;

    public Trophy(int lastTrophyX) {
        // สุ่มตำแหน่ง X ใหม่ ให้ห่างจากถ้วยรางวัลตัวก่อนหน้านี้อย่างน้อย 200
        do {
            this.x = lastTrophyX + 700 + random.nextInt(800); // ห่างกัน 500 ถึง 1200
        } while (Math.abs(this.x - lastTrophyX) < 800); // ตรวจสอบว่าอยู่ห่างจากถ้วยรางวัลตัวก่อนหน้านี้อย่างน้อย 600
        this.y = (Math.random() < 0.5) ? 250: 200; // สุ่มตำแหน่ง Y
        
        // โหลดรูปถ้วยรางวัลจากไฟล์
        trophyImage = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Trophy.png").getImage(); // ใส่ path รูปภาพของคุณที่นี่
    }
    
    public void draw(Graphics g) {
        if (!collected) {
            // วาดรูปถ้วยรางวัลที่ตำแหน่ง x และ y
            g.drawImage(trophyImage, x, y, 80, 90, null); // ปรับขนาดรูปภาพให้เป็น 50x60 พิกเซล
        }
    }

    public void update() {
        x -= 10;  // ความเร็วการเคลื่อนที่ของถ้วยรางวัล
        if (x < 0) {
            // รีเซ็ตถ้วยรางวัลไปที่ตำแหน่งใหม่
            x = random.nextInt(800) + random.nextInt(500); // สุ่มตำแหน่ง X ใหม่
            isCollected = false;  // รีเซ็ตสถานะการเก็บถ้วยรางวัล
            y = random.nextBoolean() ? 250 : 200; // สุ่มตำแหน่ง Y ใหม่เมื่อรีเซ็ต
        }
    }

    public void collect() {
        collected = true; // เปลี่ยนสถานะถ้วยรางวัลเป็นถูกเก็บ
    }

    public boolean isCollected() {
        return collected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void resetPosition() {
        x = 800 + (int)(Math.random() * 500); // สุ่มตำแหน่งถ้วยรางวัลใหม่ที่ด้านหน้า
        y = random.nextBoolean() ? 250 : 200;
        collected = false; // ตั้งค่าสถานะกลับเป็นไม่ถูกเก็บ
    }

    void reset() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
