package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.up.ihelppo.R;

public class MenuActivity extends AppCompatActivity {

    private  Button btnMeusDados, btnCadastrarCategoria, btnHistoricoAtividades, btnAtividadesPendentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMeusDados = (Button) findViewById(R.id.btnMeusDados);
        btnCadastrarCategoria = (Button) findViewById(R.id.btnCadastrarCategoria);
        btnHistoricoAtividades = (Button) findViewById(R.id.btnHistoricoAtividades);
        btnAtividadesPendentes = (Button) findViewById(R.id.btnAtividadesPendentes);
    }

    public void btnHistoricoAtivClick(View view) {
        Intent intent = new Intent(MenuActivity.this, HistoricoAtividadesActivity.class );
        startActivity(intent);
    }

    public void btnCadastrarCategoriaClick(View view) {
        Intent intent = new Intent(MenuActivity.this, CadastroCategoriaActivity.class );
        startActivity(intent);
    }

    public void btnAtividadesPendentesClick(View view) {
        Intent intent = new Intent(MenuActivity.this, ListarAtividadesPendentesActivity.class );
        startActivity(intent);
    }
}
