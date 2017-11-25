package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by TiagoMartins on 11/11/2017.
 */

public class MenuAdmin extends AppCompatActivity {


    Button menuadddish, menueditardish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuadmin);

        menuadddish = (Button)findViewById(R.id.startmenuaadddish);
        menueditardish = (Button)findViewById(R.id.startmenueditardish);
    }


    public void startAddDishMenu(View v){


        Intent startadddishmenu = new Intent(this , AdicionarPrato.class);

        startActivity(startadddishmenu);


    }

    public void startEditPratoMenu(View v){


        Intent startmenuedit = new Intent(this , EditPrato.class);

        startActivity(startmenuedit);


    }

    //falta o eliminar


    public void sair (View v){


        Intent sairparamenuprincipal = new Intent( this, MenuPrincipal.class);
        Toast.makeText(MenuAdmin.this, "A terminar a sessão...", Toast.LENGTH_SHORT).show();
        startActivity(sairparamenuprincipal);


    }



}
