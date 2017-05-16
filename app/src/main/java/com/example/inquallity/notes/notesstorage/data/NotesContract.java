package com.example.inquallity.notes.notesstorage.data;

import android.provider.BaseColumns;

/**
 * Created by Alexandrova Olga on 16-May-17.
 */

public final class NotesContract {

    private NotesContract(){};

    public static final class NewNote implements BaseColumns{
        public final static String TABLE_NAME = "notes";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NOTETEXT = "notetext";

    }
}
