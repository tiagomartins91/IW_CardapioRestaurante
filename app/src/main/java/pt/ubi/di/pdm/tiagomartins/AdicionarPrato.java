package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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


    }


    public void adicionarpratobd(View v) { //meto do botão adicionar prato

        int flag = 0;

        nomeprato = (EditText) findViewById(R.id.nomeprato);
        descricao = (EditText) findViewById(R.id.descricao);
        preco = (EditText) findViewById(R.id.preco);
        adicionarprato = (Button) findViewById(R.id.adicionarprato);

        if (flag == 0) { // verificação se as caixas estão vazias

            if (TextUtils.isEmpty(nomeprato.getText().toString())) {

                nomeprato.requestFocus();
                nomeprato.setError("Nome do prato obrigatório");

            }
            else if (TextUtils.isEmpty(descricao.getText().toString())) {


                descricao.requestFocus();
                descricao.setError("Descricação obrigatória");

            }
            else if (TextUtils.isEmpty(preco.getText().toString())) {

                preco.requestFocus();
                preco.setError("Preço obrigatório");

            }
            else
                flag = 1;


        }


        if (flag == 1) {


            AjudanteParaAbrirBD ajudanteBD = new AjudanteParaAbrirBD(this);
            SQLiteDatabase db = ajudanteBD.getWritableDatabase();

            if (ajudanteBD.existeprato(nomeprato.getText().toString()) == false) { //inserir teste na bd com verificação se já existe


                boolean inserirsucesso = ajudanteBD.inserirDados(nomeprato.getText().toString(),
                        descricao.getText().toString(),
                        Double.parseDouble(preco.getText().toString()));


                if (inserirsucesso == true) {

                    db.close();
                    Toast.makeText(AdicionarPrato.this, "Prato inserido com sucesso!", Toast.LENGTH_LONG).show();
                    Intent menuAdmin = new Intent(this, MenuAdmin.class);
                    startActivity(menuAdmin);

                }
            } else {

                Toast.makeText(AdicionarPrato.this, "Prato já existe!", Toast.LENGTH_LONG).show();
                nomeprato.requestFocus();
            }

        }
    }

}
