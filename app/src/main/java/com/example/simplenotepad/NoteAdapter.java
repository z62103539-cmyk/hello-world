package com.example.simplenotepad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    Context context;
    ArrayList<String> titles;
    ArrayList<String> bodies;

    public NoteAdapter(Context context, ArrayList<String> titles, ArrayList<String> bodies) {
        this.context = context;
        this.titles = titles;
        this.bodies = bodies;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tvTitle.setText(titles.get(position));
        holder.tvBody.setText(bodies.get(position));

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditNoteActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("title", titles.get(position));
            intent.putExtra("body", bodies.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvBody;
        Button btnEdit;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}