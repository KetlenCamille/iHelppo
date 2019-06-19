package edu.up.ihelppo.dal;

import android.provider.BaseColumns;

import java.util.Date;

public final class Contrato {

    private Contrato() {    }

    public static abstract class TabelaCategoria implements BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Categorias";
        public static final String COLUNA_ID = "IdCategoria";
        public static final String COLUNA_DESCRICAO = "Descricao";
        public static final String COLUNA_EHINATIVO = "Inativo";

    }

    public static abstract class TabelaUsuario implements  BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Usuarios";
        public static final String COLUNA_ID = "IdUsuario";
        public static final String COLUNA_NOME = "Nome";
        public static final String COLUNA_SOBRENOME = "Sobrenome";
        public static final String COLUNA_EMAIL = "Email";
        public static final String COLUNA_SENHA = "Senha";
        public static final String COLUNA_DATANASCIMENTO = "DataNascimento";
        public static final String COLUNA_EHINATIVO = "EhInativo";
    }

    public static abstract class TabelaAtividade implements  BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Atividades";
        public static final String COLUNA_ID = "IdAtividade";
        public static final String COLUNA_ID_USUARIO = "IdUsuario";
        public static final String COLUNA_ID_CATEGORIA = "IdCategoria";
        public static final String COLUNA_ID_DIASDASEMANA = "IdDiaSemana";
        public static final String COLUNA_TITULO = "Titulo";
        public static final String COLUNA_DESCRICAO = "Descricao";
        public static final String COLUNA_DATA_CRIACAO = "DataCriacao";
    }

    public static abstract class TabelaDiasDaSemana implements  BaseColumns {
        public static final String NOME_DA_TABELA = "tb_DiasDaSemana";
        public static final String COLUNA_ID = "IdDiaSemana";
        public static final String COLUNA_SEGUNDA = "Segunda";
        public static final String COLUNA_TERCA = "Terca";
        public static final String COLUNA_QUARTA = "Quarta";
        public static final String COLUNA_QUINTA = "Quinta";
        public static final String COLUNA_SEXTA = "Sexta";
        public static final String COLUNA_SABADO = "Sabado";
        public static final String COLUNA_DOMINGO = "Domingo";
    }
}
