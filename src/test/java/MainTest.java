import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void mainTimeout() {
        String[] args = new String[0];
        assertTimeout(
                ofSeconds(22),
                () -> Main.main(args)
        );
    }
}