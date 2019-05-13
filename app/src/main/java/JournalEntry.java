import android.provider.MediaStore;

public class JournalEntry {
    private String mJournalTitle;
    private String mJournalContents;
    private MediaStore.Images.Media mJournalMedia;

    public String getJournalTitle() { return mJournalTitle;  }
    public void setJournalTitle(String s) { this.mJournalTitle = s; }

    public String getJournalContents() { return mJournalContents; }
    public void setJournalContents(String s) {  this.mJournalContents = s; }

    public MediaStore.Images.Media getJournalMedia() { return mJournalMedia;}
    public void setJournalMedia(MediaStore.Images.Media m) { this.mJournalMedia = m; }
}
