package edu.up.ihelppo.dal;

import android.content.Context;

import java.util.ArrayList;

import edu.up.ihelppo.model.DiasDaSemana;

public class DiasDaSemanaDAO {

    public static long cadastrarDiasDaSemana(Context context, DiasDaSemana diasDaSemana){
        Banco banco = new Banco(context);
        return banco.cadastrarDiasDaSemana(diasDaSemana);
    }

    public static long alterarDiasDaSemana(Context context, DiasDaSemana diasDaSemana) {
        Banco banco = new Banco(context);
        return banco.alterarDiasDaSemana(diasDaSemana);
    }

    public static ArrayList<DiasDaSemana> listarDiasDaSemana(Context context) {
        Banco banco = new Banco(context);
        return banco.listarDiasDaSemana();
    }

    public static DiasDaSemana buscarDiasDaSemanaExistente(Context context, DiasDaSemana diasDaSemana, int idUsuario){
        Banco banco = new Banco(context);
        return  banco.buscarDiasDaSemanaExistente(diasDaSemana, idUsuario);
    }

    public static DiasDaSemana buscarDiaDaSemanaPorId(Context context, int  idDiasDaSemana) {
        Banco banco = new Banco(context);
        return banco.buscarDiaDaSemanaPorID(idDiasDaSemana);
    }
}
