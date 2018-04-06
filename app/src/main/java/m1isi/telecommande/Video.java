package m1isi.telecommande;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class Video extends Observable{

    //attribut d'une video
    private static int MAX_VOLUME = 10;
    private static int MIN_VOLUME = 0;
    private String id;
    private String file ;
    private boolean distributed = false;
    private int volume = 5;
    private boolean mute = false;
    private Departure departure = new Departure(0,0);
    private VideoStates state = VideoStates.STOP;
    private boolean loop ;
    private boolean selected ;//boolean permetant de savoir si la video est selectionner sur le mur
    private List<Screen> screens ; //liste des ecrants sur les quelles la video est jouer



    public Video(String id,String file, boolean distributed,int volume,boolean mute,Departure departure,VideoStates state,boolean loop,List<Screen> screens){

        this.id = id;
        this.file = file;
        this.distributed = distributed;
        this.volume = volume;
        this.mute = mute;
        this.departure = departure;
        this.state = state;
        this.loop = loop;
        this.screens=screens;
        this.addObserver(MessageSender.getInstance());
        for(int i = 0; i<screens.size();i++){screens.get(i).setVideo(this);} //on informe les ecrants de la video qui est jouer sur eux
    }

    /**
     * methode qui coupe le son d'une video
     */
    public void mute(){

        setChanged();
        if(mute == true){

            mute = false;
            notifyObservers(VideoStates.UNMUTE);
        }
        else{
            mute = true ;
            notifyObservers(VideoStates.MUTE);
        }

    }


    /**
     * methode qui augmente le volume d'une video
     */
    public void volumeUp(){

        if(mute == true){this.mute();}
        if(volume < MAX_VOLUME){
            setChanged();
            volume ++;
            notifyObservers(VideoStates.VOLUMEUP);
        }

    }

    /**
     *methode qui diminu le volume d'une video
     */
    public void volumeDown(FileOutputStream outputStream){


        if(mute == true){this.mute();}
        if(volume > 0){
            setChanged();
            volume --;
            notifyObservers(VideoStates.VOLUMEDOWN);
        }
    }

    /**
     * methode qui permet de definir la date de depart d'une video
     * @param departure
     */
    public void setDeparture(Departure departure){

        this.departure = departure;
    }

    /**
     *methode qui permet de mettre une video sur play
     */
    public void play() {

        setChanged();
        this.state = VideoStates.PLAY;
        notifyObservers(VideoStates.PLAY);
    }

    /**
     *methode qui permet de mettre une video sur pause
     */
    public void pause() {
        setChanged();
        this.state = VideoStates.PAUSE;
        notifyObservers(VideoStates.PAUSE);
    }

    /**
     *methode qui permet de mettre une video sur stop
     */
    public void stop() {

        this.state = VideoStates.STOP;
        setChanged();
        notifyObservers(VideoStates.STOP);

    }

    /**
     *methode qui permet de mettre une video en lecture en boucle
     */
    public void loop(FileOutputStream outputStream) {
        setChanged();
        if(loop == true){

            loop = false;
            notifyObservers(VideoStates.UNLOOP);
        }
        else{

            loop = true;
            notifyObservers(VideoStates.LOOP);
        }

    }


    /**
     * methode qui permet de mettre a jour la variable selected
     * @param selected
     */
    public void setSelected(Boolean selected){

        this.selected = selected;
        setChanged();
        if(selected){

            notifyObservers(VideoStates.SELECTED);
        }
        else{

            notifyObservers(VideoStates.UNSELECTED);
        }

    }

    /**
     * methode qui permet de savoir si l'ecrant est selectionner dans le mur
     * @return
     */
    public boolean getSelected(){return selected;}


    public String getId(){
        return id;
    }
    public int getVolume(){
        return volume;
    }
    @Override
    public String toString() {
        return " Video : "+file+" Screens : "+screens;
    }
}
