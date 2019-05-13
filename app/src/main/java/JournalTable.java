import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class JournalTable {

    public static final String TABLE_NAME = "journalentry";
    public static final String KEY_JOURNAL_TITLE = "journaltitle";
    public static final String KEY_JOURNAL_CONTENTS = "journalconents";
    public static final String KEY_JOURNAL_MEDIA = "journalmedia";

    public static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME
            + " (" + KEY_JOURNAL_TITLE + " integer primary key autoincrement, "
            + KEY_JOURNAL_CONTENTS + " string not null, "
            + KEY_JOURNAL_MEDIA + " string not null " + ");";


    public static void insert(SQLiteDatabase db, JournalEntry j){
        ContentValues values = new ContentValues();
        values.put(KEY_JOURNAL_CONTENTS, j.getJournalContents());

        values.put(KEY_JOURNAL_MEDIA, j.getJournalMedia());

        values.put(KEY_JOURNAL_TITLE, j.getJournalTitle());

        db.insert(TABLE_NAME, null, values);
    }
}
