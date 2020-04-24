package com.example.carrinhocomprasandroidstudiojc.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "carrinho_compras_DB";

    public ConexaoSQLite(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstancia(Context context) {
        if (INSTANCIA_CONEXAO == null) {
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqlLiteDatabase) {

        String sqlTableProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                           "id INTEGER PRIMARY KEY," +
                           "nome TEXT," +
                           "quantidade_em_estoque INTEGER," +
                           "preco REAL" +
                        ")";

        sqlLiteDatabase.execSQL(sqlTableProduto);

        String sqlTableVenda =
                "CREATE TABLE IF NOT EXISTS venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "data INTEGER" +
                        ")";
        sqlLiteDatabase.execSQL(sqlTableVenda);

        String sqlTableItemDaVenda =
                "CREATE TABLE IF NOT EXISTS itens_da_venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "quantidade_vendida INTEGER," +
                        "id_produto INTEGER," +
                        "id_venda INTEGER" +
                        ")";

        sqlLiteDatabase.execSQL(sqlTableItemDaVenda);

     //   try {
     //       sqlLiteDatabase.execSQL(sqlTableProduto);
     //   } catch (Exception e) {
     //        e.toString();
     //   }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
