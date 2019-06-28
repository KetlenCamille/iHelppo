package edu.up.ihelppo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.up.ihelppo.R;
import edu.up.ihelppo.model.Atividade;

public class DetalhesAtividadeActivity extends AppCompatActivity {

    private EditText edtDataAtividade, edtTituloAtividade, edtDescricaoAtividade;
    private Button btnFeito, btnExcluirAtv;
    private Atividade atividade = new Atividade();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_atividade);

        edtDataAtividade = (EditText) findViewById(R.id.edtDataAtividade);
        edtTituloAtividade = (EditText) findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = (EditText) findViewById(R.id.edtDescricaoAtividade);
        btnFeito = (Button) findViewById(R.id.btnFeito);
        btnExcluirAtv =(Button) findViewById(R.id.btnExcluirAtv);

        atividade = (Atividade) getIntent().getSerializableExtra("ATIVIDADE");

        edtDataAtividade.setText(atividade.getDataCriacao());
        edtTituloAtividade.setText(atividade.getTitulo());
        edtDescricaoAtividade.setText(atividade.getDescricaoAtividade());

    }

    public void btnFeitoClick(View view) {
    }

    public void btnExcluirClick(View view) {
    }
}
