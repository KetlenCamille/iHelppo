package edu.up.ihelppo.view;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Usuario;

public class MeusDadosActivity extends AppCompatActivity {

    private EditText edtNome, edtSobrenome, edtEmail, edtDataNasc, edtSenha;
    private Button btnAlterarUsuario, btnInativarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDataNasc = (EditText) findViewById(R.id.edtDataNasc);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnAlterarUsuario = (Button) findViewById(R.id.btnAlterarUsuario);
        btnInativarUsuario = (Button) findViewById(R.id.btnInativarUsuario);

        int idUsu = UsuarioDAO.retornarUsuario();
        Usuario usuario = UsuarioDAO.buscarUsuarioPorID(this, idUsu);
        if(usuario.getIdUsuario() == 0){
            Toast.makeText(this, "Usuário Inativo!", Toast.LENGTH_SHORT).show();
        }

        edtNome.setText(usuario.getNome());
        edtSobrenome.setText(usuario.getSobrenome());
        edtEmail.setText(usuario.getEmail());
        edtDataNasc.setText(usuario.getDataNascimento());
        edtSenha.setText(usuario.getSenha());

    }

    public void btnAlterarUsuarioClick(View view) {

        int idUsu = UsuarioDAO.retornarUsuario();
        Usuario usuario = UsuarioDAO.buscarUsuarioPorID(this, idUsu);
        usuario.setNome(edtNome.getText().toString());
        usuario.setSobrenome(edtSobrenome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setDataNascimento(edtDataNasc.getText().toString());
        usuario.setSenha(edtSenha.getText().toString());

        usuario.setEhInativo("N");

        UsuarioDAO.alterarUsuario(this,usuario);

        Toast.makeText(this, "Dados Alterados com Sucesso!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MeusDadosActivity.this, MenuActivity.class );
        startActivity(intent);

    }

    public void btnInativarUsuarioClick(View view) {

        int idUsu = UsuarioDAO.retornarUsuario();
        Usuario usuario = UsuarioDAO.buscarUsuarioPorID(this, idUsu);
        usuario.setEhInativo("S");
        UsuarioDAO.alterarUsuario(this, usuario);

        Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class );
        startActivity(intent);

    }

}
