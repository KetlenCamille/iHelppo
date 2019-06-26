package edu.up.ihelppo.view;

import android.content.Intent;
import android.provider.AlarmClock;
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
    private Spinner categoria_spinner, horas_spinner, minutos_spinner;
    private CheckBox chkDom, chkSeg, chkTer, chkQua, chkQui, chkSex, chkSab;

    public String segunda = "";
    public String terca = "";
    public String quarta = "";
    public String quinta = "";
    public String sexta = "";
    public String sabado = "";
    public String domingo = "";
    public String diasDaSemana = "";
    public String alarme = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);

        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoCategoria);
        txtDataAtividade = (TextView) findViewById(R.id.txtDataAtividade);
        chkDom = (CheckBox) findViewById(R.id.chkDom);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);
        horas_spinner = (Spinner) findViewById(R.id.horas_spinner);
        minutos_spinner = (Spinner) findViewById(R.id.minutos_spinner);
        categoria_spinner.setOnItemSelectedListener(this);
        horas_spinner.setOnItemSelectedListener(this);
        minutos_spinner.setOnItemSelectedListener(this);

        String data = (String) getIntent().getSerializableExtra("DATA_ATIVIDADE");
        txtDataAtividade.setText(data);


        // Populando o Spinner de Categorias:

        ArrayList<String> categorias = CategoriaDAO.listarCategoriasPorNome(this);
        ArrayAdapter adapterCategorias = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoria_spinner.setAdapter(adapterCategorias);

        // Populando o Spinner de Horas:

        ArrayAdapter<CharSequence> adapterHoras = ArrayAdapter.createFromResource(this,
                R.array.horas_array, android.R.layout.simple_spinner_item);
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        horas_spinner.setAdapter(adapterHoras);

        // Populando o Spinner de Minutos:

        ArrayAdapter<CharSequence> adapterMinutos = ArrayAdapter.createFromResource(this,
                R.array.minutos_array, android.R.layout.simple_spinner_item);
        adapterMinutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        minutos_spinner.setAdapter(adapterMinutos);

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
        //atribuir hora e minutos do spinner ao alarme!

        if(alarme == "S"){
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

            //Mensagem do alarme
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, edtTituloAtividade.getText().toString());
            //Definir Hora
            intent.putExtra(AlarmClock.EXTRA_HOUR, 9);
            //Definir Minuto
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 50);

            startActivity(intent);
        }

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

    public void checkAlarmeClick(View view) {
        alarme = "S";

        //Habilitar Spiner de hora e minuto
    }
}
