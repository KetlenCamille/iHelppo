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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.up.ihelppo.R;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Usuario;

public class MeusDadosActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNome, edtSobrenome, edtEmail, edtSenha;
    private TextView edtDataNasc;
    private Button btnAlterarUsuario, btnInativarUsuario;

    private DatePickerDialog.OnDateSetListener DateSetListener;

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        InitiFabMenu();

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDataNasc = (TextView) findViewById(R.id.edtDataNasc);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnAlterarUsuario = (Button) findViewById(R.id.btnAlterarUsuario);
        btnInativarUsuario = (Button) findViewById(R.id.btnInativarUsuario);

        edtDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MeusDadosActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DateSetListener, year, month, day);

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
                Intent intent = new Intent(MeusDadosActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabPerfil:
                intent = new Intent(MeusDadosActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                Intent homeIntent = new Intent(MeusDadosActivity.this, MainActivity.class);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }

}
