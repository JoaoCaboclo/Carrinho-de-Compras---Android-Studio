package com.example.carrinhocomprasandroidstudiojc.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.adapters.AdapterItensDoCarrinho;
import com.example.carrinhocomprasandroidstudiojc.controller.ProdutoController;
import com.example.carrinhocomprasandroidstudiojc.controller.VendaController;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.ItemDoCarrinho;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;
import com.example.carrinhocomprasandroidstudiojc.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityVenda extends AppCompatActivity {

    // Objetos necessário para a carregar a lista de produtos no spinner
    //  é equivalente ao combobox
    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoController produtoController;
    private EditText quantidadeItem;
    private TextView  tvTotalCompra;

    // Objetos relativos ao carrinho de compras
    private ListView lsvCarrinhoCompras;
    private List<ItemDoCarrinho> listaItensDoCarrinho;
    private AdapterItensDoCarrinho adapterItensDoCarrinho;

    //  Controlador para permitir chegar até a clase vendaDAO e salvar a venda
    private VendaController vendaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        // Inicializa o objeto vendaController;
        this.vendaController = new VendaController(ConexaoSQLite.getInstancia(this));

        // Faz o link (bind) ligando os componentes que estão lá na tela Activity_venda.xml
        // com os objetos aqui no lado java
        this.spnProdutos = (Spinner) findViewById(R.id.spnProduto);
        this.produtoController = new ProdutoController(ConexaoSQLite.getInstancia(this));
        this.listaProduto = produtoController.getListaProdutosController();


        //  Configurando o adapter que servira de canal entre os dados do banco de dados e o spinner da tela
        ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<Produto>(
                this, android.R.layout.simple_spinner_item, this.listaProduto);

        //  Agora vamos ligar o adapter ao spinner e a lista de produto será disponibilizada na tela
        this.spnProdutos.setAdapter(spnProdutoAdapter);

        this.quantidadeItem =  this.findViewById(R.id.edtQuantidadeProduto);

        // Carregar objeto referênte o o componente da tela Total da compra
        this.tvTotalCompra = (TextView) findViewById(R.id.tvTotalCompra);

        //  Instanciar variaveis e objetos referentes ao carrinho de compras
        this.lsvCarrinhoCompras = (ListView) findViewById(R.id.lsvProdutos);
        this.listaItensDoCarrinho = new ArrayList<>();
        //  Configurando o adapterItensDoCarrinho com o contexto e a coleção
        this.adapterItensDoCarrinho = new AdapterItensDoCarrinho(ActivityVenda.this, this.listaItensDoCarrinho);
        // Adicionando o adapterItensDoCarrinho a lsitview lsvCarrinhoCompras
        this.lsvCarrinhoCompras.setAdapter(this.adapterItensDoCarrinho);
    }

    //   Clique do botão adicionar ao carrinho
    public void eventAddProduto(View view) {

        ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho();
        Produto produtoSelecionado = (Produto) this.spnProdutos.getSelectedItem();
        int quantidadeProduto = 0;
        if (this.quantidadeItem.getText().toString().equals("")) {
            quantidadeProduto= 1;
        } else {
            quantidadeProduto = Integer.parseInt(this.quantidadeItem.getText().toString());
        }

        itemDoCarrinho.setNome(produtoSelecionado.getNome());
        itemDoCarrinho.setIdProduto(produtoSelecionado.getId());
        itemDoCarrinho.setQuantidadeSelecionada(quantidadeProduto);
        itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
        itemDoCarrinho.setPrecoDoItemVenda( itemDoCarrinho.getQuantidadeSelecionada() * itemDoCarrinho.getPrecoProduto());

        this.adapterItensDoCarrinho.addItemCarrinho(itemDoCarrinho);
        double totalDaCompra =  this.calcularTotalCompra(this.listaItensDoCarrinho);
        this.atualizarValorTotalCompra(totalDaCompra);

        // Método para permitir abrir uma caixa de dialog para exclusão ou não do item do carrinho
        this.lsvCarrinhoCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //  aletramos os parâmetros gerados automaticamnete
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                final ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) adapterItensDoCarrinho.getItem(posicao);
                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(ActivityVenda.this);
                janelaEscolha.setTitle("Exclusão de Item do Carrinho");
                janelaEscolha.setMessage("Confirma a exclusão do Item: " + itemDoCarrinho.getNome() + " ?" );

                // O botão de negação
                janelaEscolha.setNegativeButton("Não", null);

                // O botão confirmando a exclusão
                janelaEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean excluiu = false;
                        excluiu = adapterItensDoCarrinho.removerItemDoCarrinho(posicao);

                        double totalDaCompra =  calcularTotalCompra(listaItensDoCarrinho);
                        atualizarValorTotalCompra(totalDaCompra);
                        if (!excluiu) {
                            Toast.makeText(ActivityVenda.this, "Erro ao tentar excluir item", Toast.LENGTH_LONG).show();
                        } else {

                           // TEMOS QUE TIRAR O "THIS" porque aqui não podemos usar as variáveis de class
                           //   porque estamos dentro de uma chamada da interface DialogInterface
                           // this.adapterItensDoCarrinho.addItemCarrinho(itemDoCarrinho);
                           // double totalDaCompra =  this.calcularTotalCompra(this.listaItensDoCarrinho);
                           // this.atualizarValorTotalCompra(totalDaCompra);

                        }

                    }
                });

                janelaEscolha.show();

            }
        });

   //   Toast.makeText(ActivityVenda.this, "Vou dar o refresh", Toast.LENGTH_LONG ).show();
  }

  //  Método para calcular o preco da compra
  private double calcularTotalCompra(List<ItemDoCarrinho> pListaItensdDoCarrinho) {
      double totalCompra = 0.0d;
      for (ItemDoCarrinho itemDoCarrinho :pListaItensdDoCarrinho) {
          totalCompra += itemDoCarrinho.getPrecoDoItemVenda();
          Log.d("PRODUTO: ", itemDoCarrinho.toString() );
      }
      return totalCompra;
  }

  private void atualizarValorTotalCompra(double pvalorTotal){
     this.tvTotalCompra.setText(String.valueOf(pvalorTotal));
  }

  public void eventFecharVenda(View view) {
    Venda vendaFechada = this.criarVenda();
    salvarVenda(vendaFechada);
  }

  private Venda criarVenda() {
    Venda venda = new Venda();
    venda.setDataDaVenda(new Date());
    venda.setItensDaVenda(this.listaItensDoCarrinho);
    return venda;
  }

  public boolean salvarVenda(Venda pvenda)  {

    long idVenda = vendaController.salvarVendaController(pvenda);
    if ( idVenda> 0 ) {
        pvenda.setId(idVenda);
        if (vendaController.salvarItensVendaController(pvenda)  ) {
           Toast.makeText(this, "Venda: " + idVenda + ", Fechada com Sucesso!", Toast.LENGTH_LONG).show();
           return true;
        } else {
           Toast.makeText(this, "Erro ao tentar Fechar a Venda", Toast.LENGTH_LONG).show();
           return false;
        }
    }
    return false;
  }

}
