package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    EditText edtTitle, edtDescription;
    Button btnCancel, btnSave;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Intent intent = getIntent();
        linearLayout = findViewById(R.id.btn_holder);
        edtTitle = findViewById(R.id.edit_edit_title);
        edtDescription = findViewById(R.id.edit_edit_description);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Setting buttons invisible
                btnCancel.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(linearLayout);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(edtTitle.getText().toString(), edtDescription.getText().toString());
                note.setId(intent.getIntExtra("id", 1));

                if(new NoteHandler(EditNote.this).update(note)){
                    Toast.makeText(EditNote.this, "Note updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditNote.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });

        edtTitle.setText(intent.getStringExtra("title"));
        edtDescription.setText(intent.getStringExtra("description"));


    }
}