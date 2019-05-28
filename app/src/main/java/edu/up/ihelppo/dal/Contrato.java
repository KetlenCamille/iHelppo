package edu.up.ihelppo.dal;

import android.provider.BaseColumns;

public final class Contrato {

    private Contrato() {    }

    public static abstract class TabelaCategoria implements BaseColumns{
        public static final String NOME_DA_TABELA = "tb_Categorias";
        public static final String COLUNA_ID = "IdCategoria";
        public static final String COLUNA_DESCRICAO = "Descricao";
    }
}
