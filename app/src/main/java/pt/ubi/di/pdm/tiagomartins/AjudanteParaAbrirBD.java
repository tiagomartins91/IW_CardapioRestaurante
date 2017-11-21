package pt.ubi.di.pdm.tiagomartins;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by TiagoMartins on 14/11/2017.
 */

public class AjudanteParaAbrirBD extends SQLiteOpenHelper {


    private static final int VERSAO_BASEDADOS = 1;
    private static final String NOME_BASEDADOS = "DBRestaurante";
    protected static final String NOME_TABELA1 = "Prato";
    protected static final String NOME_TABELA2 = "User";
    protected static final String T1_COLUNA1 = "nomedoprato";
    protected static final String T1_COLUNA2 = "descricao";
    protected static final String T1_COLUNA3 = "preco";
    protected static final String T2_COLUNA1 = "username";
    protected static final String T2_COLUNA2 = "password";


    private static final String CRIAR_TABELA_PRATO =
            "CREATE TABLE " + NOME_TABELA1 + "(" +
            T1_COLUNA1 + " VARCHAR(30) PRIMARY KEY NOT NULL, " +
            T1_COLUNA2 + " VARCHAR(200) NOT NULL, " +
            T1_COLUNA3 + " DOUBLE NOT NULL);";


    private static final String CRIAR_TABELA_USER =
            "CREATE TABLE " + NOME_TABELA2 + "(" +
                    T2_COLUNA1 + " VARCHAR(30) PRIMARY KEY NOT NULL, " +
                    T2_COLUNA2 + " VARCHAR(30) NOT NULL);";




    AjudanteParaAbrirBD(Context context) {

        super(context, NOME_BASEDADOS, null, VERSAO_BASEDADOS);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CRIAR_TABELA_PRATO);
        db.execSQL(CRIAR_TABELA_USER);
        //db.execSQL("insert into " + NOME_TABELA2 + "(" + T2_COLUNA1 + "," + T2_COLUNA2 +  ")" + values ( 'pdm' + "," + 'pdm' ) );

        db.execSQL("insert into User ( username, password ) values ('pdm', 'pdm')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {

        //db.execSQL("DROP TABLE " + NOME_TABELA1 + ";");
        //db.execSQL(CRIAR_TABELA_PRATO);
    }



    public boolean inserirDados (String nomedoprato,String descricao,double preco){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(T1_COLUNA1, nomedoprato);
        contentValues.put(T1_COLUNA2, descricao);
        contentValues.put(T1_COLUNA3, preco);

        long resultado = db.insert(NOME_TABELA1,null,contentValues);

        if (resultado==-1)
            return false;

        return true;


    }

    public Cursor getPratos (){

        Cursor queryres;
        SQLiteDatabase db = this.getWritableDatabase();

        queryres = db.rawQuery("select * " +  " from  " + NOME_TABELA1,null);

        return queryres;


    }

    public boolean existeprato(String prato){


        Cursor queryres;
        SQLiteDatabase db = this.getWritableDatabase();

        queryres = db.rawQuery("select " + T1_COLUNA1 + " from  " + NOME_TABELA1 + " where " + T1_COLUNA1 + " = " + "'"+prato+"'", null);

        //System.out.println("Erro: " + queryres.getCount());

        if(queryres.getCount()==0)
            return false;



        return true;


    }


    public Cursor swhowinfoprato(String prato){


        Cursor queryres;

        SQLiteDatabase db = this.getWritableDatabase();

        queryres = db.rawQuery("select * "  + " from  " + NOME_TABELA1 + " where " + T1_COLUNA1 + " = " + "'"+prato+"'", null);

        System.out.println("Numero devolvido pela query: " + queryres.getCount());

        return queryres;


    }

    public Cursor getuser(){

        Cursor queryres;

        SQLiteDatabase db = this.getWritableDatabase();

        queryres = db.rawQuery("select * " +  " from  " + NOME_TABELA2,null);

        return queryres;

    }


}
