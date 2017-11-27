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
    TextView showinfoprato, showinfodescricao, showinfopreco;


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

        showinfoprato = (TextView) findViewById(R.id.showprato);
        showinfodescricao = (TextView) findViewById(R.id.showdescricao);
        showinfopreco = (TextView) findViewById(R.id.showpreco);

        Cursor queryres = ajudanteBD.swhowinfoprato(nomepratoItento); //acesso ao metodo que devolve a informação de um dado prato (tipo cursor)

        queryres.moveToFirst();

        showinfoprato.setText(queryres.getString(0));
        showinfodescricao.setText(queryres.getString(1));
        showinfopreco.setText(queryres.getString(2));

        queryres.close();

    }





}
