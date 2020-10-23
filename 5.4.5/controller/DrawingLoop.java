package controller;

import model.Character;
import view.Platform;

import java.util.ArrayList;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform){
        this.platform=platform;
        frameRate =60;
        interval=1000.0f/frameRate;
        running=true;
    }
    public void checkCollision(ArrayList<Character> character) throws InterruptedException {
        for(Character cha : character){
            cha.checkReachGameWall();
            cha.checkReachHighest();
            cha.checkReachFloor();
        }
        for(Character cha : character){
            for(Character cha2:character){
                if(cha!=cha2){
                    if(cha.getBoundsInParent().intersects(cha2.getBoundsInParent())){
                        cha.collided(cha2);
                        cha2.collided(cha);
                        return;
                    }
                }
            }
        }
    }

    public void paint(ArrayList<Character> character){
        for(Character cha : character){
            cha.repaint();
        }

    }
    @Override
    public void run() {
        System.out.println("thread drawing");
        while (running){
            float time = System.currentTimeMillis();
            try {
                checkCollision(platform.getCharacter());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paint(platform.getCharacter());
            time =System.currentTimeMillis()-time;
            if(time<interval){
                try{
                    Thread.sleep((long) (interval-time));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                try{
                    Thread.sleep((long)(interval-(interval%time)));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
