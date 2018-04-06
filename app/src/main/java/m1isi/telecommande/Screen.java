package m1isi.telecommande;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Screen implements Observer {

    //liste de tout les ecrants
    public static List<Screen> screens = new ArrayList<Screen>();
    private String id;
    private boolean tactile = false;
    private String resolution = "1920x1080";
    private Orientation orientation = Orientation.LANDSCAPE;
    private int row;
    private int col;
    private InetAddress ipv4;
    private Video video;
    private ImageButton button;




    public Screen(String id,boolean tactile,String resolution,Orientation orientation,int row,int col,String ipv4) throws UnknownHostException {

        this.id = id;
        this.row = row;
        this.col = col;
        this.ipv4 = InetAddress.getByName(ipv4);
        this.tactile = tactile;
        this.resolution = resolution;
        this.orientation = orientation;
        screens.add(this);
    }

    /**
     * methode static qui recherche un ecran par son id dans la liste des ecrants
     * @param id
     * @return un ecran s'il existe si non null
     */
    public static Screen getScreenById(String id){

        Screen result = null;
        for(int i=0;i<screens.size();i++){

            if(screens.get(i).getId().equals(id)==true){

                result = screens.get(i);
            }
        }
        return result;
    }

    /**
     *
     * @param video
     */
    public void setVideo(Video video) {
        if(this.video != null){
            this.video.deleteObserver(this);
        }
        this.video = video;
        this.video.addObserver(this);
    }

    public String getId() {
        return id;
    }
    public Video getVideo() {
        return video;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    /**
     * methode qui renvoie l'indice d'un ecran dans le mur
     * @param numCols le nombre de colonnes dans le mur
     * @return
     */
    public int getIndice(int numCols) {
        return (row-1)*(numCols-1)+(col-1);
    }


    /**
     * method qui assossi un ecrant à un ecran sur l'interface graphique
     * @param button
     */
    public void setButton(ImageButton button) {

        this.button = button;
        this.button.setBackgroundColor(Color.GRAY);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (video.getSelected()) {video.setSelected(false);}
                else {video.setSelected(true);}
            }
        });
    }

    /**
     *
     * @return
     */
    public ImageButton getButton(){return button;}



    @Override
    public void update(Observable observable, Object o) {

        if(observable instanceof  Video){

            VideoStates state = (VideoStates)o;

            if(state == VideoStates.SELECTED){
                button.setBackgroundColor(Color.BLUE);
            }
            else if(state == VideoStates.UNSELECTED){
                button.setBackgroundColor(Color.GRAY);
            }

            if (state == VideoStates.PLAY){
                button.setImageResource(R.drawable.ic_play_circle_outline_black_80dp);
            }
            else if(state == VideoStates.PAUSE){
                button.setImageResource(R.drawable.ic_pause_circle_outline_black_80dp);
            }
            else if(state == VideoStates.STOP){
                button.setImageResource(R.drawable.ic_stop_black_24dp);
            }

        }

    }

    @Override
    public String toString() {
        return "Screen id :"+id+" tacticle :"+tactile+" resolustion "+resolution+" row :"+row+" col: "+col+"####";
    }
}
