package Model;

import GraphADT.EppsteinsAlgorithm;
import GraphADT.MultiGraph;
import GraphADT.UndirectedUnweightedColouredEdge;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MetroSystemTest {
    static Metro metro;

    @BeforeAll
    static void setUpEach() throws FileNotFoundException {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> algorithm = new EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        metro = new Metro(graph, algorithm);
        metro.init("bostonmetro.txt");
    }

    @Test
    void testGetAllNames() {
        Map<Integer, String> expectedNames = new HashMap<>();
        expectedNames.put(1, "OakGrove");
        expectedNames.put(2, "Malden");
        expectedNames.put(3, "Wonderland");
        expectedNames.put(4, "RevereBeach");
        expectedNames.put(5, "Wellington");
        expectedNames.put(6, "Beachmont");
        expectedNames.put(7, "Davis");
        expectedNames.put(8, "Alewife");
        expectedNames.put(9, "SuffolkDowns");
        expectedNames.put(10, "Porter");
        expectedNames.put(11, "OrientHeights");
        expectedNames.put(12, "SullivanSquare");
        expectedNames.put(13, "WoodIsland");
        expectedNames.put(14, "Harvard");
        expectedNames.put(15, "CommunityCollege");
        expectedNames.put(16, "Airport");
        expectedNames.put(17, "Lechmere");
        expectedNames.put(18, "Maverick");
        expectedNames.put(19, "SciencePark");
        expectedNames.put(20, "NorthStation");
        expectedNames.put(21, "Central");
        expectedNames.put(22, "Haymarket");
        expectedNames.put(23, "Kendall");
        expectedNames.put(24, "Bowdoin");
        expectedNames.put(25, "Charles/MGH");
        expectedNames.put(26, "Aquarium");
        expectedNames.put(27, "GovernmentCenter");
        expectedNames.put(28, "State");
        expectedNames.put(29, "ParkStreet");
        expectedNames.put(30, "DowntownCrossing");
        expectedNames.put(31, "Boylston");
        expectedNames.put(32, "Chinatown");
        expectedNames.put(33, "SouthStation");
        expectedNames.put(34, "Arlington");
        expectedNames.put(35, "BabcockStreet");
        expectedNames.put(36, "BrightonAvenue");
        expectedNames.put(37, "PleasantStreet");
        expectedNames.put(38, "St.PaulStreet");
        expectedNames.put(39, "BostonUniversityWest");
        expectedNames.put(40, "FordhamRoad");
        expectedNames.put(41, "Copley");
        expectedNames.put(42, "HarvardAvenue");
        expectedNames.put(43, "BostonUniversityCentral");
        expectedNames.put(44, "NewEnglandMedicalCenter");
        expectedNames.put(45, "BostonUniversityEast");
        expectedNames.put(46, "BlandfordStreet");
        expectedNames.put(47, "Kenmore");
        expectedNames.put(48, "GriggsStreet/LongwoodAvenue");
        expectedNames.put(49, "WarrenStreet");
        expectedNames.put(50, "AllstonStreet");
        expectedNames.put(51, "Hynes/ICA");
        expectedNames.put(52, "BackBay/SouthEnd");
        expectedNames.put(53, "Prudential");
        expectedNames.put(54, "St.Mary'sStreet");
        expectedNames.put(55, "SummitAvenue");
        expectedNames.put(56, "HawesStreet");
        expectedNames.put(57, "Fenway");
        expectedNames.put(58, "KentStreet");
        expectedNames.put(59, "WashingtonStreet");
        expectedNames.put(60, "Broadway");
        expectedNames.put(61, "St.PaulStreet");
        expectedNames.put(62, "Symphony");
        expectedNames.put(63, "CoolidgeCorner");
        expectedNames.put(64, "MassachusettsAvenue");
        expectedNames.put(65, "Longwood");
        expectedNames.put(66, "MountHoodRoad");
        expectedNames.put(67, "SutherlandRoad");
        expectedNames.put(68, "WinchesterStreet/SummitAv.");
        expectedNames.put(69, "BostonCollege");
        expectedNames.put(70, "NortheasternUniversity");
        expectedNames.put(71, "ChiswickRoad");
        expectedNames.put(72, "GreycliffRoad");
        expectedNames.put(73, "BrandonHall");
        expectedNames.put(74, "FairbanksStreet");
        expectedNames.put(75, "SouthStreet");
        expectedNames.put(76, "WashingtonSquare");
        expectedNames.put(77, "TappanStreet");
        expectedNames.put(78, "ChestnutHillAvenue");
        expectedNames.put(79, "MuseumofFineArts");
        expectedNames.put(80, "DeanRoad");
        expectedNames.put(81, "EnglewoodAvenue");
        expectedNames.put(82, "Ruggles");
        expectedNames.put(83, "ClevelandCircle");
        expectedNames.put(84, "LongwoodMedicalArea");
        expectedNames.put(85, "Beaconsfield");
        expectedNames.put(86, "Reservoir");
        expectedNames.put(87, "BrighamCircle");
        expectedNames.put(88, "FenwoodRoad");
        expectedNames.put(89, "MissionPark");
        expectedNames.put(90, "BrooklineVillage");
        expectedNames.put(91, "BrooklineHills");
        expectedNames.put(92, "Riverway");
        expectedNames.put(93, "RoxburyCrossing");
        expectedNames.put(94, "Andrew");
        expectedNames.put(95, "BackOfTheHill");
        expectedNames.put(96, "HeathStreet");
        expectedNames.put(97, "JacksonSquare");
        expectedNames.put(98, "JFK/UMass");
        expectedNames.put(99, "StonyBrook");
        expectedNames.put(100, "SavinHill");
        expectedNames.put(101, "GreenStreet");
        expectedNames.put(102, "ForestHills");
        expectedNames.put(103, "FieldsCorner");
        expectedNames.put(104, "Shawmut");
        expectedNames.put(105, "Ashmont");
        expectedNames.put(106, "CedarGrove");
        expectedNames.put(107, "ButlerStreet");
        expectedNames.put(108, "Milton");
        expectedNames.put(109, "CentralAvenue");
        expectedNames.put(110, "ValleyRoad");
        expectedNames.put(111, "CapenStreet");
        expectedNames.put(112, "Mattapan");
        expectedNames.put(113, "ChesnutHill");
        expectedNames.put(114, "NewtonCenter");
        expectedNames.put(115, "NewtonHighlands");
        expectedNames.put(116, "Eliot");
        expectedNames.put(117, "Waban");
        expectedNames.put(118, "Woodland");
        expectedNames.put(119, "Riverside");
        expectedNames.put(120, "NorthQuincy");
        expectedNames.put(121, "Wollaston");
        expectedNames.put(122, "QuincyCenter");
        expectedNames.put(123, "QuincyAdams");
        expectedNames.put(124, "Braintree");
        Map<Integer, String> actualNames = metro.getStationsNames();

        assertEquals(expectedNames, actualNames);
    }

    @Test
    void testGetAllLines() {
        Map<Integer, Set<String>> expectedLines = new HashMap<>();
        expectedLines.put(1, Set.of("ORANGE"));
        expectedLines.put(2, Set.of("ORANGE"));
        expectedLines.put(3, Set.of("BLUE"));
        expectedLines.put(4, Set.of("BLUE"));
        expectedLines.put(5, Set.of("ORANGE"));
        expectedLines.put(6, Set.of("BLUE"));
        expectedLines.put(7, Set.of("RED"));
        expectedLines.put(8, Set.of("RED"));
        expectedLines.put(9, Set.of("BLUE"));
        expectedLines.put(10, Set.of("RED"));
        expectedLines.put(11, Set.of("BLUE"));
        expectedLines.put(12, Set.of("ORANGE"));
        expectedLines.put(13, Set.of("BLUE"));
        expectedLines.put(14, Set.of("RED"));
        expectedLines.put(15, Set.of("ORANGE"));
        expectedLines.put(16, Set.of("BLUE"));
        expectedLines.put(17, Set.of("GREEN"));
        expectedLines.put(18, Set.of("BLUE"));
        expectedLines.put(19, Set.of("GREEN"));
        expectedLines.put(20, Set.of("GREEN", "ORANGE"));
        expectedLines.put(21, Set.of("RED"));
        expectedLines.put(22, Set.of("GREEN", "ORANGE"));
        expectedLines.put(23, Set.of("RED"));
        expectedLines.put(24, Set.of("BLUE"));
        expectedLines.put(25, Set.of("RED"));
        expectedLines.put(26, Set.of("BLUE"));
        expectedLines.put(27, Set.of("BLUE", "GREEN"));
        expectedLines.put(28, Set.of("BLUE", "ORANGE"));
        expectedLines.put(29, Set.of("RED", "GREEN"));
        expectedLines.put(30, Set.of("RED", "ORANGE"));
        expectedLines.put(31, Set.of("GREEN"));
        expectedLines.put(32, Set.of("ORANGE"));
        expectedLines.put(33, Set.of("RED"));
        expectedLines.put(34, Set.of("GREENB", "GREENC", "GREEN", "GREEND", "GREENE"));
        expectedLines.put(35, Set.of("GREENB"));
        expectedLines.put(36, Set.of("GREENB"));
        expectedLines.put(37, Set.of("GREENB"));
        expectedLines.put(38, Set.of("GREENB"));
        expectedLines.put(39, Set.of("GREENB"));
        expectedLines.put(40, Set.of("GREENB"));
        expectedLines.put(41, Set.of("GREENB", "GREENC", "GREEN", "GREEND", "GREENE"));
        expectedLines.put(42, Set.of("GREENB"));
        expectedLines.put(43, Set.of("GREENB"));
        expectedLines.put(44, Set.of("ORANGE"));
        expectedLines.put(45, Set.of("GREENB"));
        expectedLines.put(46, Set.of("GREENB"));
        expectedLines.put(47, Set.of("GREENB", "GREENC", "GREEND"));
        expectedLines.put(48, Set.of("GREENB"));
        expectedLines.put(49, Set.of("GREENB"));
        expectedLines.put(50, Set.of("GREENB"));
        expectedLines.put(51, Set.of("GREENB", "GREENC", "GREEND"));
        expectedLines.put(52, Set.of("ORANGE"));
        expectedLines.put(53, Set.of("GREENE"));
        expectedLines.put(54, Set.of("GREENC"));
        expectedLines.put(55, Set.of("GREENB"));
        expectedLines.put(56, Set.of("GREENC"));
        expectedLines.put(57, Set.of("GREEND"));
        expectedLines.put(58, Set.of("GREENC"));
        expectedLines.put(59, Set.of("GREENB"));
        expectedLines.put(60, Set.of("RED"));
        expectedLines.put(61, Set.of("GREENC"));
        expectedLines.put(62, Set.of("GREENE"));
        expectedLines.put(63, Set.of("GREENC"));
        expectedLines.put(64, Set.of("ORANGE"));
        expectedLines.put(65, Set.of("GREEND"));
        expectedLines.put(66, Set.of("GREENB"));
        expectedLines.put(67, Set.of("GREENB"));
        expectedLines.put(68, Set.of("GREENC"));
        expectedLines.put(69, Set.of("GREENB"));
        expectedLines.put(70, Set.of("GREENE"));
        expectedLines.put(71, Set.of("GREENB"));
        expectedLines.put(72, Set.of("GREENB"));
        expectedLines.put(73, Set.of("GREENC"));
        expectedLines.put(74, Set.of("GREENC"));
        expectedLines.put(75, Set.of("GREENB"));
        expectedLines.put(76, Set.of("GREENC"));
        expectedLines.put(77, Set.of("GREENC"));
        expectedLines.put(78, Set.of("GREENB"));
        expectedLines.put(79, Set.of("GREENE"));
        expectedLines.put(80, Set.of("GREENC"));
        expectedLines.put(81, Set.of("GREENC"));
        expectedLines.put(82, Set.of("ORANGE"));
        expectedLines.put(83, Set.of("GREENC"));
        expectedLines.put(84, Set.of("GREENE"));
        expectedLines.put(85, Set.of("GREEND"));
        expectedLines.put(86, Set.of("GREEND"));
        expectedLines.put(87, Set.of("GREENE"));
        expectedLines.put(88, Set.of("GREENE"));
        expectedLines.put(89, Set.of("GREENE"));
        expectedLines.put(90, Set.of("GREEND"));
        expectedLines.put(91, Set.of("GREEND"));
        expectedLines.put(92, Set.of("GREENE"));
        expectedLines.put(93, Set.of("ORANGE"));
        expectedLines.put(94, Set.of("RED", "REDA", "REDB"));
        expectedLines.put(95, Set.of("GREENE"));
        expectedLines.put(96, Set.of("GREENE"));
        expectedLines.put(97, Set.of("ORANGE"));
        expectedLines.put(98, Set.of("RED", "REDA", "REDB"));
        expectedLines.put(99, Set.of("ORANGE"));
        expectedLines.put(100, Set.of("REDA"));
        expectedLines.put(101, Set.of("ORANGE"));
        expectedLines.put(102, Set.of("ORANGE"));
        expectedLines.put(103, Set.of("REDA"));
        expectedLines.put(104, Set.of("REDA"));
        expectedLines.put(105, Set.of("MATTAPAN", "REDA"));
        expectedLines.put(106, Set.of("MATTAPAN"));
        expectedLines.put(107, Set.of("MATTAPAN"));
        expectedLines.put(108, Set.of("MATTAPAN"));
        expectedLines.put(109, Set.of("MATTAPAN"));
        expectedLines.put(110, Set.of("MATTAPAN"));
        expectedLines.put(111, Set.of("MATTAPAN"));
        expectedLines.put(112, Set.of("MATTAPAN"));
        expectedLines.put(113, Set.of("GREEND"));
        expectedLines.put(114, Set.of("GREEND"));
        expectedLines.put(115, Set.of("GREEND"));
        expectedLines.put(116, Set.of("GREEND"));
        expectedLines.put(117, Set.of("GREEND"));
        expectedLines.put(118, Set.of("GREEND"));
        expectedLines.put(119, Set.of("GREEND"));
        expectedLines.put(120, Set.of("REDB"));
        expectedLines.put(121, Set.of("REDB"));
        expectedLines.put(122, Set.of("REDB"));
        expectedLines.put(123, Set.of("REDB"));
        expectedLines.put(124, Set.of("REDB"));

        Map<Integer, Set<String>> actualLines = metro.getStationsLines();
        assertEquals(expectedLines, actualLines);
    }

    @Test
    void testFindPathsBetween_ParkSt_and_State() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        29, 30, 28
                ),
                List.of(
                        29, 27, 28
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(29, 28);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_Boylston_and_Aquarium() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        31, 29, 27, 28, 26
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(31, 26);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_DowntownCrossing_and_NorthStation() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        30, 28, 22, 20
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(30, 20);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_Copley_and_Longwood() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        65, 57, 47, 51, 41
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(65, 41);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_Charles_and_Aquarium() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        25, 29, 30, 28, 26
                ),
                List.of(
                        25, 29, 27, 28, 26
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(25, 26);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_Malden_OakGrove() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        2, 1
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(2, 1);

        assertEquals(actualPaths, expectedPaths);
    }

    @Test
    void testFindPathsBetween_OakGrove_Riverside() {
        Set<List<Integer>> expectedPaths = Set.of(
                List.of(
                        1, 2, 5, 12, 15, 20, 22, 27, 29, 31, 34, 41, 51, 47, 57, 65, 90, 91, 85, 86, 113, 114, 115, 116, 117, 118, 119
                )
        );
        Set<List<Integer>> actualPaths = metro.getShortestPaths(1, 119);

        assertEquals(actualPaths, expectedPaths);
    }
}
