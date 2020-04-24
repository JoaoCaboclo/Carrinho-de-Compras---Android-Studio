package com.example.carrinhocomprasandroidstudiojc.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrinhocomprasandroidstudiojc.Activities.ActivityVenda;
import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.model.ItemDoCarrinho;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;

import java.util.List;

public class AdapterItensDoCarrinho extends BaseAdapter {

    private Context context;
    private List<ItemDoCarrinho> ItensDoCarrinho;
    TextView tvNomeProduto;
    TextView tvPrecoProduto;
    TextView tvQuantidadeSelecionada;
    TextView tvValorItem;


    public AdapterItensDoCarrinho(Context context, List<ItemDoCarrinho> ItensDoCarrinho) {
        this.context = context;
        this.ItensDoCarrinho = ItensDoCarrinho;
    }

    @Override
    public int getCount() {
        return this.ItensDoCarrinho.size();
    }

    @Override
    public Object getItem(int position) {
        return this.ItensDoCarrinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

     public boolean removerItemDoCarrinho(int posicao) {
        this.ItensDoCarrinho.remove(posicao);
        notifyDataSetChanged();
        return true;
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View v = View.inflate(this.context, R.layout.layout_carrinho_produtos, null);
       TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
       TextView tvPrecoProduto = (TextView) v.findViewById(R.id.tvPrecoProduto);
       TextView tvQuantidadeSelecionada = (TextView) v.findViewById(R.id.tvQteProduto);
       TextView tvValorItem = (TextView) v.findViewById(R.id.tvValorTotalItem);

       if (! this.ItensDoCarrinho.isEmpty() ) {
           tvNomeProduto.setText(this.ItensDoCarrinho.get(position).getNome());
           tvPrecoProduto.setText(String.valueOf(this.ItensDoCarrinho.get(position).getPrecoProduto()));
           tvQuantidadeSelecionada.setText(String.valueOf(this.ItensDoCarrinho.get(position).getQuantidadeSelecionada()));
           tvValorItem.setText(String.valueOf(this.ItensDoCarrinho.get(position).getPrecoDoItemVenda()));
       }
       return v;
    }

    //  Método para fazer o ADAPTER adicionar o item  ItemDoCarrinho a lista
    public void addItemCarrinho(ItemDoCarrinho pitemDoCarrinho){
        this.ItensDoCarrinho.add(pitemDoCarrinho);
        this.notifyDataSetChanged();
    }

    //  Método para atualizar a lista de produtos do adapter
    public void atualizar(List<ItemDoCarrinho> pItensDoCarrinho) {
        this.ItensDoCarrinho.clear();      //  limpa a lista que esta no lstview
        this.ItensDoCarrinho = pItensDoCarrinho;  //  Recebe a nova lista
        this.notifyDataSetChanged();   //  Notifica a lstview para que a mudança possa refletir na tela
    }
}

