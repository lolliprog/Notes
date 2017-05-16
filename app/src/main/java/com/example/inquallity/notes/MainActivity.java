package com.example.inquallity.notes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inquallity.notes.notesstorage.data.NotesContract;
import com.example.inquallity.notes.notesstorage.data.NotesDbHelper;

import java.util.Locale;

/**
 * Created by Alexandrova Olga on 03-May-17.
 */

public class MainActivity extends AppCompatActivity {

    private NotesDbHelper mNotesDbHelper;

    static final private int COUNT = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        Button bAddNewNote = (Button) findViewById(R.id.bAddNewNote);
        bAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, COUNT);
            }
        });

        mNotesDbHelper = new NotesDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mNotesDbHelper.getReadableDatabase();

        String[] projection = {
                NotesContract.NewNote._ID, NotesContract.NewNote.COLUMN_NOTETEXT
        };

        Cursor cursor = db.query(NotesContract.NewNote.TABLE_NAME, projection, null, null, null, null, null);

        final TextView tvNewAddedNote = (TextView) findViewById(R.id.tvNewAddedNote);
        final TextView tvNoNotes = (TextView) findViewById(R.id.tvNoNotes);
        tvNewAddedNote.setText(null);
        tvNoNotes.setText(null);

        try {
            int idColumnIndex = cursor.getColumnIndex(NotesContract.NewNote._ID);
            int noteTextColumnIndex = cursor.getColumnIndex(NotesContract.NewNote.COLUMN_NOTETEXT);

            if (cursor.moveToFirst()) {
                tvNoNotes.setText(R.string.tv_add_next);
                final StringBuilder builder = new StringBuilder();
                do {
                    final int currentID = cursor.getInt(idColumnIndex);
                    final String currentNote = cursor.getString(noteTextColumnIndex);
                    if (currentID > 0) {
                        final String noteString = String.format(Locale.getDefault(), "%d.%s", currentID, currentNote);
                        builder.append(noteString).append("\n\n");
                    } else {
                        builder.append(R.string.will_be).append("\n\n");
                    }
                } while (cursor.moveToNext());
                tvNewAddedNote.setText(builder.toString());
            } else {
                tvNoNotes.setText(R.string.add_the_new_one);
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onAnimationClick(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.startAnimation(new ViewAnimation());
    }

    public void onDelAllClick(View view) {
        deleteAllNotes();
    }

    private void deleteAllNotes() {
        TextView tvNewAddedNote = (TextView) findViewById(R.id.tvNewAddedNote);

        String note = tvNewAddedNote.getText().toString().trim();
        NotesDbHelper mNotesDbHelper = new NotesDbHelper(this);
        SQLiteDatabase db = mNotesDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContract.NewNote.COLUMN_NOTETEXT, note);
        long newRowId = db.delete(NotesContract.NewNote.TABLE_NAME, null, null);

        if (newRowId == -1) {
            Toast.makeText(this, "Some mistake!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You deleted all - " + newRowId + " notes", Toast.LENGTH_SHORT).show();
            displayDatabaseInfo();
        }
    }


    public class ViewAnimation extends Animation {
        int centerX, centerY;

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(5000);
            setFillAfter(true);
            setInterpolator(new LinearInterpolator());
            centerX = width / 2;
            centerY = height / 2;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final Matrix matrix = t.getMatrix();
            matrix.setScale(interpolatedTime, interpolatedTime);
        }
    }
}
