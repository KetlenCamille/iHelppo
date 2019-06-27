package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    public void CadastrarClick(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroUsuario.class );
        startActivity(intent);
    }

    public void btnEntrarClick(View view) {
        Usuario usuario = UsuarioDAO.validarUsuarioLogin(this, edtEmail.getText().toString(), edtSenha.getText().toString());

        if(usuario.getIdUsuario() == 0)
        {
            Toast.makeText(this, "E-mail ou Senha incorreta!", Toast.LENGTH_SHORT).show();
        }
        else{
            UsuarioDAO.setarUsuario(usuario.getIdUsuario());
            Intent intent = new Intent(MainActivity.this, CalendarioActivity.class );
            startActivity(intent);
        }
    }
}
