/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sxu
 * 
 * generate a list of Configs for a experiment scenario and keep it in a text file
 */
public class ConfigGenerator {
    //generate a new file name using current date
    private static final File configs=new File(new SimpleDateFormat("yyyyMMdd").format(new Date())+".config");
    private static final int bound=120;
    
    public static ArrayList<StimuliConfig> generateConfigFile(int sizeOfRO, int distMin, int distMax, int incre, int setTotal, File configFile) throws IOException{
        if (!configFile.exists()){
            configFile.createNewFile();
        }
        BufferedWriter bw=new BufferedWriter(new FileWriter(configFile));
        bw.append("Distance|RO_X|RO_Y|LO_X|LO_Y");
        bw.newLine();
        //add boundary checking for distMax (cannot be larger than sqrt(2)*bound, .etc
        if (distMax>=Math.sqrt(2)*bound ||distMax<=distMin){
            throw new IllegalArgumentException("illegal distMax for generating configs");
        }
        
        
        ArrayList<StimuliConfig> ret=new ArrayList<>();
        Random r=new Random();
        StimuliConfig tempConfig;
        for (int i=distMin;i<=distMax;i=i+incre){
            for (int j=0;j<setTotal;j++){
                Coordinates ro=new Coordinates(r.nextInt(bound-sizeOfRO),r.nextInt(bound-sizeOfRO));
                double angle=r.nextInt(360)/360.0*2*Math.PI;
                //the two lines below calculates tempX and tempY as the coordinates of new LO circle's top let corner
                int tempX=ro.getX()+(int)Math.round(Math.cos(angle)*((double)((i+sizeOfRO)*Math.sqrt(2.0))));
                int tempY=ro.getY()+(int)Math.round(Math.sin(angle)*((double)((i+sizeOfRO)*Math.sqrt(2.0))));
                int trials=0;
                while (tempX<sizeOfRO||tempX>120-sizeOfRO||tempY<sizeOfRO||tempY>120-sizeOfRO){
                    trials++;
                    angle=r.nextInt(360)/360.0*2*Math.PI;
                    tempX=ro.getX()+(int)Math.round(Math.cos(angle)*((double)(i+sizeOfRO)));
                    tempY=ro.getY()+(int)Math.round(Math.sin(angle)*((double)(i+sizeOfRO)));
                    
                    if (trials>10){
                        ro=new Coordinates(r.nextInt(bound-sizeOfRO),r.nextInt(bound-sizeOfRO));
                        angle=r.nextInt(360)/360.0*2*Math.PI;
                        tempX=ro.getX()+(int)Math.round(Math.cos(angle)*((double)(i+sizeOfRO)));
                        tempY=ro.getY()+(int)Math.round(Math.sin(angle)*((double)(i+sizeOfRO)));
                        trials=0;
                    }
//                    System.out.println(ro.toString());
//                    System.out.println(tempX+" "+tempY);
//                    System.out.println("stuck here");
                }
                Coordinates lo=new Coordinates(tempX,tempY);
                tempConfig=new StimuliConfig(ro,lo);
                ret.add(tempConfig);
                bw.append(i+"|"+ro.getX()+"|"+ro.getY()+"|"+lo.getX()+"|"+lo.getY());
                bw.newLine();
            }
        }
        bw.close();
        return ret;
        
    }
    
    
    
    public static ArrayList<StimuliConfig> generateConfig(int sizeOfRO, int distMin, int distMax, int incre, int setTotal) throws IOException{
        if (!configs.exists()){
            configs.createNewFile();
        }
        BufferedWriter bw=new BufferedWriter(new FileWriter(configs));
        bw.append("Distance|RO_X|RO_Y|LO_X|LO_Y");
        bw.newLine();
        //add boundary checking for distMax (cannot be larger than sqrt(2)*bound, .etc
        if (distMax>=Math.sqrt(2)*bound ||distMax<=distMin){
            throw new IllegalArgumentException("illegal distMax for generating configs");
        }
        
        
        ArrayList<StimuliConfig> ret=new ArrayList<>();
        Random r=new Random();
        StimuliConfig tempConfig;
        for (int i=distMin;i<=distMax;i=i+incre){
            for (int j=0;j<setTotal;j++){
                Coordinates ro=new Coordinates(r.nextInt(bound),r.nextInt(bound));
                double angle=r.nextInt(360)/360.0*2*Math.PI;
                int tempX=ro.getX()+(int)Math.round(Math.cos(angle)*((double)(i+sizeOfRO)));
                int tempY=ro.getY()+(int)Math.round(Math.sin(angle)*((double)(i+sizeOfRO)));
                int trials=0;
                while (tempX<0||tempX>120||tempY<0||tempY>120){
                    trials++;
                    angle=r.nextInt(360)/360.0*2*Math.PI;
                    tempX=ro.getX()+(int)Math.round(Math.cos(angle)*((double)(i+sizeOfRO)));
                    tempY=ro.getY()+(int)Math.round(Math.sin(angle)*((double)(i+sizeOfRO)));
                    
                    if (trials>10){
                        ro=new Coordinates(r.nextInt(bound),r.nextInt(bound));
                    }
//                    System.out.println(ro.toString());
//                    System.out.println(tempX+" "+tempY);
//                    System.out.println("stuck here");
                }
                Coordinates lo=new Coordinates(tempX,tempY);
                tempConfig=new StimuliConfig(ro,lo);
                ret.add(tempConfig);
                bw.append(i+"|"+ro.getX()+"|"+ro.getY()+"|"+lo.getX()+"|"+lo.getY());
                bw.newLine();
            }
        }
        bw.close();
        return ret;
        
    }
    
    public static void main(String[] args){
        try {
            ArrayList<StimuliConfig> configList=generateConfig(8, 0, 100, 4, 3);
            for (StimuliConfig c:configList){
                System.out.println(c.toString());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
