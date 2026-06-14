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
    private int correctMountain=1;
    
    //Strings used later for texts
    private String txt1 = "";
    private String txt2 = "";
    private String endingMessage = "";
    private String userInput = "";
    private String mountainMessage = "";
    private String soundText = "";
    
    //booleans used
    private boolean metOldMan=false;
    private boolean ateStarch=false;
    private boolean enteringName= false;
    
    //counters
    private int dialogueIndex = 0;//grandpa
    private int dialogueIndex2 = 0;//grandma
    private int mountainDialogueIndex=0;
    private int timer=0;
    private int attempts = 0;
    
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
        //2D array for mountain  peaks 
    int [][]mountainPeaks={ //leftX,rightX, topY, bottomY
        {222, 276, 55, 88}, //mountain 1
        {500, 544, 137, 174}, //mountain 2
        {868, 909, 300, 325} //mountain 3
    };
    
        //stage3--starch
    int[] starchX = new int[5];
    int[] starchY = new int[5];
    boolean []collected = new boolean[5];
    
////////////////////////////////////////////////////////////////
    public void settings() {
        size(1000, 660); //set screen size to 1000x660
    }
    
////////////////////////////////////////////////////////////////
    public void setup() {
        textSize(20); //set text size to 20
        
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
            SChest = new GameObject(this,140,240,"images/SchestClose.png");
            LChest = new GameObject(this,520,250,"images/LchestClose.png");
        
        //set random x and y coodinate for each index -- AI USAGE
        for(int i=0;i<5;i++){
            starchX[i]=(int)random(100,900); //set x to random int from 100-900
            starchY[i]=(int)random(100,500);//set y to random int from 100-500
        }
    }//end setup
    
////////////////////////////////////////////////////////////////    
    public void draw(){
        if(stage==1){
            image(bg,0,0,width,height); //show image 
            if(enteringName){ //if enteringName is true
                fill(255);//change text color to white
                rect(60,400,400,100); //draw rectangle
                fill(0); //change text color to black
                text("Enter Sparrow Name:",110,440);//prompt user
                text(userInput,110,480);//get user input
            }
            
    ////////////////////////////STAGE 2////////////////////////////    
        } else if (stage==2){
            image(bg2,0,0,width,height);//set background image
            sparrow.display(); //display sparrow
            old.display(); //display old man
            
            //if sparrow is colliding with old man
            if(sparrow.isCollidingWith(old)){
                metOldMan=true; //set metOldMan to true
                old.displayInfo(this); //display name
                fill(255);//change color
                rect(20,500,960,120);//draw rectangle
                fill(0);//change color
                text(grandpaDialogue[dialogueIndex],100,550); //show dialogue at index dialogueIndex
                text("Click to contiue",100,600);//show text
            }
            
            if (keyPressed){
                if(keyCode==LEFT){ //if left arrow pressed
                    sparrow.move(-10,0); //call move method and shift 10px left
                    sparrow.setNewImage("images/sparrowLfly2.png");//call setNewImage method and change image
                } else if (keyCode==RIGHT){//if right arrow pressed
                    sparrow.move(10,0); //call move method and shift 10px right
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                } else if (keyCode==UP){//if up arrow pressed
                    sparrow.move(0,-10);//call move method and shift 10px up
                    sparrow.setNewImage("images/sparrowRfly1.png");//call setNewImage method and change image
                } else if (keyCode==DOWN){//if down arrow pressed
                    sparrow.move(0,10);//call move method and shift 10px down
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                }
            }//end if
    ////////////////////////////STAGE 3////////////////////////////
        } else if (stage==3){
            image(bg3,0,0,width,height);//set background image
            sparrow.display();//display sparrow
            fill(255);//change color
            rect(5,5,190,40);//draw rectangle
            fill(0);//change color
            
            if (keyPressed){
                if(keyCode==LEFT){ //if left arrow pressed
                    sparrow.move(-10,0); //call move method and shift 10px left
                    sparrow.setNewImage("images/sparrowLfly2.png");//call setNewImage method and change image
                } else if (keyCode==RIGHT){//if right arrow pressed
                    sparrow.move(10,0); //call move method and shift 10px right
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                } else if (keyCode==UP){//if up arrow pressed
                    sparrow.move(0,-10);//call move method and shift 10px up
                    sparrow.setNewImage("images/sparrowRfly1.png");//call setNewImage method and change image
                } else if (keyCode==DOWN){//if down arrow pressed
                    sparrow.move(0,10);//call move method and shift 10px down
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                }
            }//end if
            
            //loop through arrays
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
                    
                    //if touching is true
                    if (touching){
                        collected[i]=true;//set collected[i] to ture
                        Sparrow.collectStarch();//call collectStarch method
                        //write to score.txt file
                        try{
                            FileWriter w= new FileWriter("score.txt",true);//append to score.txt
                            PrintWriter output = new PrintWriter(w);
                            //write the starch# and coordinate at which they were collected
                            output.println("Starch #"+(i+1)+" collected at "+starchX[i]+ ", "+starchY[i]);
                            output.close();//close PrintWriter
                        } catch(IOException e){
                            //print error msg
                            System.err.println("Java exception: "+ e);
                        } //end try-catch
                    }//end if touching
                }//end not collected if statement
            }//end for loop
            
            //shows text with number of starch collected
            text("Starch Collected: "+ Sparrow.getTotalStarchCollected(),20,30);
            
            //if all starch are collected
            if(Sparrow.getTotalStarchCollected()==5){
                image(arrow,700,400);//show arrow
                //if sparrow is within the range
                if (sparrow.x >= 700 && sparrow.y >= 400){
                    stage = 4;//change stage to 4
                }    
            }
    ////////////////////////////STAGE 4////////////////////////////    
        } else if (stage==4){
            image(bg2,0,0,width,height);//set backgrond img
            oldWoman.display();//display oldWoman
            oldWoman.displayInfo(this);//display naem
            fill(255);//change color
            rect(20,500,960,120);//draw rect
            fill(0);//change color
            text(grandmaDialogue[dialogueIndex2],100,550);//show dialogue at index dialogueIndex2
            text("Click to contiue",100,600);//show text
        ////////////////////////////STAGE 5////////////////////////////    
        } else if (stage==5){
            image(bg3,0,0,width,height);//set background img
            sparrow.display();//display sparrow
            oldWoman.display();//display oldWoman
            oldWoman.setNewImage("images/OWomanAngry.png");//change image
            
            if (keyPressed){
                if(keyCode==LEFT){ //if left arrow pressed
                    sparrow.move(-10,0); //call move method and shift 10px left
                    sparrow.setNewImage("images/sparrowLfly2.png");//call setNewImage method and change image
                } else if (keyCode==RIGHT){//if right arrow pressed
                    sparrow.move(10,0); //call move method and shift 10px right
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                } else if (keyCode==UP){//if up arrow pressed
                    sparrow.move(0,-10);//call move method and shift 10px up
                    sparrow.setNewImage("images/sparrowRfly1.png");//call setNewImage method and change image
                } else if (keyCode==DOWN){//if down arrow pressed
                    sparrow.move(0,10);//call move method and shift 10px down
                    sparrow.setNewImage("images/sparrowRfly2.png");//call setNewImage method and change image
                }
            }//end if
            
            //AI ENHANCEMENT
            timer++;//increment timer
            if(timer%60==0){
                //change position to random 
                oldWoman.setPosition((int)random(50,900),(int)random(50,500));
            }
            
            //if sparrow is colliding with oldWoman
            if(sparrow.isCollidingWith(oldWoman)){
                sparrow.loseHealth(25);//call loseHealth method and take away 25
            }
            fill(255);//change color
            text("Health: "+sparrow.getHealth(),20,20);//show text and health
            
            //if sparrow's healath is <=0
            if(sparrow.getHealth()<=0){
                sparrow.splitTongue();//call spitTongue method (sets to true)
                
                rect(20,500,960,120);//draw rect
                fill(0);//change color
                text("The sparrow escaped into the mountains!",40,550);//show text
                sparrow.setNewImage("images/sparrowRfly1.png");//change sparrow image
                sparrow.fly();//call fly method
                //if sparrow's y is 0
                if(sparrow.y==0){
                    stage=6;//change stage to 6
                }
            }//end if
            
    ////////////////////////////STAGE 6////////////////////////////
        } else if (stage==6){
            image(bgMountain,0,0);//set bg img
            old.display();//display old man
            old.setPosition(550, 150);//change position of old man
            
            //used for testing
//            text("x:"+mouseX,350,150);
//            text("y:"+mouseY,350,170);
            
            fill(255);//change color
            rect(20,500,960,120);//draw rect
            fill(0);//change color
            text(grandpaMountainDialogue[mountainDialogueIndex],40,550);//show dialogue at index mountainDialogueIndex
            text(mountainMessage,40,590);//show mountain message
            
    ////////////////////////////STAGE 7////////////////////////////    
        }else if (stage==7){
            image(bgMountain,0,0);//set bg img
            SChest.display();//display small chest
            LChest.display();//display large chest
    ////////////////////////////STAGE 8////////////////////////////
        } else if (stage==8){ //small chest ending
            image(bgGood,0,0);//change backgroundiamge
            fill(255);//change color
            SChest.display();//display small chest
            SChest.setNewImage("images/SchestOpen.png");//change image
            SChest.setPosition(595,336);//change position
            sparrow.display();//display sparrow
            sparrow.setPosition(660,140);//change position
            sparrow.setNewImage("images/sparrowStill.png");//change image
            
            rect(50,150,600,180);//draw rectangle
            text(soundText,sparrow.x+100,100);//show soundText
            fill(0);//change color
            
            try{
                //create scanner obj to read from ending.txt
                Scanner fileInput =new Scanner(new File("ending.txt"));
                if(fileInput.hasNextLine()){
                    String output = fileInput.nextLine();
                    String []text=output.split(",");//split line into parts sep by comma
                    txt1 = text[0].trim();//set txt1 to text[0] and trim
                    txt2 = text[1].trim();//set txt2 to text[1] and trim
                    endingMessage = text[2].trim();//set endingMessage to text[2] and trim
                }
                fileInput.close();//close scanner
            }catch(IOException e){
                System.err.println("Java exception: " + e);//print error msg
            }//end try-catch
            
            text(txt1,70,200);//show txt1
            text(txt2,70,250);//show txt2
            text(endingMessage+ " " + sparrow.getName()+ "!",70,300);//shows endingMessage and name
            
    ////////////////////////////STAGE 9////////////////////////////
        } else if (stage==9){//large chest ending1
            image(bgEvil,0,0);//set background image
            fill(255);//change color
            text("The large basket released a monster!",250,250);//show text
            LChest.display();//display large chest
            LChest.setPosition(520,250);//change position
            LChest.setNewImage("images/LchestOpen.png");//change image
            mayo.display();//display mayo
            mayo.move(0, -5);//call move method and decrease y by 5
            if(mayo.y==0){//if mayo touches the top
                stage=10;//change stage to 10
            }
            
    ////////////////////////////STAGE 10////////////////////////////
        } else if  (stage==10){//large chest ending2
            image(bgEvil,0,0);//set background img
            image(bigMayo,500,100);//show bigMayo image
            textSize(50);//change text size
            text("Don't be greedy!",150,250);//show text
            text(soundText,mayo.x-20,100);//show soundText
        }
    }//end draw
    
////////////////////////////////////////////////////////////////    
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
        
        //if in stage 2 and metOldMan is true 
        if (stage ==2&&metOldMan&&key==' '){
            stage=3;//change to stage3
        }
        
        //if in stage 3 and ateStarch is true
        if(stage==3&&ateStarch&&key==' '){
            stage=4;//change to stage4
        }
    }//end keyPressed

////////////////////////////////////////////////////////////////    
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
        
        //if click detected in stage 2 and metOldMan is true
        if(stage==2&&metOldMan){
            dialogueIndex++;//when clicked, dialogueIndex increments
            //if all dialogue is finished
            if(dialogueIndex>=grandpaDialogue.length){
                stage=3;//change stage to 3
            }
        }
        
        //if click detected in stage 4
        if(stage==4){
            dialogueIndex2++;//when clicked, dialogueIndex2 increments
            //if all dialogue is finished
            if(dialogueIndex2>=grandmaDialogue.length){
                stage=5;//change stage to 5
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
        
        //if click detected in stage 8
        if(stage == 8){
            //if sparrow is clicked
            if(sparrow.isClicked(mouseX, mouseY)){
                soundText = sparrow.makeSound();//call makeSound method and set to soundText
            }
        }       
        
        //if click detected in stage 10
        if(stage==10){
            //if creature/mayo is clicked
            if(mayo.isClicked(mouseX,mouseY)){
                soundText = mayo.makeSound();//call makeSound method and set to soundText
            }
        }
        
    }//end mousePressed
}//end MySketch
