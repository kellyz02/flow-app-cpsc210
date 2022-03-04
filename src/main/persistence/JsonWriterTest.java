package persistence;

import model.FlowDay;
import model.FlowMonth;
import model.FlowTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    private FlowTracker ft;
    private FlowMonth fm1;
    private FlowMonth fm2;
    private FlowDay fd1;
    private FlowDay fd2;
    private FlowDay fd3;

    @BeforeEach
    void runBefore() {
        ft = new FlowTracker("Your FlowTracker");
        fm1 = new FlowMonth("03/2022");

        fd1 = new FlowDay("03/03/2022");
        fd1.enterMood("happy");
        fd1.enterSymptom("cramps");
        fd1.enterFlow("heavy");

        fd2 = new FlowDay("20/03/2022");
        fd2.enterMood("angry");
        fd2.enterSymptom("headaches");
        fd2.enterFlow("spotting");

        fm2 = new FlowMonth("04/2022");

        fd3 = new FlowDay("04/04/2022");
        fd3.enterMood("sad");
        fd3.enterSymptom("food cravings");
        fd3.enterFlow("light");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTracker.json");
            writer.open();
            writer.write(ft);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTracker.json");
            ft = reader.read();
            assertEquals("Your FlowTracker", ft.getName());
            assertEquals(0, ft.getMonths().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FlowMonth fm1 = new FlowMonth("03/2022");
            FlowDay fd1 = new FlowDay("03/03/2022");
            fd1.enterMood("happy");
            fd1.enterSymptom("cramps");
            fd1.enterFlow("heavy");
            fm1.addFlowDay(fd1);
            FlowDay fd2 = new FlowDay("20/03/2022");
            fd2.enterMood("angry");
            fd2.enterSymptom("headaches");
            fd2.enterFlow("spotting");
            fm1.addFlowDay(fd2);
            ft.addMonth(fm1, "03/2022");

            FlowMonth fm2 = new FlowMonth("04/2022");
            FlowDay fd3 = new FlowDay("04/04/2022");
            fd3.enterMood("sad");
            fd3.enterSymptom("food cravings");
            fd3.enterFlow("light");
            fm2.addFlowDay(fd3);
            ft.addMonth(fm2, "04/2022");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTracker.json");
            writer.open();
            writer.write(ft);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTracker.json");
            ft = reader.read();
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
            checkMonth("04/2022", monthList.get(1).getFlowDays(), monthList.get(0));
            checkDay("04/04/2022", "food cravings", "sad", "light",
                    monthList.get(1).getFlowDays().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
