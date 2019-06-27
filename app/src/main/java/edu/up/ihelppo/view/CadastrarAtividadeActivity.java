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
import edu.up.ihelppo.dal.DiasDaSemanaDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.DiasDaSemana;

public class CadastrarAtividadeActivity extends AppCompatActivity implements OnItemSelectedListener{

    private EditText edtTituloAtividade, edtDescricaoAtividade, edtCategoriaAtividade;
    private TextView txtDataAtividade;
    private Spinner categoria_spinner, horas_spinner, minutos_spinner;
    private CheckBox chkDom, chkSeg, chkTer, chkQua, chkQui, chkSex, chkSab, chkAlarme;

    public String[] diasPreenchidos = new String[7];
    public String segunda = "";
    public String terca = "";
    public String quarta = "";
    public String quinta = "";
    public String sexta = "";
    public String sabado = "";
    public String domingo = "";
    public String diasDaSemana = "";
    public String alarme = "N";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);

        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoAtividade);
        txtDataAtividade = (TextView) findViewById(R.id.txtDataAtividade);
        chkDom = (CheckBox) findViewById(R.id.chkDom);
        chkSeg = (CheckBox) findViewById(R.id.chkSeg);
        chkTer = (CheckBox) findViewById(R.id.chkTer);
        chkQua = (CheckBox) findViewById(R.id.chkQua);
        chkQui = (CheckBox) findViewById(R.id.chkQui);
        chkSex = (CheckBox) findViewById(R.id.chkSex);
        chkSab = (CheckBox) findViewById(R.id.chkSab);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);
        chkAlarme = (CheckBox) findViewById(R.id.chkAlarme);
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

        horas_spinner.setEnabled(false);
        minutos_spinner.setEnabled(false);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void SalvarAtividadeClick(View view) {
        Atividade atividade = new Atividade();
        atividade.setDataCriacao(txtDataAtividade.getText().toString());
        atividade.setTitulo(edtTituloAtividade.getText().toString());
        atividade.setDescricaoAtividade(edtDescricaoAtividade.getText().toString());

        //Pegar Usuario da Sessao
        atividade.setIdUsuario(UsuarioDAO.retornarUsuario());


        String categoria = String.valueOf(categoria_spinner.getSelectedItem());

        Categoria categoriaPesq = CategoriaDAO.buscarCategoriaPorNome(this, categoria);
        if(categoriaPesq.getIdCategoria() == 0){
            Toast.makeText(this, "Selecione uma categoria válida!", Toast.LENGTH_SHORT).show();
        }
        else{
            atividade.setIdCategoria(categoriaPesq.getIdCategoria());
        }

        DiasDaSemana diasDaSemana = new DiasDaSemana();
        diasDaSemana.setDomingo(diasPreenchidos[0]);
        diasDaSemana.setSegunda(diasPreenchidos[1]);
        diasDaSemana.setTerca(diasPreenchidos[2]);
        diasDaSemana.setQuarta(diasPreenchidos[3]);
        diasDaSemana.setQuinta(diasPreenchidos[4]);
        diasDaSemana.setSexta(diasPreenchidos[5]);
        diasDaSemana.setSabado(diasPreenchidos[6]);

        DiasDaSemana dia = DiasDaSemanaDAO.buscarDiasDaSemanaExistente(this, diasDaSemana);

        if( dia.getIdDiasDaSemana() == 0){
            long id = DiasDaSemanaDAO.cadastrarDiasDaSemana(this, diasDaSemana);
            Toast.makeText(this, "Id Cadastro: " + id, Toast.LENGTH_SHORT).show();
        }
        DiasDaSemana diasPesq = DiasDaSemanaDAO.buscarDiasDaSemanaExistente(this, diasDaSemana);

        atividade.setIdDiasSemana(diasPesq.getIdDiasDaSemana());

        long id = AtividadeDAO.cadastrarAtividade(this, atividade);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();


        //Atribuir hora e minutos do spinner ao alarme!
        if(alarme == "S"){
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

            //Mensagem do alarme
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, edtTituloAtividade.getText().toString());
            //Definir Hora
            intent.putExtra(AlarmClock.EXTRA_HOUR, String.valueOf(horas_spinner.getSelectedItem()));
            //Definir Minuto
            intent.putExtra(AlarmClock.EXTRA_MINUTES, String.valueOf(minutos_spinner.getSelectedItem()));

            startActivity(intent);
        }
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

        for(int i=0; i< diasPreenchidos.length; i++)
        {
            if(i == 0){
                diasPreenchidos[0] = domingo;
            }else if(i == 1){
                diasPreenchidos[1] = segunda;
            }else if(i == 2){
                diasPreenchidos[2] = terca;
            }else if(i == 3){
                diasPreenchidos[3] = quarta;
            }else if(i == 4){
                diasPreenchidos[4] = quinta;
            }else if(i == 5){
                diasPreenchidos[5] = sexta;
            }
            else if(i == 6){
                diasPreenchidos[6] = sabado;
            }
        }
    }

    public void checkAlarmeClick(View view) {
        alarme = "S";

        //Habilitar Spinner de hora e minuto
        if(chkAlarme.isChecked()){
            horas_spinner.setEnabled(true);
            minutos_spinner.setEnabled(true);
        }
        else{
            horas_spinner.setEnabled(false);
            minutos_spinner.setEnabled(false);
        }
    }
}
