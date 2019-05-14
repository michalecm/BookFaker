package Bookfaker;

import Bookfaker.FakeBookFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import util.guice.PersistenceModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FakeBookFactory fakeBookFactory = new FakeBookFactory();
        ArrayList<FakedBook> fakedBookArrayList = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("How many books would you like to create?::  ");
        int toCreate = keyboard.nextInt();
        System.out.println();
        keyboard.close();
        for(int x = 0; x < toCreate; x++) {
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
