package edu.up.ihelppo.dal;

import android.content.Context;

import edu.up.ihelppo.model.Usuario;

public class UsuarioDAO {

    public static long cadastrarUsuario(Context context, Usuario usuario) {
        Banco banco = new Banco(context);
        return banco.cadastrarUsuario(usuario);
    }
}
