package com.example.carrinhocomprasandroidstudiojc;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import com.example.carrinhocomprasandroidstudiojc.Activities.ActivityProduto;
        import com.example.carrinhocomprasandroidstudiojc.Activities.ActivityVenda;
        import com.example.carrinhocomprasandroidstudiojc.Activities.ListarProdutosActivity;
        import com.example.carrinhocomprasandroidstudiojc.Activities.VendasConsolidadasActivity;
        import com.example.carrinhocomprasandroidstudiojc.controller.ProdutoController;
        import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
        import com.example.carrinhocomprasandroidstudiojc.model.Produto;
public class MainActivity extends AppCompatActivity {

    private Button btnCadastroProdutos;
    private Button btnListarProdutos;
    private Button btnVender;
    private Button btnMinhasCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite.getInstancia(this);

        this.btnCadastroProdutos = (Button) findViewById(R.id.btnCadastroProdutos);
        this.btnCadastroProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);
                //  finish();
            }
        });

        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);
        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                startActivity(intent);
            }
        });

        this.btnVender = (Button) findViewById(R.id.btnVender);
        this.btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityVenda.class);
                startActivity(intent);
            }
        });

        this.btnMinhasCompras = (Button) findViewById(R.id.btnMinhasCompras);

        this.btnMinhasCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VendasConsolidadasActivity.class);
                startActivity(intent);
            }
        });



    }
}
