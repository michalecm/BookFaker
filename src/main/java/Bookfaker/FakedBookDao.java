package Bookfaker;

import util.jpa.GenericJpaDao;

import javax.transaction.Transactional;
import java.util.List;

public class FakedBookDao extends GenericJpaDao {
    public FakedBookDao() {
        super(FakedBook.class);
    }

    @Transactional
    public List<FakedBook> getAll() {
        return entityManager.createQuery("SELECT b FROM FakedBook b ORDER BY b.publishDate ASC", FakedBook.class)
                .getResultList();
    }
}
