package com.example.carrinhocomprasandroidstudiojc.model;

public class ItemDoCarrinho {

    private long id;
    private long idProduto;
    private String nome;
    private int quantidadeSelecionada;
    private double precoProduto;
    private double precoDoItemVenda;  //   quantidadeSelecionada * precoProduto

    public long getId() {
        return id;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeSelecionada() {
        return quantidadeSelecionada;
    }

    public void setQuantidadeSelecionada(int quantidadeSelecionada) {
        this.quantidadeSelecionada = quantidadeSelecionada;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPrecoDoItemVenda() {
        return precoDoItemVenda;
    }

    public void setPrecoDoItemVenda(double precoDoItemVenda) {
        this.precoDoItemVenda = precoDoItemVenda;
    }

    @Override
    public String toString() {
        return "ItemDoCarrinho{" +
                "id=" + id +
                ", idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", quantidadeSelecionada=" + quantidadeSelecionada +
                ", precoProduto=" + precoProduto +
                ", precoDoItemVenda=" + precoDoItemVenda +
                '}';
    }
}
