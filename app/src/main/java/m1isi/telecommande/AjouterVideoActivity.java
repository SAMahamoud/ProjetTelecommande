package m1isi.telecommande;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;


public class AjouterVideoActivity extends AppCompatActivity {

    private EditText file;
    private EditText id;
    private CheckBox distributed;
    private CheckBox loop;
    private NumberPicker volume;
    private CheckBox mute;
    private NumberPicker departureMinute;
    private NumberPicker departureSec;
    private Button valider;
    private TableLayout screensTableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        file = (EditText)findViewById(R.id.fileTextView);
        id   = (EditText)findViewById(R.id.idTextView);
        distributed = (CheckBox)findViewById(R.id.distributed);
        loop = (CheckBox)findViewById(R.id.loop);
        mute = (CheckBox)findViewById(R.id.mute);
        volume = (NumberPicker)findViewById(R.id.volumeNumePicker);
        departureMinute = (NumberPicker)findViewById(R.id.departureMinute);
        departureSec = (NumberPicker)findViewById(R.id.departureSec);
        valider = (Button)findViewById(R.id.validerButton);
        screensTableLayout = (TableLayout)findViewById(R.id.screensTableLayout);


        volume.setMaxValue(10);
        volume.setMinValue(0);

        departureMinute.setMaxValue(120);
        departureMinute.setMinValue(0);

        departureSec.setMaxValue(59);
        departureSec.setMinValue(0);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifier que les champ on été remplit
                //creer la video et retourner à l'activité presedente
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mute.isChecked()){
                    volume.setEnabled(false);
                }
                else{
                    volume.setEnabled(true);

                }
            }
        });
    }

}
