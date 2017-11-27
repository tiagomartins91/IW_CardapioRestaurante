package pt.ubi.di.pdm.tiagomartins;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    TextView nomep, labelnomep, labeldescricao, labelpreco;
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
        nomep = (TextView)findViewById(R.id.showpratoedit);
        labelnomep = (TextView) findViewById(R.id.labelnomeprato);
        labeldescricao = (TextView) findViewById(R.id.labeldescricao);
        labelpreco = (TextView)findViewById(R.id.labelpreco);
        editdes = (EditText) findViewById(R.id.editdescricao);
        editpreco = (EditText) findViewById(R.id.editpreco);


        //aplicar algumas propriedades a cada botão

        editb.setVisibility(View.VISIBLE);
        saveb.setEnabled(false);
        cancelb.setEnabled(false);
        nomep.setVisibility(View.INVISIBLE);
        labelnomep.setVisibility(View.INVISIBLE);
        labeldescricao.setVisibility(View.INVISIBLE);
        labelpreco.setVisibility(View.INVISIBLE);
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

        //spinner com todos os pratos que é possivel editar

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pratos_array);
        pratospinner.setAdapter(adapter);



    }


    public void editprato (View v){ //botão para activar o editar de um respectivo prato

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


            pratospinner.getSelectedView();
            pratospinner.setEnabled(false);

            editb.setEnabled(false);
            saveb.setEnabled(true);
            cancelb.setEnabled(true);

            nomep.setVisibility(View.VISIBLE);
            labelnomep.setVisibility(View.VISIBLE);
            labeldescricao.setVisibility(View.VISIBLE);
            labelpreco.setVisibility(View.VISIBLE);
            editdes.setVisibility(View.VISIBLE);
            editpreco.setVisibility(View.VISIBLE);

            nomep.setText(queryres.getString(0));
            editdes.setText(queryres.getString(1));
            editpreco.setText(queryres.getString(2));



        }




    }


    public void buttonGuardar (View V){ //metodo do botão guardar com verificação dos campos e dupla confirmação para alterar informação o prato

        int flag=0;

        final AjudanteParaAbrirBD ajudanteBD = new AjudanteParaAbrirBD(this);
        final Intent menuAdmin = new Intent(this, MenuAdmin.class);


        if (TextUtils.isEmpty(editdes.getText().toString())) {


            editdes.requestFocus();
            editdes.setError("Descricação obrigatória");

        }
        else if (TextUtils.isEmpty(editpreco.getText().toString())) {

            editpreco.requestFocus();
            editpreco.setError("Preço obrigatório");

        }
        else
            flag = 1;

        if (flag==1) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);

            a_builder.setMessage("Pretende mesmo fazer esta alteração?")
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            boolean updatesucesso = ajudanteBD.updatePrato(nomep.getText().toString(),
                                    editdes.getText().toString(),
                                    Double.parseDouble(editpreco.getText().toString()));

                            if (updatesucesso == true) {

                                Toast.makeText(EditPrato.this, "Prato alterado com sucesso!", Toast.LENGTH_LONG).show();
                                startActivity(menuAdmin);

                            }
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


    public void butttoncancelar (View v){ //cancela todas as operações

        pratospinner.getSelectedView();
        pratospinner.setEnabled(true);
        editb.setEnabled(true);
        saveb.setEnabled(false);
        cancelb.setEnabled(false);
        nomep.setVisibility(View.INVISIBLE);
        labelnomep.setVisibility(View.INVISIBLE);
        labeldescricao.setVisibility(View.INVISIBLE);
        labelpreco.setVisibility(View.INVISIBLE);
        editdes.setVisibility(View.INVISIBLE);
        editpreco.setVisibility(View.INVISIBLE);
        pratospinner.setSelection(0);
    }


}
