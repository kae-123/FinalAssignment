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
public class OldMan extends Person{
    private Sparrow sparrow;
    
    public OldMan(PApplet p, int x, int y,String name,
            String imagePath, Sparrow sparrow){
        super(p,x,y,name,imagePath);
        this.sparrow=sparrow;
    }
    
    public void searchForSparrow(){
        System.out.println("Where is the sparrow?");
    }
}
