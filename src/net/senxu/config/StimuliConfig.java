/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.senxu.config;

/**
 *
 * @author sxu
 * 
 * Each stimuli consists of :
   * a backdrop (single color background/maps/satelite image)
   * coordinate of Reference Object
   * Size of ..
   * Type of ..
   * coordinate of Located Object
   * Size of ..
   * Type of ..
 */
public class StimuliConfig {
    //public Type type;
//    public Backdrop backdrop;
    public Coordinates ro_loc;
//    public int ro_size;
    public Coordinates lo_loc;
//    public int lo_size;
    public StimuliConfig(Coordinates ro, Coordinates lo){
        this.ro_loc=ro;
        this.lo_loc=lo;
    }
    public String toString(){
        return "ro: "+this.ro_loc.toString()+" lo: "+this.lo_loc.toString();
    }
}
