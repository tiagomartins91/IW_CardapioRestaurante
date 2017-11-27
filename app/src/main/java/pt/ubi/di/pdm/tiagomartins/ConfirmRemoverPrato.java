package pt.ubi.di.pdm.tiagomartins;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TiagoMartins on 26/11/2017.
 */

public class ConfirmRemoverPrato extends AppCompatActivity {


    String nomepratoItento;
    TextView showprato, showdescricao, showpreco;
    Button bremover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmreomverprato);

        //recebe o intento que vem da outra actividade
        Bundle bundle = getIntent().getExtras();
        nomepratoItento = bundle.getString("nome");


        AjudanteParaAbrirBD ajudanteBD = new AjudanteParaAbrirBD(this); //ajudante da bd

        showprato = (TextView) findViewById(R.id.showpratoremove);
        showdescricao = (TextView) findViewById(R.id.showdescricaoremove);
        showpreco = (TextView) findViewById(R.id.showprecoremove);

        Cursor queryres = ajudanteBD.swhowinfoprato(nomepratoItento);

        queryres.moveToFirst();


        showprato.setText(queryres.getString(0));
        showdescricao.setText(queryres.getString(1));
        showpreco.setText(queryres.getString(2));


        queryres.close();



    }



    public void remover(View v){ //botão remover com um Alerta para dupla confirmação da acção do user

        bremover = (Button)findViewById(R.id.buttonremover);

        final AjudanteParaAbrirBD ajudanteBD = new AjudanteParaAbrirBD(this); //ajudante da bd



        final Intent voltaraoremover = new Intent(this, RemoverPratos.class);


        AlertDialog.Builder a_builder = new AlertDialog.Builder(this);

        a_builder.setMessage("Pretende mesmo remover este prato?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ajudanteBD.removerPrato(showprato.getText().toString());
                        Toast.makeText(ConfirmRemoverPrato.this, "Prato removido com sucesso!", Toast.LENGTH_LONG).show();
                        startActivity(voltaraoremover);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alert = a_builder.create();
        alert.setTitle("AVISO");
        alert.show();


    }


}
