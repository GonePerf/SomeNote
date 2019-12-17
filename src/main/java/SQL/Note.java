package SQL;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;

@Entity
@Table(name = "notes")
public class Note implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private long note_id;         //Primary key
    @Column(name = "notebook_id")
    private long notebook_id;     //Foregin key
    @Column(name = "note_name")
    private String name;
    @Column(name = "note_content")
    private String content;
    @Column(name = "note_date")
    @CreationTimestamp
    private Timestamp date;     // Date of creating note

    public Note(){}
    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public long getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(long notebook_id) {
        this.notebook_id = notebook_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return name;
    }
}
