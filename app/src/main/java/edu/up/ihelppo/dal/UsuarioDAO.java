package edu.up.ihelppo.dal;

import android.content.Context;

import java.util.ArrayList;

import edu.up.ihelppo.model.Usuario;

public class UsuarioDAO {

   public static  int usuarioSessao = 0;

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

    public static Usuario buscarUsuarioPorID(Context context, int idUsuario){
        Banco banco = new Banco(context);
        return  banco.buscarUsuarioPorID(idUsuario);
    }

    public static void setarUsuario(int value){
        usuarioSessao = value;
    }

    public static int retornarUsuario(){
        return  usuarioSessao;
    }

    public static Usuario validarUsuarioLogin(Context context, String email, String senha){
        Banco banco = new Banco(context);
        return banco.validacaoLogin(email, senha);
    }
}