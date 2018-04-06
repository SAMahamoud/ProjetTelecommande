package m1isi.telecommande;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class CreerMacroActivity extends AppCompatActivity {

    private EditText titre;
    private Button ajouterVideo;
    private Button termineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_macro);

        titre = (EditText)findViewById(R.id.titreScenarioButton);
        ajouterVideo = (Button)findViewById(R.id.ajoutVideoButton);
        termineButton = (Button)findViewById(R.id.termineButton);

        ajouterVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AjouterVideoActivity.class);
                startActivity(intent);
            }
        });

        termineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast txt = Toast.makeText(getApplicationContext(),"Ajout du scenario : "+titre.getText(),Toast.LENGTH_SHORT);
                txt.show();
            }
        });
    }
}
