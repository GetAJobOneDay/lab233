package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedSprite extends ImageView {
    int count,columns,rows,offSetX,offSetY,width,height,curIndex,curColumn=0,curRowIndex=0;
    public AnimatedSprite(Image img,int count,int column,int rows,int offSetX,int offSetY,int width,int height){
        this.setImage(img);
        this.columns=column;
        this.count=count;
        this.height=height;
        this.rows=rows;
        this.offSetX=offSetX;
        this.offSetY=offSetY;
        this.width=width;
        this.setViewport(new Rectangle2D(offSetX,offSetY,width,height));
    }
    public void tick(){
        curColumn =curIndex%columns;
        curRowIndex =curIndex/columns;
        curIndex =(curIndex+1)%(columns*rows);
        interpolate();
    }
    protected void interpolate(){
        final int x =curColumn*width+offSetX;
        final int y =curRowIndex*height+offSetY;
        this.setViewport(new Rectangle2D(x,y,width,height));
    }
}
