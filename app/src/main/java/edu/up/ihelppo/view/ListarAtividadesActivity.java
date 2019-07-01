package edu.up.ihelppo.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.opengl.EGLExt;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.ihelppo.R;
import edu.up.ihelppo.Utils.ArrayAdapterAtividade;
import edu.up.ihelppo.dal.AtividadeDAO;
import edu.up.ihelppo.dal.CategoriaDAO;
import edu.up.ihelppo.dal.UsuarioDAO;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;

public class ListarAtividadesActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lstAvancada;
    private Button btnAdicionarAtividade;

    //Botões menu
    FloatingActionButton fabMain, fabListarAtv, fabPerfil, fabSair;
    Float translationY = 10f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atividades);

        //Menu
        InitiFabMenu();

        lstAvancada = (ListView) findViewById(R.id.lstAvancada);
        btnAdicionarAtividade = (Button) findViewById(R.id.btnAdicionarAtividade);

        final ArrayList<Atividade> atividadesArray = AtividadeDAO.listarAtividadesDoDia(this, UsuarioDAO.retornarUsuario());
        String[] atividades = new String[atividadesArray.size()];

        for(int i=0; i < atividadesArray.size(); i++){
            atividades[i] = atividadesArray.get(i).getTitulo();
        }

        ArrayAdapterAtividade arrayAdapterAtividade = new ArrayAdapterAtividade(this, AtividadeDAO.listarAtividades(this));
        //ArrayAdapterAtividade arrayAdapterAtividade = new ArrayAdapterAtividade(this, AtividadeDAO.listarAtividadesDoDia(this, UsuarioDAO.retornarUsuario()));

        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Criar um canal de comunicação
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String nome = "CANAL_COMUNICADO";
            String descricao = "DESCRICAO_CANAL";
            int prioridade = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel("idCanal01", nome, prioridade);
            notificationChannel.setDescription(descricao);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Criar a notificação
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "idCanal01enade");
        builder.setContentText("Voce possui " + atividadesArray.size() + " atividades hoje!");
        builder.setContentTitle("iHelppo INFORMA:");
        builder.setSmallIcon(R.drawable.logo);
        //builder.setContentIntent(pendingIntent);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(5000);
                    notificationManager.notify(1, builder.build());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        notificationManager.notify(1, builder.build());

        lstAvancada.setAdapter(arrayAdapterAtividade);

        lstAvancada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(ListarAtividadesActivity.this, DetalhesAtividadeActivity.class);
                intent.putExtra("ATIVIDADE", atividadesArray.get(position));
                startActivity(intent);
            }
        });

    }

    public void btnAdicionarAtividadeClick(View view) {
        Intent intent = new Intent(ListarAtividadesActivity.this, CalendarioActivity.class );
        startActivity(intent);
    }

    //Menu
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
            case R.id.fabPerfil:
                Intent intent = new Intent(ListarAtividadesActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.fabSair:
                finish();
                System.exit(0);
                break;
        }
    }
}
