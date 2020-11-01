package model;

import javafx.geometry.Point2D;
import view.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    private Random random = new Random();
    private int score;
    private Direction direction;
    private Point2D head;
    private Point2D tail;
    private ArrayList<Point2D> body;
    public Snake(Point2D position){
        score=0;
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);

    }
    public void update(){
        head = head.add(direction.current);
        tail = body.remove(body.size()-1);
        body.add(0,head);
    }
    public void setCurrentDirection(Direction down) {
        this.direction=down;
    }

    public Point2D getHead() {
        return this.head;
    }
    public Direction getDirection(){return this.direction;}

    public void grow() {
        body.add(tail);
    }

    public List<Point2D> getBody() {
        return body;
    }

    public int getLength() {
        return body.size();
    }

    public boolean isCollidingWithFood(Food food) {
        if( head.equals(food.getPosition())){
            if(food.type!=1){
                score++;
            }else{
                score=score+5;
            }
            System.out.println("your score now: "+score);
            food.type = random.nextInt(5);
        }
        return head.equals(food.getPosition());
    }

    public boolean isDead() {
        boolean isDead = head.getX()<0||head.getY()<0||head.getX()> Platform.WIDTH||head.getY()>Platform.HEIGHT;
        boolean hitBody = body.lastIndexOf(head)>0;
        return isDead||hitBody;
    }

    public int getScore() {
        return score;
    }
}
