package com.example.carrinhocomprasandroidstudiojc.adapters;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;
import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public AdapterListaProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

     public void removerProduto(int posicao) {
        this.produtoList.remove(posicao);
        notifyDataSetChanged();
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View v = View.inflate(this.context, R.layout.layout_produto, null);
       TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNompeProduto);
       TextView tvPrecoProduto = (TextView) v.findViewById(R.id.tvPrecoProduto);
       TextView tvEstoqueProduto = (TextView) v.findViewById(R.id.tvEstoqueProduto);
       tvNomeProduto.setText(this.produtoList.get(position).getNome());
       tvPrecoProduto.setText(String.valueOf(this.produtoList.get(position).getPreco()));
       tvEstoqueProduto.setText(String.valueOf(this.produtoList.get(position).getQuantidadeEmEstoque()));
       return v;
    }

    //  Método para atualizar a lista de produtos do adapter
    public void atualizarListaProdutosNoAdaper(List<Produto> pProdutos) {
        this.produtoList.clear();      //  limpa a lista que esta no lstview
        this.produtoList = pProdutos;  //  Recebe a nova lista
        this.notifyDataSetChanged();   //  Notifica a lstview para que a mudança possa refletir na tela
    }


}
