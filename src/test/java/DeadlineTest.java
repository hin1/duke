import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] work1 (by: 22nd of January 2019, 8.23pm)", new Deadline("work1", "22/1/2019 2023").toString());
    }

}
