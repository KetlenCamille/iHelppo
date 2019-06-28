package edu.up.ihelppo.dal;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public static Atividade buscarAtividadePorId(Context context, Atividade atividade) {
        Banco banco = new Banco(context);
        return banco.buscarAtividadePorID(atividade.getIdAtividade());
    }

    public static long alterarAtividade(Context context, Atividade atividade) {
        Banco banco = new Banco(context);
        return banco.alterarAtividade(atividade);
    }

    public static ArrayList<Atividade> listarAtividadesDoDia(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        SimpleDateFormat formataData = new SimpleDateFormat("dd/M/yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);

        return banco.listarAtividadesDoDia(dataFormatada, idUsuario);
    }

    public static ArrayList<Atividade> listarHistoricoAtividades(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        SimpleDateFormat formataData = new SimpleDateFormat("dd/M/yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        return banco.listarHistoricoAtividades(dataFormatada, idUsuario);
    }

    public static long excluirAtividade(Context context, Atividade atividade){
        Banco banco = new Banco(context);
        return banco.removerAtividade(atividade);
    }
}
