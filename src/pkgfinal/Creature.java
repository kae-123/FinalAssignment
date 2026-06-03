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
public class Creature extends Animal{
    public Creature(PApplet app,int x, int y, String imagePath){
        super(app,x,y,imagePath,"Creature");
    }
    
    public void scare(){
        System.out.println("BOO!!!");
    }
}
