package pt.ubi.di.pdm.tiagomartins;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TiagoMartins on 14/11/2017.
 */

public class AjudanteParaAbrirBD extends SQLiteOpenHelper {


    private static final int VERSAO_BASEDADOS = 1;
    private static final String NOME_BASEDADOS = "DBRestaurante";
    public static final String NOME_TABELA1 = "Prato";
    public static final String COLUNA1 = "id";
    public static final String COLUNA2 = "nomedoprato";
    public static final String COLUNA3 = "descricao";
    public static final String COLUNA4 = "preco";


    private static final String CRIAR_TABELA_PRATO =
            "CREATE TABLE " + NOME_TABELA1 + "(" +
            COLUNA1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUNA2 + " VARCHAR(30) NOT NULL, " +
            COLUNA3 + " VARCHAR(200), " +
            COLUNA4 + " DOUBLE NOT NULL); ";


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
        contentValues.put(COLUNA2, nomedoprato);
        contentValues.put(COLUNA3, descricao);
        contentValues.put(COLUNA4, preco);

        long resultado = db.insert(NOME_TABELA1,null,contentValues);

        if (resultado==-1)
            return false;

        return true;


    }






}
