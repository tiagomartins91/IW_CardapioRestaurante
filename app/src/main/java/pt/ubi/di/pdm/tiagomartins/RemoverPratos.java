package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by TiagoMartins on 16/11/2017.
 */

public class RemoverPratos extends AppCompatActivity {


    ArrayList<String> pratos_array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.removerpratos);


        AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

        final ListView pratos = (ListView) findViewById(R.id.listapratos);


        Cursor queryres = ajudanteBD.getPratos(); //query para obter todos os pratos e mostrar na listview

        queryres.moveToFirst();

        while (!queryres.isAfterLast()){

            pratos_array.add(queryres.getString(0)); //nome do prato

            queryres.moveToNext();

        }

        queryres.close();
        db.close(); //fechar acesso à bd

        //criar a listview

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pratos_array);
        pratos.setAdapter(adapter);


        //ir para o layout do prato selecionado a remover

        pratos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = (String) pratos.getItemAtPosition(position);


                Bundle bundle = new Bundle();
                bundle.putString("nome", nome);
                Intent showinfoprato = new Intent(getApplicationContext(), ConfirmRemoverPrato.class);
                showinfoprato.putExtras(bundle); // anexar extras ao intento para ir para a outra view
                startActivity(showinfoprato);
            }
        });



    }


}
