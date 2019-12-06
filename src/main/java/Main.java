import SQL.Note;
import SQL.Notebook;
import SQL.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
//        primaryStage.setTitle("");
//        primaryStage.setScene(new Scene(root));

        SceneManager.setStage(primaryStage);
        SceneManager.addScene("register", "Register.fxml");
        SceneManager.addScene("forgotPassword", "ForgotPassword.fxml");
        SceneManager.addScene("login", "Login.fxml");
        SceneManager.addScene("someNote", "SomeNote.fxml");

        SceneManager.renderScene("login");

//        primaryStage.setResizable(false);
//        root.requestFocus();
//        primaryStage.show();
    }


    public static void main(String[] args) {
          launch(args);
//        User user = new User();
//        user.setFirstName("Adrian");
//        user.setPassword("SFB@&87b");
//        user.setEmail("adik84@op.com");
//        user.setPin("8573");
//        user.setNotebooks(new ArrayList<Notebook>());
//        Notebook notebook = new Notebook();
//        notebook.setName("Chemia");
//        notebook.setNotes(new ArrayList<Note>());
//        Note note = new Note();
//        note.setName("Pierwiastki");
//        note.setContent("O - Tlen");
//        user.addNotebook(notebook);
//        user.addNotebook(notebook);
//        notebook.addNote(note);
//        Configuration configuration = new Configuration().
//                configure("cfg/hibernateMySQL.cfg.xml").
//                addAnnotatedClass(User.class).
//                addAnnotatedClass(Notebook.class).
//                addAnnotatedClass(Note.class);
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(note);
//        session.save(notebook);
//        session.save(user);
//
//        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
//        criteriaQuery.from(User.class);
//        List<User> users = session.createQuery(criteriaQuery).getResultList();
//
//
//        transaction.commit();
//        session.close();
//        sessionFactory.close();
//        StandardServiceRegistryBuilder.destroy(serviceRegistry);
//        System.out.println(users.size());
//
//        Iterator<User> i = users.iterator();
//        while (i.hasNext()){
//            User user1 = i.next();
//            System.out.println(user1.toString());
//
//        }
    }
}

