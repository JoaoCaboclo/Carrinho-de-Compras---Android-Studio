package com.example.carrinhocomprasandroidstudiojc.model;

import java.util.Date;
import java.util.List;

public class Venda {

    private long id;
    private Produto produtoVendido;
    private Date dataDaVenda;
    private String nomeDoCliente;
    private List<ItemDoCarrinho> ItensDaVenda;
    private double TotalVenda;
    private int QtdeItens;

    public Venda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public Date getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Date dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public List<ItemDoCarrinho> getItensDaVenda() {
        return ItensDaVenda;
    }

    public void setItensDaVenda(List<ItemDoCarrinho> itensDaVenda) {
        ItensDaVenda = itensDaVenda;
    }

    public double getTotalVenda() {
        return TotalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        TotalVenda = totalVenda;
    }

    public int getQtdeItens() {
        return QtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        QtdeItens = qtdeItens;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", produtoVendido=" + produtoVendido.toString() +
                ", dataDaVenda=" + dataDaVenda +
                ", nomeDoCliente='" + nomeDoCliente + '\'' +
                ", ItensDaVenda=" + ItensDaVenda +
                ", TotalVenda=" + TotalVenda +
                ", QtdeItens=" + QtdeItens +
                '}';
    }
}
