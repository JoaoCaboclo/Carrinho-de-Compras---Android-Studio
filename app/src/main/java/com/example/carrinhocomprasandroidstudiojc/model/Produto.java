package com.example.carrinhocomprasandroidstudiojc.model;

public class Produto {
    private long id;
    private String nome;
    private int quantidadeEmEstoque;
    private double preco;

    public Produto(){
        //
    }

    public long getId() {
        return id;
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

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return  this.id + " - " + this.nome + "  | Preço: " + this.getPreco();
    }

   // @Override
   // public String toString() {
    //    return "Produto{" +
    //            "id=" + id +
    //            ", nome='" + nome + '\'' +
    //            ", quantidadeEmEstoque=" + quantidadeEmEstoque +
    //            ", preco=" + preco +
    //            '}';
   // }



}
