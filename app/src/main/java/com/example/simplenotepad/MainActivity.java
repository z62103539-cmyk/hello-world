package com.example.simplenotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNotes;
    Button btnAdd;
    ArrayList<String> titles;
    ArrayList<String> bodies;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.rvNotes);
        btnAdd = findViewById(R.id.btnAdd);

        titles = new ArrayList<>();
        bodies = new ArrayList<>();

        loadNotes();

        adapter = new NoteAdapter(this, titles, bodies);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            intent.putExtra("position", -1);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        titles.clear();
        bodies.clear();
        loadNotes();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void loadNotes() {
        SharedPreferences sp = getSharedPreferences("SimpleNotes", MODE_PRIVATE);

        int count = sp.getInt("count", 0);

        for (int i = 0; i < count; i++) {
            String title = sp.getString("title_" + i, "");
            String body = sp.getString("body_" + i, "");

            if (!title.isEmpty()) {
                titles.add(title);
                bodies.add(body);
            }
        }
    }
}