package com.example.carrinhocomprasandroidstudiojc.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;
import com.example.carrinhocomprasandroidstudiojc.model.Venda;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterListaDasCompras extends BaseAdapter {

    private Context context;
    private List<Venda> compraList;
    private SimpleDateFormat simpleDateFormat;

    public AdapterListaDasCompras(Context context, List<Venda> compraList) {
        this.context = context;
        this.compraList = compraList;
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getCount() {
        return this.compraList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.compraList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

/*  public void removerProduto(int posicao) {
        this.compraList.remove(posicao);
        notifyDataSetChanged();
     }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View v = View.inflate(this.context, R.layout.layout_minhas_compras, null);
       TextView tvDataCompra = (TextView) v.findViewById(R.id.tvDataCompra);
       TextView tvTotalCompra = (TextView) v.findViewById(R.id.tvTotalCompra);
       TextView tvQtdeItens = (TextView) v.findViewById(R.id.tvQtdeItens);

        tvDataCompra.setText( this.simpleDateFormat.format(this.compraList.get(position).getDataDaVenda()));
        tvTotalCompra.setText(String.valueOf(this.compraList.get(position).getTotalVenda()));
        tvQtdeItens.setText(String.valueOf(this.compraList.get(position).getQtdeItens()));

       return v;
    }

    //  Método para atualizar a lista de compras do adapter
    public void atualizarListaProdutosNoAdaper(List<Venda> pCompras) {
        this.compraList.clear();      //  limpa a lista que esta no lstview
        this.compraList = pCompras;  //  Recebe a nova lista
        this.notifyDataSetChanged();   //  Notifica a lstview para que a mudança possa refletir na tela
    }


}
