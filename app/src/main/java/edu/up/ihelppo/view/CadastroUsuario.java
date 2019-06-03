package edu.up.ihelppo.view;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import edu.up.ihelppo.R;
import edu.up.ihelppo.model.Usuario;

public class CadastroUsuario extends AppCompatActivity {

    private EditText edtNome, edtSobrenome, edtEmail, edtDataNasc, edtSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDataNasc = (EditText) findViewById(R.id.edtDataNasc);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
    }

    public void btnCadastrarUsuarioClick(View view) {
        Usuario usuario = new Usuario();
        usuario.setNome(edtNome.getText().toString());
        usuario.setSobrenome(edtSobrenome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        //usuario.setDataNascimento(edtDataNasc.getText().toString());
        usuario.setSenha(edtSenha.getText().toString());

        //Chamar DAO

        //Criar Intent pra Main
    }
}
