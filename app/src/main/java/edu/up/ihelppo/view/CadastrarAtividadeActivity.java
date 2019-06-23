package edu.up.ihelppo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;

public class CadastrarAtividadeActivity extends AppCompatActivity implements OnItemSelectedListener{

    private EditText edtTituloAtividade, edtDescricaoAtividade, edtCategoriaAtividade;
    private TextView txtDataAtividade;
    private Spinner categoria_spinner;
    private CheckBox chkDom, chkSeg, chkTer, chkQua, chkQui, chkSex, chkSab;

    public String segunda = "";
    public String terca = "";
    public String quarta = "";
    public String quinta = "";
    public String sexta = "";
    public String sabado = "";
    public String domingo = "";
    public String diasDaSemana = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);

        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoCategoria);
        txtDataAtividade = (TextView) findViewById(R.id.txtDataAtividade);
        chkDom = (CheckBox) findViewById(R.id.chkDom);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);

        String data = (String) getIntent().getSerializableExtra("DATA_ATIVIDADE");
        txtDataAtividade.setText(data);


        ArrayList<String> categorias = CategoriaDAO.listarCategoriasPorNome(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoria_spinner.setAdapter(adapter);

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


    public void SalvarAtividadeClick(View view) {
        Atividade atividade = new Atividade();
        atividade.setDataCriacao(txtDataAtividade.getText().toString());
        atividade.setTitulo(edtTituloAtividade.getText().toString());
        atividade.setDescricaoAtividade(edtDescricaoAtividade.getText().toString());
        atividade.setIdCategoria(categoria_spinner.getId());

        if(chkDom.isChecked())
        {
            Toast.makeText(this, "Domingo CHECK!", Toast.LENGTH_SHORT).show();
        }

        /*long id = AtividadeDAO.cadastrarAtividade(this, atividade);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();*/
    }


    public void onCheckboxClicked(View view) {
        if(chkDom.isChecked())
        {
            domingo = "S";
        }
        else{
            domingo = "N";
        }
        if(chkSeg.isChecked())
        {
             segunda = "S";
        }
        else{
            segunda = "N";
        }
        if(chkTer.isChecked())
        {
            terca = "S";
        }
        else{
            terca = "N";
        }
        if(chkQua.isChecked())
        {
            quarta = "S";
        }
        else{
            quarta = "N";
        }
        if(chkQui.isChecked())
        {
            quinta = "S";
        }
        else{
            quinta = "N";
        }
        if(chkSex.isChecked())
        {
            sexta = "S";
        }
        else{
            sexta = "N";
        }
        if(chkSab.isChecked())
        {
            sabado = "S";
        }
        else{
            sabado = "N";
        }

        diasDaSemanaCheck(segunda,terca,quarta,quinta,sexta,sabado,domingo);
    }

    public void diasDaSemanaCheck(String segunda, String terca, String quarta, String quinta, String sexta, String sabado, String domingo){

    }
}
