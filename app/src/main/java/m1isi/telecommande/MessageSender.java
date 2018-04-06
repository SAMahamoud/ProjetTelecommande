package m1isi.telecommande;


import java.util.Observable;
import java.util.Observer;

public class MessageSender implements Observer{

    private static MessageSender sender;

    /**
     * constructeur private
     */
    private  MessageSender(){

    }

    public static MessageSender getInstance(){
        if(sender == null){
            sender = new MessageSender();
        }
        return  sender;
    }

    @Override
    public void update(Observable observable, Object o) {

        //recuperer toutes les notifications provenant des video et les ecrire au format json dans un fichier

        if(observable instanceof  Video){

            Video video =(Video)observable;
            VideoStates state = (VideoStates)o;

            if (state == VideoStates.PLAY){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"state\": \"play\"}]}";

            }
            else if(state == VideoStates.PAUSE){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"state\": \"pause\"}]}";

            }
            else if(state == VideoStates.STOP){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"state\": \"stop\"}]}";
            }
            else if(state == VideoStates.MUTE){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"mute\": 0 }]}";
            }
            else if(state == VideoStates.VOLUMEDOWN){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"volume\": "+video.getVolume()+"}]}";
            }
            else if(state == VideoStates.VOLUMEUP){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"volume\": "+video.getVolume()+"}]}";
            }
            else if(state == VideoStates.LOOP){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"loop\": 1 }]}";
            }
            else if(state == VideoStates.UNLOOP){
                String jsonTxt = "{\"cmd\": [{\"idv\": \""+video.getId()+"\", {\"loop\": 0 }]}";
            }


        }
    }
}
