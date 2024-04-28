package interfaces;

import anotherpackage.Automaton;
import anotherpackage.AutomatonManager;

public interface CommandLineInterface {
    void start();
    void open(String filename);
    void close();
    void save();
    void saveAs(String fileName);
    void help();
    void list();
    void print(int id);
    void empty(int id);
    void deterministic(int id);
    void recognize(int id, String word);
    void union(int id1, int id2);
    void concat(int id1, int id2);
    void un(int id);
    void reg(String regex);
    void exit();




}
