package com.infnet.ads.projetodebloco.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.infnet.ads.projetodebloco.Manager.OperationScreen;
import com.infnet.ads.projetodebloco.R;

public class UpdateFragment extends Fragment implements FragmentComunication{

    private EditText txtId;
    private EditText txtName;
    private EditText txtPrice;
    private EditText txtDescription;

    private Spinner type;
    private Spinner subtype;
    private ArrayAdapter<CharSequence> typeAdapter;
    private ArrayAdapter<CharSequence> subtypeAdapter;

    private OperationScreen father;


    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        father = (OperationScreen) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update, container, false);

        txtId = view.findViewById(R.id.txtIDUpdate);
        txtName = view.findViewById(R.id.txtNameUpdate);
        txtPrice = view.findViewById(R.id.txtPriceUpdate);
        txtDescription = view.findViewById(R.id.txtDescriptionUpdate);

        type = view.findViewById(R.id.itemTypeUp);
        subtype = view.findViewById(R.id.itemSubtypeUp);

        typeAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.product_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(typeAdapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:case 1:case 2:

                        subtypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.product_subtype_pizza,
                                android.R.layout.simple_spinner_item);
                        break;

                    case 3:

                        subtypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.product_subtype_drinks,
                                android.R.layout.simple_spinner_item);
                        break;

                    case 4:

                        subtypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.product_subtype_icecream,
                                android.R.layout.simple_spinner_item);
                        break;
                }

                subtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                subtype.setAdapter(subtypeAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (((OperationScreen)getActivity()).getId() != null){
            txtId.setText(((OperationScreen) getActivity()).getId());
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public String getID() {
        if (txtId!= null){
            return txtId.getText().toString();
        }
        else return "error";
    }

    @Override
    public String getName() {
        if (txtName != null){
            return txtName.getText().toString();
        }
        else return "error";
    }

    @Override
    public String getPrice() {
        if (txtPrice != null){
            return txtPrice.getText().toString();
        }
        else return "error";
    }

    @Override
    public String getDescription() {
        if (txtDescription != null){
            return txtDescription.getText().toString();
        }
        else return "error";
    }

    public String getProductType() {
        if (type != null) {
            return type.getSelectedItem().toString();
        } else return "error";
    }

    public String getProductSubType(){
        if (subtype != null){
            return subtype.getSelectedItem().toString();
        } else return "error";
    }
}