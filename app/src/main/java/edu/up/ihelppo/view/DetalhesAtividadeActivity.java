package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.Metodos;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.dal.DiasDaSemanaDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.DiasDaSemana;

public class DetalhesAtividadeActivity extends AppCompatActivity {

    private EditText edtDataAtividade, edtTituloAtividade, edtDescricaoAtividade;
    private Button btnFeito, btnNaoFeito, btnExcluirAtv, btnAlterarAtv;
    private TextView txtStatus;
    private Spinner categoria_spinner;
    private CheckBox chkDom, chkSeg, chkTer, chkQua, chkQui, chkSex, chkSab;
    private Atividade atividade = new Atividade();

    DiasDaSemana diasDaSemanaPreenc = new DiasDaSemana();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_atividade);

        edtDataAtividade = (EditText) findViewById(R.id.edtDataAtividade);
        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoAtividade);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        chkDom = (CheckBox) findViewById(R.id.chkDom);
        chkSeg = (CheckBox) findViewById(R.id.chkSeg);
        chkTer = (CheckBox) findViewById(R.id.chkTer);
        chkQua = (CheckBox) findViewById(R.id.chkQua);
        chkQui = (CheckBox) findViewById(R.id.chkQui);
        chkSex = (CheckBox) findViewById(R.id.chkSex);
        chkSab = (CheckBox) findViewById(R.id.chkSab);
        btnFeito = (Button) findViewById(R.id.btnFeito);
        btnNaoFeito = (Button) findViewById(R.id.btnNaoFeito);
        btnExcluirAtv = (Button) findViewById(R.id.btnExcluirAtv);
        btnAlterarAtv = (Button) findViewById(R.id.btnAlterarAtv);
        categoria_spinner = (Spinner) findViewById(R.id.categoria_spinner);

        atividade = (Atividade) getIntent().getSerializableExtra("ATIVIDADE");

        Toast.makeText(this, "" + atividade.getIdDiasSemana(), Toast.LENGTH_SHORT).show();

        if (atividade.getFoiRealizada().equals("N")) {
            txtStatus.setText("Atividade Não Realizada!");
            btnAlterarAtv.setEnabled(false);
            btnFeito.setEnabled(true);
            btnNaoFeito.setEnabled(false);
        } else if (atividade.getFoiRealizada().equals("S")) {
            txtStatus.setText("Atividade Realizada!");
            btnAlterarAtv.setEnabled(false);
            btnFeito.setEnabled(false);
            btnNaoFeito.setEnabled(false);
            chkDom.setEnabled(false);
            chkSeg.setEnabled(false);
            chkTer.setEnabled(false);
            chkQua.setEnabled(false);
            chkQui.setEnabled(false);
            chkSex.setEnabled(false);
            chkSab.setEnabled(false);
        } else if (atividade.getFoiRealizada().equals("")) {
            txtStatus.setText("Atividade Pendente!");
        }

        edtDataAtividade.setText(Metodos.ConverterData(atividade.getDataCriacao()));
        edtTituloAtividade.setText(atividade.getTitulo());
        edtDescricaoAtividade.setText(atividade.getDescricaoAtividade());

        //categoria_spinner.setOnItemSelectedListener(this);
        // Populando o Spinner de Categorias:

        ArrayList<String> categorias = CategoriaDAO.listarCategoriasPorNome(this, UsuarioDAO.retornarUsuario());
        ArrayAdapter adapterCategorias = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoria_spinner.setAdapter(adapterCategorias);

        DiasDaSemana diasDaSemana = DiasDaSemanaDAO.buscarDiaDaSemanaPorId(this, atividade.getIdDiasSemana());

        if (diasDaSemana.getDomingo().equals("S")) {
            chkDom.setChecked(true);
            diasDaSemanaPreenc.setDomingo("S");
        } else {
            chkDom.setChecked(false);
            diasDaSemanaPreenc.setDomingo("N");
        }
        if (diasDaSemana.getSegunda().equals("S")) {
            chkSeg.setChecked(true);
            diasDaSemanaPreenc.setSegunda("S");
        } else {
            chkSeg.setChecked(false);
            diasDaSemanaPreenc.setSegunda("N");
        }
        if (diasDaSemana.getTerca().equals("S")) {
            chkTer.setChecked(true);
            diasDaSemanaPreenc.setTerca("S");
        } else {
            chkTer.setChecked(false);
            diasDaSemanaPreenc.setTerca("N");
        }
        if (diasDaSemana.getQuarta().equals("S")) {
            chkQua.setChecked(true);
            diasDaSemanaPreenc.setQuarta("S");
        }else{
            chkQua.setChecked(false);
            diasDaSemanaPreenc.setQuarta("N");
        }
        if (diasDaSemana.getQuinta().equals("S")) {
            chkQui.setChecked(true);
            diasDaSemanaPreenc.setQuinta("S");
        }else{
            chkQui.setChecked(false);
            diasDaSemanaPreenc.setQuinta("N");
        }
        if (diasDaSemana.getSexta().equals("S")) {
            chkSex.setChecked(true);
            diasDaSemanaPreenc.setSexta("S");
        }else{
            chkSex.setChecked(false);
            diasDaSemanaPreenc.setSexta("N");
        }if (diasDaSemana.getSabado().equals("S")) {
            chkSab.setChecked(true);
            diasDaSemanaPreenc.setSabado("S");
        }
        else{
            chkSab.setChecked(false);
            diasDaSemanaPreenc.setSabado("N");
        }

    }

    public void btnFeitoClick(View view) {
        Atividade atividadePesq = AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setFoiRealizada("S");

        AtividadeDAO.alterarAtividade(this, atividadePesq);

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class);
        startActivity(intent);
    }

    public void btnExcluirClick(View view) {
        long id = AtividadeDAO.excluirAtividade(this, atividade);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class);
        startActivity(intent);
    }

    public void btnNaoFeitoClick(View view) {
        Atividade atividadePesq = AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setFoiRealizada("N");

        AtividadeDAO.alterarAtividade(this, atividadePesq);

        Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class);
        startActivity(intent);
    }

    public void btnAlterarClick(View view) {
        Atividade atividadePesq = AtividadeDAO.buscarAtividadePorId(this, atividade);
        atividadePesq.setDataCriacao(atividade.getDataCriacao().toString());
        if (edtTituloAtividade.getText().toString().equals("")) {
            Toast.makeText(this, "Informe um título!", Toast.LENGTH_SHORT).show();
        } else {

            atividadePesq.setTitulo(edtTituloAtividade.getText().toString());
            atividadePesq.setDescricaoAtividade(edtDescricaoAtividade.getText().toString());

            String categoria = String.valueOf(categoria_spinner.getSelectedItem());

            Categoria categoriaPesq = CategoriaDAO.buscarCategoriaPorNome(this, categoria, UsuarioDAO.retornarUsuario());
            if (categoriaPesq.getDescricao() == null) {
                Toast.makeText(this, "Selecione uma categoria válida!", Toast.LENGTH_SHORT).show();
            } else {
                atividade.setIdCategoria(categoriaPesq.getIdCategoria());

                DiasDaSemana dia = DiasDaSemanaDAO.buscarDiasDaSemanaExistente(this, diasDaSemanaPreenc, UsuarioDAO.retornarUsuario());

                if (dia.getIdDiasDaSemana() == 0) {
                    diasDaSemanaPreenc.setIdUsuario(UsuarioDAO.retornarUsuario());
                    long id = DiasDaSemanaDAO.cadastrarDiasDaSemana(this, diasDaSemanaPreenc);
                    if (id < 1) {
                        Toast.makeText(this, "Erro ao cadastrar Dia da Semana: " + id, Toast.LENGTH_SHORT).show();
                    }
                }
                DiasDaSemana diasPesq = DiasDaSemanaDAO.buscarDiasDaSemanaExistente(this, diasDaSemanaPreenc, UsuarioDAO.retornarUsuario());

                atividadePesq.setIdDiasSemana(diasPesq.getIdDiasDaSemana());

                long id = AtividadeDAO.alterarAtividade(this, atividadePesq);
                Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
            }
        }
    }

    public void onCheckboxClicked(View view) {
        if (chkDom.isChecked()) {
            diasDaSemanaPreenc.setDomingo("S");
        } else {
            diasDaSemanaPreenc.setDomingo("N");
        }
        if (chkSeg.isChecked()) {
            diasDaSemanaPreenc.setSegunda("S");
        } else {
            diasDaSemanaPreenc.setSegunda("N");
        }
        if (chkTer.isChecked()) {
            diasDaSemanaPreenc.setTerca("S");
        } else {
            diasDaSemanaPreenc.setTerca("N");
        }
        if (chkQua.isChecked()) {
            diasDaSemanaPreenc.setQuarta("S");
        } else {
            diasDaSemanaPreenc.setQuarta("N");
        }
        if (chkQui.isChecked()) {
            diasDaSemanaPreenc.setQuinta("S");
        } else {
            diasDaSemanaPreenc.setQuinta("N");
        }
        if (chkSex.isChecked()) {
            diasDaSemanaPreenc.setSexta("S");
        } else {
            diasDaSemanaPreenc.setSexta("N");
        }
        if (chkSab.isChecked()) {
            diasDaSemanaPreenc.setSabado("S");
        } else {
            diasDaSemanaPreenc.setSabado("N");
        }
    }
}
