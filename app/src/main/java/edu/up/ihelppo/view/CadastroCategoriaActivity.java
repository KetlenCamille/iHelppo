package edu.up.ihelppo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {

    private EditText edtDescricao;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        edtDescricao = (EditText) findViewById(R.id.edtDescricaoCategoria);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCategoria);
    }

    public void btnCadastrarCategoriaClick(View view) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(edtDescricao.getText().toString());

        long id = CategoriaDAO.cadastrarCategoria(this, categoria);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        /*Intent intent = new Intent(CadastroCategoriaActivity.this, MainActivity.class);
        startActivity(intent);*/

    }
}
