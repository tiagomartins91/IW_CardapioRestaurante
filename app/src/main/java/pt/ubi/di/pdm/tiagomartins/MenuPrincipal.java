package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {


    private AjudanteParaAbrirBD ajudanteBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        //para o caso da aplicação nunca ter sido instalada, e criar logo a bd com dados necessários
        ajudanteBD = new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

    }




    public void startMenuAdmin(View v){


        Intent startMenuLogin = new Intent(this , LoginAdmin.class);

        startActivity(startMenuLogin);


    }

    public void startConsultarPratos(View v){

        Intent startConsultarPratos = new Intent(this, ConsultarPratos.class);

        startActivity(startConsultarPratos);


    }



}
