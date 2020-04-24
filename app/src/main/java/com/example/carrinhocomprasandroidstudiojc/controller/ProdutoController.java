package com.example.carrinhocomprasandroidstudiojc.controller;

import com.example.carrinhocomprasandroidstudiojc.DAO.ProdutoDAO;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;

import java.util.List;

public class ProdutoController {
   private final ProdutoDAO produtoDAO;

   public ProdutoController(ConexaoSQLite pConexaoSQLite) {
       produtoDAO = new ProdutoDAO(pConexaoSQLite);
   }

   public long salvarProdutoController(Produto pProduto) {
      return this.produtoDAO.salvarProdutoDAO(pProduto);
   }

   public List<Produto> getListaProdutosController(){
      return this.produtoDAO.getListaProdutosDAO();
   }

   public boolean excluirProdutoController(long pIdProduto) {
     return this.produtoDAO.excluirProdutoDAO(pIdProduto);
   }

   public boolean atualizarProdutoController(Produto pProduto) {
      return this.produtoDAO.atualizarProdutoDAO(pProduto);
   }

}
