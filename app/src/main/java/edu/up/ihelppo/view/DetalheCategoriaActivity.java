package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Categoria;

public class DetalheCategoriaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtDescricaoCategoriaDet;
    private Button btnAlterar, btnRemover;
    private Categoria categoria = new Categoria();

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_categoria);

        InitiFabMenu();

        edtDescricaoCategoriaDet = (EditText) findViewById(R.id.edtDescricaoCategoriaDet);
        btnRemover = (Button) findViewById(R.id.btnRemoverCategoria);
        btnAlterar = (Button) findViewById(R.id.btnAlterarCategoria);

        categoria = (Categoria) getIntent().getSerializableExtra("CATEGORIA");

        edtDescricaoCategoriaDet.setText(categoria.getDescricao());
    }

    public void btnAlterarCategoriaClick(View view) {
        Categoria categoriaPesq =  CategoriaDAO.buscarCategoriaPorId(this, categoria);
        categoriaPesq.setDescricao(edtDescricaoCategoriaDet.getText().toString());
        categoriaPesq.setEhInativo("N");

        CategoriaDAO.alterarCategoria(this,categoriaPesq);

        Intent intent = new Intent(DetalheCategoriaActivity.this, ListarCategoriasActivity.class );
        startActivity(intent);
    }

    public void btnRemoverCategoriaClick(View view) {
       Categoria categoriaPesq =  CategoriaDAO.buscarCategoriaPorId(this, categoria);
       categoriaPesq.setEhInativo("S");

       CategoriaDAO.alterarCategoria(this,categoriaPesq);

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
                Intent intent = new Intent(DetalheCategoriaActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabPerfil:
                intent = new Intent(DetalheCategoriaActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                Intent homeIntent = new Intent(DetalheCategoriaActivity.this, MainActivity.class);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }
}
