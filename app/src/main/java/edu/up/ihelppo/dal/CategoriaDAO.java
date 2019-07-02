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

    public static ArrayList<String> listarCategoriasPorNome (Context context, int idUsuario) {
        Banco banco = new Banco(context);
        return banco.listarCategoriasPorNome(idUsuario);
    }

    public static ArrayList<Categoria> listarCategoriasAtivas(Context context, int idUsuario) {
        Banco banco = new Banco(context);
        return banco.listarCategoriasAtivas(idUsuario);
    }

    public static Categoria buscarCategoriaPorId(Context context, Categoria categoria) {
        Banco banco = new Banco(context);
        return banco.buscarCategoriaPorID(categoria.getIdCategoria());
    }

    public static Categoria buscarCategoriaPorNome(Context context, String categoria, int idUsuario) {
        Banco banco = new Banco(context);
        return banco.buscarCategoriaPorNome(categoria, idUsuario);
    }
}
