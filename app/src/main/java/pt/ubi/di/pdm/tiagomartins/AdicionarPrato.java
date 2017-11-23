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

        ajudanteBD = new AjudanteParaAbrirBD(this);



    }


    public void adicionarpratobd(View v) {

        int flag = 0;

        nomeprato = (EditText) findViewById(R.id.nomeprato);
        descricao = (EditText) findViewById(R.id.descricao);
        preco = (EditText) findViewById(R.id.preco);
        adicionarprato = (Button) findViewById(R.id.adicionarprato);

        if (flag == 0) {

            if (TextUtils.isEmpty(nomeprato.getText().toString())) {

                Toast.makeText(this, "Nome do prato obrigatório!", Toast.LENGTH_SHORT).show();
                nomeprato.requestFocus();

            }
            else if (TextUtils.isEmpty(descricao.getText().toString())) {

                Toast.makeText(this, "Descrição obrigatória!", Toast.LENGTH_SHORT).show();
                descricao.requestFocus();

            }
            else if (TextUtils.isEmpty(preco.getText().toString())) {

                Toast.makeText(this, "Preço obrigatório!", Toast.LENGTH_SHORT).show();
                preco.requestFocus();

            }
            else
                flag = 1;


        }


        if (flag == 1) {


            AjudanteParaAbrirBD ajudanteBD = new AjudanteParaAbrirBD(this);
            SQLiteDatabase db = ajudanteBD.getWritableDatabase();

            if (ajudanteBD.existeprato(nomeprato.getText().toString()) == false) {


                boolean inserirsucesso = ajudanteBD.inserirDados(nomeprato.getText().toString(),
                        descricao.getText().toString(),
                        Double.parseDouble(preco.getText().toString()));


                if (inserirsucesso == true) {

                    Toast.makeText(AdicionarPrato.this, "Prato Inserido com sucesso!", Toast.LENGTH_LONG).show();
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
