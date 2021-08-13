package etec.com.ewen.matheus.appcameragaleria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDImagens extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Fotos.db";
    private static final int VERSAO = 1;
    private static final String TABELA = "tbFotos";

    private static final String CODIGO = "codigo";
    private static final String ID = "id";
    private static final String CAMINHO = "caminho";

    public BDImagens(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " (" +
                CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ID + " TEXT," +
                CAMINHO + " TEXT);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    //CADASTRO
    public long cadastra(Fotos foto) {
        ContentValues valores = new ContentValues();
        long retornoDB;

        valores.put(ID, foto.getId());
        valores.put(CAMINHO, foto.getCaminho());

        retornoDB = getWritableDatabase().insert(TABELA, null, valores);
        return retornoDB;
    }

    //ALTERAÇÃO
    public long altera(Fotos foto) {
        ContentValues valores = new ContentValues();
        long retornoDB;

        valores.put(ID, foto.getId());
        valores.put(CAMINHO, foto.getCaminho());
        String[] args = {String.valueOf(foto.getId())};
        retornoDB = getWritableDatabase().update(TABELA, valores, "id=?", args);
        return retornoDB;
    }


    public void excluiTudo() {
        getWritableDatabase().delete(TABELA, null, null);
    }

    //CONSULTA
    public ArrayList<Fotos> consultarFotos() {
        //SELECT * FROM TBCARROS
        String[] colunas = {ID, CAMINHO};

        Cursor cursor = getWritableDatabase().query(TABELA, colunas, null, null, null, null,
                null, null);

        ArrayList<Fotos> listaCarro = new ArrayList<>();

        Fotos fotos;
        while (cursor.moveToNext()) {
            fotos = new Fotos();


            fotos.setId(cursor.getString(0));
            fotos.setCaminho(cursor.getString(1));

            listaCarro.add(fotos);
        }
        return listaCarro;
    }
}

