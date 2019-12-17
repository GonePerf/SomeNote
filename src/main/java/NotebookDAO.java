import SQL.Notebook;

import java.util.List;

public class NotebookDAO extends DAO<Notebook,Long> {
    public Notebook findById(Long aLong) {
        openCurrentSession();
        Notebook notebook = getCurrentSession().get(Notebook.class,aLong);
        closeCurrentSession();
        return notebook;
    }

    public List<Notebook> findAll() {
        openCurrentSession();
        List<Notebook> notebooks = getCurrentSession().createQuery("from Notebook").list();
        closeCurrentSession();
        return notebooks;
    }
    public List<Notebook> findByUserId(Long user_id){
        openCurrentSession();
        List<Notebook> notebooks = getCurrentSession().createQuery("from Notebook where user_id='"+user_id+"'").list();
        closeCurrentSession();
        return notebooks;
    }
    public Long getNotebookId(String name){
        openCurrentSession();
        Notebook notebook = (Notebook) getCurrentSession().createQuery("from Notebook where name = '"+name+"'");
        closeCurrentSession();
        return notebook.getNotebook_id();
    }
}
