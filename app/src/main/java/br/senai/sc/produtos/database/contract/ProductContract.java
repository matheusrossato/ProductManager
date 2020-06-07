package br.senai.sc.produtos.database.contract;

import br.senai.sc.produtos.database.entity.ProductEntity;

public class ProductContract {
    public static final String createTable() {
        return "CREATE TABLE " + ProductEntity.TABLE_NAME + " (" +
                ProductEntity._ID + " INTEGER PRIMARY KEY, " +
                ProductEntity.COLUMN_NAME_NOME + " TEXT, " +
                ProductEntity.COLUMN_NAME_VALOR + " REAL)";

    }

    public static final String removeTable() {
        return "DROP TABLE IF EXISTS " + ProductEntity.TABLE_NAME;

    }
}
