package controller;

import model.Character;
import view.Platform;
import view.Score;

import java.util.ArrayList;

public class GameLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public GameLoop(Platform platform){
        this.platform=platform;
        frameRate=10;
        interval=1000.0f/frameRate;
        running=true;
    }
    public void update(Character character){
        if(platform.getKey().isPressed(character.getLeftKey())){
            character.setScaleX(-1);
            character.moveLeft();
            ArrayList<Character> temp =platform.getCharacter();
            for(Character cha:temp){
                cha.trace();
            }
        }
        if(platform.getKey().isPressed(character.getRightKey())){
            character.setScaleX(1);
            character.moveRight();
            ArrayList<Character> temp =platform.getCharacter();
            for(Character cha:temp){
                cha.trace();
            }
        }
        if(!platform.getKey().isPressed(character.getLeftKey()) && !platform.getKey().isPressed(character.getRightKey())){
            character.stop();
        }

        if(platform.getKey().isPressed(character.getUpKey())){
            character.jump();
            character.trace();
        }
        if(platform.getKey().isPressed(character.getLeftKey()) || platform.getKey().isPressed(character.getRightKey())){
            character.getImageView().tick();
        }
    }

    @Override
    public void run() {
        System.out.println("thread gameloop");
        while (running){
            float time = System.currentTimeMillis();
            ArrayList<Character> temp =platform.getCharacter();
            for(Character cha:temp){
                update(cha);
            }
            updateScore(platform.getScore(),platform.getCharacter());
            time = System.currentTimeMillis()-time;
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
    private void updateScore(ArrayList<Score> score,ArrayList<Character> characters){
        javafx.application.Platform.runLater(()->{
            for(int i=0;i<score.size();i++){
                score.get(i).setPoint(characters.get(i).getScore());
            }
        });
    }
}
