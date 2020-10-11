package controller;

import model.Character;
import view.Platform;

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
            platform.getCharacter().trace();
            platform.getRockman().trace();
        }
        if(platform.getKey().isPressed(character.getRightKey())){
            character.setScaleX(1);
            character.moveRight();
            platform.getCharacter().trace();
            platform.getRockman().trace();
        }
        if(!platform.getKey().isPressed(character.getLeftKey()) && !platform.getKey().isPressed(character.getRightKey())){
            character.stop();
        }

        if(platform.getKey().isPressed(character.getUpKey())){
            character.jump();
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
            update(platform.getCharacter());
            update(platform.getRockman());
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
}
