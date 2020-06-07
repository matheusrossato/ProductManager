package br.senai.sc.produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.senai.sc.produtos.models.Product;

public class ProductCreateActivity extends AppCompatActivity {

    private final int RESULT_CODE_CREATE_PRODUCT = 10;
    private final int RESULT_CODE_EDIT_PRODUCT = 20;
    private final int RESULT_CODE_DELETE_PRODUCT = 30;
    private boolean editing = false;
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
            editing = true;
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
        Intent intent = new Intent();
        if (editing) {
            intent.putExtra("editProduct", product);
            setResult(RESULT_CODE_EDIT_PRODUCT, intent);
        }
        else {
            intent.putExtra("createProduct", product);
            setResult(RESULT_CODE_CREATE_PRODUCT, intent);
        }
        finish();
    }
    public void onClickDeleteProduct(View v) {
        EditText editTextName = findViewById(R.id.editText_name);
        EditText editTextValue = findViewById(R.id.editText_value);

        String name = editTextName.getText().toString();
        Float value = Float.parseFloat(editTextValue.getText().toString());

        Product product = new Product(id, name, value);
        Intent intent = new Intent();
        if (editing) {
            intent.putExtra("deleteProduct", product);
            setResult(RESULT_CODE_DELETE_PRODUCT, intent);
        }
        finish();
    }
}
