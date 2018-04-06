package m1isi.telecommande;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class JsonParser {

    private static JsonParser instance;

    private Context context;
    private InputStream wallIS;
    private InputStream scenarioIS;
    private String wallTxt;
    private String scenarioTxt;
    private Wall wall;
    private Scenario scenario;
    private List<Screen> wallScreens;
    private List<Video> videos;

    private JsonParser(Context context) {

        try {

            this.context     = context;
            this.wallIS      = this.context.getResources().openRawResource(R.raw.wall);
            this.scenarioIS  = this.context.getResources().openRawResource(R.raw.scenario);
            this.wallTxt     = convertStreamToString(wallIS);
            this.scenarioTxt = convertStreamToString(scenarioIS);

            JSONObject wallJson     = new JSONObject(wallTxt);
            JSONObject scenarioJson = new JSONObject(scenarioTxt);

            createWall(wallJson);
            createScenario(scenarioJson);

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (UnknownHostException e) {

            e.printStackTrace();
        }


    }

    public static  JsonParser getInstance(Context context){

        if(instance == null){

            instance = new JsonParser(context);
        }

        return instance;
    }


    /**
     * methode qui retourne le text lu dans un InputStream
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {

        String line           = null;
        StringBuilder sb      = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {

            while ((line = reader.readLine()) != null) {

                sb.append(line).append('\n');
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {

                is.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param wallJson
     * @throws JSONException
     */
    private void createWall(JSONObject wallJson) throws JSONException, UnknownHostException {

        JSONArray wallArray = wallJson.getJSONArray("wall");
        JSONObject wall     = wallArray.getJSONObject(0);//on considere que l'on à un seul mur d'ecrant  dans le fichier json

        String name  = wall.getString("name");
        int row      = wall.getInt("rows");
        int cols     = wall.getInt("cols");


        try {

            wallScreens = listeEcrant(wallJson,wall);
            this.wall = new Wall(name,row,cols,wallScreens);

        } catch (JSONException e) {

            Log.d("JsonParser: ", e.toString());

        } catch (UnknownHostException e) {

            Log.d("JsonParser: ", e.toString());
        }

    }


    /**
     * methode qui crait une liste d'ecrant à partire des information lu dans le fichier json wall.json
     * @param wallJson
     * @param wall
     * @return
     * @throws JSONException
     * @throws UnknownHostException
     */
    private List<Screen> listeEcrant(JSONObject wallJson,JSONObject wall) throws JSONException, UnknownHostException {

        List<Screen>  screens = new ArrayList<Screen>();
        JSONArray screenArray  = wall.getJSONArray("screen");//recuperation des ecran qui compose le mure
        JSONArray screenTypes  = wallJson.getJSONArray("screen");//rectuperation des types d'ecrant definit dans le fichier json


        for(int i=0;i<screenArray.length();i++){//recuperation des informations d'un ecrant dans le fichier json

            JSONObject screen = screenArray.getJSONObject(i);
            String id = screen.getString("id");
            int row = screen.getInt("row");
            int cols = screen.getInt("cols");
            String orientation = screen.getString("orientation");
            String type = screen.getString("type");
            String ipv4 = screen.getString("ipv4");
            boolean tactile = false;
            String resolution = "1920x1080";
            Orientation or = Orientation.LANDSCAPE;

            for(int k=0 ;k<screenTypes.length();k++){

                JSONObject screenType = screenTypes.getJSONObject(k);
                if(type.equals(screenType.getString("ids"))){

                    if(screenType.getInt("tactile")!=0){

                        tactile = true;
                    }
                    if(screenType.getString("resolution").equals(resolution)==false){

                        resolution = screenType.getString("resolution");
                    }

                }
            }

            if(orientation == "landscape"){

                or = Orientation.LANDSCAPE;
            }

            Screen sc = new Screen(id,tactile,resolution,or,row,cols,ipv4);
            screens.add(sc);
        }

        return screens;
    }

    /**
     *
     * @param scenarioJ
     * @throws JSONException
     */
    private void createScenario(JSONObject scenarioJ) throws JSONException {

        try {

            JSONObject scenario = scenarioJ.getJSONObject("scenario");
            String name = scenario.getString("name");
            listVideo(scenario);
            this.scenario = new Scenario(name,videos);

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }

    private List<Video> listVideo(JSONObject scenario) throws JSONException {

        videos = new ArrayList<Video>();
        try {

            JSONArray videosArray = scenario.getJSONArray("video");

            for(int i=0;i<videosArray.length();i++){

                JSONObject video = videosArray.getJSONObject(i);
                String id = video.getString("idv");
                String file = video.getString("file");
                boolean distributed = false;
                int volume = video.getInt("volume");
                boolean mute = false;
                Departure departure = new Departure(video.getString("departure"));
                VideoStates state = VideoStates.STOP;
                boolean loop = false;
                List<Screen> screens = new ArrayList<Screen>();
                JSONArray screensJ = video.getJSONArray("screens");

                if(video.getInt("distributed")==1){

                    distributed = true;
                }
                if(video.getInt("mute")!=0){

                    mute = true;
                }

                for (int k = 0;k<screensJ.length();k++){

                    screens.add(Screen.getScreenById(screensJ.getString(k)));
                }


                videos.add(new Video(id,file,distributed,volume,mute,departure,state,loop,screens));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  videos;
    }

    public Wall getWall() {return wall;}
}
