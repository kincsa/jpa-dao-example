package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);

        Scanner sc  = new Scanner(System.in);
        System.out.println("A tarolando konyvek szama:");
        int input = sc.nextInt();

        BookGenerator bookGenerator = new BookGenerator();
        for (int i = 0; i < input; i++) {
            Book book = bookGenerator.generateRandomBook();
            bookDao.persist(book);
        }

        bookDao.findAll()
                .stream()
                .forEach(System.out::println);

    }
}