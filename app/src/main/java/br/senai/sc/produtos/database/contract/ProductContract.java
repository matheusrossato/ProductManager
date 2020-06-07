package br.senai.sc.produtos.database.contract;

import br.senai.sc.produtos.database.entity.ProdutoEntity;

public class ProdutoContract {
    public static final createTable() {
        return "Criar Tabela " + ProdutoEntity.TABLE_NAME + " (" +
                ProdutoEntity._ID + " INTEGER PRIMARY KEY";

    }
}
