package com.example.programminglknotebook;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.programminglknotebook.db.DBHelper;
import com.example.programminglknotebook.dto.Note;
import com.example.programminglknotebook.util.UtilityMethods;

public class NoteFragment extends Fragment  {
    private ProgressDialog progressDialog;


    private int mode;
    private Note selctedNote;
    private String user;
    private DBHelper db;

    Button btn_add_note;
    Button btn_cancel;
    Button btn_delete;
    Button btn_edit;
    EditText etHeader;
    EditText etContent;

    NoteFragmentListner fragmentListner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        mode = getArguments().getInt("MODE");
        selctedNote = getArguments().getParcelable("SELECTED_NOTE");
        user = getArguments().getString("LOGGED_USER");
        db = DBHelper.getInstance(getActivity());

        return inflater.inflate(R.layout.fragment_note, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_add_note = view.findViewById(R.id.btn_add);
        btn_cancel =  view.findViewById(R.id.btn_cancel);
        btn_delete =  view.findViewById(R.id.btn_delete);
        btn_edit =  view.findViewById(R.id.btn_edit);
        etHeader=  view.findViewById(R.id.et_header);
        etContent=  view.findViewById(R.id.et_content);

        if(mode == HomeActivity.MODE_ADD ){
            btn_delete.setVisibility(View.GONE);
            btn_edit.setVisibility(View.GONE);
        }else if(mode == HomeActivity.MODE_DEL){
            btn_add_note.setVisibility(View.GONE);
            btn_edit.setVisibility(View.GONE);
        }else if(mode == HomeActivity.MODE_EDIT){
            btn_add_note.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
        }else if(mode == HomeActivity.MODE_DEF){
            btn_add_note.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
            btn_edit.setVisibility(View.GONE);
        }
        setonClickListners();
        updateUI();

    }

    private void updateUI() {
        if(selctedNote != null){
            etHeader.setText(selctedNote.getHeader());
            etContent.setText(selctedNote.getContent());
        }
    }

    private void setonClickListners() {
        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputsAndInsertToDB();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                fragmentListner.cancelButtonClicked();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSelectedNote();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSelectedNote();
            }
        });
    }

    private void deleteSelectedNote() {
        DeleteAsyncTask asyncTask = new DeleteAsyncTask();
        asyncTask.execute(selctedNote);
    }

    private void editSelectedNote() {
        EditAsyncTask async = new EditAsyncTask();
        selctedNote.setHeader(etHeader.getText().toString().trim());
        selctedNote.setContent(etContent.getText().toString().trim());
        async.execute(selctedNote);
    }

    private void validateInputsAndInsertToDB() {
        String header = etHeader.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if(header.isEmpty() || content.isEmpty()){
            UtilityMethods.showMessage("Error", "Header and Content ,both are required!!", getContext());
        }else{
            Note noteToAdd = new Note();
            noteToAdd.setContent(content);
            noteToAdd.setHeader(header);
            noteToAdd.setUserName(user);

            InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
            insertAsyncTask.execute(noteToAdd);


        }
    }



    public void setListner(NoteFragmentListner listner) {
        fragmentListner = listner;
    }

    private class InsertAsyncTask extends AsyncTask<Note, String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...Inserting");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Boolean doInBackground(Note... note) {
            boolean isertNoteSuccess = false;
            try {
                isertNoteSuccess = db.insert_Note(note[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isertNoteSuccess;
        }
        @Override
        protected void onPostExecute(Boolean isertNoteSuccess) {
            super.onPostExecute(isertNoteSuccess);
            progressDialog.hide();
            if(isertNoteSuccess) {
                getActivity().getSupportFragmentManager().popBackStack();
                fragmentListner.cancelButtonClicked();
                UtilityMethods.showMessage("Success", "NoteAdded", getContext());
            }else {
                UtilityMethods.showMessage("Error", "Note was not added", getContext());
            }
        }
    }

    private class EditAsyncTask extends AsyncTask<Note, String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...Updating");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Boolean doInBackground(Note... note) {
            boolean updateNoteSuccess = false;
            try {
                updateNoteSuccess =  db.update_Notes(note[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return updateNoteSuccess;
        }
        @Override
        protected void onPostExecute(Boolean updateNoteSuccess) {
            super.onPostExecute(updateNoteSuccess);
            progressDialog.hide();
            if(updateNoteSuccess) {
                getActivity().getSupportFragmentManager().popBackStack();
                fragmentListner.cancelButtonClicked();
                UtilityMethods.showMessage("Success", "Note Updated", getContext());
            }else {
                UtilityMethods.showMessage("Error", "Note was not Updated", getContext());
            }
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note, String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...Deleting");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Boolean doInBackground(Note... note) {
            boolean dltNoteSuccess = false;
            try {
                dltNoteSuccess =  db.delete_Notes(note[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dltNoteSuccess;
        }
        @Override
        protected void onPostExecute(Boolean dltNoteSuccess) {
            super.onPostExecute(dltNoteSuccess);
            progressDialog.hide();
            if(dltNoteSuccess) {
                //UtilityMethods.showMessage("Success", "Note deleted", getContext());
                getActivity().getSupportFragmentManager().popBackStack();
                fragmentListner.cancelButtonClicked();
                UtilityMethods.showMessage("Success", "Note deleted", getContext());
            }else {
                UtilityMethods.showMessage("Error", "Note was not deleted", getContext());
            }
        }
    }

}
