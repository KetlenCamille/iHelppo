package edu.up.ihelppo.dal;

import android.content.Context;

import java.util.ArrayList;

import edu.up.ihelppo.model.Categoria;

public class CategoriaDAO {
    public static long cadastrarCategoria(Context context, Categoria categoria){
        Banco banco = new Banco(context);
        return banco.cadastrarCategoria(categoria);
    }

    public static ArrayList<Categoria> listarCategorias (Context context) {
        Banco banco = new Banco(context);
        return banco.listarCategorias();
    }

    public static long alterarCategoria (Context context, Categoria categoria) {
        Banco banco = new Banco(context);
        return banco.alterarCategoria(categoria);
    }

    public static ArrayList<String> listarCategoriasPorNome (Context context) {
        Banco banco = new Banco(context);
        return banco.listarCategoriasPorNome();
    }

    public static ArrayList<Categoria> listarCategoriasAtivas(Context context) {
        Banco banco = new Banco(context);
        return banco.listarCategoriasAtivas();
    }

    public static Categoria buscarCategoriaPorId(Context context, Categoria categoria) {
        Banco banco = new Banco(context);
        return banco.buscarCategoriaPorID(categoria.getIdCategoria());
    }

    public static Categoria buscarCategoriaPorNome(Context context, String categoria) {
        Banco banco = new Banco(context);
        return banco.buscarCategoriaPorNome(categoria);
    }
}
