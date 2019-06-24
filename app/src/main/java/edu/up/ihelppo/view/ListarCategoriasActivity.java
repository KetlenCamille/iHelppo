package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.model.Categoria;

public class ListarCategoriasActivity extends AppCompatActivity {

    private ListView lstCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categorias);

        lstCategorias = (ListView) findViewById(R.id.lstCategorias);

        final ArrayList<Categoria> categoriasArray = CategoriaDAO.listarCategoriasAtivas(this);
        String[] categorias = new String[categoriasArray.size()];

        for(int i=0; i < categoriasArray.size(); i++){
            categorias[i] = categoriasArray.get(i).getDescricao();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categorias);
        lstCategorias.setAdapter(adapter);

        lstCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(ListarCategoriasActivity.this, DetalheCategoriaActivity.class);
                intent.putExtra("CATEGORIA", categoriasArray.get(position));
                startActivity(intent);
            }
        });

    }
}
