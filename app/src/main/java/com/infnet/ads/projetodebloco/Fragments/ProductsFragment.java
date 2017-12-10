package com.infnet.ads.projetodebloco.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infnet.ads.projetodebloco.Products.RecyclerViewFactory;
import com.infnet.ads.projetodebloco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view_generic);

        return view;
    }

    public void createRecyclerView(String category, String subCategory){


        new RecyclerViewFactory(getActivity(), recyclerView, category, subCategory);
    }

}
