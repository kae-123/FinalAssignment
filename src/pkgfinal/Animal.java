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
public class Animal extends GameObject{
    private String species;
    private int health;
    private static int animalCount=0;
    
    public Animal(PApplet p,int x, int y,String imagePath,String species){
        super(p,x,y,imagePath);
        this.species = species;
        this.health = 100;
        animalCount++;
    }
    
    public static int getAnimalCount(){
        return animalCount;
    }
    public String getSpecies(){
        return species;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void loseHealth(int amt){
        health-=amt;
    }
    
    public void makeSound(){
        System.out.println("Animal Sound");
    }
}
