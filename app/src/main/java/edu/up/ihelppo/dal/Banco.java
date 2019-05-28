package edu.up.ihelppo.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import edu.up.ihelppo.model.Categoria;

public class Banco extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "iHelppo.db";
    private static final int VERSAO_BANCO = 1;

    private static final String TIPO_TEXTO = " TEXT";
    private static final String TIPO_INTEIRO = " INTEGER";
    private static final String VIRGULA = ", ";

    private static final String SQL_CRIAR_TABELA_CATEGORIA =
            "CREATE TABLE IF NOT EXISTS " + Contrato.TabelaCategoria.NOME_DA_TABELA + " (" +
                    Contrato.TabelaCategoria.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaCategoria.COLUNA_DESCRICAO + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_CATEGORIA =
            "DROP TABLE IF EXISTS " + Contrato.TabelaCategoria.NOME_DA_TABELA;

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

    //Cadastrar Categoria
    public long cadastrarCategoria(Categoria categoria) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaCategoria.COLUNA_DESCRICAO, categoria.getDescricao());

        return db.insert(Contrato.TabelaCategoria.NOME_DA_TABELA, null, values);
    }
}
