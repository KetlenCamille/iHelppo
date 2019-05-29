package edu.up.ihelppo.dal;

import android.provider.BaseColumns;

import java.util.Date;

public final class Contrato {

    private Contrato() {    }

    public static abstract class TabelaCategoria implements BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Categorias";
        public static final String COLUNA_ID = "IdCategoria";
        public static final String COLUNA_DESCRICAO = "Descricao";
    }

    public static abstract class TabelaUsuario implements  BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Usuarios";
        public static final String COLUNA_ID = "IdUsuario";
        public static final String COLUNA_NOME = "Nome";
        public static final String COLUNA_EMAIL = "Email";
        public static final String COLUNA_SENHA = "Senha";
        public static final String COLUNA_DATANASCIMENTO = "Data de Nascimento";
    }

}
