package com.example.carrinhocomprasandroidstudiojc.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarProdutoDAO(Produto pProduto) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = new  ContentValues();
            values.put("id",pProduto.getId());
            values.put("nome",pProduto.getNome());
            values.put("quantidade_em_estoque",pProduto.getQuantidadeEmEstoque());
            values.put("preco",pProduto.getPreco());
            long idProdutoIserido = db.insert("produto", null, values);
            return idProdutoIserido;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return 0;
    }

    @SuppressLint("LongLogTag")
    public List<Produto> getListaProdutosDAO() {
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;
        String query = "SELECT * FROM produto;";
        try {
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                Produto produtotmp = null;
                do {
                    produtotmp = new Produto();
                    produtotmp.setId(cursor.getLong(0));
                    produtotmp.setNome(cursor.getString(1));
                    produtotmp.setQuantidadeEmEstoque(cursor.getInt(2));
                    produtotmp.setPreco(cursor.getDouble(3));
                    listaProdutos.add(produtotmp);
                }while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERRO getListaProdutosDAO", "Erro ao carregar Produtos");
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return listaProdutos;
    }


    public boolean excluirProdutoDAO(long pIdProduto) {
       SQLiteDatabase db = null;
       try {
           db = this.conexaoSQLite.getWritableDatabase();
           db.delete(
                   "produto",
                   "id = ?",
                   new String[]{String.valueOf(pIdProduto)}
           );
       } catch (Exception e) {
           Log.d("excluirProdutoDAO", "Não foi possível excluir o produto");
           return false;
       } finally {
           if (db != null) {
               db.close();
           }
       }
       return true;
    }

    public boolean atualizarProdutoDAO(Produto pProduto) {

        SQLiteDatabase db = null;

        try {
            db = conexaoSQLite.getWritableDatabase();
            ContentValues produtoAtributos = new  ContentValues();
            //values.put("id",pProduto.getId());
            produtoAtributos.put("nome",pProduto.getNome());
            produtoAtributos.put("quantidade_em_estoque",pProduto.getQuantidadeEmEstoque());
            produtoAtributos.put("preco",pProduto.getPreco());

            long atualizou = db.update("produto", produtoAtributos, "id = ?",
                    new String[] {String.valueOf(pProduto.getId())} );
            if (atualizou > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return false;
    }

}
