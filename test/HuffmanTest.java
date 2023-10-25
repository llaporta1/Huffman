import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestHuffman {


    @Test
    @DisplayName("[5] Test if your getFrequency works correctly")
    public void testGetFrequency() throws IOException {
        String frequencyFile = "frequencyCountInput.txt";
        HuffmanCodeGeneratorSolution sol = new HuffmanCodeGeneratorSolution(frequencyFile);
        HuffmanCodeGenerator student = new HuffmanCodeGenerator(frequencyFile);
        for (int i = 0; i < 128; i++) {
            assertEquals(sol.getFrequency((char) i), student.getFrequency((char) i));
        }
    }

    @Test
    @DisplayName("[5] Test if your getCode works correctly")
    public void testGetCode() throws IOException {
        String frequencyFile = "frequencyCountInput.txt";
        HuffmanCodeGeneratorSolution sol = new HuffmanCodeGeneratorSolution(frequencyFile);
        HuffmanCodeGenerator student = new HuffmanCodeGenerator(frequencyFile);
        for (int i = 0; i < 128; i++) {
            if (sol.getCode((char) i) != null) {
                assertEquals(sol.getCode((char) i), student.getCode((char) i));
            }
        }
    }

    @Test
    @DisplayName("[5] Test if your encodeChar works correctly")
    public void testEncodeChar() throws IOException {
        String rosetta = "smallRosetta.txt";
        HuffmanEncoderSolution sol = new HuffmanEncoderSolution(rosetta);
        HuffmanEncoder student = new HuffmanEncoder(rosetta);
        for (char c = 0; c < 128; c++) {
            assertEquals(sol.encodeChar(c), student.encodeChar(c));
        }
    }

    @Test
    @DisplayName("[5] Test if your encodeLong works correctly")
    public void testEncodeLong() throws IOException {
        String rosetta = "smallRosetta.txt";
        String input = "smallInput.txt";
        String soloutput = "solutionoutput.txt";
        String studentoutput = "studentoutput.txt";
        HuffmanEncoderSolution sol = new HuffmanEncoderSolution(rosetta);
        HuffmanEncoder student = new HuffmanEncoder(rosetta);
        sol.encodeLong(input, soloutput);
        student.encodeLong(input, studentoutput);
        BufferedReader solreader = new BufferedReader(new FileReader(soloutput));
        BufferedReader studentreader = new BufferedReader(new FileReader(studentoutput));
        while (solreader.ready()) {
            assertEquals(solreader.read(), studentreader.read());
        }
        solreader.close();
        studentreader.close();
    }

    @Test
    @DisplayName("[5] Test if your decodeChar works correctly")
    public void testDecodeChar() throws IOException {
        String rosetta = "smallRosetta.txt";
        HuffmanDecoderSolution sol = new HuffmanDecoderSolution(rosetta);
        HuffmanDecoder student = new HuffmanDecoder(rosetta);
        Scanner scan = new Scanner(new File(rosetta));
        while(scan.hasNext()) {
            String code = scan.next();
            assertEquals(sol.decodeChar(code), student.decodeChar(code));
        }
        scan.close();
    }

    @Test
    @DisplayName("[5] Test if your decodeLong works correctly")
    public void testDecodeLong() throws IOException {
        String rosetta = "smallRosetta.txt";
        String input = "smallOutput.txt";
        String soloutput = "solutionoutput.txt";
        String studentoutput = "studentoutput.txt";
        HuffmanDecoderSolution sol = new HuffmanDecoderSolution(rosetta);
        HuffmanDecoder student = new HuffmanDecoder(rosetta);
        sol.decodeLong(input, soloutput);
        student.decodeLong(input, studentoutput);
        BufferedReader solreader = new BufferedReader(new FileReader(soloutput));
        BufferedReader studentreader = new BufferedReader(new FileReader(studentoutput));
        while (solreader.ready()) {
            assertEquals(solreader.read(), studentreader.read());
        }
        solreader.close();
        studentreader.close();
    }

    @Test
    @DisplayName("[5] Test if your encodeFile works correctly")
    public void testEncodeFile() throws IOException {
        String rosetta = "smallRosetta.txt";
        String input = "encodefile.txt";
        String soloutput = "encodefile.txt.sol";
        HuffmanEncoderSolution sol = new HuffmanEncoderSolution(rosetta);
        HuffmanEncoder student = new HuffmanEncoder(rosetta);
        sol.encodeFile(input, soloutput);
        student.encodeFile(input);
        BufferedReader solreader = new BufferedReader(new FileReader(soloutput));
        BufferedReader studentreader = new BufferedReader(new FileReader(input + ".huf"));
        while (solreader.ready()) {
            assertEquals(solreader.read(), studentreader.read());
        }
        solreader.close();
        studentreader.close();
    }

    @Test
    @DisplayName("[5] Test if your decodeFile works correctly")
    public void testDecodeFile() throws IOException {
        String rosetta = "smallRosetta.txt";
        String input = "decodefile.txt.huf";
        String soloutput = "decodefile.txt.sol";
        String studentoutput = input.substring(0, input.length() - 4);
        HuffmanDecoderSolution sol = new HuffmanDecoderSolution(rosetta);
        HuffmanDecoder student = new HuffmanDecoder(rosetta);
        sol.decodeFile(input, soloutput);
        student.decodeFile(input);
        BufferedReader solreader = new BufferedReader(new FileReader(soloutput));
        BufferedReader studentreader = new BufferedReader(new FileReader(studentoutput));
        while (solreader.ready()) {
            assertEquals(solreader.read(), studentreader.read());
        }
        assertFalse(studentreader.ready());
        solreader.close();
        studentreader.close();
    }

}

