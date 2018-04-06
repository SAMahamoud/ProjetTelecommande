package m1isi.telecommande;

import java.util.List;
import java.util.Timer;

class Scenario  extends Timer {

    private String name;
    List<Video> videos;

    public Scenario(String name,List<Video> videos){

        this.name = name;
        this.videos =videos;
    }

    @Override
    public String toString() {
        return "Scenario : "+name+" videos :"+videos;
    }

    public void setName(String name){

        this.name = name ;
    }

    public String getName(){

        return name;
    }

    public void play(int row, int call){

    }

    public void pause(int row, int call){

    }

    public void mute(int row, int call){

    }

    public void stop(int row, int call){

    }

    public void delete(){

    }

}

