import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void firstConstructorParameterNotNull(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 5.0, 10.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t"})
    public void firstConstructorParameterNoSpace(String string){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(string, 5.0, 10.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void secondConstructorParameterNonNegative(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Strawberry", -5.0, 10.0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void thirdConstructorParameterNonNegative(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Strawberry", 5.0, -10.0)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        assertEquals("Strawberry", new Horse("Strawberry", 5.0, 10.0).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(5.0, new Horse("Strawberry", 5.0, 10.0).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(10.0, new Horse("Strawberry", 5.0, 10.0).getDistance());
    }

    @Test
    void getDistanceZero() {
        assertEquals(0, new Horse("Strawberry", 5.0).getDistance());
    }

    @Test
    void getRandomNumberInMoveMethod() {
        try (MockedStatic<Horse> staticMock = Mockito.mockStatic(Horse.class)) {
            staticMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Strawberry", 5.0, 10.0);
            horse.move();
            staticMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    void distanceVerifyInMoveMethod(double mockValue) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockValue);

            Horse horse = new Horse("Strawberry", 5.0, 10.0);
            double initialDistance = horse.getDistance();
            horse.move();
            double expectedDistance = initialDistance + horse.getSpeed() * mockValue;

            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}