package com.infnet.ads.projetodebloco.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.infnet.ads.projetodebloco.Manager.OperationScreen;
import com.infnet.ads.projetodebloco.R;

public class RemoveFragment extends Fragment implements FragmentComunication {

    private EditText txtId;
    private OperationScreen father;


    public RemoveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove, container, false);

        txtId = view.findViewById(R.id.txtIDRemove);

        return view;
    }

    @Override
    public String getID() {
        if (txtId != null) {
            return txtId.getText().toString();
        }
        else return "error";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPrice() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getProductType() {
        return null;
    }

    @Override
    public String getProductSubType() {
        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        father = (OperationScreen) context;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
