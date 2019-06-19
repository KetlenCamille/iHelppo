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
                    Contrato.TabelaCategoria.COLUNA_DESCRICAO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaCategoria.COLUNA_EHINATIVO + TIPO_TEXTO  + ")";

    private static final String SQL_DELETAR_TABELA_CATEGORIA =
            "DROP TABLE IF EXISTS " + Contrato.TabelaCategoria.NOME_DA_TABELA;

    /*---------------- USUARIO -------------------*/
    private static final String SQL_CRIAR_TABELA_USUARIO =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaUsuario.NOME_DA_TABELA + " (" +
                    Contrato.TabelaUsuario.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_SOBRENOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_EMAIL + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO + TIPO_TEXTO  + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_SENHA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_EHINATIVO + TIPO_TEXTO + ")";

    /* -------------- DIAS DA SEMANA ------------- */
    private static final String SQL_CRIAR_TABELA_DIAS_DA_SEMANA =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaDiasDaSemana.NOME_DA_TABELA + " (" +
                    Contrato.TabelaDiasDaSemana.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_SEGUNDA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_TERCA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_QUARTA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_QUINTA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_SEXTA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_SABADO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaDiasDaSemana.COLUNA_DOMINGO + TIPO_TEXTO + ")";

    /* ---------------- ATIVIDADE ------------------*/
    private static final String SQL_CRIAR_TABELA_ATIVIDADE =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaAtividade.NOME_DA_TABELA + " (" +
                    Contrato.TabelaAtividade.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaAtividade.COLUNA_TITULO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaAtividade.COLUNA_DESCRICAO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaAtividade.COLUNA_DATA_CRIACAO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaAtividade.COLUNA_ID_CATEGORIA + TIPO_INTEIRO + VIRGULA +
                    " FOREIGN KEY (" + Contrato.TabelaAtividade.COLUNA_ID_CATEGORIA + ") REFERENCES " + Contrato.TabelaCategoria.NOME_DA_TABELA + "( " + Contrato.TabelaCategoria.COLUNA_ID + ")" +
                    ")";

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
        values.put(Contrato.TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());
        values.put(Contrato.TabelaCategoria.COLUNA_EHINATIVO, categoria.getEhInativo());

        return db.insert(Contrato.TabelaCategoria.NOME_DA_TABELA, null, values);
    }

    //Listar Categorias
    public ArrayList<Categoria> listarCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                Contrato.TabelaCategoria.COLUNA_ID,
                Contrato.TabelaCategoria.COLUNA_DESCRICAO,
                Contrato.TabelaCategoria.COLUNA_EHINATIVO
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
                categoria.setEhInativo(cursor.getString(2));
                categorias.add(categoria);
            }while(cursor.moveToNext());
        }
        return categorias;
    }

    public ArrayList<String> listarCategoriasPorNome(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> categorias = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Contrato.TabelaCategoria.NOME_DA_TABELA,null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                categorias.add(cursor.getString(cursor.getColumnIndex("Descricao")));
            }while(cursor.moveToNext());
        }
        return categorias;
    }

    //Alterar Categoria
    public long alterarCategoria(Categoria categoria){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = Contrato.TabelaCategoria.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(categoria.getIdCategoria())};

        return db.update(Contrato.TabelaCategoria.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Categoria
    public long removerCategoria(Categoria categoria){
        SQLiteDatabase db = getWritableDatabase();
        String condicao = Contrato.TabelaCategoria.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(categoria.getIdCategoria())};

        return db.delete(Contrato.TabelaCategoria.NOME_DA_TABELA, condicao, argumentos);
    }

    /* ------------- USUÁRIO ---------------- */
    public long cadastrarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_SOBRENOME, usuario.getSobrenome());
        values.put(Contrato.TabelaUsuario.COLUNA_EMAIL, usuario.getEmail());
        values.put(Contrato.TabelaUsuario.COLUNA_DATANASCIMENTO, usuario.getDataNascimento());
        values.put(Contrato.TabelaUsuario.COLUNA_SENHA, usuario.getSenha());
        values.put(Contrato.TabelaUsuario.COLUNA_EHINATIVO, usuario.getEhInativo());

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
                Contrato.TabelaUsuario.COLUNA_SENHA,
                Contrato.TabelaUsuario.COLUNA_EHINATIVO
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
                usuario.setEhInativo(cursor.getString(6));
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
        values.put(Contrato.TabelaUsuario.COLUNA_EHINATIVO, usuario.getEhInativo());

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

    /* ----------- ATIVIDADE ------------ */
    public long cadastrarAtividade(Atividade atividade) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaAtividade.COLUNA_TITULO, atividade.getTitulo() );
        values.put(Contrato.TabelaAtividade.COLUNA_DESCRICAO, atividade.getDescricaoAtividade());
        values.put(Contrato.TabelaAtividade.COLUNA_ID_CATEGORIA, atividade.getIdCategoria());

        return db.insert(Contrato.TabelaAtividade.NOME_DA_TABELA, null, values);
    }


    /* -------------- DIAS DA SEMANA ----------------- */
    public long cadastrarDiasDaSemana(DiasDaSemana diasDaSemana){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SEGUNDA, diasDaSemana.getSegunda());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_TERCA, diasDaSemana.getTerca());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_QUARTA, diasDaSemana.getQuarta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_QUINTA, diasDaSemana.getQuinta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SEXTA, diasDaSemana.getSexta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SABADO, diasDaSemana.getSabado());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_DOMINGO, diasDaSemana.getDomingo());

        return  db.insert(Contrato.TabelaDiasDaSemana.NOME_DA_TABELA, null, values);
    }

    //Listar Dias Da Semana
    public ArrayList<DiasDaSemana> listarDiasDaSemana(){
        ArrayList<DiasDaSemana> diasDaSemanas = new ArrayList<DiasDaSemana>();
        SQLiteDatabase db = getReadableDatabase();

        //
        String[] colunas = {
                Contrato.TabelaDiasDaSemana.COLUNA_ID,
                Contrato.TabelaDiasDaSemana.COLUNA_SEGUNDA,
                Contrato.TabelaDiasDaSemana.COLUNA_TERCA,
                Contrato.TabelaDiasDaSemana.COLUNA_QUARTA,
                Contrato.TabelaDiasDaSemana.COLUNA_QUINTA,
                Contrato.TabelaDiasDaSemana.COLUNA_SEXTA,
                Contrato.TabelaDiasDaSemana.COLUNA_SABADO,
                Contrato.TabelaDiasDaSemana.COLUNA_DOMINGO
        };

        Cursor cursor = db.query(Contrato.TabelaDiasDaSemana.NOME_DA_TABELA, colunas, null,null,null,null, null);

        //Colando o cursor para a 1a posição
        cursor.moveToFirst();
        if(cursor.getCount()> 0)
        {
            do{
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
            }while(cursor.moveToNext());
        }
        return diasDaSemanas;
    }

    //Alterar Dias da Semana
    public long alterarDiasDaSemana(DiasDaSemana diasDaSemana){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SEGUNDA, diasDaSemana.getSegunda());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_TERCA, diasDaSemana.getTerca());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_QUARTA, diasDaSemana.getQuarta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_QUINTA, diasDaSemana.getQuinta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SEXTA, diasDaSemana.getSexta());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_SABADO, diasDaSemana.getSabado());
        values.put(Contrato.TabelaDiasDaSemana.COLUNA_DOMINGO, diasDaSemana.getDomingo());

        //No lugar do valor para comparar, colocar o ponto de interrogação
        String condicao = Contrato.TabelaDiasDaSemana.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(diasDaSemana.getIdDiasDaSemana())};

        return db.update(Contrato.TabelaDiasDaSemana.NOME_DA_TABELA, values, condicao, argumentos);
    }

    //Remover Dias da Semana
    public long removerDiasDaSemana(DiasDaSemana diasDaSemana){
        SQLiteDatabase db = getWritableDatabase();
        String condicao = Contrato.TabelaDiasDaSemana.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(diasDaSemana.getIdDiasDaSemana())};

        return db.delete(Contrato.TabelaDiasDaSemana.NOME_DA_TABELA, condicao, argumentos);
    }
}
