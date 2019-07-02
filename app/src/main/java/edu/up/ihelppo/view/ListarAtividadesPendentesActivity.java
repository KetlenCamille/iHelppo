package edu.up.ihelppo.view;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.ArrayAdapterAtividade;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;

public class ListarAtividadesPendentesActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lstAvancada;

    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividades_pendentes);

        InitiFabMenu();

        lstAvancada = (ListView) findViewById(R.id.lstAvancada);

        final ArrayList<Atividade> atividadesArray = AtividadeDAO.listarAtividadesPendentes(this, UsuarioDAO.retornarUsuario());
        String[] atividades = new String[atividadesArray.size()];

        for(int i=0; i < atividadesArray.size(); i++){
            atividades[i] = atividadesArray.get(i).getTitulo();
        }

        ArrayAdapterAtividade arrayAdapterAtividade = new ArrayAdapterAtividade(this, AtividadeDAO.listarAtividadesPendentes(this, UsuarioDAO.retornarUsuario()));

        lstAvancada.setAdapter(arrayAdapterAtividade);

        lstAvancada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(ListarAtividadesPendentesActivity.this, DetalhesAtividadeActivity.class);
                intent.putExtra("ATIVIDADE", atividadesArray.get(position));
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(ListarAtividadesPendentesActivity.this, ListarAtividadesActivity.class);
                startActivity(intent);
                break;
            case R.id.fabPerfil:
                intent = new Intent(ListarAtividadesPendentesActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                finish();
                System.exit(0);
                break;
        }
    }

}