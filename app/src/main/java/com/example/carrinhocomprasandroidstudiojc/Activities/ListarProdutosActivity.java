package com.example.carrinhocomprasandroidstudiojc.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.adapters.AdapterListaProdutos;
import com.example.carrinhocomprasandroidstudiojc.controller.ProdutoController;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    // Criando os elementos necessários para expor a lista de produtos
    // Use ALT + ENTER --  para incluir o import das classes
    //  System.out.Println("Estou na activity Produto e vou criar os produtos");
    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    ProdutoController produtoController = new ProdutoController(ConexaoSQLite.getInstancia(ListarProdutosActivity.this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        this.produtoController = new ProdutoController(ConexaoSQLite.getInstancia(ListarProdutosActivity.this));
        produtoList = produtoController.getListaProdutosController();

        this.lsvProdutos = (ListView) findViewById(R.id.lsvProdutos);
        this.adapterListaProdutos = new AdapterListaProdutos(ListarProdutosActivity.this, this.produtoList);
        this.lsvProdutos.setAdapter(this.adapterListaProdutos);

        //  Seleciona o produto selecionado no click do botão
        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int Item_position, long id_Item_Selected) {
                final Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(Item_position);
                final int indiceNaLista = Item_position;
//              Toast.makeText(ListarProdutosActivity.this, "Produto: " +produtoSelecionado.getNome(), Toast.LENGTH_LONG ).show();
                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ListarProdutosActivity.this);
                janelaDeEscolha.setTitle("Opções:");
                janelaDeEscolha.setMessage("O que deseja fazer com o produto: " + produtoSelecionado.getNome());
                // criando os botões da janela
                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean excluiu =  produtoController.excluirProdutoController(produtoSelecionado.getId());
                        dialog.cancel();
                        if (excluiu == true) {
                            adapterListaProdutos.removerProduto(indiceNaLista);  //  Retira da lista e atualiza o listview
                            Toast.makeText(ListarProdutosActivity.this, "Produto excluido com sucesso: " +produtoSelecionado.getNome(), Toast.LENGTH_LONG ).show();
                        } else {
                           Toast.makeText(ListarProdutosActivity.this, "Erro ao tentar excluir o produdo: " +produtoSelecionado.getNome(), Toast.LENGTH_LONG ).show();
                        }
                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundleDadosProduto = new Bundle();
                        bundleDadosProduto.putLong("id_produto", produtoSelecionado.getId() );
                        bundleDadosProduto.putString("nome_produto", produtoSelecionado.getNome());
                        bundleDadosProduto.putDouble("preco_produto", produtoSelecionado.getPreco());
                        bundleDadosProduto.putInt("estoque_produto", produtoSelecionado.getQuantidadeEmEstoque());

                        Intent intentEditarProdutos = new Intent(ListarProdutosActivity.this, EditarProdutosActivity.class);
                        intentEditarProdutos.putExtras(bundleDadosProduto);
                        startActivity(intentEditarProdutos);
                        dialog.cancel();
                    }
                });
                janelaDeEscolha.create().show();
            }
        });
    }

    //  Botão para recarregar a lista pois não alteração o listview não é atualizado automaticamente
    public void eventAtualizarProdutos(View view) {
        // 1-Observar que a forma de definição e uso do objeto , produtoController, foi alterada
        // 2-Chamar o Update na class Adapter
        this.adapterListaProdutos.atualizarListaProdutosNoAdaper(this.produtoController.getListaProdutosController());
    }

}
