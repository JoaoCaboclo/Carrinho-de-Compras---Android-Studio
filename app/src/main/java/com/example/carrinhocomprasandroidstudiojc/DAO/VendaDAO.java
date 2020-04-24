package com.example.carrinhocomprasandroidstudiojc.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carrinhocomprasandroidstudiojc.adapters.AdapterItensDoCarrinho;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.ItemDoCarrinho;
import com.example.carrinhocomprasandroidstudiojc.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {

    private final ConexaoSQLite conexaoSQLite;

    public VendaDAO(ConexaoSQLite pconexaoSQLite) {
        this.conexaoSQLite = pconexaoSQLite;
    }

    public List<Venda> listarComprasDAO() {

        List<Venda> listaCompras = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;   //  Permite navegar nos registros retornados do banco de dados

        String query =
                "SELECT " +
                " venda.id, " +
                " venda.data, " +
                " SUM(produto.preco), " +
                " COUNT(produto.id)  " +
                " from venda " +
                " INNER JOIN itens_da_venda ON (venda.id = itens_da_venda.id_venda) " +
                " INNER JOIN produto ON (itens_da_venda.id_produto = produto.id)   " +
                " GROUP BY venda.id; ";

        try {

            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst())  {
               Venda vendaTmp = null;
               do {
                   vendaTmp = new Venda();
                   vendaTmp.setId(cursor.getLong(0));
                   vendaTmp.setDataDaVenda( new Date(cursor.getLong(1)));
                   vendaTmp.setTotalVenda(cursor.getDouble(2));
                   vendaTmp.setQtdeItens(cursor.getInt(3));
                   listaCompras.add(vendaTmp);
               } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return listaCompras;
    }

    public long salvarVendaDAO(Venda pVenda) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("data", pVenda.getDataDaVenda().getTime());
            long idVendaInserida = db.insert("venda", null, values);
            return idVendaInserida;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return 0;
    }

    public boolean salvarItensDaVendaDAO(Venda pVenda) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {
            ContentValues values =  null;  new ContentValues();
            for (ItemDoCarrinho itemVenda : pVenda.getItensDaVenda()) {
                values =  new ContentValues();
                values.put("quantidade_vendida", itemVenda.getQuantidadeSelecionada());
                values.put("id_produto", itemVenda.getIdProduto());
                values.put("id_venda", pVenda.getId());
                db.insert("itens_da_venda", null, values);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }



}
