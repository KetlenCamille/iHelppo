package edu.up.ihelppo.view;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import edu.up.ihelppo.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private  Button btnMeusDados, btnCadastrarCategoria, btnListarCategorias, btnHistoricoAtividades, btnAtividadesPendentes, btnAtividadesFuturas;

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        InitiFabMenu();

        btnMeusDados = (Button) findViewById(R.id.btnMeusDados);
        btnCadastrarCategoria = (Button) findViewById(R.id.btnCadastrarCategoria);
        btnHistoricoAtividades = (Button) findViewById(R.id.btnHistoricoAtividades);
        btnAtividadesPendentes = (Button) findViewById(R.id.btnAtividadesPendentes);
        btnAtividadesFuturas = (Button) findViewById(R.id.btnAtividadesFuturas);
        btnListarCategorias = (Button) findViewById(R.id.btnListarCategorias);

    }

    public void btnMeusDadosClick(View view){
        Intent intent = new Intent(MenuActivity.this, MeusDadosActivity.class);
        startActivity(intent);
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

    public void btnAtividadesFuturasClick(View view) {
        Intent intent = new Intent(MenuActivity.this, ListarAtividadesFuturasActivity.class );
        startActivity(intent);
    }

    private void InitiFabMenu() {
        fabMain = findViewById(R.id.fabMain);
        fabListarAtv = findViewById(R.id.fabListarAtv);
        fabPerfil = findViewById(R.id.fabPerfil);
        fabSair = findViewById(R.id.fabSair);

        fabListarAtv.setAlpha(0f);
        fabPerfil.setAlpha(0f);
        fabSair.setAlpha(0f);

        fabListarAtv.setTranslationY(translationY);
        fabPerfil.setTranslationY(translationY);
        fabSair.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fabListarAtv.setOnClickListener(this);
        fabPerfil.setOnClickListener(this);
        fabSair.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(180f).setDuration(1000).start();

        fabListarAtv.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabPerfil.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabSair.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(1000).start();

        fabListarAtv.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabPerfil.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabSair.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabMain:
                if(isMenuOpen) {
                    closeMenu();
                }
                else {
                    openMenu();
                }
                break;
            case R.id.fabListarAtv:
                Intent intent = new Intent(MenuActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                Intent homeIntent = new Intent(MenuActivity.this, MainActivity.class);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }

    public void btnListarCategoriasClick(View view) {
        Intent intent = new Intent(MenuActivity.this, ListarCategoriasActivity.class );
        startActivity(intent);
    }
}
