package com.example.carrinhocomprasandroidstudiojc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.carrinhocomprasandroidstudiojc.R;
import com.example.carrinhocomprasandroidstudiojc.adapters.AdapterListaDasCompras;
import com.example.carrinhocomprasandroidstudiojc.controller.VendaController;
import com.example.carrinhocomprasandroidstudiojc.dbHelper.ConexaoSQLite;
import com.example.carrinhocomprasandroidstudiojc.model.Venda;

import java.util.List;

public class VendasConsolidadasActivity extends AppCompatActivity {

    //  Declara os objetos necessários para alimentar o adapter
    private ListView lsvCompras;
    private List<Venda> lstcompras;
    private AdapterListaDasCompras adapterListaDasCompras;
    private VendaController vendaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_consolidadas);

        this.vendaController = new VendaController(ConexaoSQLite.getInstancia(VendasConsolidadasActivity.this));
        lstcompras = this.vendaController.listarComprasController();
        this.lsvCompras = (ListView) findViewById(R.id.lsvMinhasCompras);
        this.adapterListaDasCompras = new AdapterListaDasCompras(VendasConsolidadasActivity.this, lstcompras );
        this.lsvCompras.setAdapter(this.adapterListaDasCompras);

    }
}
