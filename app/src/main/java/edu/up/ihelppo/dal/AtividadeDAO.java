package edu.up.ihelppo.dal;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.DiasDaSemana;

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

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(data);
        int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

        if(diaDaSemana == 1){
            return banco.listarAtividadesDoDiaDomingo(dataFormatada, idUsuario);
        }else if(diaDaSemana == 2){
            return  banco.listarAtividadesDoDiaSegunda(dataFormatada,idUsuario);
        }else if(diaDaSemana == 3){
            return banco.listarAtividadesDoDiaTerca(dataFormatada, idUsuario);
        }else if(diaDaSemana == 4){
            return  banco.listarAtividadesDoDiaQuarta(dataFormatada, idUsuario);
        }else if (diaDaSemana == 5) {
            return  banco.listarAtividadesDoDiaQuinta(dataFormatada, idUsuario);
        }else if(diaDaSemana == 6){
            return  banco.listarAtividadesDoDiaSexta(dataFormatada, idUsuario);
        }else if(diaDaSemana == 7){
            return banco.listarAtividadesDoDiaSabado(dataFormatada, idUsuario);
        }

        return banco.listarAtividadesDoDia(dataFormatada, idUsuario);
    }

    public static ArrayList<Atividade> listarHistoricoAtividades(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        SimpleDateFormat formataData = new SimpleDateFormat("dd/M/yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        return banco.listarHistoricoAtividades(dataFormatada, idUsuario);
    }

    public static ArrayList<Atividade> listarAtividadesFuturas(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        SimpleDateFormat formataData = new SimpleDateFormat("dd/M/yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        return banco.listarHistoricoAtividades(dataFormatada, idUsuario);
    }

    public static ArrayList<Atividade> listarAtividadesPendentes(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        return banco.listarAtividadesPendentes(idUsuario);
    }

    public static long excluirAtividade(Context context, Atividade atividade){
        Banco banco = new Banco(context);
        return banco.removerAtividade(atividade);
    }
}
