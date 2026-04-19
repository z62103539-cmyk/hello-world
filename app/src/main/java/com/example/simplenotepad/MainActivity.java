package com.example.simplenotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        Button btnAddNote = findViewById(R.id.btn_add_note);
        if (btnAddNote != null) {
            btnAddNote.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tvDisplay = findViewById(R.id.tv_note_display);
        if (tvDisplay != null) {
            SharedPreferences sharedPreferences = getSharedPreferences("SimpleNoteData", MODE_PRIVATE);
            String title = sharedPreferences.getString("note_title", "No saved notes yet");
            String body = sharedPreferences.getString("note_body", "");

            if (!title.equals("No saved notes yet")) {
                tvDisplay.setText("Latest Note:\n\n" + title + "\n" + body);
                tvDisplay.setTextColor(Color.BLACK);
            } else {
                tvDisplay.setText(title);
                tvDisplay.setTextColor(Color.GRAY);
            }
        }
    }
}