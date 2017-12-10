package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.Fragments.ProductsFragment;
import com.infnet.ads.projetodebloco.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView bigPizza;
    TextView averagePizza;
    TextView smallPizza;
    TextView meatPizza;
    TextView sweetPizza;

    TextView saltyPizza;
    TextView vegetarianPizza;
    String categorySelected;
    int idAlreadySelected;

    TableRow subCategoryLayout;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationViewLogin;

    ActionBarDrawerToggle toggle;
    ProductsFragment productsFragment;
    View previousCategoryView;
    View previousSubView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();


        navigationViewLogin = findViewById(R.id.nav_view_login);

        navigationViewLogin.setNavigationItemSelectedListener(this);

        if (!LogInActivity.logInOn) {

            navigationViewLogin.inflateMenu(R.menu.activity_main_drawer_logoff);

        } else {

            navigationViewLogin.inflateMenu(R.menu.activity_main_drawer_login);

        }

        idAlreadySelected = 0;

        productsFragment = new ProductsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frameContainerMain, productsFragment).commit();

        subCategoryLayout = findViewById(R.id.sub_category_row);


        //Category implementation

        bigPizza = findViewById(R.id.big_pizza);

        averagePizza = findViewById(R.id.average_pizza);

        smallPizza = findViewById(R.id.small_pizza);

        //Sub-category implementation

        meatPizza = findViewById(R.id.meat_pizza);

        sweetPizza = findViewById(R.id.sweet_pizza);

        saltyPizza = findViewById(R.id.salty_pizza);

        vegetarianPizza = findViewById(R.id.vegetarian_pizza);

        //Category listeners

        bigPizza.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                categorySelected = ((TextView) view).getText().toString();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));

                view.setEnabled(false);

                if (previousCategoryView != null) {
                    ((TextView) previousCategoryView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousCategoryView.setEnabled(true);
                }

                previousCategoryView = view;

                subCategoryLayout.setVisibility(View.VISIBLE);
                previousSubView = null;

                // In case the subCategory menu has been already displayed

                if (idAlreadySelected == R.id.meat_pizza) {
                    meatPizza.callOnClick();
                } else if (idAlreadySelected == R.id.sweet_pizza) {
                    sweetPizza.callOnClick();
                } else if (idAlreadySelected == R.id.salty_pizza) {
                    saltyPizza.callOnClick();
                } else if (idAlreadySelected == R.id.vegetarian_pizza) {
                    vegetarianPizza.callOnClick();
                }
            }

        });

        averagePizza.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                categorySelected = ((TextView) view).getText().toString();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));

                view.setEnabled(false);

                if (previousCategoryView != null) {
                    ((TextView) previousCategoryView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousCategoryView.setEnabled(true);
                }

                previousCategoryView = view;

                subCategoryLayout.setVisibility(View.VISIBLE);
                previousSubView = null;

                // In case the subCategory menu has been already displayed

                if (idAlreadySelected == R.id.meat_pizza) {
                    meatPizza.callOnClick();
                } else if (idAlreadySelected == R.id.sweet_pizza) {
                    sweetPizza.callOnClick();
                } else if (idAlreadySelected == R.id.salty_pizza) {
                    saltyPizza.callOnClick();
                } else if (idAlreadySelected == R.id.vegetarian_pizza) {
                    vegetarianPizza.callOnClick();
                }
            }
        });

        smallPizza.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                categorySelected = ((TextView) view).getText().toString();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));

                view.setEnabled(false);

                if (previousCategoryView != null) {
                    ((TextView) previousCategoryView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousCategoryView.setEnabled(true);
                }

                previousCategoryView = view;

                subCategoryLayout.setVisibility(View.VISIBLE);

                previousSubView = null;

                // In case the subCategory menu has been already displayed

                if (idAlreadySelected == R.id.meat_pizza) {
                    meatPizza.callOnClick();
                } else if (idAlreadySelected == R.id.sweet_pizza) {
                    sweetPizza.callOnClick();
                } else if (idAlreadySelected == R.id.salty_pizza) {
                    saltyPizza.callOnClick();
                } else if (idAlreadySelected == R.id.vegetarian_pizza) {
                    vegetarianPizza.callOnClick();
                }

            }
        });

        // Sub-category listeners

        meatPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idAlreadySelected = view.getId();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));
                view.setEnabled(false);

                if (previousSubView != null) {
                    ((TextView) previousSubView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousSubView.setEnabled(true);
                }

                previousSubView = view;

                productsFragment.createRecyclerView(categorySelected, ((TextView) view).getText().toString());
            }
        });

        sweetPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAlreadySelected = view.getId();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));
                view.setEnabled(false);

                if (previousSubView != null) {
                    ((TextView) previousSubView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousSubView.setEnabled(true);
                }

                previousSubView = view;

                productsFragment.createRecyclerView(categorySelected, ((TextView) view).getText().toString());
            }
        });

        saltyPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAlreadySelected = view.getId();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));
                view.setEnabled(false);

                if (previousSubView != null) {
                    ((TextView) previousSubView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousSubView.setEnabled(true);
                }

                previousSubView = view;

                productsFragment.createRecyclerView(categorySelected, ((TextView) view).getText().toString());
            }
        });

        vegetarianPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAlreadySelected = view.getId();

                ((TextView) view).setTextColor(getResources().getColor(R.color.selectedItem));
                view.setEnabled(false);

                if (previousSubView != null) {
                    ((TextView) previousSubView).setTextColor(getResources().getColor(R.color.colorSecondary));
                    previousSubView.setEnabled(true);
                }

                previousSubView = view;

                productsFragment.createRecyclerView(categorySelected, ((TextView) view).getText().toString());
            }
        });
    }


    @Override

    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }

    }


    @SuppressWarnings("StatementWithEmptyBody")

    @Override

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.nav_perfil) {

            Intent clientSettings = new Intent(getApplicationContext(), ClientSettingsActivity.class);

            startActivity(clientSettings);


        } else if (id == R.id.nav_historico) {

            Intent historicalActivity = new Intent(getApplicationContext(), HistoricalActivity.class);

            startActivity(historicalActivity);


        } else if (id == R.id.nav_entrar) {

            Intent logInActivity = new Intent(getApplicationContext(), LogInActivity.class);

            startActivity(logInActivity);


        } else if (id == R.id.nav_sair) {

            LogInActivity.logInOn = false;

            Intent logInActivity = new Intent(getApplicationContext(), LogInActivity.class);

            startActivity(logInActivity);

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cartIcon:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}