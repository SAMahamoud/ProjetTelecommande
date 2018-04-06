package m1isi.telecommande;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button gererMur;
    private Button creerMacro;
    private Button jouerMacro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assossiation des boutons du fichier XML au code
        gererMur   = (Button)findViewById(R.id.gererMurButton);
        creerMacro = (Button)findViewById(R.id.creerMacroButton);
        jouerMacro = (Button)findViewById(R.id.jouerMacroButton);

        gererMur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),GererMurActivity.class);
                startActivity(intent);
            }
        });

        creerMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CreerMacroActivity.class);
                startActivity(intent);
            }
        });

        jouerMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),JouerMacroActivity.class);
                startActivity(intent);
            }
        });
    }
}
