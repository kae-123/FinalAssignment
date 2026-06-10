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
    private OldWoman oldWoman;
    private Sparrow sparrow;
    private int stage = 1;
    private PImage bg,bg2,bg3;
    //stage 2
    private PImage dialog,dialog2,pressEnter;
    private boolean metOldMan=false;
    private boolean ateStarch=false;
    
    private PImage starch;
    
    private Animal[] animals;
    private int [] riceX = {100,200,300,400,500};
    
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
        sparrow = new Sparrow(this,100,100,"images/sparrow.png");
        old = new OldMan(this,600,300,"old man","images/OMan1pic (1).png",sparrow);
        oldWoman = new OldWoman(this,200,300,"Old Woman","images/oldwoman.png");
        dialog = loadImage("images/OMan1.png");
        pressEnter = loadImage("images/pressEnter.png");
        
        animals = new Animal[2];
        animals[0] = sparrow;
        //animals[1] = new Creature();
        
    }
    
    public void draw(){
        //background(255);
        
        if(stage==1){
            image(bg,0,0,width,height);
        } else if (stage==2){
            image(bg2,0,0,width,height);
            sparrow.display();
            old.display();
            
            if(sparrow.isCollidingWith(old)){
                metOldMan=true;
                image(dialog,30,520); //pretends it says hi sparrow, u look hungry
                image(pressEnter,500,560);
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
            
            //if (sparrow.x>=800&&sparrow.y>=600){
            //        stage = 3;
            //    }
        } else if (stage==3){
            image(bg3,0,0,width,height);
            for (int i=0;i<riceX.length;i++){
                image(starch,riceX[i],100,100,100);
            }
            
            sparrow.display();
            if(keyPressed){
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
            
            if(sparrow.touchingStarch(100, 100, 100, 100)){
                ateStarch=true;
                
                fill(255);
                rect(20,500,960,120);
                fill(0);
                text("The sparrow ate the starch paste!",40,550);
                text("Press SPACE.",40,590);
            }
        } else if (stage==4){
            image(bg2,0,0,width,height);
            oldWoman.display();
            
            fill(255);
            rect(20,500,960,120);
            fill(0);
            text("Old Woman: Who ate my starch paste?!",40,550);
            text("Press SPACE.",40,590);
        } else if (stage==5){
            image(bg3,0,0,width,height);
            sparrow.display();
            
            fill(255);
            rect(20,500,960,120);

            fill(0);

            text("The sparrow escaped into the mountains!",40,550);
            sparrow.fly();
            if(sparrow.x>900){
                stage=6;
            }
        } else if (stage==6){
            background(200);
            fill(0);

            text("Choose a basket!",350,150);

            rect(250,250,150,100);
            rect(600,250,150,100);

            fill(255);

            text("Small",290,310);
            text("Large",645,310);
        } else if (stage==7){
            background(255);

            fill(0);

            text("The old man chose the small basket.",250,250);
            text("Inside was treasure!",250,300);
        } else if (stage==8){
            background(255);

            //creature.display();

            fill(0);

            text("The large basket released monsters!",250,250);
        }
        
        
    }//end draw
    public void keyPressed(){
        if (stage==1){
            if (keyCode==ENTER){
                stage=2;
            }
        }
        
        if (stage ==2&&metOldMan&&key==' '){
            stage=3;
        }
        
        if(stage==3&&ateStarch&&key==' '){
            stage=4;
        }
        
        if(stage==4&&key==' '){
            sparrow.splitTongue();
            stage=5;
        }
    }
    
    public void mousePressed(){
        if(stage==6){
            if(mouseX>250&&mouseX<400&&mouseY>250&&mouseY<350){
                stage=7;
            }
            
            //large basket
            if(mouseX>600&&mouseX<750&&mouseY>250&&mouseY<350){
                stage=8;
            }
        }
    }
}
