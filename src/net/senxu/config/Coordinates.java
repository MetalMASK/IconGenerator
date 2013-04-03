/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.config;

/**
 *
 * @author sxu
 */
public class Coordinates {
    private int x;
    private int y;
    public Coordinates(int a, int b){
        x=a;
        y=b;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public String toString(){
        return "coordinates.x: "+x+" coordinates.y: "+y;
    }
}
