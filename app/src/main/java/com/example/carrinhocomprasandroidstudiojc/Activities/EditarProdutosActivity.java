package com.example.carrinhocomprasandroidstudiojc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.controller.ProdutoController;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;

public class EditarProdutosActivity extends AppCompatActivity {

    private Produto  produto;
    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtPrecoProduto;
    private EditText edtEstoqueProduto;
    private Button btnSalvarAlteracoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produtos);

        this.edtIdProduto       = (EditText) findViewById(R.id.edtIdProduto);
        this.edtNomeProduto     = (EditText) findViewById(R.id.edtNomeProduto);
        this.edtPrecoProduto    = (EditText) findViewById(R.id.edtPrecoProduto);
        this.edtEstoqueProduto  = (EditText) findViewById(R.id.edtQuantidadeProduto);
        this.btnSalvarAlteracoes = (Button)  findViewById(R.id.btnSalvarProduto);

        //  Recebe os dados enviados da tela de consulta
        Bundle bundleDadosProduto = getIntent().getExtras();

        Produto produto = new Produto();
        produto.setId(bundleDadosProduto.getLong("id_produto"));
        produto.setNome(bundleDadosProduto.getString("nome_produto"));
        produto.setPreco(bundleDadosProduto.getDouble("preco_produto"));
        produto.setQuantidadeEmEstoque(bundleDadosProduto.getInt("estoque_produto"));

        this.setDadosProduto(produto);
        this.ClickNoBotaoSalvarListener();

    }

    private void setDadosProduto(Produto produto) {

        this.edtIdProduto.setText(String.valueOf(produto.getId())); ;
        this.edtNomeProduto.setText(produto.getNome()); ;
        this.edtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        this.edtEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEmEstoque()));

    }

    private void ClickNoBotaoSalvarListener(){

        this.btnSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto produtoAAtualizar = getDadosProdutoDoFormulario();
                if (produtoAAtualizar != null) {
//                  Toast.makeText(EditarProdutosActivity.this, "Tudo certo ate aqui - Produto alterado: " + produtoACadastrar.toString(), Toast.LENGTH_LONG ).show();
                    ProdutoController produtoController = new ProdutoController(ConexaoSQLite.getInstancia(EditarProdutosActivity.this));
                    boolean atualizou = produtoController.atualizarProdutoController(produtoAAtualizar);
                    if (atualizou == true ) {
                        Toast.makeText(EditarProdutosActivity.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG ).show();
                    } else {
                        Toast.makeText(EditarProdutosActivity.this, "Problemas ao tentar salvar o Produto. Tente outra vez!", Toast.LENGTH_LONG ).show();
                    }

                } else {
                    Toast.makeText(EditarProdutosActivity.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG ).show();
                }

            }
        });

    }

    // Pega os dados digitados na tela no momento do click no botão salvar
    private Produto getDadosProdutoDoFormulario() {
        this.produto = new Produto();
        if (this.edtIdProduto.getText().toString().isEmpty() == false ) {
            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
        } else {
            return null;
        }
        if (this.edtNomeProduto.getText().toString().isEmpty() == false ) {
            this.produto.setNome(this.edtNomeProduto.getText().toString());
        } else {
            return null;
        }
        if (this.edtEstoqueProduto.getText().toString().isEmpty() == false ) {
            int quantidadeProduto = Integer.parseInt(this.edtEstoqueProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(quantidadeProduto);
        } else {
            return null;
        }
        if (this.edtPrecoProduto.getText().toString().isEmpty() == false ) {
            double precoProduto = Double.parseDouble(this.edtPrecoProduto.getText().toString());
            this.produto.setPreco(precoProduto);
        } else {
            return null;
        }
        return produto;
    }



}
