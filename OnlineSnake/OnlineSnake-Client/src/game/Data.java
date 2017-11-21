package game;


import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ducba
 */
public class Data {

    public static Image imageHead;
    public static Image imageBody;
    public static Image imageWorm;

    public static void loadImage() {
        try {
            imageHead = ImageIO.read(new File("image/snake_head.png"));
            imageBody = ImageIO.read(new File("image/snake_body.png"));
            imageWorm = ImageIO.read(new File("image/worm.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
