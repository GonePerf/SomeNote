import SQL.Note;
import SQL.Notebook;
import SQL.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.web.HTMLEditor;

import java.util.ArrayList;
import java.util.List;


public class SomeNoteController {


    private Notebook lastSelectedNotebook;
    private Note lastSelectedNote;
    static NoteDAO noteDAO = new NoteDAO();
    static Note note;
    static boolean isNoteChanged = false;

    private static User user;
    @FXML
    Label imie;
    @FXML
    Label email;
    @FXML
    HTMLEditor content;
    @FXML
    ListView listNotebooks;
    @FXML
    ListView listNotes;

    public SomeNoteController(){

    }



    @FXML
    void initialize(){

        listNotebooks.setFixedCellSize(40);
        listNotes.setFixedCellSize(30);
        listNotebooks.setStyle("-fx");
        listNotebooks.getItems().addAll(user.getNotebooks());
        imie.setText("Imie: " + user.getFirstName());
        email.setText("Email: " + user.getEmail());
        content.setDisable(true);
        HibernateUtil.OpenConnection("cfg/HibernateMySQL.cfg.xml");
    }

    @FXML
    public void showNotes(){

       if(isNoteChanged) {
           noteDAO.update(note);
           isNoteChanged = false;
       }
        listNotes.getItems().clear();
        content.setHtmlText("");
        content.setDisable(true);
        if(listNotebooks.getSelectionModel().getSelectedItem() != null || !(lastSelectedNotebook == listNotebooks.getSelectionModel().getSelectedItem()) ) {

            Notebook notebook = (Notebook) listNotebooks.getSelectionModel().getSelectedItem();
            List<Note> list = notebook.getNotes();
            listNotes.getItems().addAll(list);
            lastSelectedNotebook = notebook;
        }

    }

    @FXML
    public void showContent(){
        if(listNotes.getSelectionModel().getSelectedItem() != null || lastSelectedNote == listNotes.getSelectionModel().getSelectedItem()) {
            if(note != null)
                noteDAO.update(note);
            note = (Note) listNotes.getSelectionModel().getSelectedItem();

            content.setHtmlText(note.getContent());
            content.setDisable(false);
            lastSelectedNote = note;
        }

    }
    @FXML
    public void saveContent(){

        note = (Note) listNotes.getSelectionModel().getSelectedItem();
        note.setContent(content.getHtmlText());
        isNoteChanged = true;



    }

    @FXML
    public void addNotebook() throws Exception {
        String name = DialogUtils.getNameDialog("zeszytu");
        if(name.length() < 3 || name.isEmpty()) DialogUtils.badNameDialog();
        else {
            Notebook notebook = new Notebook();
            notebook.setNotes(new ArrayList<>());
            notebook.setName(name);
            notebook.setUser_id(user.getId());

            NotebookDAO notebookDAO = new NotebookDAO();
            notebookDAO.save(notebook);
            UserDAO userDAO = new UserDAO();
            user.addNotebook(notebook);
            userDAO.update(user);
            listNotebooks.getItems().add(notebook);
        }



    }

    @FXML
    public void addNote(){
        String name = DialogUtils.getNameDialog("tematu");
        if(name.length() < 3 || name.isEmpty()) DialogUtils.badNameDialog();
        else {
            if(note != null)
                noteDAO.update(note);
            Note note = new Note();
            note.setName(name);
            Notebook notebook = (Notebook) listNotebooks.getSelectionModel().getSelectedItem();
            note.setNotebook_id(notebook.getNotebook_id());
            NoteDAO noteDAO = new NoteDAO();
            noteDAO.save(note);
            NotebookDAO notebookDAO = new NotebookDAO();
            notebook.addNote(note);
            notebookDAO.update(notebook);
            listNotes.getItems().add(note);
        }
    }

    @FXML
    public void showAccountPanel(){}

    @FXML
    public void logout(){

        if(note != null)
            noteDAO.update(note);
        SceneManager.renderScene("login");
    }

    public static void setUser(User user) {
        SomeNoteController.user = user;
    }
//    public void initialize(URL location, ResourceBundle resources) {
//        for(Notebook notebook : user.getNotebooks()){
//            Button notebooksB = new Button(notebook.getName());
//            final List<Note> noteList = notebook.getNotes();
//            notebooksB.setOnAction(new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent event) {
//                    for(final Note note : noteList){
//                        Button notesB = new Button(note.getName());
//                        notesB.setOnAction(new EventHandler<ActionEvent>() {
//                            public void handle(ActionEvent event) {
//                                content.setHtmlText(note.getContent());
//                            }
//                        });
//                        notes.getChildren().add(notesB);
//                    }
//                }
//            });
//            notebooks.getChildren().add(notebooksB);
//        }
    }

