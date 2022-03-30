package Model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StationReaderTest {

    static final String filename = "test_data_file.txt";

    @Test
    public void readStationsTest() throws FileNotFoundException {
        Map<Integer, String> expected = new HashMap<>();
        expected.put(1, "Aaa");
        expected.put(2, "Bbbb");
        expected.put(3, "Cc");
        expected.put(4, "D");
        expected.put(5, "Eeee");
        expected.put(6, "Fffffffffffffff");
        expected.put(7, "Gg");
        expected.put(8, "hH");
        expected.put(9, "IiIi");
        expected.put(10, "jjjJJJ");
        Map<Integer, String> actual = StationReader.readStations(filename);

        assertEquals(expected, actual);
    }

    @Test
    public void readAdjacenciesTest() throws FileNotFoundException {
        Map<Set<Integer>, Set<String>> expected = new HashMap<>();
        expected.put(Set.of(1,2), Set.of("ORANGE"));
        expected.put(Set.of(2,7), Set.of("ORANGE"));
        expected.put(Set.of(7,6), Set.of("ORANGE"));
        expected.put(Set.of(6,5), Set.of("ORANGE"));
        expected.put(Set.of(3,4), Set.of("BLUE"));
        expected.put(Set.of(4,6), Set.of("BLUE"));
        expected.put(Set.of(6,9), Set.of("BLUE"));
        expected.put(Set.of(8,4), Set.of("RED"));
        expected.put(Set.of(4,7), Set.of("RED"));
        expected.put(Set.of(7,10),Set.of("RED"));

        Map<Set<Integer>, Set<String>> actual = StationReader.readAdjacencies(filename);

        assertEquals(expected, actual);
    }
}