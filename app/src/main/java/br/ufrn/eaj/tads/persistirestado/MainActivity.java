package br.ufrn.eaj.tads.persistirestado;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "PREFS_FILE" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Restore preferences
        SharedPreferences settings = getSharedPreferences( PREFS_NAME, MODE_PRIVATE );
        boolean salvar = settings.getBoolean("salvar", false);
        String texto = settings.getString("texto", "" );

        if (salvar){
            CheckBox checkBox = findViewById(R.id.checkBox);
            EditText editText = findViewById(R.id.editText);

            checkBox.setChecked(salvar);
            editText.setText(texto);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        CheckBox checkBox = findViewById(R.id.checkBox);
        EditText editText = findViewById(R.id.editText);

        if (checkBox.isChecked()){

            Log.i("AULA05", "Salvando preferencias");

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.putBoolean("salvar", checkBox.isChecked());
            editor.putString("texto", editText.getText().toString());

            // Commit the edits!
            editor.commit();
        }else{

            Log.i("AULA05", "Removendo preferencias");

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("salvar");
            editor.remove("texto");

            editor.commit();
        }
    }

    public void onClick(View v){
        finish();
    }
}
