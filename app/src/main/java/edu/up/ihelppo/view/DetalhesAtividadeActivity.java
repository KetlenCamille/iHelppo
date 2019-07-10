package edu.up.ihelppo.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.Metodos;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.dal.DiasDaSemanaDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.DiasDaSemana;

public class DetalhesAtividadeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTituloAtividade, edtDescricaoAtividade;
    private Button btnFeito, btnNaoFeito, btnExcluirAtv, btnAlterarAtv;
    private TextView txtStatus, txtDataAtividade;
    private Spinner categoria_spinner;
    private CheckBox chkDom, chkSeg, chkTer, chkQua, chkQui, chkSex, chkSab;
    private Atividade atividade = new Atividade();

    private DatePickerDialog.OnDateSetListener DateSetListener;

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    DiasDaSemana diasDaSemanaPreenc = new DiasDaSemana();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_atividade);

        InitiFabMenu();

        txtDataAtividade = (TextView) findViewById(R.id.txtDataAtividade);
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

        txtDataAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(DetalhesAtividadeActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                txtDataAtividade.setText(date);
            }
        };

        atividade = (Atividade) getIntent().getSerializableExtra("ATIVIDADE");

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

        txtDataAtividade.setText(Metodos.ConverterData(atividade.getDataCriacao()));
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
        if(id > 0){
            Toast.makeText(this, "Atividade excluída com Sucesso!", Toast.LENGTH_SHORT).show();
        }
        else if(id < 0){
            Toast.makeText(this, "Atividade não foi excluída! " + id, Toast.LENGTH_SHORT).show();
        }


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
                if(id > 0){
                    Toast.makeText(this, "Atividade alterada com Sucesso!", Toast.LENGTH_SHORT).show();
                }
                else if(id < 0){
                    Toast.makeText(this, "Atividade não foi alterada! " + id, Toast.LENGTH_SHORT).show();
                }
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

    private void InitiFabMenu() {
        fabMain = findViewById(R.id.fabMain);
        fabListarAtv = findViewById(R.id.fabListarAtv);
        fabPerfil = findViewById(R.id.fabPerfil);
        fabSair = findViewById(R.id.fabSair);

        fabListarAtv.setAlpha(0f);
        fabPerfil.setAlpha(0f);
        fabSair.setAlpha(0f);

        fabListarAtv.setTranslationY(translationY);
        fabPerfil.setTranslationY(translationY);
        fabSair.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fabListarAtv.setOnClickListener(this);
        fabPerfil.setOnClickListener(this);
        fabSair.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(180f).setDuration(1000).start();

        fabListarAtv.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabPerfil.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabSair.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(1000).start();

        fabListarAtv.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabPerfil.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabSair.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabMain:
                if(isMenuOpen) {
                    closeMenu();
                }
                else {
                    openMenu();
                }
                break;
            case R.id.fabListarAtv:
                Intent intent = new Intent(DetalhesAtividadeActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabPerfil:
                intent = new Intent(DetalhesAtividadeActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                finish();
                System.exit(0);
                break;
        }
    }
}
