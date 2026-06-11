/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
/**
 *
 * @author 345341119
 */
public class MySketch extends PApplet{
    //Game Objects
    private Person person;
    private OldMan old;
    private OldWoman oldWoman;
    private Sparrow sparrow;
    
    //setup
    private int stage = 1;
    private PImage bg,bg2,bg3;
    
    //stage 2
    private boolean metOldMan=false;
    private boolean ateStarch=false;
    
    private PImage starch;
    private PImage arrow;
    private int dialogueIndex = 0;//grandpa
    private int dialogueIndex2 = 0;//grandma
    
    String[] grandpaDialogue = {
        "Hello little sparrow!",
        "You look hungry today.",
        "Feel free to explore the house."
    };
    String[] grandmaDialogue = {
        "Who ate my starch paste?",
        "Was it you, little sparrow?",
        "You naughty bird!"
    };
    
    //arrays for stage3
    int[] starchX = new int[5];
    int[] starchY = new int[5];
    boolean []collected = new boolean[5];
    
    ///////
    public void settings() {
        size(1000, 660);
    }
    
    public void setup() {
        textSize(20);
        
        //backgrounds
        bg = loadImage("images/MainMenuBG.png");
        bg2 = loadImage("images/BG2.png");
        bg3 = loadImage("images/BG3.png");
        
        arrow = loadImage("images/arrow.png");
        starch = loadImage("images/starch.png");
        
        //Game objects
        sparrow = new Sparrow(this,100,100,"images/sparrowRfly1.png");
        old = new OldMan(this,600,150,"Old Man","images/OMan.png",sparrow);
        oldWoman = new OldWoman(this,130,170,"Old Woman","images/OWoman.png");
        
        //set random x and y coodinate for each index
        for(int i=0;i<5;i++){
            starchX[i]=(int)random(100,900);
            starchY[i]=(int)random(100,500);
        }
    }//end setup
    
    public void draw(){
        if(stage==1){
            image(bg,0,0,width,height);
        ////////////////////////////STAGE 2////////////////////////////    
        } else if (stage==2){
            image(bg2,0,0,width,height);
            sparrow.display();
            old.display();
            
            if(sparrow.isCollidingWith(old)){
                metOldMan=true;
                fill(255);
                rect(20,500,960,120);
                fill(0);
                text(grandpaDialogue[dialogueIndex],100,550);
                text("Click to contiue",100,600);
            }
            
            if (keyPressed){
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                } else if (keyCode==RIGHT){
                    sparrow.setNewImage("images/sparrowRfly1.png");
                    sparrow.move(10,0);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                }
            }
        ////////////////////////////STAGE 3////////////////////////////
        } else if (stage==3){
            image(bg3,0,0,width,height);
            sparrow.display();
            sparrow.setX(50);
            sparrow.setY(50);
            
            if(keyPressed){
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                    sparrow.setNewImage("images/sparrowRfly2png");
                } else if (keyCode==RIGHT){
                    sparrow.move(10,0);
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                }
            }
            
            for(int i=0;i<5;i++){
                if(!collected[i]){
                    image(starch,starchX[i],starchY[i],50,50);
                    
                    boolean touching = sparrow.x < starchX[i] + 50 &&
                        sparrow.x + sparrow.image.width > starchX[i] &&
                        sparrow.y < starchY[i] + 50 &&
                        sparrow.y + sparrow.image.height > starchY[i];
                    
                    if (touching){
                        collected[i]=true;
                        //write to score.txt file
                        try{
                            FileWriter w= new FileWriter("score.txt",true);
                            PrintWriter output = new PrintWriter(w);
                            output.println("Starch #" + (i+1)+ " collected at "+ starchX[i]+ ", "+ starchY[i]);
                            output.close();
                        } catch(IOException e){
                            System.err.println("Java exception: "+ e);
                        }
                    }
                }//end not collected if statement
            }//end for loop
            
            int collectedCount =0;
            for(int i=0;i<5;i++){
                if(collected[i]){
                    collectedCount++;
                }
            }
            if(collectedCount==5){
                image(arrow,700,400);
                if (sparrow.x >= 700 && sparrow.y >= 400){
                    stage = 4;
                }    
            }
        ////////////////////////////STAGE 4////////////////////////////    
        } else if (stage==4){
            image(bg2,0,0,width,height);
            oldWoman.display();
            fill(255);
            rect(20,500,960,120);
            fill(0);
            text(grandmaDialogue[dialogueIndex2],100,550);
            text("Click to contiue",100,600);
        ////////////////////////////STAGE 5////////////////////////////    
        } else if (stage==5){
            image(bg3,0,0,width,height);
            //oldWoman(this,130,170,"Old Woman","images/OWomanAngry.png");
            
            sparrow.display();
            
            fill(255);
            rect(20,500,960,120);
            fill(0);
            text("The sparrow escaped into the mountains!",40,550);
            
            sparrow.fly();
            if(sparrow.y==0){
                stage=6;
            }
        ////////////////////////////STAGE 6////////////////////////////
        } else if (stage==6){
            background(200);
            fill(0);

            text("Choose a basket!",350,150);

            rect(250,250,150,100);
            rect(600,250,150,100);

            fill(255);

            text("Small",290,310);
            text("Large",645,310);
        ////////////////////////////STAGE 7////////////////////////////
        } else if (stage==7){
            background(255);

            fill(0);

            text("The old man chose the small basket.",250,250);
            text("Inside was treasure!",250,300);
        ////////////////////////////STAGE 8////////////////////////////
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
    }//end keyPressed
    
    public void mousePressed(){
        if(stage==2&&metOldMan){
            dialogueIndex++;
            if(dialogueIndex>=grandpaDialogue.length){
                stage=3;
            }
        }
        
        if(stage==4){
            dialogueIndex2++;
            if(dialogueIndex2>=grandmaDialogue.length){
                stage=5;
            }
        }
        
        if(stage==6){
            if(mouseX>250&&mouseX<400&&mouseY>250&&mouseY<350){
                stage=7;
            }
            
            //large basket
            if(mouseX>600&&mouseX<750&&mouseY>250&&mouseY<350){
                stage=8;
            }
        }
    }//end mousePressed
}
