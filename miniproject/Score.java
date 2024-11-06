/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author DELL
 */

public class Score {
    private int score;
    private Image emptyTrophyImage; // รูปถ้วยรางวัลว่างเปล่า
    private Image goldTrophyImage;  // รูปถ้วยรางวัลสีทอง
    private final Color transparentColor = new Color(255, 215, 0, 128); // สีทองโปร่งใส
    private final Image TrophyImage0;
    private final Image TrophyImage1;
    private final Image TrophyImage2;
    private final Image TrophyImage3;

    public Score() {
        this.score = 0;
        // โหลดรูปถ้วยรางวัลว่างเปล่า
        TrophyImage0 = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\trophy0.png").getImage(); // ใส่ path รูปถ้วยว่างเปล่าที่นี่
        // โหลดรูปถ้วยรางวัลสีทอง
        TrophyImage1 = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\trophy1.png").getImage(); // ใส่ path รูปถ้วยว่างเปล่าที่นี่
        TrophyImage2 = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\trophy2.png").getImage(); // ใส่ path รูปถ้วยว่างเปล่าที่นี่
        TrophyImage3 = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Miniproject\\src\\main\\resources\\imges\\troph3.png").getImage(); // ใส่ path รูปถ้วยทองที่นี่
    }

    // เพิ่มคะแนนเมื่อเก็บถ้วยรางวัล
    public void increaseScore() {
        if (score < 3) { // จำกัดให้คะแนนไม่เกิน 3
            score++;
        }
    }

   public void draw(Graphics g) {
    for (int i = 0; i < 3; i++) { 
        int trophyX = 10 + i * 40; 
        int trophyY = 10; 

        // วาดถ้วยว่างเปล่าเสมอเป็นพื้นฐาน
        g.drawImage(TrophyImage0, trophyX, trophyY, 30, 40, null); 

        // วาดถ้วยสีทองตามจำนวนคะแนนที่ได้
        if (i < score) {
            g.drawImage(TrophyImage1, trophyX, trophyY, 30, 40, null); 
        }
    }
}


    public int getScore() {
        return score;
    }
}
