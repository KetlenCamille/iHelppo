package edu.up.ihelppo.dal;

import android.content.Context;

import java.util.ArrayList;

import edu.up.ihelppo.model.Atividade;

public class AtividadeDAO {

    public static long cadastrarAtividade(Context context, Atividade atividade) {
        Banco banco = new Banco(context);
        return banco.cadastrarAtividade(atividade);
    }

    public static ArrayList<Atividade> listarAtividades(Context context) {
        Banco banco = new Banco(context);
        return banco.listarAtividades();
    }

    public static long alterarAtividade(Context context, Atividade atividade) {
        Banco banco = new Banco(context);
        return banco.alterarAtividade(atividade);
    }
}
