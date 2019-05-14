package Bookfaker;

import com.github.javafaker.Number;
import lombok.Data;
import com.github.javafaker.*;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.text.NumberFormat;
import java.util.*;
import java.time.LocalDate;

@Data
@Entity
public class FakedBook {

    @Embedded
    @Column(nullable = false)
    private List<String> authors;

    @Embedded
    @Column(nullable = false)
    private List<String> genres;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn13;

    @Id
    @Column(nullable = false)
    private String isbn10;

    @Column(nullable = false)
    private String localPrice;

    @Column(nullable = false)
    private String priceUSD;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate publishDate;

    public FakedBook(Faker faker){
        title = faker.book().title();
        authors = initAuthors(createRandomIntBetween(0, 4), faker);
        publisher = faker.book().publisher();
        publishDate = initPublishDate();
        genres = initGenres(createRandomIntBetween(0, 7), faker);
        isbn13 = faker.code().isbn13();
        isbn10 = faker.code().isbn10();
        priceUSD = faker.commerce().price(0, 150.00);
        localPrice = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("HU")).format(((Double.parseDouble(priceUSD)) * 280.10)) + "HUF";
        priceUSD = NumberFormat.getCurrencyInstance(Locale.US).format(Double.parseDouble(priceUSD)) + "USD";
        description = initDescription(faker);
    }

    public String initDescription(Faker faker){
        Random rand = new Random();
        int lengthOfDescInWords = rand.nextInt(55);
        String toReturn = faker.lorem().paragraph(lengthOfDescInWords);
        if(toReturn.length() > 255){
            toReturn = toReturn.substring(0, 255);
        }
        return toReturn;
    }

    public LocalDate initPublishDate(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(gregorianCalendar.YEAR, createRandomIntBetween(1677, LocalDate.now().getYear()));
        gregorianCalendar.set(gregorianCalendar.DAY_OF_YEAR, createRandomIntBetween(1, gregorianCalendar.getActualMaximum(gregorianCalendar.DAY_OF_YEAR)));
        return gregorianCalendar.toZonedDateTime().toLocalDate();
    }

    public int createRandomIntBetween(int begin, int end) {
        return begin + (int) Math.round(Math.random() * (end-begin));
    }

    public List<String> initGenres(int count, Faker faker) {
        List<String> toReturn = new ArrayList<>();
        for(int x = 0; x <= count; x++){
            toReturn.add(faker.book().genre());
        }

        return toReturn;
    }

    public List<String> initAuthors(int count, Faker faker) {
        List<String> toReturn = new ArrayList<>();
        for(int x = 0; x <= count; x++){
            toReturn.add(faker.book().author());
        }

        return toReturn;
    }
}
