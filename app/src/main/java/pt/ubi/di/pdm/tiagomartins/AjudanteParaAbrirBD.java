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
    protected static final String COLUNA1 = "nomedoprato";
    protected static final String COLUNA2 = "descricao";
    protected static final String COLUNA3 = "preco";


    private static final String CRIAR_TABELA_PRATO =
            "CREATE TABLE " + NOME_TABELA1 + "(" +
            COLUNA1 + " VARCHAR(30) PRIMARY KEY NOT NULL, " +
            COLUNA2 + " VARCHAR(200) NOT NULL, " +
            COLUNA3 + " DOUBLE NOT NULL);";


    AjudanteParaAbrirBD(Context context) {

        super(context, NOME_BASEDADOS, null, VERSAO_BASEDADOS);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CRIAR_TABELA_PRATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {

        db.execSQL("DROP TABLE " + NOME_TABELA1 + ";");
        db.execSQL(CRIAR_TABELA_PRATO);
    }

    public boolean inserirDados (String nomedoprato,String descricao,double preco){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA1, nomedoprato);
        contentValues.put(COLUNA2, descricao);
        contentValues.put(COLUNA3, preco);

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

        queryres = db.rawQuery("select " + COLUNA1 + " from  " + NOME_TABELA1 + " where " + COLUNA1 + " = " + "'"+prato+"'", null);

        //System.out.println("Erro: " + queryres.getCount());

        if(queryres.getCount()==0)
            return false;



        return true;


    }


    public Cursor swhowinfoprato(String prato){


        Cursor queryres;

        SQLiteDatabase db = this.getWritableDatabase();

        queryres = db.rawQuery("select * "  + " from  " + NOME_TABELA1 + " where " + COLUNA1 + " = " + "'"+prato+"'", null);

        System.out.println("Numero devolvido pela query: " + queryres.getCount());

        return queryres;


    }


}
