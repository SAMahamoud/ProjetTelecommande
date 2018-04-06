package m1isi.telecommande;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class JouerMacroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer_macro);

        Toast txt = Toast.makeText(getApplicationContext(),"Fonctionnalité non implementé",Toast.LENGTH_SHORT);
        txt.show();
    }
}
