import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    private Hippodrome hippodrome;

    @Test
    void hippodromeConstructorNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void hippodromeConstructorListNull() {
        List <Horse> horsesList = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horsesList)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse " + i;
            horsesList.add(new Horse(name, i, i));
        }
        hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList, hippodrome.getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }
        hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse mockHorse : mockHorses) {
            Mockito.verify(mockHorse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse " + i;
            horsesList.add(new Horse(name, i, i));
        }
        assertEquals(horsesList.get(horsesList.size()-1), new Hippodrome(horsesList).getWinner());
    }
}