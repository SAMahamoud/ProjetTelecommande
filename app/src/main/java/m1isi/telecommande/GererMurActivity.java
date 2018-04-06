package m1isi.telecommande;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;



public class GererMurActivity extends AppCompatActivity {

    private JsonParser jsonParser;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_mur);

        tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        jsonParser = JsonParser.getInstance(this);
        createScreens();

        Toast toast = Toast.makeText(this,"WALL : Cols = "+jsonParser.getWall().getCols()+" row = "+jsonParser.getWall().getRow(),Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * methode qui creer dinamyquement le mure d'ecran en fonction du nombre d'ecran definit dans le fichier json
     */
    private void createScreens(){

        int next = 0 ;
        for(int i = 1;i<=jsonParser.getWall().getRow();i++){//on creer les lignes du mur

            TableRow tableRow = new TableRow(this);

            for(int j =1;j <= jsonParser.getWall().getCols();j++){//on y ajoute les ecrants


                ImageButton imageButton = new ImageButton(tableLayout.getContext());
                imageButton.setImageResource(R.drawable.ic_pause_circle_outline_black_80dp);

                Screen sc = jsonParser.getWall().getScreens().get(next);
                sc.setButton(imageButton);

                tableRow.addView(imageButton,j-1);
                next++;
            }

            tableLayout.addView(tableRow,i-1);
        }

    }
}
