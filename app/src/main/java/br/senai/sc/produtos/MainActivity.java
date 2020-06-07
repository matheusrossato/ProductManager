package br.senai.sc.produtos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.senai.sc.produtos.database.ProductDAO;
import br.senai.sc.produtos.models.Product;

public class MainActivity extends AppCompatActivity {
/*
    private final int REQUEST_CODE_CREATE_PRODUCT = 1;
    private final int RESULT_CODE_CREATE_PRODUCT = 10;
    private final int REQUEST_CODE_EDIT_PRODUCT = 2;
    private final int RESULT_CODE_EDIT_PRODUCT = 20;
    private final int RESULT_CODE_DELETE_PRODUCT = 30;
*/
    private ListView listViewProducts;
    private ArrayAdapter<Product> adapterProducts;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");

        listViewProducts = findViewById(R.id.listView_products);
        ArrayList<Product> products = new ArrayList<Product>();
        defineOnClickListenerListView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ProductDAO productDAO = new ProductDAO(getBaseContext());
        adapterProducts = new ArrayAdapter<Product>(MainActivity.this,
                android.R.layout.simple_list_item_1, productDAO.list());
        listViewProducts.setAdapter(adapterProducts);
    }

    private void defineOnClickListenerListView(){
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product productClicked = adapterProducts.getItem(position);
                Intent intent = new Intent(MainActivity.this, ProductCreateActivity.class);
                intent.putExtra("editProduct", productClicked );
                //startActivityForResult(intent,REQUEST_CODE_EDIT_PRODUCT);
                startActivity(intent);
            }
        });

    }

    public void onClickCreateProduct(View v) {
        Intent intent = new Intent(MainActivity.this, ProductCreateActivity.class);
        startActivity(intent);
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE_EDIT_PRODUCT && resultCode==RESULT_CODE_EDIT_PRODUCT){
            Product editedProduct = (Product) data.getExtras().getSerializable("editProduct");
            for(int i = 0; i < adapterProducts.getCount();i++) {
                Product product = adapterProducts.getItem(i);
                if(product.getId() == editedProduct.getId()) {
                    adapterProducts.remove(product);
                    adapterProducts.insert(editedProduct, i);
                    break;
                }
            }
            Toast.makeText(MainActivity.this,"Item editado com sucesso!", Toast.LENGTH_LONG).show();
        }
        else if (requestCode==REQUEST_CODE_EDIT_PRODUCT && resultCode==RESULT_CODE_DELETE_PRODUCT){
            Product deleteProduct= (Product) data.getExtras().getSerializable("deleteProduct");
            for(int i = 0; i < adapterProducts.getCount();i++) {
                Product product = adapterProducts.getItem(i);
                if(product.getId()==deleteProduct.getId()) {
                    adapterProducts.remove(product);
                    Toast.makeText(MainActivity.this, " Item excluido com sucesso!", Toast.LENGTH_LONG).show();
                    break;
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    } */
}
