package com.example.simplenotepad;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        EditText etTitle = findViewById(R.id.et_note_title);
        EditText etBody = findViewById(R.id.et_note_body);
        Button btnSave = findViewById(R.id.btn_save_note);

        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                String title = etTitle.getText().toString().trim();
                String body = etBody.getText().toString().trim();

                if (!title.isEmpty()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("SimpleNoteData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("note_title", title);
                    editor.putString("note_body", body);
                    editor.apply();

                    Toast.makeText(this, "Note Saved to Device!", Toast.LENGTH_SHORT).show();


                    finish();
                } else {
                    Toast.makeText(this, "Please enter a title before saving", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}