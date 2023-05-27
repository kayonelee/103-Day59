import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> numFile1 = readFile("C:/Users/klee9/CTAC/Day59/InputOutput/src/input1.txt");
        List<Integer> numFile2 = readFile("C:/Users/klee9/CTAC/Day59/InputOutput/src/input2.txt");
//MERGING TWO LIST OF NUMBERS
        List<Integer> mergedNumbers = new ArrayList<>(numFile1);
        mergedNumbers.addAll(numFile2);
        boolean merged = writeIntegerFile("C:/Users/klee9/CTAC/Day59/InputOutput/src/merged.txt", mergedNumbers);
//PRINT IF MERGING WAS SUCCESSFUL
        if (merged) {
            System.out.println("Merging of integers was successful!");
            System.out.println();
        } else {
            System.out.println("Error with merging integers.");
            System.out.println();
        }
//FIND COMMON INTEGERS IN THE TWO LISTS
        numFile1.retainAll(numFile2);
        boolean common = writeIntegerFile("C:/Users/klee9/CTAC/Day59/InputOutput/src/common.txt", numFile1);
//PRINT IF COMMON INTEGERS WAS WRITTEN TO FILE SUCCESSFULLY
        if (common) {
            System.out.println("Common integers was successfully written to file!");
        } else {
            System.out.println("Error, unable to write common integers to file.");
        }
    }

//READING INTEGERS, IF SUCCESSFUL RETURN TRUE
    private static List<Integer> readFile(String input) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please double check the directory: " + input);
        } catch (IOException e) {
            System.err.println("Error reading file: " + input);
        } catch (NumberFormatException e) {
            System.err.println("Number format invalid. Integers only: " + input);
        }
        return list;
    }
//WRITING INTEGERS, IF SUCCESSFUL RETURN TRUE
    private static boolean writeIntegerFile(String filename, List<Integer> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : list) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }
            return true; // RETURN TRUE IF WRITING WAS SUCCESSFUL
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
            e.printStackTrace();
            return false;
        }
    }
}
