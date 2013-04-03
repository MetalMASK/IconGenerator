/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.exgenerator.run;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.senxu.config.ConfigGenerator;
import net.senxu.config.StimuliConfig;
import net.senxu.imagegenerator.ImageGenerator;

/**
 *
 * @author sxu
 */
public class RunExGenerator {
    private static final int size=8;
    private static final int minDist=2;
    private static final int maxDist=100;
    private static final int incre=8;
    private static final int eachSet=3;
    
    
    public static void main(String[] args){
        //step 0. generate a file directory to put configs and icons
        File dir=new File("./"+new SimpleDateFormat("yyyyMMdd").format(new Date()));
        dir.mkdir();
        //step 1. generate configs
        File configFile=new File(dir.toString()+"/conf.config");
        try {
            ArrayList<StimuliConfig> configList=ConfigGenerator.generateConfigFile(size, minDist, maxDist, incre, eachSet,configFile);
            //step 2. generate icon based on the configs in step 1
            BufferedImage bgImage = ImageGenerator.readImage("mapNoScale.jpg");
            BufferedImage ro = ImageGenerator.readImage("blackOval12X12.png");
            BufferedImage lo = ImageGenerator.readImage("blueOval12X12.png");
            BufferedImage overlayedImage;
            int dist=minDist;
            int i=0;
            for (StimuliConfig c:configList){
                //generate icons for each config
                
                overlayedImage = ImageGenerator.overlayImages(bgImage, ro, lo, c);
                ImageGenerator.writeImage(overlayedImage, dir.toString()+"/"+"D"+dist/incre+"_"+i+".jpg", "JPG");
                i++;
                overlayedImage.flush();
                if (i==eachSet){
                    i=0;
                    dist+=incre;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RunExGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
