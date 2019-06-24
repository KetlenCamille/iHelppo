package edu.up.ihelppo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.up.ihelppo.R;
import edu.up.ihelppo.model.Categoria;

public class DetalheCategoriaActivity extends AppCompatActivity {

    private EditText edtDescricaoCategoriaDet;
    private Button btnAlterar, btnRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_categoria);

        edtDescricaoCategoriaDet = (EditText) findViewById(R.id.edtDescricaoCategoriaDet);
        btnRemover = (Button) findViewById(R.id.btnRemoverCategoria);
        btnAlterar = (Button) findViewById(R.id.btnAlterarCategoria);

        Categoria categoria =(Categoria) getIntent().getSerializableExtra("CATEGORIA");

        edtDescricaoCategoriaDet.setText(categoria.getDescricao());
    }

    public void btnAlterarCategoriaClick(View view) {
    }

    public void btnRemoverCategoriaClick(View view) {
    }
}
