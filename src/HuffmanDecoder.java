import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HuffmanDecoder{
    private HashMap<String,Character> codeMap;

    public HuffmanDecoder(String codeFile) throws FileNotFoundException, IOException{
        codeMap = new HashMap<String,Character>();
        File file = new File(codeFile);
        Scanner scan = new Scanner(file);
        for (int i=0; i<128; i++)
        {
            codeMap.put(scan.nextLine(),(char)i);
        }
        System.out.println(codeMap);
    }

    public boolean isCode(String binary){
        return codeMap.containsKey(binary);
    }

    public char decodeChar(String binary){
        if (isCode(binary))
        {
            return codeMap.get(binary);
        }
        return '*';
    }

    public void decodeLong(String encodedFile, String decodedFile) throws FileNotFoundException, IOException
    {
        PrintWriter out = new PrintWriter(decodedFile);
        File file = new File (encodedFile);
        FileReader fileR = new FileReader (file);
        BufferedReader br = new BufferedReader (fileR);
        String codeChunk = "";
        int next = br.read();
        while (next != -1)
        {
            codeChunk += (char)next;
            if (isCode(codeChunk))
            {
                out.print("" + decodeChar(codeChunk));
                codeChunk = "";
            }
            next = br.read();
        }
        out.close();
    }

    public void decodeFile(String encodedFile) throws FileNotFoundException, IOException {
        decodeHelper(encodedFile,"outputFile.txt");
        decodeLong("outputFile.txt","newOutput.txt");
    }

    public void decodeHelper(String inputFile, String outputFile) throws IllegalArgumentException, FileNotFoundException, IOException
    {
        if (inputFile.indexOf(".huf") < 0)
        {
            throw new IllegalArgumentException();
        }
        PrintWriter out = new PrintWriter(outputFile);
        File file = new File (inputFile);
        FileReader fileR = new FileReader (file);
        BufferedReader br = new BufferedReader (fileR);
        String binary ="";
        int c1 = br.read();
        int c2 = br.read();
        int c3 = br.read();
        while (c3 != -1)
        {
            binary = Integer.toBinaryString(c1);
            if (binary.length() != 8)
            {
                for (int i=0; i<8-binary.length(); i++)
                {
                    binary = "0" + binary;
                }
            }
            out.print(binary);
            c1 = c2;
            c2 = c3;
            c3 = br.read();
        }
        binary = Integer.toBinaryString(c1);
        if (binary.length() != 8)
        {
            for (int i=0; i<8-binary.length(); i++)
            {
                binary = "0" + binary;
            }
        }
        out.print(binary.substring(0,(binary.length()-c2)));
        out.close();
    }

    public static void main (String [] args) throws FileNotFoundException, IOException
    {
        HuffmanDecoder decoder = new HuffmanDecoder("testMakeCodeFile.txt");
        decoder.decodeLong("decodeLongTest.txt","newoutput.txt");
        //decoder.decodeFile("Tester.txt.huf");
    }
}
