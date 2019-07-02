package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.ArrayAdapterAtividade;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;

public class ListarAtividadesFuturasActivity extends AppCompatActivity {

    private ListView lstAvancada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividades_futuras);

        lstAvancada = (ListView) findViewById(R.id.lstAvancada);

        final ArrayList<Atividade> atividadesArray = AtividadeDAO.listarAtividadesFuturas(this, UsuarioDAO.retornarUsuario());
        String[] atividades = new String[atividadesArray.size()];

        for (int i = 0; i < atividadesArray.size(); i++) {
            atividades[i] = atividadesArray.get(i).getTitulo();
        }

        ArrayAdapterAtividade arrayAdapterAtividade = new ArrayAdapterAtividade(this, AtividadeDAO.listarAtividadesFuturas(this, UsuarioDAO.retornarUsuario()));

        lstAvancada.setAdapter(arrayAdapterAtividade);

        lstAvancada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(ListarAtividadesFuturasActivity.this, DetalhesAtividadeActivity.class);
                intent.putExtra("ATIVIDADE", atividadesArray.get(position));
                startActivity(intent);
            }
        });
    }
}
