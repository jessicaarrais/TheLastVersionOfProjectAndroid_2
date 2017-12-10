package com.infnet.ads.projetodebloco.Products;

import java.io.Serializable;

public class ProductOrdered implements Serializable{

    public String nameOrdered;
    public String descriptionOrdered;
    public String priceOrdered;
    public StringBuilder addOrdered  = new StringBuilder();
    public String cornerOrdered;
    public String pastaOrdered;
    public String psOrdered;

    public ProductOrdered() {}
}