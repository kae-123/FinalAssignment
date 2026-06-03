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
    private int stage = 0;
    
    public void settings() {
        size(400, 400);
    }
    
    public void setup() {
        background(255); 
        person = new Person(this, 100, 100, "Mr. Lu", 99, "images/person.png");
    }
}
