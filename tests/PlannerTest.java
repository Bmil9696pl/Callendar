import gui.Planner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    static JComboBox comboTest;
    @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    void initFormTest(int i) {
        //given
        String[] month = { "Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien",
                "Wrzesien", "Pazdziernik", "Listopad", "Grudzien" };
        List expected = new List();
        for(int j = 0; j < 12; j++) {
            expected.add(month[j]);
        }
        System.out.println(expected.getItem(i));
        //when
        Planner actual = new Planner();
        actual.initForm(actual);
        //then
        assertEquals(expected.getItem(i), actual.comboBox1.getItemAt(i));
    }
}