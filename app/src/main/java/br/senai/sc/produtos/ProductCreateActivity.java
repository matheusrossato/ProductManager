package br.senai.sc.produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sc.produtos.database.ProductDAO;
import br.senai.sc.produtos.models.Product;

public class ProductCreateActivity extends AppCompatActivity {
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);
        setTitle("Cadastro de produto");
        loadProduct();
    }

    private void loadProduct() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("editProduct") != null ) {
            Product product = (Product) intent.getExtras().get("editProduct");
            EditText editTextName = findViewById(R.id.editText_name);
            EditText editTextValue = findViewById(R.id.editText_value);
            editTextName.setText(product.getName());
            editTextValue.setText(String.valueOf(product.getValue()));
            id = product.getId();
        }
    }
    public void onClickBack(View v) {
        finish();
    }
    public void onClickCreateProduct(View v) {
        EditText editTextName = findViewById(R.id.editText_name);
        EditText editTextValue = findViewById(R.id.editText_value);

        String name = editTextName.getText().toString();
        Float value = Float.parseFloat(editTextValue.getText().toString());

        Product product = new Product(id, name, value);
            ProductDAO productDAO = new ProductDAO(getBaseContext());
            boolean saved = productDAO.save(product);
            if (saved) {
                finish();
            }
            else {
                Toast.makeText(ProductCreateActivity.this, "Erro ao salvar ", Toast.LENGTH_SHORT).show();
            }

        finish();
    }
    public void onClickDeleteProduct(View v) {
        EditText editTextName = findViewById(R.id.editText_name);
        EditText editTextValue = findViewById(R.id.editText_value);

        String name = editTextName.getText().toString();
        Float value = Float.parseFloat(editTextValue.getText().toString());

        Product product = new Product(id, name, value);
        ProductDAO productDAO = new ProductDAO(getBaseContext());
        int deleted = productDAO.delete(product);
        if (deleted>0){
            Toast.makeText(ProductCreateActivity.this, "Excluido " + deleted + " produto(s) com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(ProductCreateActivity.this, "Erro ao excluir produto ", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
