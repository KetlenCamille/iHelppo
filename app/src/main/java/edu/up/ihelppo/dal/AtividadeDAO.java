package edu.up.ihelppo.dal;

import android.content.Context;

import edu.up.ihelppo.model.Atividade;

public class AtividadeDAO {
    public static long cadastrarAtividade(Context context, Atividade atividade) {
        Banco banco = new Banco(context);
        return banco.cadastrarAtividade(atividade);
    }
}
