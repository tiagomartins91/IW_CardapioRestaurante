package pt.ubi.di.pdm.tiagomartins;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toolbar;

/**
 * Created by TiagoMartins on 17/11/2017.
 */

public class InfoPrato extends AppCompatActivity{

    String nomepratoItento;
    TextView showprato, showdescricao, showpreco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoprato);



        //recebe o intento que vem da outra actividade
        Bundle bundle = getIntent().getExtras();
        nomepratoItento = bundle.getString("nome");

        SQLiteDatabase db;
        AjudanteParaAbrirBD ajudanteBD;

        ajudanteBD = new AjudanteParaAbrirBD(this); //ajudante da bd

        showprato = (TextView) findViewById(R.id.showprato);
        showdescricao = (TextView) findViewById(R.id.showdescricao);
        showpreco = (TextView) findViewById(R.id.showpreco);

        Cursor queryres = ajudanteBD.swhowinfoprato(nomepratoItento);

        queryres.moveToFirst();


        showprato.setText(queryres.getString(0));
        showdescricao.setText(queryres.getString(1));
        showpreco.setText(queryres.getString(2));


        queryres.close();

    }





}
