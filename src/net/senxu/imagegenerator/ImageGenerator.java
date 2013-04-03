/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.imagegenerator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.senxu.config.Coordinates;
import net.senxu.config.StimuliConfig;

/**
 *
 * @author sxu
 */
public class ImageGenerator {
    
    
    public static void main(String[] args){
        /**
         * Read a background image
         */
        BufferedImage bgImage = readImage("WhiteBack.jpg");
        StimuliConfig sc=new StimuliConfig(new Coordinates(10,10),new Coordinates(20,20));
        /**
         * Read a foreground image
         */
        BufferedImage ro = readImage("ro.jpg");
        BufferedImage lo = readImage("lo.jpg");
        BufferedImage overlayedImage = overlayImages(bgImage, ro, lo, sc);
        writeImage(overlayedImage, "overLayedImage.jpg", "JPG");
    }

    public static BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
        }
        return img;
    }

    public static BufferedImage overlayImages(BufferedImage bgImage, BufferedImage ro, BufferedImage lo, StimuliConfig sc) {
        /**Create a Graphics  from the background image**/
        BufferedImage ret=new BufferedImage(bgImage.getHeight(),bgImage.getWidth(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g = ret.createGraphics();
        /**Set Antialias Rendering**/
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        /**
         * Draw background image at location (0,0)
         * You can change the (x,y) value as required
         */

        g.drawImage(bgImage, 0, 0, null);
 
        /**
         * Draw foreground image at location (0,0)
         * Change (x,y) value as required.
         */
        g.drawImage(ro, sc.ro_loc.getX(), sc.ro_loc.getY(), null);
        g.drawImage(lo, sc.lo_loc.getX(), sc.lo_loc.getY(), null);
 
        g.dispose();
        return ret;
    }

    public static void writeImage(BufferedImage overlayedImage, String fileLocation, String extension) {
        try {
            BufferedImage bi = overlayedImage;
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
        } catch (IOException e) {
        }
    }
    
}
