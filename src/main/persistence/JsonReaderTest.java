package persistence;


import model.FlowMonth;
import model.FlowTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlowTracker ft = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTracker.json");
        try {
            FlowTracker ft = reader.read();
            assertEquals("My flow tracker", ft.getName());
            assertEquals(0, ft.getMonths().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTracker.json");
        try {
            FlowTracker ft = reader.read();
            assertEquals("My flow tracker", ft.getName());
            Collection<FlowMonth> months = ft.getMonths();
            List<FlowMonth> monthList = new ArrayList<>();
            for (FlowMonth m : months) {
                monthList.add(m);
            }
            assertEquals(2, monthList.size());
            checkMonth("03/2022", monthList.get(0).getFlowDays(), monthList.get(0));
            checkDay("03/03/2022", "cramps", "happy", "heavy",
                    monthList.get(0).getFlowDays().get(0));
            checkDay("20/03/2022", "headaches", "angry", "spotting",
                    monthList.get(0).getFlowDays().get(1));
            checkMonth("04/2022", monthList.get(1).getFlowDays(), monthList.get(1));
            checkDay("04/04/2022", "food cravings", "sad", "light",
                    monthList.get(1).getFlowDays().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
