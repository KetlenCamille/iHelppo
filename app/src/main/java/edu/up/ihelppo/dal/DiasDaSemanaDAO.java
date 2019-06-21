package edu.up.ihelppo.dal;

import android.content.Context;

import edu.up.ihelppo.model.DiasDaSemana;

public class DiasDaSemanaDAO {
    public static long cadastrarDiasDaSemana(Context context, DiasDaSemana diasDaSemana){
        Banco banco = new Banco(context);
        return banco.cadastrarDiasDaSemana(diasDaSemana);
    }
}
