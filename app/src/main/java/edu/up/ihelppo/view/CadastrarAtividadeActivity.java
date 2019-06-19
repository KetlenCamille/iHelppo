package edu.up.ihelppo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.dal.DiasDaSemanaDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.DiasDaSemana;

public class CadastrarAtividadeActivity extends AppCompatActivity implements OnItemSelectedListener{

    private EditText edtTituloAtividade, edtDescricaoAtividade, edtCategoriaAtividade;
    private TextView txtDataAtividade;
    private Spinner categoria_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);

        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoCategoria);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);
        txtDataAtividade = (TextView) findViewById(R.id.txtDataAtividade);

        String dataAtividade =  getIntent().getStringExtra("DATA_ATIVIDADE");
        if(dataAtividade != null){
            txtDataAtividade.setText(dataAtividade);
        }

        // Spinner Drop down elements
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        categorias = CategoriaDAO.listarCategorias(this);

        // !!! Criar um ArrayAdapter usando o array de string e um layout de spinner default
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, categorias);

        // Especificar o layout a ser usado quando a lista de escolhas aparecer
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar o adapter para o spinner
        categoria_spinner.setAdapter(adapter);

    }



    public void SalvarAtividadeClick(View view) {

        DiasDaSemana diasDaSemana = new DiasDaSemana();
        diasDaSemana.setSegunda("S");
        diasDaSemana.setTerca("S");
        diasDaSemana.setQuarta("N");
        diasDaSemana.setQuinta("N");
        diasDaSemana.setSexta("N");
        diasDaSemana.setSabado("N");
        diasDaSemana.setDomingo("N");

        /*Atividade atividade = new Atividade();
        atividade.setTitulo(edtTituloAtividade.getText().toString());
        atividade.setDescricaoAtividade(edtDescricaoAtividade.getText().toString());
        atividade.setDataCriacao(txtDataAtividade.getText().toString());
        long id = AtividadeDAO.cadastrarAtividade(this, atividade);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();*/



        long id = DiasDaSemanaDAO.cadastrarDiasDaSemana(this, diasDaSemana);
        Toast.makeText(this, "Id:" + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
