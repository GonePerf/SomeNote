import SQL.Note;
import SQL.Notebook;

import java.util.List;

public class NoteDAO extends DAO<Note,Long> {
    public Note findById(Long aLong) {
        openCurrentSession();
        Note note = (Note) getCurrentSession().get(Note.class,aLong);
        closeCurrentSession();
        return note;
    }

    public List<Note> findAll() {
        openCurrentSession();
        List<Note> notes = (List<Note>) getCurrentSession().createQuery("from Note").list();
        closeCurrentSession();
        return notes;
    }
    public List<Note> findNotesForNotebook(Notebook notebook){
        openCurrentSession();
        List<Note> notes = (List<Note>) getCurrentSession().createQuery("from Note where notebook_id='"+notebook.getNotebook_id()+"'").list();
        closeCurrentSession();
        return notes;
    }


    public String getNoteName(Long note_id){
        openCurrentSession();
        Note note = (Note) getCurrentSession().get(Note.class,note_id);
        closeCurrentSession();
        return note.getName();
    }
    public void addNote(Note note){
        openCurrentSessionwithTransaction();
        getCurrentSession().save(note);
        closeCurrentSessionwithTransaction();
    }
}
