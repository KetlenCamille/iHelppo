package edu.up.ihelppo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtDescricao;
    private Button btnCadastrar;

    //Botões Menu
    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        edtDescricao = (EditText) findViewById(R.id.edtDescricaoCategoria);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCategoria);

        InitiFabMenu();

    }

    public void btnCadastrarCategoriaClick(View view) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(edtDescricao.getText().toString());
        categoria.setEhInativo("N");

        long id = CategoriaDAO.cadastrarCategoria(this, categoria);
        if(id > 0){
            Toast.makeText(this, "Categoria Cadastrada com Sucesso!", Toast.LENGTH_SHORT).show();
        }
        else if(id < 0){
            Toast.makeText(this, "Erro ao Cadastrar Categoria!" + id, Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(CadastroCategoriaActivity.this, ListarAtividadesActivity.class);
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
                Intent intent = new Intent(CadastroCategoriaActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabPerfil:
                intent = new Intent(CadastroCategoriaActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                Intent homeIntent = new Intent(CadastroCategoriaActivity.this, MainActivity.class);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }
}
