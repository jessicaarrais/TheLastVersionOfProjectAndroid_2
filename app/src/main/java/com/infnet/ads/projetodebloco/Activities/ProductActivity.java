package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.infnet.ads.projetodebloco.Products.Product;
import com.infnet.ads.projetodebloco.Products.ProductOrdered;
import com.infnet.ads.projetodebloco.R;

public class ProductActivity extends AppCompatActivity {

    //PRODUCT ORDERED
    ProductOrdered productOrdered;
    public static List<ProductOrdered> productOrderedList = new ArrayList<>();
    //GET INTENT
    Product product;
    int tempPrice;
    int priceCorner;
    // DISPLAYED TEXTS
    TextView name;
    TextView description;
    TextView productType;
    TextView price;
    //INT HALF
    RadioGroup radioGroupInthalf;
    // ADD
    TextView addProduct;
    CardView addListProduct;
    // CORNER
    TextView cornerProduct;
    CardView cornerListProduct;
    RadioGroup radioGroupCorner;
    //PASTA
    TextView pastaProduct;
    CardView pastaListProducts;
    RadioGroup radioGroupPasta;
    //PS
    TextView psProduct;
    CardView psBoxProduct;
    EditText psTextProduct;
    //FINISH
    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // DISPLAYED TEXTS
        name = findViewById(R.id.name_product);
        description = findViewById(R.id.description_product);
        productType = findViewById(R.id.type_product);
        price = findViewById(R.id.price_product);

        //PRODUCT ORDERED - change!!!
        productOrdered = new ProductOrdered();

        //GET INTENT
        Intent it = getIntent();
        product = (Product) it.getSerializableExtra("product");

        tempPrice = Integer.parseInt(product.getPrice());

        name.setText("Pizza de "+ product.getName());
        description.setText(product.getDescription());
        price.setText(product.getPrice() + ",00");
        productType.setText(product.getProductType());

        productOrdered.nameOrdered = product.getName();
        productOrdered.descriptionOrdered = product.getDescription();
        productOrdered.priceOrdered = String.valueOf(tempPrice);

        //INTHALF
        radioGroupInthalf = findViewById(R.id.radio_group_inthalf);

        // ADD
        addProduct = findViewById(R.id.add_product);
        addListProduct = findViewById(R.id.add_list_product);
        // CORNER
        cornerProduct = findViewById(R.id.corner_product);
        cornerListProduct = findViewById(R.id.corner_list_product);
        radioGroupCorner = findViewById(R.id.radio_group_corner);
        //PASTA
        pastaProduct = findViewById(R.id.pasta_product);
        pastaListProducts = findViewById(R.id.pasta_list_product);
        radioGroupPasta = findViewById(R.id.radio_group_pasta);
        //PS
        psProduct = findViewById(R.id.ps_product);
        psBoxProduct = findViewById(R.id.ps_box_product);
        psTextProduct = findViewById(R.id.ps_text_product);
        //FINISH
        addToCart = findViewById(R.id.add_to_cart_product);

        //INTHALF
        radioGroupInthalf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioSelected = radioGroup.getCheckedRadioButtonId();

                switch (radioSelected) {
                    case R.id.integer_product:
                        //save "Inteira" on object
                        break;
                    case R.id.half_product:
                        // save "Meio a meio" on object;
                }
            }
        });

        // ADD
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addListProduct.getVisibility() == View.GONE) {
                    addListProduct.setVisibility(View.VISIBLE);
                } else {
                    addListProduct.setVisibility(View.GONE);
                }
            }
        });

        // CORNER
        cornerProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cornerListProduct.getVisibility() == View.GONE) {
                    cornerListProduct.setVisibility(View.VISIBLE);
                } else {
                    cornerListProduct.setVisibility(View.GONE);
                }
            }
        });

        radioGroupCorner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioSelected = radioGroup.getCheckedRadioButtonId();

                switch (radioSelected) {
                    case R.id.catupiry_corner:
                        productOrdered.cornerOrdered = "Catupiry";
                        if (priceCorner != 0) {
                            tempPrice -= priceCorner;
                        }
                        priceCorner = 7;
                        tempPrice += priceCorner;
                        price.setText(String.valueOf(tempPrice) + ",00");
                        break;

                    case R.id.cheddar_corner:
                        productOrdered.cornerOrdered = "Cheddar";
                        if (priceCorner != 0) {
                            tempPrice -= priceCorner;
                        }
                        priceCorner = 6;
                        tempPrice += priceCorner;
                        price.setText(String.valueOf(tempPrice) + ",00");
                        break;

                    case R.id.four_cheese_corner:
                        productOrdered.cornerOrdered = "4 Queijos";
                        if (priceCorner != 0) {
                            tempPrice -= priceCorner;
                        }
                        priceCorner = 8;
                        tempPrice += priceCorner;
                        price.setText(String.valueOf(tempPrice) + ",00");
                        break;

                    case R.id.nutella_corner:
                        productOrdered.cornerOrdered = "Nutella";
                        if (priceCorner != 0) {
                            tempPrice -= priceCorner;
                        }
                        priceCorner = 11;
                        tempPrice += priceCorner;
                        price.setText(String.valueOf(tempPrice) + ",00");
                        break;

                    case R.id.without_corner:
                        productOrdered.cornerOrdered = "Sem borda";
                        if (priceCorner != 0) {
                            tempPrice -= priceCorner;
                        }
                        priceCorner = 0;
                        price.setText(String.valueOf(tempPrice) + ",00");
                        break;
                }
                productOrdered.priceOrdered = String.valueOf(tempPrice);
            }
        });

        //PASTA
        pastaProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pastaListProducts.getVisibility() == View.GONE) {
                    pastaListProducts.setVisibility(View.VISIBLE);
                } else {
                    pastaListProducts.setVisibility(View.GONE);
                }
            }
        });

        radioGroupPasta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioSelected = radioGroup.getCheckedRadioButtonId();

                switch (radioSelected) {
                    case R.id.pan_pasta:
                        productOrdered.pastaOrdered = "Pan";
                        break;
                    case R.id.thin_pasta:
                        productOrdered.pastaOrdered = "Fina";
                        break;
                    case R.id.traditional_pasta:
                        productOrdered.pastaOrdered = "Tradicional";
                        break;
                    case R.id.integral_pasta:
                        productOrdered.pastaOrdered = "Integral";
                        break;
                }
            }
        });

        //PS
        psProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (psBoxProduct.getVisibility() == View.GONE) {
                    psBoxProduct.setVisibility(View.VISIBLE);
                } else {
                    psBoxProduct.setVisibility(View.GONE);
                }
            }
        });



        //FINISH
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.hasItemCart = true;

                if (!LogInActivity.logInOn) {
                    Intent loginActivity = new Intent(getApplicationContext(), LogInActivity.class);
                    loginActivity.putExtra("fromProduct", productOrdered);
                    startActivity(loginActivity);
                } else {
                    productOrdered.psOrdered = psTextProduct.getText().toString();

                    ProductActivity.productOrderedList.add(productOrdered);
                    ++ CartActivity.items;
                    CartActivity.total += tempPrice;

                    Intent cartActivity = new Intent(getApplicationContext(), CartActivity.class);
                    cartActivity.putExtra("confirmation", (Serializable) productOrderedList);
                    startActivity(cartActivity);
                }
            }
        });
    }

    public void onCheckBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.garlic:
                int g = productOrdered.addOrdered.indexOf("Alho ");
                if (checked) {
                    productOrdered.addOrdered.append("Alho ");
                    tempPrice += 2;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(g, g + "Alho ".length());
                        tempPrice -= 2;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;

            case R.id.catupiry:
                int t = productOrdered.addOrdered.indexOf("Catupiry ");
                if (checked) {
                    productOrdered.addOrdered.append("Catupiry ");
                    tempPrice += 4;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(t, t + "Catupiry ".length());
                        tempPrice -= 4;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;

            case R.id.champignon:
                int c = productOrdered.addOrdered.indexOf("Champignon ");
                if (checked) {
                    productOrdered.addOrdered.append("Champignon ");
                    tempPrice += 3;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(c, c + "Champignon ".length());
                        tempPrice -= 3;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;

            case R.id.eggs:
                int o = productOrdered.addOrdered.indexOf("Ovos ");
                if (checked) {
                    productOrdered.addOrdered.append("Ovos ");
                    tempPrice += 2;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(o, o + "Ovos ".length());
                        tempPrice -= 2;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;

            case R.id.palmitto:
                int p = productOrdered.addOrdered.indexOf("Palmito ");
                if (checked) {
                    productOrdered.addOrdered.append("Palmito ");
                    tempPrice += 5;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(p, p + "Palmito".length());
                        tempPrice -= 5;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;

            case R.id.tomato:
                int a = productOrdered.addOrdered.indexOf("Tomate ");
                if (checked) {
                    productOrdered.addOrdered.append("Tomate ");
                    tempPrice += 5;
                } else {
                    if (tempPrice > Integer.parseInt(product.getPrice())) {
                        productOrdered.addOrdered.delete(a, a + "Tomato ".length());
                        tempPrice -= 5;
                    }
                }
                price.setText(String.valueOf(tempPrice) + ",00");
                break;
        }
        productOrdered.priceOrdered = String.valueOf(tempPrice);
    }
}