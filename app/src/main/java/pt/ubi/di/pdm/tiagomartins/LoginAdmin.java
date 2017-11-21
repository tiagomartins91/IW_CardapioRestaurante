package pt.ubi.di.pdm.tiagomartins;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by TiagoMartins on 21/11/2017.
 */

public class LoginAdmin extends AppCompatActivity {

    private AjudanteParaAbrirBD ajudanteBD;

    EditText et_username, et_password;
    Button b_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginadmin);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        b_login = (Button) findViewById(R.id.login);


    }


    public void loginbutton (View v){



        ajudanteBD = new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();



        Cursor queryuser = ajudanteBD.getuser();

        queryuser.moveToFirst();




        if (queryuser.getString(0).equals(et_username.getText().toString()))


               if( queryuser.getString(1).equals(et_password.getText().toString())){


                   Toast.makeText(LoginAdmin.this, "Login feito com sucesso!", Toast.LENGTH_LONG).show();
                    Intent startMenuAdmin = new Intent(this , MenuAdmin.class);

                    startActivity(startMenuAdmin);

               }


    }


}
