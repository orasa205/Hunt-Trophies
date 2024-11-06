/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class Person {
    private int x;
    private int y;
    private int yVelocity = 0;
    private final int GRAVITY = 1;
    private boolean onGround = true;
    private int moveSpeed = 5;  // ความเร็วในการเคลื่อนที่
    private Image personImage;
    private int jumpCount = 0;
    private int velocityY;
    private boolean isJumping;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        
        // โหลดรูปภาพของตัวละคร (ไฟล์ GIF แทน PNG)
        ImageIcon personIcon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Person2.gif");
        personImage = personIcon.getImage();
    }

    public void draw(Graphics g) {
        // วาดรูปตัวละคร (GIF จะแสดงการเคลื่อนไหวโดยอัตโนมัติ)
        g.drawImage(personImage, x, y, null);  
    }

    public void update() {
        if (isJumping) {
            y += velocityY;
            velocityY += GRAVITY;

            // ทำให้ตกกลับลงมา
            if (y >= 250) { // ตำแหน่งพื้น
                y = 250;
                isJumping = false;
                jumpCount = 0; // รีเซ็ตจำนวนการกระโดด
                velocityY = 0; // รีเซ็ตความเร็ว
            }
        }
    }

    public void jump() {
        if (jumpCount < 3) { // ให้กระโดดได้สูงสุด 2 ครั้ง
            if (jumpCount == 0) {
                velocityY = -15; // กระโดดแรก
            } else if (jumpCount == 1) {
                velocityY = -15; // กระโดดครั้งที่สอง
            } else if (jumpCount == 2) {
                velocityY = -15;
            }
            isJumping = true;
            jumpCount++; // เพิ่มจำนวนการกระโดด
        }
    }

    public void moveLeft() {
        x -= moveSpeed;  // ขยับไปทางซ้าย
        if (x < 0) {
            x = 0;  // ตรวจสอบไม่ให้หลุดขอบซ้ายของจอ
        }
    }

    public void moveRight() {
        x += moveSpeed;  // ขยับไปทางขวา
        if (x > 750) {
            x = 750;  // ตรวจสอบไม่ให้หลุดขอบขวาของจอ (จอขนาด 800)
        }
    }

    public boolean isOnGround() {
        return onGround;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
