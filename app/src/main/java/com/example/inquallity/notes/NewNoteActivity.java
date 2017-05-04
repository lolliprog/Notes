package com.example.inquallity.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Inquallity on 04-May-17.
 */

public class NewNoteActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_mew_note);
    }

    public void onClick(View view) {
        saveNewNote();
    }

    public void saveNewNote() {
        EditText etNewNote = (EditText) findViewById(R.id.etNewNote);
//        Intent intent = new Intent(NewNoteActivity.this, MainActivity.class);
//        intent.putExtra("newNote", etNewNote.getText().toString());
//        startActivity(intent);
        Intent intent = new Intent();
        intent.putExtra("newNote", etNewNote.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
