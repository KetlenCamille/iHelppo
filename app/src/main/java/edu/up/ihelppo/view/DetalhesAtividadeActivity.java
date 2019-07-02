package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.Metodos;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;

public class DetalhesAtividadeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtDataAtividade, edtTituloAtividade, edtDescricaoAtividade;
    private Button btnFeito, btnExcluirAtv, btnSalvarAtv;
    private Spinner categoria_spinner;
    private Atividade atividade = new Atividade();

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_atividade);

        InitiFabMenu();

        edtDataAtividade = (EditText) findViewById(R.id.edtDataAtividade);
        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoAtividade);
        btnFeito = (Button) findViewById(R.id.btnFeito);
        btnExcluirAtv =(Button) findViewById(R.id.btnExcluirAtv);
        btnSalvarAtv = (Button) findViewById(R.id.btnSalvarAtv);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);

        atividade = (Atividade) getIntent().getSerializableExtra("ATIVIDADE");

        edtDataAtividade.setText(Metodos.ConverterData(atividade.getDataCriacao()));
        edtTituloAtividade.setText(atividade.getTitulo());
        edtDescricaoAtividade.setText(atividade.getDescricaoAtividade());

        //categoria_spinner.setOnItemSelectedListener(this);
        // Populando o Spinner de Categorias:

        ArrayList<String> categorias = CategoriaDAO.listarCategoriasPorNome(this);
        ArrayAdapter adapterCategorias = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoria_spinner.setAdapter(adapterCategorias);

    }

    public void btnFeitoClick(View view) {
        Atividade atividadePesq =  AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setFoiRealizada("S");

        AtividadeDAO.alterarAtividade(this,atividadePesq);

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class );
        startActivity(intent);
    }

    public void btnExcluirClick(View view) {
        long id = AtividadeDAO.excluirAtividade(this, atividade);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class );
        startActivity(intent);
    }

    public void btnNaoFeitoClick(View view) {
        Atividade atividadePesq =  AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setFoiRealizada("N");

        AtividadeDAO.alterarAtividade(this,atividadePesq);

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class );
        startActivity(intent);
    }

    public void btnAlterarClick(View view) {
        Atividade atividadePesq =  AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setDataCriacao(atividade.getDataCriacao().toString());
        atividadePesq.setTitulo(edtTituloAtividade.getText().toString());
        atividadePesq.setDescricaoAtividade(edtDescricaoAtividade.getText().toString());

        String categoria = String.valueOf(categoria_spinner.getSelectedItem());

        Categoria categoriaPesq = CategoriaDAO.buscarCategoriaPorNome(this, categoria);
        if(categoria.equals("")){
            Toast.makeText(this, "Selecione uma categoria válida!", Toast.LENGTH_SHORT).show();
        }
        else{
            atividade.setIdCategoria(categoriaPesq.getIdCategoria());
        }

        AtividadeDAO.alterarAtividade(this, atividadePesq);

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class );
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
                //Intent intent = new Intent(this, .class);
                //startActivity(intent);
                break;
            case R.id.fabPerfil:
                //intent = new Intent(this, .class);
                //startActivity(intent);
                break;
            case R.id.fabSair:
                Intent homeIntent = new Intent(DetalhesAtividadeActivity.this, MainActivity.class);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }
}
