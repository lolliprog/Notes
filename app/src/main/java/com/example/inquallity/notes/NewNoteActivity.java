package com.example.inquallity.notes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inquallity.notes.notesstorage.data.NotesContract;
import com.example.inquallity.notes.notesstorage.data.NotesDbHelper;

/**
 * Created by Alexandrova Olga on 04-May-17.
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
        insertNotes();
    }

    private void finishSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    private void insertNotes() {
        EditText etNewNote = (EditText) findViewById(R.id.etNewNote);

        String note = etNewNote.getText().toString().trim();
        NotesDbHelper mNotesDbHelper = new NotesDbHelper(this);
        SQLiteDatabase db = mNotesDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContract.NewNote.COLUMN_NOTETEXT, note);
        long newRowId = db.insert(NotesContract.NewNote.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Some mistake!", Toast.LENGTH_SHORT).show();
        } else {
            finishSuccess();
            Toast.makeText(this, "You added note #" + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


}
