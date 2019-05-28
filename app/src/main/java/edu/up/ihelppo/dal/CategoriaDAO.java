package edu.up.ihelppo.dal;

import android.content.Context;

import edu.up.ihelppo.model.Categoria;

public class CategoriaDAO {
    public static long cadastrarCategoria(Context context, Categoria categoria){
        Banco banco = new Banco(context);
        return banco.cadastrarCategoria(categoria);
    }
}
