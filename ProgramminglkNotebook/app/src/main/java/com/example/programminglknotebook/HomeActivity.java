package com.example.programminglknotebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.programminglknotebook.db.DBHelper;
import com.example.programminglknotebook.dto.Note;
import com.example.programminglknotebook.util.UtilityMethods;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NoteFragmentListner, CustomDialogFragmentListner {

    public static final int MODE_DEF = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_DEL = 2;
    public static final int MODE_EDIT = 3;

    String loggedUser;
    DBHelper db ;
    ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    NotesAdapter adapter;
    StaggeredGridLayoutManager layoutManager;
    List<Note> notesList = new ArrayList<>();

    private  FloatingActionButton btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loggedUser = getIntent().getStringExtra("USER_NAME");
        db = DBHelper.getInstance(this);

        initUI();
        retriveNotesFromDB();

        setClicklistners();
    }

    private void retriveNotesFromDB() {
        notesList.clear();
        GetNotesAsyncTask async = new GetNotesAsyncTask();
        async.execute(loggedUser)    ;
    }

    private void setClicklistners() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("MODE", MODE_ADD);
                bundle.putString("LOGGED_USER" , loggedUser);
                bundle.putParcelable("SELECTED_NOTE", new Note());

                Fragment fragment = new NoteFragment();
                ((NoteFragment)fragment).setListner(HomeActivity.this);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.note_fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                btn_add.setVisibility(View.GONE);
            }
        });
    }

    private void initUI() {
        btn_add = findViewById(R.id.btn_add_note);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setHasFixedSize(true);
        layoutManager =  new StaggeredGridLayoutManager(2 , LinearLayoutManager.VERTICAL);
        adapter = new NotesAdapter(notesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnNoteItemClickListner(new NotesAdapter.OnNoteClickListner() {
            @Override
            public void onNoteItemClick(int position) {
                System.out.println("test");
                CustomDialog dialog = new CustomDialog(position);
                dialog.setListner(HomeActivity.this);
                dialog.show(getSupportFragmentManager(), "CUSTOM_DIALOG");
            }
        });
    }

    @Override
    public void cancelButtonClicked() {
        btn_add.setVisibility(View.VISIBLE);
        retriveNotesFromDB();
    }

    @Override
    public void viewButtonClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("MODE", MODE_DEF);
        bundle.putParcelable("SELECTED_NOTE" , notesList.get(position));
        bundle.putString("LOGGED_USER" , loggedUser);

        Fragment fragment = new NoteFragment();
        ((NoteFragment) fragment).setListner(HomeActivity.this);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.note_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        btn_add.setVisibility(View.GONE);

    }

    @Override
    public void editClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("MODE", MODE_EDIT);
        bundle.putParcelable("SELECTED_NOTE" , notesList.get(position));
        bundle.putString("LOGGED_USER" , loggedUser);

        Fragment fragment = new NoteFragment();
        ((NoteFragment) fragment).setListner(HomeActivity.this);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.note_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        btn_add.setVisibility(View.GONE);
    }

    @Override
    public void deleteClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("MODE", MODE_DEL);
        bundle.putParcelable("SELECTED_NOTE" , notesList.get(position));
        bundle.putString("LOGGED_USER" , loggedUser);

        Fragment fragment = new NoteFragment();
        ((NoteFragment) fragment).setListner(HomeActivity.this);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.note_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        btn_add.setVisibility(View.GONE);
    }

    private class GetNotesAsyncTask extends AsyncTask<String, String, Cursor> {
        String uName = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Please wait...Retriving!");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Cursor doInBackground(String... strings) {
            uName = strings[0];
            Cursor cursor = null;
            try {
                cursor =   db.get_Notes(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            progressDialog.hide();
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    Note n = new Note();
                    n.setId(cursor.getInt(0));
                    n.setHeader(cursor.getString(1));
                    n.setContent(cursor.getString(2));
                    n.setUserName(cursor.getString(3));

                    notesList.add(n);

                }
                adapter.notifyDataSetChanged();
            }else{
                UtilityMethods.showMessage("Warning ", "No notes for you!!", HomeActivity.this);
            }
        }
    }



        @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Do You Want to Logout?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HomeActivity.this, LoginSignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}