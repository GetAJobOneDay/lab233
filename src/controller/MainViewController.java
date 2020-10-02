package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.FileFreq;
import model.PDFdocument;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class MainViewController {
    private ListView<String> keepPath = new ListView<>();
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSet;
    @FXML
    private ListView<String> inputListView;
    @FXML
    private ListView<String> listview;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button btn;
    @FXML
    public void initialize(){
        inputListView.setOnDragOver(e->{
            Dragboard db = e.getDragboard();
            final boolean isAcc = db.getFiles().get(0).getName().toLowerCase().endsWith(".pdf");
            if(db.hasFiles()&&isAcc){
                e.acceptTransferModes(TransferMode.COPY);
            }else{
                e.consume();
            }
        });
        inputListView.setOnDragDropped(e->{
            Dragboard db = e.getDragboard();
            boolean success = false;
            if(db.hasFiles()){
                success =true;
                String filePath;
                int total_files=db.getFiles().size();
                for(int i=0;i<total_files;i++){
                    File file = db.getFiles().get(i);
                    filePath =file.getAbsolutePath();
                    inputListView.getItems().add(file.getName());
                    keepPath.getItems().add(filePath);
                }
            }
            e.setDropCompleted(success);
            e.consume();
        });
            btn.setOnAction(e->{
                Parent bgRoot = Launcher.primaryStage.getScene().getRoot();
                Task<Void> processTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        ProgressIndicator pi = new ProgressIndicator();
                        VBox box = new VBox(pi);
                        box.setAlignment(Pos.CENTER);
                        Launcher.primaryStage.getScene().setRoot(box);
                        ExecutorService executor = Executors.newFixedThreadPool(4);
                        final ExecutorCompletionService<Map<String,FileFreq>> complete = new ExecutorCompletionService<>(executor);
                        List<String> inputList = keepPath.getItems();
                        int total_files= inputList.size();
                        Map<String,FileFreq>[] wordMap = new Map[total_files];
                        for(int i=0;i<total_files;i++){
                            try{
                                String filePath = keepPath.getItems().get(i);
                                PDFdocument pdf = new PDFdocument(filePath);
                                complete.submit(new WordMapPageTask(pdf));
                                progressBar.setProgress(((i+1)*100)/total_files);
                            }catch (IOException err){
                                err.printStackTrace();
                            }
                        }
                        for(int i=0;i<total_files;i++){
                            try{
                                Future<Map<String,FileFreq>> future =complete.take();
                                wordMap[i] =future.get();
                            }catch (ExecutionException|InterruptedException err){
                                err.printStackTrace();
                            }
                        }
                        try{
                            WordMapMergeTask merger = new WordMapMergeTask(wordMap);
                            Future<LinkedHashMap<String,ArrayList<FileFreq>>> future = executor.submit(merger);
                            uniqueSet = future.get();

                            Set<Map.Entry<String,ArrayList<FileFreq>>> set = uniqueSet.entrySet();
                            List<Map.Entry<String,ArrayList<FileFreq>>> list = new ArrayList<Map.Entry<String,ArrayList<FileFreq>>>(set);
                            int i=0;
                            Collections.sort(list, Comparator.comparing(o -> o.getValue().get(i).getFreq()));
                            uniqueSet.clear();
                            for(Map.Entry<String, ArrayList<FileFreq>> map:list){
                                uniqueSet.put(map.getKey(),map.getValue());
                            }
                            listview.getItems().addAll(uniqueSet.keySet());
                        }catch (InterruptedException|ExecutionException err){
                            err.printStackTrace();
                        }finally {
                            executor.shutdown();
                        }
                        return null;
                    }
                };
                processTask.setOnSucceeded(event->{
                    Launcher.primaryStage.getScene().setRoot(bgRoot);
                });
                Thread thread = new Thread(processTask);
                thread.setDaemon(true);
                thread.start();
            });
                listview.setOnMouseClicked(mouseEvent -> {
                    Button esc = new Button("ESC");
                    esc.setLayoutX(205);
                    ArrayList<FileFreq> listOfLinks =uniqueSet.get(listview.getSelectionModel().getSelectedItem());
                    ListView<FileFreq> popupListView = new ListView<>();
                    LinkedHashMap<FileFreq,String> lookupTable = new LinkedHashMap<>();
                    for(int i=0;i<listOfLinks.size();i++){
                        lookupTable.put(listOfLinks.get(i),listOfLinks.get(i).getPath());
                        System.out.println(listOfLinks.get(i).getFreq());
                        popupListView.getItems().addAll(listOfLinks.get(i));
                    }
                    popupListView.setPrefHeight(popupListView.getItems().size()*28);
                    popupListView.setOnMouseClicked(inner->{
                        Launcher.hs.showDocument("file://"+lookupTable.get(popupListView.getSelectionModel().getSelectedItem()));
                        popupListView.getScene().getWindow().hide();
                    });
                    Popup popup =new Popup();
                    esc.setOnAction(e->{
                        popup.getScene().getWindow().hide();
                    });
                    popup.getContent().addAll(popupListView,esc);
                    popup.show(Launcher.primaryStage);
                });
    }

}
