package edu.up.ihelppo.dal;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import edu.up.ihelppo.model.Atividade;
import edu.up.ihelppo.model.Categoria;
import edu.up.ihelppo.model.DiasDaSemana;
import edu.up.ihelppo.model.Usuario;

import static edu.up.ihelppo.dal.Contrato.*;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "iHelppo.db";
    private static final int VERSAO_BANCO = 1;

    private static final String TIPO_TEXTO = " TEXT";
    private static final String TIPO_INTEIRO = " INTEGER";
    private static final String TIPO_DATA = " DATE";
    private static final String VIRGULA = ", ";

    /*---------------- CATEGORIA -------------------*/
    private static final String SQL_CRIAR_TABELA_CATEGORIA =
            "CREATE TABLE IF NOT EXISTS " + TabelaCategoria.NOME_DA_TABELA + " (" +
                    TabelaCategoria.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    TabelaCategoria.COLUNA_DESCRICAO + TIPO_TEXTO + VIRGULA +
                    TabelaCategoria.COLUNA_IDUSUARIO + TIPO_INTEIRO + VIRGULA +
                    TabelaCategoria.COLUNA_EHINATIVO + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_CATEGORIA =
            "DROP TABLE IF EXISTS " + TabelaCategoria.NOME_DA_TABELA;

    /*---------------- USUARIO -------------------*/
    private static final String SQL_CRIAR_TABELA_USUARIO =
            "CREATE TABLE IF NOT EXISTS " + TabelaUsuario.NOME_DA_TABELA + " (" +
                    TabelaUsuario.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    TabelaUsuario.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    TabelaUsuario.COLUNA_SOBRENOME + TIPO_TEXTO + VIRGULA +
                    TabelaUsuario.COLUNA_EMAIL + TIPO_TEXTO + VIRGULA +
                    TabelaUsuario.COLUNA_DATANASCIMENTO + TIPO_TEXTO + VIRGULA +
                    TabelaUsuario.COLUNA_SENHA + TIPO_TEXTO + VIRGULA +
                    TabelaUsuario.COLUNA_EHINATIVO + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_USUARIO =
            "DROP TABLE IF EXISTS " + TabelaUsuario.NOME_DA_TABELA;

    /* -------------- DIAS DA SEMANA ------------- */
    private static final String SQL_CRIAR_TABELA_DIAS_DA_SEMANA =
            "CREATE TABLE IF NOT EXISTS " + TabelaDiasDaSemana.NOME_DA_TABELA + " (" +
                    TabelaDiasDaSemana.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_SEGUNDA + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_TERCA + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_QUARTA + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_QUINTA + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_SEXTA + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_SABADO + TIPO_TEXTO + VIRGULA +
                    TabelaDiasDaSemana.COLUNA_DOMINGO + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_DIAS_DA_SEMANA =
            "DROP TABLE IF EXISTS " + TabelaDiasDaSemana.NOME_DA_TABELA;

    /* ---------------- ATIVIDADE ------------------*/
    private static final String SQL_CRIAR_TABELA_ATIVIDADE =
            "CREATE TABLE IF NOT EXISTS " + TabelaAtividade.NOME_DA_TABELA + " (" +
                    TabelaAtividade.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    TabelaAtividade.COLUNA_TITULO + TIPO_TEXTO + VIRGULA +
                    TabelaAtividade.COLUNA_DESCRICAO + TIPO_TEXTO + VIRGULA +
                    TabelaAtividade.COLUNA_DATA_CRIACAO + TIPO_TEXTO + VIRGULA +
                    TabelaAtividade.COLUNA_FOI_REALIZADO + TIPO_TEXTO + VIRGULA +
                    TabelaAtividade.COLUNA_ID_CATEGORIA + TIPO_INTEIRO + VIRGULA +
                    TabelaAtividade.COLUNA_ID_USUARIO + TIPO_INTEIRO + VIRGULA +
                    TabelaAtividade.COLUNA_ID_DIASDASEMANA + TIPO_INTEIRO + VIRGULA +
                    " FOREIGN KEY (" + TabelaAtividade.COLUNA_ID_CATEGORIA + ") REFERENCES " + TabelaCategoria.NOME_DA_TABELA + "( " + TabelaCategoria.COLUNA_ID + ")" + VIRGULA +
                    " FOREIGN KEY (" + TabelaAtividade.COLUNA_ID_USUARIO + ") REFERENCES " + TabelaUsuario.NOME_DA_TABELA + "( " + TabelaUsuario.COLUNA_ID + ")" + VIRGULA +
                    " FOREIGN KEY (" + TabelaAtividade.COLUNA_ID_DIASDASEMANA + ") REFERENCES " + TabelaDiasDaSemana.NOME_DA_TABELA + "( " + TabelaDiasDaSemana.COLUNA_ID + ")" +
                    ");";
    private static final String SQL_DELETAR_TABELA_ATIVIDADE =
            "DROP TABLE IF EXISTS " + TabelaAtividade.NOME_DA_TABELA;

    //Contrutor da classe
    public Banco(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_CATEGORIA);
        db.execSQL(SQL_CRIAR_TABELA_CATEGORIA);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_USUARIO);
        db.execSQL(SQL_CRIAR_TABELA_USUARIO);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_DIAS_DA_SEMANA);
        db.execSQL(SQL_CRIAR_TABELA_DIAS_DA_SEMANA);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_ATIVIDADE);
        db.execSQL(SQL_CRIAR_TABELA_ATIVIDADE);
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
        values.put(TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());
        values.put(TabelaCategoria.COLUNA_IDUSUARIO, categoria.getIdUsuario());
        values.put(TabelaCategoria.COLUNA_EHINATIVO, categoria.getEhInativo());

        return db.insert(TabelaCategoria.NOME_DA_TABELA, null, values);
    }

    //Listar Categorias
    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                TabelaCategoria.COLUNA_ID,
                TabelaCategoria.COLUNA_DESCRICAO,
                TabelaCategoria.COLUNA_IDUSUARIO,
                TabelaCategoria.COLUNA_EHINATIVO
        };

        Cursor cursor = db.query(TabelaCategoria.NOME_DA_TABELA, colunas, null, null, null, null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(cursor.getInt(0));
                categoria.setDescricao(cursor.getString(1));
                categoria.setIdUsuario(cursor.getInt(2));
                categoria.setEhInativo(cursor.getString(3));
                categorias.add(categoria);
            } while (cursor.moveToNext());
        }
        return categorias;
    }

    //Listar Categorias Por Nome
    public ArrayList<String> listarCategoriasPorNome(int idUsuario) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> categorias = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA + " WHERE " +
                TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                categorias.add(cursor.getString(cursor.getColumnIndex("Descricao")));
            } while (cursor.moveToNext());
        }
        return categorias;
    }

    public Categoria buscarCategoriaPorNome(String p_categoria, int idUsuario) {
        Categoria categoria = new Categoria();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA + " WHERE " +
                TabelaCategoria.COLUNA_DESCRICAO + " = '" + p_categoria + "' AND " +
                TabelaCategoria.COLUNA_IDUSUARIO + " = " + idUsuario, null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaCategoria.COLUNA_ID)));
            categoria.setDescricao(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_DESCRICAO)));
            categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaCategoria.COLUNA_IDUSUARIO)));
            categoria.setEhInativo(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_EHINATIVO)));
        }
        cursor.close();
        return categoria;
    }

    //Listar Categorias Ativas
    public ArrayList<Categoria> listarCategoriasAtivas(int idUsuario) {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA + " WHERE " +
                TabelaCategoria.COLUNA_EHINATIVO + " = 'N' AND " +
                TabelaCategoria.COLUNA_IDUSUARIO + " = " + idUsuario, null);
        //Colando o cursor para a 1a posição
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Categoria categoria = new Categoria();
            while (cursor.moveToNext()) {
                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaCategoria.COLUNA_ID)));
                categoria.setDescricao(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_DESCRICAO)));
                categoria.setEhInativo(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_EHINATIVO)));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    //Buscar Categorias Por ID
    public Categoria buscarCategoriaPorID(int idCategoria) {
        Categoria categoria = new Categoria();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA + " WHERE " + TabelaCategoria.COLUNA_ID + " = " + idCategoria, null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaCategoria.COLUNA_ID)));
            categoria.setDescricao(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_DESCRICAO)));
            categoria.setEhInativo(cursor.getString(cursor.getColumnIndex(TabelaCategoria.COLUNA_EHINATIVO)));
        }
        cursor.close();
        return categoria;
    }

    //Alterar Categoria
    public long alterarCategoria(Categoria categoria) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());
        values.put(TabelaCategoria.COLUNA_EHINATIVO, categoria.getEhInativo());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = TabelaCategoria.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(categoria.getIdCategoria())};

        return db.update(TabelaCategoria.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Categoria
    public long removerCategoria(Categoria categoria) {
        SQLiteDatabase db = getWritableDatabase();
        String condicao = TabelaCategoria.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(categoria.getIdCategoria())};

        return db.delete(TabelaCategoria.NOME_DA_TABELA, condicao, argumentos);
    }

    /* ------------- USUÁRIO ---------------- */
    public long cadastrarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(TabelaUsuario.COLUNA_EMAIL, usuario.getEmail());
        values.put(TabelaUsuario.COLUNA_DATANASCIMENTO, usuario.getDataNascimento());
        values.put(TabelaUsuario.COLUNA_SENHA, usuario.getSenha());
        values.put(TabelaUsuario.COLUNA_EHINATIVO, usuario.getEhInativo());

        return db.insert(TabelaUsuario.NOME_DA_TABELA, null, values);
    }

    //Listar Usuário
    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                TabelaUsuario.COLUNA_ID,
                TabelaUsuario.COLUNA_NOME,
                TabelaUsuario.COLUNA_SOBRENOME,
                TabelaUsuario.COLUNA_EMAIL,
                TabelaUsuario.COLUNA_DATANASCIMENTO,
                TabelaUsuario.COLUNA_SENHA,
                TabelaUsuario.COLUNA_EHINATIVO
        };

        Cursor cursor = db.query(TabelaUsuario.NOME_DA_TABELA, colunas, null, null, null, null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setSobrenome(cursor.getString(2));
                usuario.setEmail(cursor.getString(3));
                usuario.setDataNascimento(cursor.getString(4));
                usuario.setSenha(cursor.getString(5));
                usuario.setEhInativo(cursor.getString(6));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

    //Buscar Usuarios Por ID
    public Usuario buscarUsuarioPorID(int idUsuario) {
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaUsuario.NOME_DA_TABELA + " WHERE " + TabelaUsuario.COLUNA_ID + " = " + idUsuario+ " AND " + TabelaUsuario.COLUNA_EHINATIVO + " = 'N'", null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            usuario.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaUsuario.COLUNA_ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_NOME)));
            usuario.setSobrenome(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_SOBRENOME)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_EMAIL)));
            usuario.setDataNascimento(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_DATANASCIMENTO)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_SENHA)));
            usuario.setEhInativo(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_EHINATIVO)));
        }
        cursor.close();
        return usuario;
    }

    //Validando Usuario
    public Usuario validacaoLogin(String email, String senha) {
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaUsuario.NOME_DA_TABELA +
                        " WHERE " + TabelaUsuario.COLUNA_EMAIL + " = '" + email + "'" + " AND " +
                TabelaUsuario.COLUNA_SENHA + " = '" + senha + "'", null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            usuario.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaUsuario.COLUNA_ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_NOME)));
            usuario.setSobrenome(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_SOBRENOME)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_EMAIL)));
            usuario.setDataNascimento(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_DATANASCIMENTO)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_SENHA)));
            usuario.setEhInativo(cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_EHINATIVO)));
        }
        cursor.close();
        return usuario;
    }
    //Alterar Usuário
    public long alterarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(TabelaUsuario.COLUNA_EMAIL, usuario.getEmail());
        values.put(TabelaUsuario.COLUNA_DATANASCIMENTO, usuario.getDataNascimento());
        values.put(TabelaUsuario.COLUNA_SENHA, usuario.getSenha());
        values.put(TabelaUsuario.COLUNA_EHINATIVO, usuario.getEhInativo());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(usuario.getIdUsuario())};

        return db.update(TabelaUsuario.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Usuário
    public long removerUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        String condicao = TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(usuario.getIdUsuario())};

        return db.delete(TabelaUsuario.NOME_DA_TABELA, condicao, argumentos);
    }

    /* ----------- ATIVIDADE ------------ */
    public long cadastrarAtividade(Atividade atividade) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaAtividade.COLUNA_ID_USUARIO, atividade.getIdUsuario());
        values.put(TabelaAtividade.COLUNA_ID_CATEGORIA, atividade.getIdCategoria());
        values.put(TabelaAtividade.COLUNA_ID_DIASDASEMANA, atividade.getIdDiasSemana());
        values.put(TabelaAtividade.COLUNA_TITULO, atividade.getTitulo());
        values.put(TabelaAtividade.COLUNA_DESCRICAO, atividade.getDescricaoAtividade());
        values.put(TabelaAtividade.COLUNA_DATA_CRIACAO, atividade.getDataCriacao());
        values.put(TabelaAtividade.COLUNA_FOI_REALIZADO, atividade.getFoiRealizada());

        return db.insert(TabelaAtividade.NOME_DA_TABELA, null, values);
    }

    //Listar Atividade
    public ArrayList<Atividade> listarAtividades() {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();
        //
        String[] colunas = {
                TabelaAtividade.COLUNA_ID,
                TabelaAtividade.COLUNA_ID_USUARIO,
                TabelaAtividade.COLUNA_ID_CATEGORIA,
                TabelaAtividade.COLUNA_ID_DIASDASEMANA,
                TabelaAtividade.COLUNA_TITULO,
                TabelaAtividade.COLUNA_DESCRICAO,
                TabelaAtividade.COLUNA_DATA_CRIACAO,
                TabelaAtividade.COLUNA_FOI_REALIZADO
        };

        Cursor cursor = db.query(TabelaAtividade.NOME_DA_TABELA, colunas, null, null, null, null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(0));
                atividade.setIdUsuario(cursor.getInt(1));
                atividade.setIdCategoria(cursor.getInt(2));
                atividade.setIdDiasSemana(cursor.getInt(3));
                atividade.setTitulo(cursor.getString(4));
                atividade.setDescricaoAtividade(cursor.getString(5));
                atividade.setDataCriacao(cursor.getString(6));
                atividade.setFoiRealizada(cursor.getString(7));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
    }

    //Listar Atividades do Dia
    public ArrayList<Atividade> listarAtividadesDoDia(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaAtividade.NOME_DA_TABELA + " WHERE " +
                TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "'" , null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
    }

    //Listar Atividades Do Dia e Domingo
    public ArrayList<Atividade> listarAtividadesDoDiaDomingo(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '" + data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_DOMINGO + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Segunda
    public ArrayList<Atividade> listarAtividadesDoDiaSegunda(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_SEGUNDA + " = 'S'", null);
        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Terça
    public ArrayList<Atividade> listarAtividadesDoDiaTerca(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_TERCA + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Quarta
    public ArrayList<Atividade> listarAtividadesDoDiaQuarta(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_QUARTA + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Quinta
    public ArrayList<Atividade> listarAtividadesDoDiaQuinta(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_QUINTA + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Sexta
    public ArrayList<Atividade> listarAtividadesDoDiaSexta(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_SEXTA + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Atividades Do Dia e Sábado
    public ArrayList<Atividade> listarAtividadesDoDiaSabado(String data, int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID  + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_CATEGORIA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_TITULO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DESCRICAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO + VIRGULA +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO +
                " FROM " + TabelaAtividade.NOME_DA_TABELA + " INNER JOIN " +
                TabelaDiasDaSemana.NOME_DA_TABELA + " ON " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_DIASDASEMANA  + " = " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_ID + " WHERE " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_FOI_REALIZADO + " <> 'S' AND " +
                TabelaAtividade.NOME_DA_TABELA + "." + TabelaAtividade.COLUNA_DATA_CRIACAO +  " LIKE '"+ data + "' OR " +
                TabelaDiasDaSemana.NOME_DA_TABELA + "." + TabelaDiasDaSemana.COLUNA_SABADO + " = 'S'", null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return atividades;
    }

    //Listar Historico de Atividades
    public ArrayList<Atividade> listarHistoricoAtividades( int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaAtividade.NOME_DA_TABELA +
                " WHERE " + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                        TabelaAtividade.COLUNA_FOI_REALIZADO + " <> '' AND " +
                "strftime('%Y%m%d'," + TabelaAtividade.COLUNA_DATA_CRIACAO + ") <  strftime('%Y%m%d', 'now')", null);
        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
    }

    //Listar  Atividades Futuras
    public ArrayList<Atividade> listarAtividadesFuturas( int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaAtividade.NOME_DA_TABELA +
                " WHERE " + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +
                TabelaAtividade.COLUNA_FOI_REALIZADO + " = '' AND " +
                "strftime('%Y%m%d'," + TabelaAtividade.COLUNA_DATA_CRIACAO + ") >  strftime('%Y%m%d', 'now')", null);
        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
    }

    //Listar Atividades Pendentes
    public ArrayList<Atividade> listarAtividadesPendentes(int idUsuario) {
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaAtividade.NOME_DA_TABELA + " WHERE " + TabelaAtividade.COLUNA_ID_USUARIO + " = " + idUsuario + " AND " +  TabelaAtividade.COLUNA_FOI_REALIZADO + " = ''", null);
        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
                atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
                atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
                atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
                atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
                atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
                atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
    }

    //Buscar Atividade Por ID
    public Atividade buscarAtividadePorID(int idAtividade) {
        Atividade atividade = new Atividade();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaAtividade.NOME_DA_TABELA + " WHERE " + TabelaAtividade.COLUNA_ID + " = " + idAtividade, null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            atividade.setIdAtividade(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID)));
            atividade.setIdUsuario(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_USUARIO)));
            atividade.setIdCategoria(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_CATEGORIA)));
            atividade.setIdDiasSemana(cursor.getInt(cursor.getColumnIndex(TabelaAtividade.COLUNA_ID_DIASDASEMANA)));
            atividade.setTitulo(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_TITULO)));
            atividade.setDescricaoAtividade(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DESCRICAO)));
            atividade.setDataCriacao(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_DATA_CRIACAO)));
            atividade.setFoiRealizada(cursor.getString(cursor.getColumnIndex(TabelaAtividade.COLUNA_FOI_REALIZADO)));
        }
        cursor.close();
        return atividade;
    }

    //Alterar Atividade
    public long alterarAtividade(Atividade atividade) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaAtividade.COLUNA_ID_USUARIO, atividade.getIdUsuario());
        values.put(TabelaAtividade.COLUNA_ID_CATEGORIA, atividade.getIdCategoria());
        values.put(TabelaAtividade.COLUNA_ID_DIASDASEMANA, atividade.getIdDiasSemana());
        values.put(TabelaAtividade.COLUNA_TITULO, atividade.getTitulo());
        values.put(TabelaAtividade.COLUNA_DESCRICAO, atividade.getDescricaoAtividade());
        values.put(TabelaAtividade.COLUNA_DATA_CRIACAO, atividade.getDataCriacao());
        values.put(TabelaAtividade.COLUNA_FOI_REALIZADO, atividade.getFoiRealizada());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = TabelaAtividade.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(atividade.getIdAtividade())};

        return db.update(TabelaAtividade.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Atividade
    public long removerAtividade(Atividade atividade) {
        SQLiteDatabase db = getWritableDatabase();
        String condicao = TabelaAtividade.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(atividade.getIdAtividade())};

        return db.delete(TabelaAtividade.NOME_DA_TABELA, condicao, argumentos);
    }

    /* -------------- DIAS DA SEMANA ----------------- */
    public long cadastrarDiasDaSemana(DiasDaSemana diasDaSemana) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaDiasDaSemana.COLUNA_SEGUNDA, diasDaSemana.getSegunda());
        values.put(TabelaDiasDaSemana.COLUNA_TERCA, diasDaSemana.getTerca());
        values.put(TabelaDiasDaSemana.COLUNA_QUARTA, diasDaSemana.getQuarta());
        values.put(TabelaDiasDaSemana.COLUNA_QUINTA, diasDaSemana.getQuinta());
        values.put(TabelaDiasDaSemana.COLUNA_SEXTA, diasDaSemana.getSexta());
        values.put(TabelaDiasDaSemana.COLUNA_SABADO, diasDaSemana.getSabado());
        values.put(TabelaDiasDaSemana.COLUNA_DOMINGO, diasDaSemana.getDomingo());

        return db.insert(TabelaDiasDaSemana.NOME_DA_TABELA, null, values);
    }

    //Listar Dias Da Semana
    public ArrayList<DiasDaSemana> listarDiasDaSemana() {
        ArrayList<DiasDaSemana> diasDaSemanas = new ArrayList<DiasDaSemana>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                TabelaDiasDaSemana.COLUNA_ID,
                TabelaDiasDaSemana.COLUNA_SEGUNDA,
                TabelaDiasDaSemana.COLUNA_TERCA,
                TabelaDiasDaSemana.COLUNA_QUARTA,
                TabelaDiasDaSemana.COLUNA_QUINTA,
                TabelaDiasDaSemana.COLUNA_SEXTA,
                TabelaDiasDaSemana.COLUNA_SABADO,
                TabelaDiasDaSemana.COLUNA_DOMINGO
        };

        Cursor cursor = db.query(TabelaDiasDaSemana.NOME_DA_TABELA, colunas, null, null, null, null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                DiasDaSemana diaDaSemana = new DiasDaSemana();
                diaDaSemana.setIdDiasDaSemana(cursor.getInt(0));
                diaDaSemana.setSegunda(cursor.getString(1));
                diaDaSemana.setTerca(cursor.getString(2));
                diaDaSemana.setQuarta(cursor.getString(3));
                diaDaSemana.setQuinta(cursor.getString(4));
                diaDaSemana.setSexta(cursor.getString(5));
                diaDaSemana.setSabado(cursor.getString(6));
                diaDaSemana.setDomingo(cursor.getString(7));
                diasDaSemanas.add(diaDaSemana);
            } while (cursor.moveToNext());
        }
        return diasDaSemanas;
    }

    //Buscar Dia da Semana Por ID
    public DiasDaSemana buscarDiaDaSemanaPorID(int idDiaDaSemana) {
        DiasDaSemana diasDaSemana = new DiasDaSemana();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaDiasDaSemana.NOME_DA_TABELA + " WHERE " + TabelaDiasDaSemana.COLUNA_ID + " = " + idDiaDaSemana, null);
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            diasDaSemana.setIdDiasDaSemana(cursor.getInt(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_ID)));
            diasDaSemana.setDomingo(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_DOMINGO)));
            diasDaSemana.setSegunda(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SEGUNDA)));
            diasDaSemana.setTerca(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_TERCA)));
            diasDaSemana.setQuarta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_QUARTA)));
            diasDaSemana.setQuinta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_QUINTA)));
            diasDaSemana.setSexta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SEXTA)));
            diasDaSemana.setSabado(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SABADO)));
        }
        cursor.close();
        return diasDaSemana;
    }

    //Buscar Dia Da Semana Igual
    public DiasDaSemana buscarDiasDaSemanaExistente(DiasDaSemana p_diasDaSemana) {
        DiasDaSemana diasDaSemana = new DiasDaSemana();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaDiasDaSemana.NOME_DA_TABELA +
                " WHERE " + TabelaDiasDaSemana.COLUNA_SEGUNDA + " = '" + p_diasDaSemana.getSegunda() + "'" + " AND " +
                TabelaDiasDaSemana.COLUNA_TERCA + " = '" + p_diasDaSemana.getTerca() + "'" + " AND " +
                TabelaDiasDaSemana.COLUNA_QUARTA + " = '" + p_diasDaSemana.getQuarta() + "'" +  " AND " +
                TabelaDiasDaSemana.COLUNA_QUINTA + " = '" + p_diasDaSemana.getQuinta() + "'" + " AND " +
                TabelaDiasDaSemana.COLUNA_SEXTA + " = '" + p_diasDaSemana.getSexta() + "'" + " AND " +
                TabelaDiasDaSemana.COLUNA_SABADO + " = '" + p_diasDaSemana.getSabado() + "'" + " AND " +
                TabelaDiasDaSemana.COLUNA_DOMINGO + " = '" + p_diasDaSemana.getDomingo() + "'", null);
        //
        //Colando o cursor para a 1a posição

        if (cursor.moveToFirst()) {
            diasDaSemana.setIdDiasDaSemana(cursor.getInt(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_ID)));
            diasDaSemana.setSegunda(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SEGUNDA)));
            diasDaSemana.setTerca(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_TERCA)));
            diasDaSemana.setQuarta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_QUARTA)));
            diasDaSemana.setQuinta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_QUINTA)));
            diasDaSemana.setSexta(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SEXTA)));
            diasDaSemana.setSabado(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_SABADO)));
            diasDaSemana.setDomingo(cursor.getString(cursor.getColumnIndex(TabelaDiasDaSemana.COLUNA_DOMINGO)));
        }
        cursor.close();
        return diasDaSemana;
    }

    //Alterar Dias da Semana
    public long alterarDiasDaSemana(DiasDaSemana diasDaSemana) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaDiasDaSemana.COLUNA_SEGUNDA, diasDaSemana.getSegunda());
        values.put(TabelaDiasDaSemana.COLUNA_TERCA, diasDaSemana.getTerca());
        values.put(TabelaDiasDaSemana.COLUNA_QUARTA, diasDaSemana.getQuarta());
        values.put(TabelaDiasDaSemana.COLUNA_QUINTA, diasDaSemana.getQuinta());
        values.put(TabelaDiasDaSemana.COLUNA_SEXTA, diasDaSemana.getSexta());
        values.put(TabelaDiasDaSemana.COLUNA_SABADO, diasDaSemana.getSabado());
        values.put(TabelaDiasDaSemana.COLUNA_DOMINGO, diasDaSemana.getDomingo());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = TabelaDiasDaSemana.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(diasDaSemana.getIdDiasDaSemana())};

        return db.update(TabelaDiasDaSemana.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Dias da Semana
    public long removerDiasDaSemana(DiasDaSemana diasDaSemana) {
        SQLiteDatabase db = getWritableDatabase();
        String condicao = TabelaDiasDaSemana.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(diasDaSemana.getIdDiasDaSemana())};

        return db.delete(TabelaDiasDaSemana.NOME_DA_TABELA, condicao, argumentos);
    }
}
