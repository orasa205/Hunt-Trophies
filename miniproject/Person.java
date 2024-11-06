/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author DELL
 */
//public class Person {
//    private int x, y;
//    private int velocityY;
//    private final int GRAVITY = 1;
//    private boolean isJumping = false;
//    private int jumpCount = 0; // จำนวนการกระโดด
//    private int startX = 100; // ตำแหน่งเริ่มต้น X
//    private int startY = 300; // ตำแหน่งเริ่มต้น Y
//
//    public Person(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.velocityY = 0;
//    }
//
//     public void update() {
//        if (isJumping) {
//            y += velocityY;
//            velocityY += GRAVITY;
//
//            // ทำให้ตกกลับลงมา
//            if (y >= 250) { // ตำแหน่งพื้น
//                y = 250;
//                isJumping = false;
//                jumpCount = 0; // รีเซ็ตจำนวนการกระโดด
//                velocityY = 0; // รีเซ็ตความเร็ว
//            }
//        }
//    }
//
//     public void jump() {
//        if (jumpCount < 3) { // ให้กระโดดได้สูงสุด 2 ครั้ง
//            if (jumpCount == 0) {
//                velocityY = -15; // กระโดดแรก
//            } else if(jumpCount == 1){
//                velocityY = -15; // กระโดดครั้งที่สอง
//            }else if(jumpCount == 2){
//                velocityY = -18;
//            }
//            isJumping = true;
//            jumpCount++; // เพิ่มจำนวนการกระโดด
//        }
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void draw(Graphics g) {
//    // วาดผิวตัวละคร
//    g.setColor(new Color(255, 220, 177)); // สีผิว
//    g.fillOval(x + 15, y - 60, 30, 30); // วาดใบหน้า
//
//    // วาดผม
//    g.setColor(Color.BLACK); 
//    g.fillOval(x + 10, y - 65, 40, 40); // วาดทรงผม
//
//    // วาดเสื้อ
//    g.setColor(new Color(64, 64, 64)); // สีเทาเข้มสำหรับเสื้อ
//    g.fillRect(x + 5, y - 30, 40, 30); // ตัวเสื้อ
//
//    // วาดแขน
//    g.fillRect(x - 5, y - 20, 10, 20); // แขนซ้าย
//    g.fillRect(x + 45, y - 20, 10, 20); // แขนขวา
//
//    // วาดกระโปรง/กางเกง
//    g.setColor(new Color(32, 32, 32)); // สีดำสำหรับกระโปรง/กางเกง
//    g.fillRect(x + 10, y, 30, 20); // กระโปรง/กางเกง
//
//    // วาดขา
//    g.setColor(new Color(255, 220, 177)); // สีผิวขา
//    g.fillRect(x + 15, y + 20, 10, 20); // ขาซ้าย
//    g.fillRect(x + 25, y + 20, 10, 20); // ขาขวา
//
//    // วาดรองเท้า
//    g.setColor(Color.BLACK);
//    g.fillRect(x + 15, y + 40, 10, 10); // รองเท้าซ้าย
//    g.fillRect(x + 25, y + 40, 10, 10); // รองเท้าขวา
//}
//
//
//    public boolean isOnGround() {
//        return y == 200; // 300 คือตำแหน่ง Y ของพื้น
//    }
////}
//public class Person {
//    private int x;
//    private int y;
//    private int yVelocity = 0;
//    private final int GRAVITY = 1;
//    private boolean onGround = true;
//    private int moveSpeed = 5;  // ความเร็วในการเคลื่อนที่
//    private Image personImage;
//    private int jumpCount=0;
//    private int velocityY;
//    private boolean isJumping;
//
//    public Person(int x, int y) {
//        this.x = x;
//        this.y = y;
//        
//        // โหลดรูปภาพของตัวละคร
//        ImageIcon personIcon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\Person.png");
//        personImage = personIcon.getImage();
//    }
//
//    public void draw(Graphics g) {
//        // วาดรูปตัวละคร
//        g.drawImage(personImage, x, y, null);  
//    }
//
//     public void update() {
//        if (isJumping) {
//            y += velocityY;
//            velocityY += GRAVITY;
//
//            // ทำให้ตกกลับลงมา
//            if (y >= 250) { // ตำแหน่งพื้น
//                y = 250;
//                isJumping = false;
//                jumpCount = 0; // รีเซ็ตจำนวนการกระโดด
//                velocityY = 0; // รีเซ็ตความเร็ว
//            }
//        }
//    }
//
//     public void jump() {
//        if (jumpCount < 3) { // ให้กระโดดได้สูงสุด 2 ครั้ง
//            if (jumpCount == 0) {
//                velocityY = -15; // กระโดดแรก
//            } else if(jumpCount == 1){
//                velocityY = -15; // กระโดดครั้งที่สอง
//            }else if(jumpCount == 2){
//                velocityY = -18;
//            }
//            isJumping = true;
//            jumpCount++; // เพิ่มจำนวนการกระโดด
//        }
//    }
//
//    public void moveLeft() {
//        x -= moveSpeed;  // ขยับไปทางซ้าย
//        if (x < 0) {
//            x = 0;  // ตรวจสอบไม่ให้หลุดขอบซ้ายของจอ
//        }
//    }
//
//    public void moveRight() {
//        x += moveSpeed;  // ขยับไปทางขวา
//        if (x > 750) {
//            x = 750;  // ตรวจสอบไม่ให้หลุดขอบขวาของจอ (จอขนาด 800)
//        }
//    }
//
//    public boolean isOnGround() {
//        return onGround;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}

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
