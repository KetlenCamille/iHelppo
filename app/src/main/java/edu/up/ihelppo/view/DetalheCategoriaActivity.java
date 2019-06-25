package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Categoria;

public class DetalheCategoriaActivity extends AppCompatActivity {

    private EditText edtDescricaoCategoriaDet;
    private Button btnAlterar, btnRemover;
    private Categoria categoria = new Categoria();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_categoria);

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
}
