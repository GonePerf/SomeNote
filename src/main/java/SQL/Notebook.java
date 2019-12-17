package SQL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notebooks")
public class Notebook implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "notebook_id")
    private long notebook_id;       //Primary key
    @Column(name = "user_id")
    private long user_id;           //Foregin key

    @Override
    public String toString() {
        return name;
    }

    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "notebook_id")
    private List<Note> notes;           //List of topics in a notebook

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
    public void addNote(Note note){
        notes.add(note);
    }
    public Notebook(){}

    public long getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(long notebook_id) {
        this.notebook_id = notebook_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
