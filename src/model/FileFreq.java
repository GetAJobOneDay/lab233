package model;


public class FileFreq  {
    private String name;
    private String path;
    private Integer freq;
    public FileFreq(){}
    public FileFreq(String name,String path,Integer freq){
        this.freq=freq;
        this.name=name;
        this.path =path;
    }
    @Override
    public String toString(){
        return String.format("{%s:%d}",this.name,this.freq);
    }

    public String getPath() {
        return this.path;
    }
    public Integer getFreq(){
        return this.freq;
    }

    public Integer getFreq(Object o) {
        return freq;
    }
}
