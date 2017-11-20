package pt.ubi.di.pdm.tiagomartins;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by TiagoMartins on 19/11/2017.
 */

public class EditPrato extends AppCompatActivity {


    Button editb, saveb, cancelb;
    TextView nomep;
    EditText editdes, editpreco;
    Spinner pratospinner;
    ArrayList<String> pratos_array = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprato);

        pratospinner = (Spinner)findViewById(R.id.spinnerprato);
        editb = (Button)findViewById(R.id.editbutton);
        saveb = (Button)findViewById(R.id.savebutton);
        cancelb = (Button)findViewById(R.id.cancelbutton);
        nomep = (TextView)findViewById(R.id.editnomeprato);
        editdes = (EditText) findViewById(R.id.editdescricao);
        editpreco = (EditText) findViewById(R.id.editpreco);

        editb.setVisibility(View.VISIBLE);
        saveb.setVisibility(View.INVISIBLE);
        cancelb.setVisibility(View.INVISIBLE);
        nomep.setVisibility(View.INVISIBLE);
        editdes.setVisibility(View.INVISIBLE);
        editpreco.setVisibility(View.INVISIBLE);

        AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

        Cursor queryres = ajudanteBD.getPratos();

        queryres.moveToFirst();

        pratos_array.add("Selecionar prato...");

        while (!queryres.isAfterLast()){

            pratos_array.add(queryres.getString(0)); //nome do prato

            queryres.moveToNext();

        }

        queryres.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pratos_array);
        pratospinner.setAdapter(adapter);



    }


    public void editprato (View v){

        AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);



        if (pratospinner.getSelectedItem().toString().equals("Selecionar prato...")){

            Toast.makeText(EditPrato.this, "Selecionar prato a editar!", Toast.LENGTH_LONG).show();
            pratospinner.setFocusable(true);
            pratospinner.requestFocus();



        }

        if (!pratospinner.getSelectedItem().toString().equals("Selecionar prato...")){

            SQLiteDatabase db = ajudanteBD.getWritableDatabase();

            String nomepratoeditar = pratospinner.getSelectedItem().toString();

            Cursor queryres = ajudanteBD.swhowinfoprato(nomepratoeditar);

            queryres.moveToFirst();

            editb.setVisibility(View.INVISIBLE);
            saveb.setVisibility(View.VISIBLE);
            cancelb.setVisibility(View.VISIBLE);
            nomep.setVisibility(View.VISIBLE);
            editdes.setVisibility(View.VISIBLE);
            editpreco.setVisibility(View.VISIBLE);

            nomep.setText(queryres.getString(0));
            editdes.setText(queryres.getString(1));
            editpreco.setText(queryres.getString(2));



        }




    }



    public void butttoncancelar (View v){

        editb.setVisibility(View.VISIBLE);
        saveb.setVisibility(View.INVISIBLE);
        cancelb.setVisibility(View.INVISIBLE);
        nomep.setVisibility(View.INVISIBLE);
        editdes.setVisibility(View.INVISIBLE);
        editpreco.setVisibility(View.INVISIBLE);
        pratospinner.setSelection(0);
    }


}
