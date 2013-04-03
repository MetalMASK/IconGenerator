/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.imagegenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author sxu
 */
public class SampleGenerator {
    private static final int len=12;
    
    public static void main(String[] args){
        try {
            BufferedImage bi=new BufferedImage(len,len, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g = bi.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setPaint ( Color.white);
            g.fillOval( 0, 0, len, len );
            g.setPaint ( Color.blue);
            g.fillOval( 2, 2, len-4, len-4);
            
//            g.dispose();
            File f = new File("blueOval"+len+"X"+len+".png");
            ImageIO.write(bi, "PNG", f);
        } catch (IOException ex) {
            Logger.getLogger(SampleGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
