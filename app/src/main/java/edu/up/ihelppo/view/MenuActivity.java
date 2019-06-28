package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.up.ihelppo.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnHistoricoAtivClick(View view) {
        Intent intent = new Intent(MenuActivity.this, HistoricoAtividadesActivity.class );
        startActivity(intent);
    }
}
