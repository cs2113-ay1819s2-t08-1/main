//@@author articstranger
package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalVolunteers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalVolunteers.getTypicalVolunteersPoints;

import java.util.ArrayList;

import org.junit.Test;

import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.model.MapObject;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MapCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void test_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult result = new MapCommand(defaultMap()).execute(model, commandHistory);
        assertEquals(model, expectedModel);
    }

    @Test
    public void test_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        new MapCommand(defaultMap()).execute(model, commandHistory);
        ArrayList<Integer> expectedPoints = getTypicalVolunteersPoints();
        assertEquals(collectPoints(model), expectedPoints);
    }

    /**
     * Goes through the @param model and collects points from all volunteers
     *
     * @return points as an arraylist
     */
    public ArrayList<Integer> collectPoints(Model model) {
        ArrayList<Integer> points = new ArrayList<>();
        model.getFilteredVolunteerList().forEach(vol -> {
            points.add(vol.getPoints());
        });
        return points;
    }

    /**
     * default mapobject stub
     *
     * @return
     */
    private MapObject defaultMap() {
        Pair<Integer, Integer> agePair = new Pair<>(2, 18);
        Pair<Integer, String> racePair = new Pair<>(1, "Chinese");
        Pair<Integer, String> medicalPair = new Pair<>(3, "Nil");
        String comparator = ">";

        return new MapObject(agePair, comparator, racePair, medicalPair);
    }


}
