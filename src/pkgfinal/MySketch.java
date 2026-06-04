/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 345341119
 */
public class MySketch extends PApplet{
    private Person person;
    private Sparrow sparrow;
    private int stage = 0;
    private PImage bg;
    
    public void settings() {
        size(545, 350);
    }
    
    public void setup() {
        //background(255); 
        textSize(20);
        bg = loadImage("images/MainBG.jpg");
        sparrow = new Sparrow(this,100,100,"images/sparrow (1).png");
        //person = new Person(this,100,100,"")
    }
    
    public void draw(){
        //background(255);
        image(bg,0,0,width,height);
        if(stage==0){
            fill(0);
            text("My Cultural Story",20,50);
            text("Press ENTER to continue",20,100);
        } else if (stage==1){
            sparrow.display();
            if (keyPressed){
                if(keyCode==LEFT){
                    sparrow.move(-5,0);
                } else if (keyCode==RIGHT){
                    sparrow.move(5,0);
                } else if (keyCode==UP){
                    sparrow.move(0,-5);
                } else if (keyCode==DOWN){
                    sparrow.move(0,5);
                }
            } 
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
