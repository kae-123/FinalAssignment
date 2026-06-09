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
    private OldMan old;
    private Sparrow sparrow;
    private int stage = 1;
    private PImage bg;
    private PImage dialog;
    private PImage bg2;
    private PImage bg3;
    private PImage starch;
    
    public void settings() {
        size(1000, 660);
    }
    
    public void setup() {
        //background(255); 
        textSize(20);
        bg = loadImage("images/MainMenuBG.png");
        bg2 = loadImage("images/BG2.jpg");
        bg3 = loadImage("images/BG3.jpg");
        starch = loadImage("images/starch (1).png");
        sparrow = new Sparrow(this,100,100,"images/sparrow (1).png");
        old = new OldMan(this,100,100,"old man","images/ojiisan03_smile.png",sparrow);
        dialog = loadImage("images/unnamed (1).png");
    }
    
    public void draw(){
        //background(255);
        
        if(stage==1){
            image(bg,0,0,width,height);
            fill(0);
            //text("My Cultural Story",20,50);
            //text("Press ENTER to continue",20,100);
            //sparrow.display();
            //sparrow.fly();
        } else if (stage==2){
            image(bg2,0,0,width,height);
            sparrow.display();
            old.display();
            
            if(sparrow.isCollidingWith(old)){
                fill(255,0,0);
                //this.text("caw", old.x, old.y);
                image(dialog,30,200);
            }
            
            if (keyPressed){
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                } else if (keyCode==RIGHT){
                    sparrow.move(10,0);
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                }
            }
            //System.out.print(sparrow.x+" "+sparrow.y);
            
            if (sparrow.x>=800&&sparrow.y>=600){
                    stage = 3;
                }
        } else if (stage==3){
            image(bg3,0,0,width,height);
            image(starch,100,100,width,height);
        }
        
    }
    
    public void keyPressed(){
        if (stage==1){
            if (keyCode==ENTER){
                stage=2;
            }
        }
    }
}
