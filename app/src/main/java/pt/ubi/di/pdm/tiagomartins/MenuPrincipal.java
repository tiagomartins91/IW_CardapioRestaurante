package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }




    public void startMenuAdmin(View v){


        Intent startMenuAdmin = new Intent(this , MenuAdmin.class);

        startActivity(startMenuAdmin);


    }

    public void startConsultarPratos(View v){

        Intent startConsultarPratos = new Intent(this, ConsultarPratos.class);

        startActivity(startConsultarPratos);


    }



}
