package pt.ubi.di.pdm.tiagomartins;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TiagoMartins on 14/11/2017.
 */

public class AjudanteParaAbrirBD extends SQLiteOpenHelper {


    private static final int VERSAO_BASEDADOS = 1;
    private static final String NOME_BASEDADOS = "CRestaurante";
    protected static final String NOME_TABELA1 = "Prato";
    protected static final String COLUNA1 = "id";
    protected static final String COLUNA2 = "nomedoprato";
    protected static final String COLUNA3 = "descricao";
    protected static final String COLUNA4 = "preco";


    private static final String CRIAR_TABELA_PRATO = "CREATE TABLE" + NOME_TABELA1 + "(" +
            COLUNA1 + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA2 + "VARCHAR(30) NOT NULL," +
            COLUNA3 + "VARCHAR(200)," +
            COLUNA4 + "DOUBLE NOT NULL);";


    AjudanteParaAbrirBD(Context context) {
        super(context, NOME_BASEDADOS, null, VERSAO_BASEDADOS);
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


}
