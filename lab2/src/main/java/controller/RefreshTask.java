package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RefreshTask extends Task<Void> {
    @Override
    protected Void call() throws Exception {
        for(;;){
            try {
                Thread.sleep((long) (5*1e3));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            FutureTask futureTask =new FutureTask(new WatchTask());
            Platform.runLater(futureTask);
            try{
                futureTask.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}
