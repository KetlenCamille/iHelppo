package edu.up.ihelppo.view;

import android.content.Intent;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;

public class ListarAtividadesActivity extends AppCompatActivity {

    private ListView lstAvancada;
    private Button btnAdicionarAtividade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividades);

        lstAvancada = (ListView) findViewById(R.id.lstAvancada);
        //btnAdicionarAtividade = (Button) findViewById(R.id.btnAdicionarAtividade);

        final ArrayList<Atividade> atividadesArray = AtividadeDAO.listarAtividadesDoDia(this, UsuarioDAO.retornarUsuario());
        String[] atividades = new String[atividadesArray.size()];

        for(int i=0; i < atividadesArray.size(); i++){
            atividades[i] = atividadesArray.get(i).getTitulo();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, atividades);
        lstAvancada.setAdapter(adapter);

        lstAvancada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(ListarAtividadesActivity.this, DetalhesAtividadeActivity.class);
                intent.putExtra("CATEGORIA", atividadesArray.get(position));
                startActivity(intent);
            }
        });

    }

    public void btnAdicionarAtividadeClick(View view) {
        Intent intent = new Intent(ListarAtividadesActivity.this, CalendarioActivity.class );
        startActivity(intent);
    }
}
