package edu.up.ihelppo.dal;

import android.content.Context;

import java.util.ArrayList;

import edu.up.ihelppo.model.Usuario;

public class UsuarioDAO {

    public static long cadastrarUsuario(Context context, Usuario usuario) {
        Banco banco = new Banco(context);
        return banco.cadastrarUsuario(usuario);
    }

    public static long alterarUsuario(Context context, Usuario usuario) {
        Banco banco = new Banco(context);
        return banco.alterarUsuario(usuario);
    }

    public static ArrayList<Usuario> listarUsuarios(Context context) {
        Banco banco = new Banco(context);
        return banco.listarUsuarios();
    }
}