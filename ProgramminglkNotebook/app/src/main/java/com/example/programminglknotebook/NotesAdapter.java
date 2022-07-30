package com.example.programminglknotebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programminglknotebook.dto.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Note>  notesList =  new ArrayList<>();
    private OnNoteClickListner noteClickListner;

    public  NotesAdapter(List<Note> notes){
        this.notesList = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v = inflator.inflate(R.layout.notes_rv_item_view, parent, false);
        NotesViewHolder vh =  new NotesViewHolder(v, noteClickListner );
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
            Note note = notesList.get(position);
        holder.tv_content.setText(note.getContent());
        holder.tv_header.setText(note.getHeader());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }



    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView tv_header;
        TextView tv_content;
        public NotesViewHolder(@NonNull View itemView, OnNoteClickListner listner) {
            super(itemView);
            tv_header = itemView.findViewById(R.id.tv_header_item);
            tv_content = itemView.findViewById(R.id.tv_content_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner != null){
                        int position =  getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onNoteItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public interface OnNoteClickListner{
        void onNoteItemClick(int position);
    }

    public void setOnNoteItemClickListner(OnNoteClickListner listner){
        noteClickListner = listner;
    }
}
