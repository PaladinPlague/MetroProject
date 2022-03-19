//package GraphADT;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class EppsteinsAlgorithmTest {
//    @Test
//    void EppsteinsAlogrithmWorks() {
//        MultiGraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new UndirectedUnweightedMultiGraph<>();
//        graph.addEdge(1, 2);
//        graph.addEdge(1, 4);
//        graph.addEdge(1, 5);
//        graph.addEdge(2, 3);
//        graph.addEdge(2, 5);
//        graph.addEdge(2, 6);
//        graph.addEdge(3, 6);
//        graph.addEdge(5, 6);
//        graph.addEdge(4, 5);
//        graph.addEdge(4, 6);
//
//        EppsteinsAlgorithm<Integer> algorithm = new EppsteinsAlgorithm<>();
//        Set<List<Integer>> result = algorithm.searchIn(graph, 1, 6);
//        result.forEach(path -> {
//            System.out.println(path);
//            System.out.println();
//        });
//
//        assertTrue(true);
//
//    }
//}