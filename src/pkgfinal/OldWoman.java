/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
/**
 *
 * @author kaetg
 */
public class OldWoman extends Person{
    
    public OldWoman(PApplet p, int x, int y,String name,String imagePath){
        super(p,x,y,name,imagePath);
    }
    
    //trying to change grandma image to red tomato
    public void setNewImage(String imagePath){
        String newImage = imagePath;
    }
    
    public void cutTongue(Sparrow sparrow){
        sparrow.splitTongue();
    }
}