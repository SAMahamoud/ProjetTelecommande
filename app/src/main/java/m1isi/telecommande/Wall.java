package m1isi.telecommande;

import android.content.Context;
import android.graphics.Color;

import java.io.FileOutputStream;
import java.util.List;


public class Wall {

    private String name;
    private  int row;
    private int cols;
    private List<Screen> screens;

    public Wall(String name,int row,int cols,List<Screen> screens){

        this.name = name;
        this.row  = row;
        this.cols = cols;
        this.screens = screens;
    }

    public  Wall(int row , int cols){

        this.name = "Screen with "+row+" rows and "+cols+" colons";
        this.row  = row;
        this.cols = cols;
    }

    public void volumeDown(){


    }

    public void volumeUp(){


    }

    public void mute(){


    }

    public void stop(){


    }

    public void pause(){


    }

    public void play(){


    }


    @Override
    public String toString() {

        return "name :"+name+" row"+row+"cols"+cols+" #### screens"+screens;
    }

    public int getCols() {
        return cols;
    }

    public int getRow() {
        return row;
    }
    public List<Screen> getScreens() {
        return screens;
    }
}
