package edu.up.ihelppo.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Usuario;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class CadastroUsuario extends AppCompatActivity {

    private EditText edtNome, edtSobrenome, edtEmail, edtSenha;
    private TextView edtDataNasc;
    private Button btnCadastrar;

    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDataNasc = (TextView) findViewById(R.id.edtDataNasc);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        edtDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CadastroUsuario.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                edtDataNasc.setText(date);
            }
        };
    }

    public void btnCadastrarUsuarioClick(View view) {
        //Criando usuário
        Usuario usuario = new Usuario();
        usuario.setNome(edtNome.getText().toString());
        usuario.setSobrenome(edtSobrenome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setDataNascimento(edtDataNasc.getText().toString());
        usuario.setSenha(edtSenha.getText().toString());
        usuario.setEhInativo("N");

        //Chamar DAO
        long id = UsuarioDAO.cadastrarUsuario(this, usuario);
        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        /*Redirecionando pra Main (tela de ínicio)*/
        Intent intent = new Intent(CadastroUsuario.this, MainActivity.class );
        startActivity(intent);
    }
}
