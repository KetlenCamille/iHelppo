package edu.up.ihelppo.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.Usuario;

public class Banco extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "iHelppo.db";
    private static final int VERSAO_BANCO = 1;

    private static final String TIPO_TEXTO = " TEXT";
    private static final String TIPO_INTEIRO = " INTEGER";
    private static final String TIPO_DATA = " DATE";
    private static final String VIRGULA = ", ";

    /*---------------- CATEGORIA -------------------*/
    private static final String SQL_CRIAR_TABELA_CATEGORIA =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaCategoria.NOME_DA_TABELA + " (" +
                    Contrato.TabelaCategoria.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaCategoria.COLUNA_DESCRICAO + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_CATEGORIA =
            "DROP TABLE IF EXISTS " + Contrato.TabelaCategoria.NOME_DA_TABELA;

    /*---------------- USUARIO -------------------*/
    private static final String CRIAR_TABELA_USUARIO =
            "CREATE TABLE IF NOT EXISTS" + Contrato.TabelaUsuario.NOME_DA_TABELA + " (" +
                    Contrato.TabelaUsuario.COLUNA_ID + TIPO_INTEIRO + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_SOBRENOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_EMAIL + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO + TIPO_TEXTO  +
                    Contrato.TabelaUsuario.COLUNA_SENHA + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_USUARIO =
            "DROP TABLE IF EXISTS " + Contrato.TabelaUsuario.NOME_DA_TABELA;

    //Contrutor da classe
    public Banco(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_CATEGORIA);
        db.execSQL(SQL_CRIAR_TABELA_CATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_CATEGORIA);
        db.execSQL(SQL_DELETAR_TABELA_CATEGORIA);
    }

    /* ----------- CATEGORIA -------------- */
    public long cadastrarCategoria(Categoria categoria) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());

        return db.insert(Contrato.TabelaCategoria.NOME_DA_TABELA, null, values);
    }

    //Listar Usuário
    public ArrayList<Categoria> listarCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                Contrato.TabelaCategoria.COLUNA_ID,
                Contrato.TabelaCategoria.COLUNA_DESCRICAO
        };

        Cursor cursor = db.query(Contrato.TabelaCategoria.NOME_DA_TABELA, colunas, null,null,null,null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if(cursor.getCount()> 0)
        {
            do{
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(cursor.getInt(0));
                categoria.setDescricao(cursor.getString(1));
                categorias.add(categoria);
            }while(cursor.moveToNext());
        }
        return categorias;
    }

    /* ------------- USUÁRIO ---------------- */
    public long cadastrarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(Contrato.TabelaUsuario.COLUNA_EMAIL, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO, usuario.getDataNascimento());
        values.put(Contrato.TabelaUsuario.COLUNA_SENHA, usuario.getSenha());

        return  db.insert(Contrato.TabelaUsuario.NOME_DA_TABELA, null, values);
    }

    //Listar Usuário
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                Contrato.TabelaUsuario.COLUNA_ID,
                Contrato.TabelaUsuario.COLUNA_NOME,
                Contrato.TabelaUsuario.COLUNA_SOBRENOME,
                Contrato.TabelaUsuario.COLUNA_EMAIL,
                Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO,
                Contrato.TabelaUsuario.COLUNA_SENHA
        };

        Cursor cursor = db.query(Contrato.TabelaUsuario.NOME_DA_TABELA, colunas, null,null,null,null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if(cursor.getCount()> 0)
        {
            do{
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setSobrenome(cursor.getString(2));
                usuario.setEmail(cursor.getString(3));
                usuario.setDataNascimento(cursor.getString(4));
                usuario.setSenha(cursor.getString(5));
                usuarios.add(usuario);
            }while(cursor.moveToNext());
        }
        return usuarios;
    }

    //Alterar Usuário
    public long alterarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(Contrato.TabelaUsuario.COLUNA_EMAIL, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO, usuario.getDataNascimento());
        values.put(Contrato.TabelaUsuario.COLUNA_SENHA, usuario.getSenha());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = Contrato.TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(usuario.getIdUsuario())};

        return db.update(Contrato.TabelaUsuario.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Usuário
    public long removerUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        String condicao = Contrato.TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(usuario.getIdUsuario())};

        return db.delete(Contrato.TabelaUsuario.NOME_DA_TABELA, condicao, argumentos);
    }
}
