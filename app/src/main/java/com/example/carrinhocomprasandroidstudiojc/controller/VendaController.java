package com.example.carrinhocomprasandroidstudiojc.controller;
import com.example.carrinhocomprasandroidstudiojc.DAO.VendaDAO;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Venda;

import java.util.List;

public class VendaController {

    private VendaDAO vendaDAO;

    public VendaController(ConexaoSQLite pConexaoSQLite) {
        this.vendaDAO = new VendaDAO(pConexaoSQLite);
    }

    public long salvarVendaController(Venda pVenda){
        long idVendaInserida = vendaDAO.salvarVendaDAO(pVenda);
        return idVendaInserida;
    }

    public boolean salvarItensVendaController(Venda pVenda) {
        return vendaDAO.salvarItensDaVendaDAO(pVenda);
    }

    public List<Venda> listarComprasController() {
        return vendaDAO.listarComprasDAO();
    }
}
