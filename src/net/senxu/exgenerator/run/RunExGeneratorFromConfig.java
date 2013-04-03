/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.exgenerator.run;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.senxu.config.Coordinates;
import net.senxu.config.StimuliConfig;
import net.senxu.imagegenerator.ImageGenerator;

/**
 *
 * @author sxu
 */
public class RunExGeneratorFromConfig {
    private static final int size=8;
    private static final int minDist=2;
    private static final int maxDist=80;
    private static final int incre=8;
    private static final int eachSet=3;
    private static final String delimiter = "\\t";
    
    
    public static void main(String[] args){
        //step 0. generate a file directory to put configs and icons
        File dir=new File("./"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"_config");
        dir.mkdir();
        
        //step 1. read from config
        File configFile=new File("tweaked.config");
        try {
            ArrayList<StimuliConfig> configList=readFromConfig(configFile);
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
            Logger.getLogger(RunExGeneratorFromConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    private static ArrayList<StimuliConfig> readFromConfig(File configFile) throws FileNotFoundException, IOException {
        ArrayList<StimuliConfig> ret=new ArrayList<>();
        BufferedReader br=new BufferedReader(new FileReader(configFile));
        String temp;
        StimuliConfig tempConfig;
        br.readLine();//skip header
        while ((temp=br.readLine())!=null){
//            System.out.println(temp.split(delimiter).length);
            if (temp.split(delimiter).length!=6) throw new IllegalArgumentException("parsing config File error, check file format (delimiter and column number)");
            int roX=Integer.parseInt(temp.split(delimiter)[1]);
            int roY=Integer.parseInt(temp.split(delimiter)[2]);
            int loX=Integer.parseInt(temp.split(delimiter)[3]);
            int loY=Integer.parseInt(temp.split(delimiter)[4]);
            tempConfig=new StimuliConfig(new Coordinates(roX,roY),new Coordinates(loX,loY));
            ret.add(tempConfig);
        }
        return ret;
    }
    
}
