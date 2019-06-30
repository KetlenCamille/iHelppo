package edu.up.ihelppo.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.up.ihelppo.R;
import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.view.DetalhesAtividadeActivity;

import java.util.ArrayList;

public class ArrayAdapterAtividade extends ArrayAdapter<Atividade> {

    private TextView txtDataAtividade, txtTituloAtividade, txtDescricaoAtividade;
    private Context context;
    private ArrayList<Atividade> atividade;

    public ArrayAdapterAtividade(Context context, ArrayList<Atividade> atividade) {
        super(context, 0, atividade);
        this.context = context;
        this.atividade = atividade;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        }

        txtDataAtividade = (TextView) convertView.findViewById(R.id.txtDataAtividade);
        txtTituloAtividade = (TextView) convertView.findViewById(R.id.txtTituloAtividade);
        txtDescricaoAtividade = (TextView) convertView.findViewById(R.id.txtDescricaoAtividade);

        txtDataAtividade.setText(atividade.get(position).getDataCriacao());
        txtTituloAtividade.setText(atividade.get(position).getTitulo());
        txtDescricaoAtividade.setText(String.valueOf(atividade.get(position).getDescricaoAtividade()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetalhesAtividadeActivity.class);
                intent.putExtra("ATIVIDADE", atividade.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
