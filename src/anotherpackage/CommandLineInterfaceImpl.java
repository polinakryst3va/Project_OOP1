package anotherpackage;

import interfaces.CommandLineInterface;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class CommandLineInterfaceImpl implements CommandLineInterface {
    private AutomatonManager manager;

    public CommandLineInterfaceImpl(AutomatonManager manager) {
        this.manager = manager;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Enter comman: ");
            command = scanner.nextLine().trim();

            String[] parts = command.split("\\s+");
            String operations = parts[0]. toLowerCase();

        }
    }

    @Override
    public void open(String filename) {

    }

    @Override
    public void close() {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveAs(String fileName) {

    }

    @Override
    public void help() {

    }

    @Override
    public void list() {

    }

    @Override
    public void print(int id) {

    }

    @Override
    public void empty(int id) {

    }

    @Override
    public void deterministic(int id) {

    }

    @Override
    public void recognize(int id, String word) {

    }

    @Override
    public void union(int id1, int id2) {

    }

    @Override
    public void concat(int id1, int id2) {

    }

    @Override
    public void un(int id) {

    }

    @Override
    public void reg(String regex) {

    }

    @Override
    public void exit() {

    }
}
