package com.example.carrinhocomprasandroidstudiojc.Activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.controller.ProdutoController;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Produto;
import android.widget.Button;
import android.widget.Toast;

public class ActivityProduto extends AppCompatActivity {

    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtQuantidadeProduto;
    private EditText edtPrecoProduto;
    private Button btnSalvarProduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_produto);
        edtIdProduto =   (EditText) findViewById(R.id.edtIdProduto);  // Código de barra
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtQuantidadeProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        btnSalvarProduto = (Button) findViewById(R.id.btnSalvarProduto);

        this.clickNoBotaoSalvarListener();
    }

    private void clickNoBotaoSalvarListener(){

        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produtoACadastrar = getDadosProdutoDoFormulario();
                if (produtoACadastrar != null) {
                    ProdutoController   produtoController = new ProdutoController(ConexaoSQLite.getInstancia(ActivityProduto.this));
                    long idProduto = produtoController.salvarProdutoController(produtoACadastrar);
                    if (idProduto > 0) {
                        Toast.makeText(ActivityProduto.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG ).show();
                    } else {
                        Toast.makeText(ActivityProduto.this, "Problemas ao tentar salvar o Produto. Tente outra vez!", Toast.LENGTH_LONG ).show();
                    }

                } else {
                    Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG ).show();
                }
            }
        });

    }


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
        if (this.edtQuantidadeProduto.getText().toString().isEmpty() == false ) {
            int quantidadeProduto = Integer.parseInt(this.edtQuantidadeProduto.getText().toString());
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
