/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
/**
 *
 * @author 345341119
 */
public class MySketch extends PApplet{
    private Person person;
    private Sparrow sparrow;
    private int stage = 0;
    
    public void settings() {
        size(545, 350);
    }
    
    public void setup() {
        background(255); 
        textSize(20);
        sparrow = new Sparrow(this,100,100,"images/ojiisan03_smile.png");
    }
    
    public void draw(){
        background(255);
        if(stage==0){
            fill(0);
            text("My Cultural Story",20,50);
            text("Press ENTER to continue",20,100);
        } else if (stage==1){
            sparrow.display();
        }
    }
    
    public void keyPressed(){
        if (stage==0){
            if (keyCode==ENTER){
                stage=1;
            }
        }
    }
}
