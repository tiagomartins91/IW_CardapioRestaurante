package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by TiagoMartins on 14/11/2017.
 */

public class AdicionarPrato extends  AppCompatActivity {


    private AjudanteParaAbrirBD ajudanteBD;

    EditText nomeprato, descricao, preco;
    Button adicionarprato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionarprato);

        ajudanteBD = new AjudanteParaAbrirBD(this);



    }


    public void adicionarpratobd(View v){

        nomeprato = (EditText)findViewById(R.id.nomeprato);
        descricao = (EditText)findViewById(R.id.descricao);
        preco =  (EditText)findViewById(R.id.preco);
        adicionarprato = (Button) findViewById(R.id.adicionarprato);

        AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

        if (ajudanteBD.existeprato(nomeprato.getText().toString())==false) {


            boolean inserirsucesso = ajudanteBD.inserirDados(nomeprato.getText().toString(),
                    descricao.getText().toString(),
                    Double.parseDouble(preco.getText().toString()));


            if (inserirsucesso == true) {

                Toast.makeText(AdicionarPrato.this, "Prato Inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent menuPrincipal = new Intent(this, MenuPrincipal.class);
                startActivity(menuPrincipal);

            }
        }
        else{

            Toast.makeText(AdicionarPrato.this, "Prato já existe!", Toast.LENGTH_LONG).show();
            nomeprato.requestFocus();
        }

    }

}
