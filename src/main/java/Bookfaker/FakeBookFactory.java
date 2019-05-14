package Bookfaker;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.javafaker.*;

import javax.persistence.Entity;

@Data
public class FakeBookFactory {
    private static final Faker FAKER = new Faker();
    public FakeBookFactory() {

    }

    public FakedBook generateFakedBook(){
        return new FakedBook(FAKER);
    }

}
