package pt.ubi.di.pdm.tiagomartins;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by TiagoMartins on 16/11/2017.
 */

public class ConsultarPratos extends AppCompatActivity {


    ArrayList<String> pratos_array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarpratos);


        AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

        ListView pratos = (ListView) findViewById(R.id.listapratos);


        Cursor queryres = ajudanteBD.getPratos();

        queryres.moveToFirst();

        while (!queryres.isAfterLast()){

            pratos_array.add(queryres.getString(1));
            pratos_array.add(queryres.getString(3));

            queryres.moveToNext();

        }

        queryres.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pratos_array);
        pratos.setAdapter(adapter);




    }


}
