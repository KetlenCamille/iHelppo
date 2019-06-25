package edu.up.ihelppo.dal;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
                    TabelaAtividade.COLUNA_ID_CATEGORIA + TIPO_INTEIRO + VIRGULA +
                    " FOREIGN KEY (" + TabelaAtividade.COLUNA_ID_CATEGORIA + ") REFERENCES " + TabelaCategoria.NOME_DA_TABELA + "( " + TabelaCategoria.COLUNA_ID + ")" +
                    ")";
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
        db.execSQL(SQL_CRIAR_TABELA_USUARIO);
        db.execSQL(SQL_CRIAR_TABELA_DIAS_DA_SEMANA);
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
                categoria.setEhInativo(cursor.getString(2));
                categorias.add(categoria);
            } while (cursor.moveToNext());
        }
        return categorias;
    }

    //Listar Categorias Por Nome
    public ArrayList<String> listarCategoriasPorNome() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> categorias = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                categorias.add(cursor.getString(cursor.getColumnIndex("Descricao")));
            } while (cursor.moveToNext());
        }
        return categorias;
    }

    //Listar Categorias Ativas
    public ArrayList<Categoria> listarCategoriasAtivas() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TabelaCategoria.NOME_DA_TABELA + " WHERE " + TabelaCategoria.COLUNA_EHINATIVO + " = 'N'", null);
        //Colando o cursor para a 1a posição
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Categoria categoria = new Categoria();
            while (cursor.moveToNext()){
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

    /*public Categoria buscarCategoriaPorId(int idCategoria){
        SQLiteDatabase db = getWritableDatabase();
        String condicao = Contrato.TabelaCategoria.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(idCategoria};

    }*/

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

    //Alterar Usuário
    public long alterarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(TabelaUsuario.COLUNA_EMAIL, usuario.getNome());
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
                TabelaAtividade.COLUNA_DATA_CRIACAO
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
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
        return atividades;
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
