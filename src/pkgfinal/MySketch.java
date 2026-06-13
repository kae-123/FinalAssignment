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
    private Creature mayo;
    private GameObject SChest,LChest;
    
    //setup
    private int stage = 1; //initial stage
    private PImage bg,bg2,bg3,bgMountain,bgEvil,bgGood; //backgrounds
    private PImage starch,arrow,bigMayo; //other images
    
    //Strings used later for texts
    private String endingMessage = "";
    private String userInput = "";
    
    //booleans used
    private boolean metOldMan=false;
    private boolean ateStarch=false;
    private boolean enteringName= false;
    
    //counters
    private int dialogueIndex = 0;//grandpa
    private int dialogueIndex2 = 0;//grandma
    private int mountainDialogueIndex=0;
    private int timer=0;
    
    //arrays
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
    String[] grandpaMountainDialogue={
        "Oh no! The sparrow got injured!",
        "I must find where it flew.",
        "Which mountain could the sparrow be in?"
    };
    int [][]mountainPeaks={ //leftX,rightX, topY, bottomY
        {222, 276, 55, 88}, //mountain 1
        {500, 544, 137, 174}, //mountain 2
        {868, 909, 300, 325} //mountain 3
    };
    
    //arrays for stage3
    int[] starchX = new int[5];
    int[] starchY = new int[5];
    boolean []collected = new boolean[5];
    
    //stage 6
    private int attempts = 0;
    private String mountainMessage = "";
    private int correctMountain=1;
    
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
        bgMountain = loadImage("images/mountainsBG.png");
        bgEvil = loadImage("images/BGevil.png");
        bgGood = loadImage("images/BGgood.png");
        
        //items
        arrow = loadImage("images/arrow.png");
        starch = loadImage("images/starch.png");
        bigMayo = loadImage("images/mayo2.png");
        
        //Game objects
        sparrow = new Sparrow(this,100,100,"images/sparrowRfly1.png");
        mayo = new Creature(this,620,225,"images/mayo.png");
        old = new OldMan(this,600,150,"Old Man","images/OMan.png",sparrow);
        oldWoman = new OldWoman(this,130,170,"Old Woman","images/OWoman.png");
            //chests
            SChest = new GameObject(this,140,190,"images/SchestClose.png");
            LChest = new GameObject(this,520,250,"images/LchestClose.png");
        //set random x and y coodinate for each index -- AI USAGE
        for(int i=0;i<5;i++){
            starchX[i]=(int)random(100,900);
            starchY[i]=(int)random(100,500);
        }
    }//end setup
    
    public void draw(){
        if(stage==1){
            image(bg,0,0,width,height);
            fill(0);
            text("x:"+mouseX,350,150);
            text("y:"+mouseY,350,170);
            if(enteringName){
                fill(255);
                rect(60,400,400,100);
                fill(0);
                text("Enter Sparrow Name:",110,440);
                text(userInput,110,480);
            }
            
            //text(userInput,20,100);
        ////////////////////////////STAGE 2////////////////////////////    
        } else if (stage==2){
            image(bg2,0,0,width,height);
            sparrow.display();
            old.display();
            
            if(sparrow.isCollidingWith(old)){
                metOldMan=true;
                old.displayInfo(this);
                fill(255);
                rect(20,500,960,120);
                fill(0);
                text(grandpaDialogue[dialogueIndex],100,550);
                text("Click to contiue",100,600);
            }
            
            if (keyPressed){
                sparrow.setNewImage("images/sparrowRfly1.png");
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                    sparrow.setNewImage("images/sparrowLfly2.png");
                } else if (keyCode==RIGHT){
                    sparrow.move(10,0);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                    sparrow.setNewImage("images/sparrowRfly1.png");
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                }
            }
        ////////////////////////////STAGE 3////////////////////////////
        } else if (stage==3){
            image(bg3,0,0,width,height);
            sparrow.display();
            fill(255);
            rect(5,5,190,40);
            fill(0);
            
            if (keyPressed){
                sparrow.setNewImage("images/sparrowRfly1.png");
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                    sparrow.setNewImage("images/sparrowLfly2.png");
                } else if (keyCode==RIGHT){
                    sparrow.move(10,0);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                    sparrow.setNewImage("images/sparrowRfly1.png");
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                }
            }
            
            for(int i=0;i<5;i++){
                //if not all starch are collected
                if(!collected[i]){
                    //load starch image with random xy coordinates
                    image(starch,starchX[i],starchY[i],50,50);
                    
                    //create boolean to detect whether or not the sparrow 
                    //and starch are touching
                    boolean touching = sparrow.x < starchX[i] + 50 &&
                        sparrow.x+sparrow.image.width > starchX[i] &&
                        sparrow.y <starchY[i] + 50 &&
                        sparrow.y + sparrow.image.height> starchY[i];
                    
                    if (touching){
                        collected[i]=true;
                        Sparrow.collectStarch();
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
            text("Starch Collected: "+ Sparrow.getTotalStarchCollected(),20,30);
            
            if(Sparrow.getTotalStarchCollected()==5){
                image(arrow,700,400);
                text("Starch Collected: "+ Sparrow.getTotalStarchCollected(),20,30);
                if (sparrow.x >= 700 && sparrow.y >= 400){
                    stage = 4;
                }    
            }
        ////////////////////////////STAGE 4////////////////////////////    
        } else if (stage==4){
            image(bg2,0,0,width,height);
            oldWoman.display();
            oldWoman.displayInfo(this);
            fill(255);
            rect(20,500,960,120);
            fill(0);
            text(grandmaDialogue[dialogueIndex2],100,550);
            text("Click to contiue",100,600);
        ////////////////////////////STAGE 5////////////////////////////    
        } else if (stage==5){
            
            image(bg3,0,0,width,height);
            sparrow.display();
            oldWoman.display();
            //System.out.println("imagechanges");
            oldWoman.setNewImage("images/OWomanAngry.png");
            
            if (keyPressed){
                sparrow.setNewImage("images/sparrowRfly1.png");
                if(keyCode==LEFT){
                    sparrow.move(-10,0);
                    sparrow.setNewImage("images/sparrowLfly2.png");
                } else if (keyCode==RIGHT){
                    sparrow.move(10,0);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                } else if (keyCode==UP){
                    sparrow.move(0,-10);
                    sparrow.setNewImage("images/sparrowRfly1.png");
                } else if (keyCode==DOWN){
                    sparrow.move(0,10);
                    sparrow.setNewImage("images/sparrowRfly2.png");
                }
            }
            
            //AI ENHANCEMENT
            timer++;
            if(timer%60==0){
                oldWoman.setPosition((int)random(50,900),(int)random(50,500));
            }
            //
            
            if(sparrow.isCollidingWith(oldWoman)){
                sparrow.loseHealth(25);
            }
            text("Health: "+sparrow.getHealth(),20,20);
            if(sparrow.getHealth()<=0){
                sparrow.splitTongue();
                fill(255);
                rect(20,500,960,120);
                fill(0);
                text("The sparrow escaped into the mountains!",40,550);
                
                sparrow.setNewImage("images/sparrowRfly1.png");
                sparrow.fly();
                if(sparrow.y==0){
                    stage=6;
                }
            }
            
        ////////////////////////////STAGE 6////////////////////////////
        } else if (stage==6){
            image(bgMountain,0,0);
            old.display();
            old.setPosition(550, 150);
            
            fill(0);
            text("x:"+mouseX,350,150);
            text("y:"+mouseY,350,170);
            
            fill(255);
            rect(20,500,960,120);
            fill(0);
            text(grandpaMountainDialogue[mountainDialogueIndex],40,550);
            text(mountainMessage,40,590);
            
        ////////////////////////////STAGE 7////////////////////////////    
        }else if (stage==7){
            background(200);
            
            fill(0);
            text("Choose a basket!",350,150);
            SChest.display();
            LChest.display();
        ////////////////////////////STAGE 8////////////////////////////
        } else if (stage==8){
            image(bgGood,0,0);
            fill(255);
            SChest.display();
            SChest.setNewImage("images/SchestOpen.png");
            SChest.setPosition(595,336);
            sparrow.display();
            sparrow.setPosition(660,140);
            sparrow.setNewImage("images/sparrowStill.png");
            
            rect(50,150,600,180);
            fill(0);
            text("The old man chose the small basket.",70,200);
            text("Inside was treasure!",70,250);
            
            textSize(30);
            try{
                Scanner fileInput =new Scanner(new File("ending.txt"));
                if(fileInput.hasNextLine()){
                    endingMessage = fileInput.nextLine();
                }
                fileInput.close();
            }catch(IOException e){
                System.err.println("Java exception: " + e);
            }
            text(endingMessage+ " " + sparrow.getName()+ "!",70,300);
        ////////////////////////////STAGE 9////////////////////////////
        } else if (stage==9){
            image(bgEvil,0,0);
            fill(255);
            text("The large basket released a monster!",250,250);
            LChest.display();
            LChest.setPosition(520,250);
            LChest.setNewImage("images/LchestOpen.png");
            mayo.display();
            mayo.move(0, -5);
            if(mayo.y==0){
                stage=10;
            }
        ////////////////////////////STAGE 10////////////////////////////
        } else if  (stage==10){
            image(bgEvil,0,0);
            image(bigMayo,500,100);
            textSize(50);
            text("Don't be greedy!",150,250);
        }
    }//end draw
    
    public void keyPressed(){
        
        //if in stage 1 and enteringName = true
        if(stage==1&&enteringName){
            //if neither enter,return,or backspace is pressed
            if(key != ENTER &&key != RETURN &&key != BACKSPACE){
                userInput += key;
            }
            //if backspace is pressed and there is something in the userInput field
            if(key == BACKSPACE &&userInput.length() > 0){
                //subtract from string
                userInput =userInput.substring(0,userInput.length()-1);
            }
            //if enter key is pressed
            if(keyCode==ENTER){
                //set sparrow's name to the userInput
                sparrow=new Sparrow(this,100,100,"images/sparrowRfly1.png",userInput);
                stage=2;//change stage to 2
            }
        }
        
        if (stage ==2&&metOldMan&&key==' '){
            stage=3;
        }
        
        if(stage==3&&ateStarch&&key==' '){
            stage=4;
        }
    }//end keyPressed
    
    public void mousePressed(){
        //if click detected in stage 1
        if(stage==1){
            //if user click on the area of where the YES button is
            if(mouseX>=580 && mouseX<=740 && mouseY>=268 && mouseY<=365){
                enteringName = true;//set enteringName to true
            }
            
            //if user click on the area of where the NO button is
            if(mouseX>=770 && mouseX<=930 && mouseY>=268 && mouseY<=365){
                //use overloaded constructor with no name; default name set to "the Sparrow"
                sparrow =new Sparrow(this,100,100,"images/sparrowRfly1.png");
                stage = 2;
            }
        }
        
        //if click detected in stage 2 AND metOldMan is true
        if(stage==2&&metOldMan){
            dialogueIndex++;
            if(dialogueIndex>=grandpaDialogue.length){
                stage=3;
            }
        }
        
        //if click detected in stage 4
        if(stage==4){
            //when clicked, dialogueIndex2 increments
            dialogueIndex2++;
            
            if(dialogueIndex2>=grandmaDialogue.length){
                stage=5;
            }
        }
        
        //if click detected in stage 6
        if(stage==6){
            //every click shows the next message
            //when the dialogue is not finished, mountainDialogueIndex is incremented
            if(mountainDialogueIndex<2){
                mountainDialogueIndex++;
            }
            
            if(mountainDialogueIndex==2){
                //loop through mountainPeaks 2D array (mouse detection)
                for(int i=0;i<mountainPeaks.length;i++){
                    if(mouseX>=mountainPeaks[i][0]&&
                            mouseX<=mountainPeaks[i][1]&&
                            mouseY>=mountainPeaks[i][2]&&
                            mouseY<=mountainPeaks[i][3]){

                        attempts++;//increment attemps
                        //if coordinates from mountainPeak[0][0] is clicked
                        if(i==0){
                            stage=7;//change stage to 7
                        } else {
                            //set mountainMessage to "Wrong! Try again"
                            mountainMessage="Wrong! Try again";
                        }
                    }
                }
            }
        }//end stage 6
        
        //if click detected in stage 7
        if(stage==7){
            //if SChest is clicked
            if(SChest.isClicked(mouseX, mouseY)){
                stage=8; //change staage to 8
            //if LChest is clicked
            }else if(LChest.isClicked(mouseX,mouseY)){
                stage=9;//change stage to 9
            }
        }//end stage 7
    }//end mousePressed
}
