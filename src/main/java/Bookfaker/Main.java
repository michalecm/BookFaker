package Bookfaker;

import Bookfaker.FakeBookFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import util.guice.PersistenceModule;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FakeBookFactory fakeBookFactory = new FakeBookFactory();
        ArrayList<FakedBook> fakedBookArrayList = new ArrayList<>();
        for(int x = 0; x < 11; x++) {
            fakedBookArrayList.add(fakeBookFactory.generateFakedBook());
        }

        Injector injector = Guice.createInjector(new PersistenceModule("generator"));
        FakedBookDao dao = injector.getInstance(FakedBookDao.class);

        for(FakedBook x : fakedBookArrayList){
            dao.persist(x);
        }

        List<FakedBook> fakedBookList = dao.getAll();
        for(FakedBook x: fakedBookList){
            System.out.println(x);
        }
    }
}
