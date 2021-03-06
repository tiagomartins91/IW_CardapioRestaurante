package pt.ubi.di.pdm.tiagomartins;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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


    public void loginbutton (View v){ //metoto do botão de login com verificação dos campos


        int flag = 0;

        ajudanteBD = new AjudanteParaAbrirBD(this);
        SQLiteDatabase db = ajudanteBD.getWritableDatabase();

        Cursor queryuser = ajudanteBD.getuser();

        queryuser.moveToFirst();


        if (TextUtils.isEmpty(et_username.getText().toString())) {
            et_username.requestFocus();
            et_username.setError("Campo Obrigatório");

        }

        if (TextUtils.isEmpty(et_password.getText().toString())){
            et_password.requestFocus();
            et_password.setError("Campo Obrigatório");

        }




        if (queryuser.getString(0).equals(et_username.getText().toString())) {


            flag = 1;


            if (queryuser.getString(1).equals(et_password.getText().toString())) {

                flag = 2;


            }

        }

        if (flag == 0){

            Toast.makeText(LoginAdmin.this, "Nome de utilizador inválido ou não existe!", Toast.LENGTH_LONG).show();
            et_username.requestFocus();
        }

        if (flag == 1){

            Toast.makeText(LoginAdmin.this, "Palavra-passe inválida!", Toast.LENGTH_LONG).show();
            et_password.requestFocus();
        }

        if (flag == 2){

            Toast.makeText(LoginAdmin.this, "Login feito com sucesso!", Toast.LENGTH_LONG).show();
            Intent startMenuAdmin = new Intent(this, MenuAdmin.class);

            startActivity(startMenuAdmin);
        }
    }


}
