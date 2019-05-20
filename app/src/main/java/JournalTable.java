import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class JournalTable {

    public static final String TABLE_NAME = "journalentry";
    public static final String KEY_JOURNAL_TITLE = "journaltitle";
    public static final String KEY_JOURNAL_CONTENTS = "journalconents";

    public static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME
            + " (" + KEY_JOURNAL_TITLE + " integer primary key autoincrement, "
            + KEY_JOURNAL_CONTENTS + " string not null"
             + ");";


    public static void insert(SQLiteDatabase db, JournalEntry j){
        ContentValues values = new ContentValues();
        values.put(KEY_JOURNAL_CONTENTS, j.getJournalContents());

        values.put(KEY_JOURNAL_TITLE, j.getJournalTitle());

        db.insert(TABLE_NAME, null, values);
    }

    public static ArrayList<JournalEntry> selectAll(SQLiteDatabase db){
        ArrayList<JournalEntry> results = new ArrayList<JournalEntry>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if(c != null){
            c.moveToFirst();
            while (!c.isAfterLast()){
                JournalEntry j = createFromCursor(c);
            }
        }

        return results;
    }

    public static JournalEntry createFromCursor (Cursor c){
        if (c == null || c.isAfterLast() || c.isBeforeFirst()){
            return null;
        } else {
            JournalEntry j = new JournalEntry();
            j.setJournalTitle(c.getString(c.getColumnIndex(KEY_JOURNAL_TITLE)));
            j.setJournalContents(c.getString(c.getColumnIndex(KEY_JOURNAL_CONTENTS)));
            return j;
        }
    }

}
