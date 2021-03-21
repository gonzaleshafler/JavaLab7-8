package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FunctionsTest {


    File file = new File("anotherfile");
    BasicFunctionality f = new BasicFunctionality(file);
    SortingMethods sortingMethods = new SortingMethods();
    @BeforeEach
    void setUp() {

    }

    @Test
    void getNotRepetitiveDiag() {
        List <Patient> test = new ArrayList<>();
        test.add(new Patient(9,"Петров",
                "Иванов",
                "Иван",
                "Отсутствует",
                "000000000",
                "0000" ,
                "Вообще не понятно"));
        test.add(new Patient(11,"ПОП",
                "ПОП",
                "ПОП",
                "Отсутствует",
                "000000000",
                "0000" ,
                "VSD"));
        test.add(new Patient(10,"ПОП",
                "ПОПУГАЙКИН",
                "ПОП",
                "Отсутствует",
                "000000000",
                "0000" ,
                "Сифилис"));

        test.forEach(System.out::println);
        sortingMethods.getNotRepetitiveDiag(f.patients).forEach(System.out::println);
        assertFalse(test.containsAll(sortingMethods.getNotRepetitiveDiag(f.patients)));
    }
}