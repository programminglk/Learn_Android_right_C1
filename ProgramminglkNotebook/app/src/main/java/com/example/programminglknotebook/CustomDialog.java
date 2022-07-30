package com.example.programminglknotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment {
    int position = -1;
    private CustomDialogFragmentListner customDialogFragmentListner;

    TextView tv_view;
    TextView tv_edit;
    TextView tv_delete;

    public CustomDialog(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflator = getActivity().getLayoutInflater();
        View v = inflator.inflate(R.layout.fragment_custom_dialog, null);

        tv_view = v.findViewById(R.id.btn_view);
        tv_edit = v.findViewById(R.id.btn_edit);
        tv_delete = v.findViewById(R.id.btn_delete);

        setOnClickListners();
        builder.setView(v);
        return builder.create();
    }

    private void setOnClickListners() {
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogFragmentListner.editClicked(position);
                dismiss();
            }
        });

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogFragmentListner.deleteClicked(position);
                dismiss();
            }
        });

        tv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogFragmentListner.viewButtonClicked(position);
                dismiss();
            }
        });
    }

    public void setListner(CustomDialogFragmentListner listner){
        customDialogFragmentListner = listner;
    }

}
