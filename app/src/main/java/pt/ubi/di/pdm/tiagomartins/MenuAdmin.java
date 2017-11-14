package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by TiagoMartins on 11/11/2017.
 */

public class MenuAdmin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuadmin);
    }


    public void startAdicionarPrato(View v){


        Intent startAdicionarPrato = new Intent(this , AdicionarPrato.class);

        startActivity(startAdicionarPrato);


    }



}
