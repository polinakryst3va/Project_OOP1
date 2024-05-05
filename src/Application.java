import anotherpackage.Automaton;
import cli.CLI;
import AutomatonParts.Node;

public class Application {
    public static void main(String[] args) {
        Automaton automaton = new Automaton("abc");
        Node s = new Node("S");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        automaton.addNode(s);
        automaton.addNode(a);
        automaton.addNode(b);
        automaton.addNode(c);
        automaton.addNode(d);
        automaton.addEdge(s, a, "b");
//      automaton.addEdge(s, b, "a");
        automaton.addEdge(a, b, "c");
        automaton.addEdge(d, a, "ab");
        System.out.println(automaton);
        System.out.println(automaton.generateWord());

        CLI.start();
    }
}
