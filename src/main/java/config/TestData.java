package config;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    private static Faker faker = new Faker();
    private static Faker fakerRU = Faker.instance(new Locale("ru-RU"));

    //For creation tests
    public static String PASSWORD = faker.bothify("##??????");
    public static String PASSWORD_LOW = faker.letterify("????????").toLowerCase();
    public static String PASSWORD_UP = faker.letterify("?????????").toUpperCase();
    public static String PASSWORD_BOTH = faker.letterify("????").toLowerCase() + faker.letterify("????").toUpperCase();
    public static String PASSWORD_DIGITS = faker.bothify("??##???#");
    public static String PASSWORD_SYMBOLS = faker.letterify("????@?$?");
    public  static  String USERNAME = faker.name().username();
    public  static String EMAIL_LOW = faker.internet().emailAddress().toLowerCase();
    public static String EMAIL_UP = faker.internet().emailAddress().toUpperCase();
    public static String EMAIL_No = faker.bothify("#????????@gmail.com");
    public static String EMAIL_DOT = faker.letterify("????.??.??@gmail.com");
    public static String EMAIL_DOM_DOT = faker.letterify("??????????@??.??.???");
    public static String EMAIL_SPACE = faker.letterify("?????-????@gmail.com");
    public static String EMAIL_EMPTY = "         ";
    public static String EMAIL_WITHOUT_AT = faker.letterify("?????????.gmail.com");
    public static String EMAIL_WITH_DOTS_ROW = faker.letterify("??????..????@gmail.com");
    public static String EMAIL_FIRST_DOT = faker.letterify(".?????????@gmail.com");
    public static String EMAIL_DOMAIN_DOT = faker.letterify("????????@.???.??");

    //For update test
    public static String USERNAME_UP_LOW = faker.letterify("????").toLowerCase() + faker.letterify("????").toUpperCase();
    public static String USERNAME_CYRILLIC = fakerRU.name().username();
    public static String USERNAME_DIGITS = faker.bothify("???????##");
    public static String USERNAME_SYMBOL = faker.letterify("@???????");
    public static String USERNAME_EMPTY = "        ";
    public static String USERNAME_LONG = faker.letterify("?").repeat(151);



}
