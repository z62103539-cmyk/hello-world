package com.example.simplenotepad;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    TextView tvBack, tvSave;
    EditText etTitle, etBody;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        tvBack = findViewById(R.id.tvBack);
        tvSave = findViewById(R.id.tvSave);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);

        position = getIntent().getIntExtra("position", -1);

        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");

        if (title != null) {
            etTitle.setText(title);
        }

        if (body != null) {
            etBody.setText(body);
        }

        tvBack.setOnClickListener(v -> finish());

        tvSave.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String body = etBody.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a note name", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = getSharedPreferences("SimpleNotes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int count = sp.getInt("count", 0);

        if (position == -1) {
            editor.putString("title_" + count, title);
            editor.putString("body_" + count, body);
            editor.putInt("count", count + 1);
        } else {
            editor.putString("title_" + position, title);
            editor.putString("body_" + position, body);
        }

        editor.apply();

        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}