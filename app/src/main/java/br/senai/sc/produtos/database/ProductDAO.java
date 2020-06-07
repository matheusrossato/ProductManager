package br.senai.sc.produtos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.produtos.ProductCreateActivity;
import br.senai.sc.produtos.database.entity.ProductEntity;
import br.senai.sc.produtos.models.Product;

public class ProductDAO {
    private DBGateway dbGateway;
    private final String SQL_LIST_ALL = "SELECT * FROM " + ProductEntity.TABLE_NAME;

    public ProductDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }
    public boolean save(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductEntity.COLUMN_NAME_NOME, product.getName());
        contentValues.put(ProductEntity.COLUMN_NAME_VALOR, product.getValue());
        if (product.getId() > 0) {
            return dbGateway.getDatabase().update(ProductEntity.TABLE_NAME,
                    contentValues,ProductEntity._ID + "=?",
                    new String[]{String.valueOf(product.getId())}) >0;
        }
        return dbGateway.getDatabase().insert(ProductEntity.TABLE_NAME, null, contentValues) >0;
    }

    public int delete(Product product){
        if (product.getId() > 0) {
            int rowsDeleted =  dbGateway.getDatabase().delete(ProductEntity.TABLE_NAME,
                    ProductEntity._ID + "=?",
                    new String[]{String.valueOf(product.getId())});
            if (rowsDeleted>0) {
                return rowsDeleted;
            }

        }
        return 0;
    }

    public List<Product> list(){
        List<Product> products = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LIST_ALL, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ProductEntity._ID));
            String name = cursor.getString(cursor.getColumnIndex(ProductEntity.COLUMN_NAME_NOME));
            Float value = cursor.getFloat(cursor.getColumnIndex(ProductEntity.COLUMN_NAME_VALOR));
            products.add(new Product(id, name, value));
        }
        cursor.close();
        return products;
    }
}
