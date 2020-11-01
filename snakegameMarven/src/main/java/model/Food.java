package model;

import javafx.geometry.Point2D;
import view.Platform;

import java.util.Random;

public class Food {
    public int type=0;
    private Point2D position;
    private Random rand;
    public Food(Point2D point2D){
        type=1;
        this.position=point2D;
        this.rand = new Random();
    }
    public Food(){
        this.rand = new Random();
        respawn();
    }
    public void respawn(){
        Point2D prev = this.position;
        do{
            this.position = new Point2D(rand.nextInt(Platform.WIDTH),rand.nextInt(Platform.HEIGHT));
        }while (prev==this.position);
    }
    public Point2D getPosition(){
        return this.position;
    }

    public int getType() {
        return type;
    }
}
