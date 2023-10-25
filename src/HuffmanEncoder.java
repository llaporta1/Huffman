import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HuffmanEncoder{

    private HashMap<Character,String> codeMap;

    public HuffmanEncoder (String codeFile) throws FileNotFoundException, IOException{
        codeMap = new HashMap<Character,String>();
        File file = new File(codeFile);
        Scanner scan = new Scanner(file);
        for (int i=0; i<128; i++)
        {
            codeMap.put((char)i,scan.nextLine());
        }
        //System.out.println(codeMap.containsKey('a'));
        //System.out.println(codeMap.get('a'));
        //System.out.println(codeMap.get('b'));
        //System.out.println(codeMap.get('c'));
    }


    public String encodeChar(char input)
    {
        //if (codeMap.containsKey(input))
        if (!codeMap.get(input).equals("\n"))
        {
            return codeMap.get(input);
        }
        return "";
    }

    public void encodeLong(String inputFile, String outputFile) throws FileNotFoundException, IOException
    {
        PrintWriter out = new PrintWriter(outputFile);
        File file = new File (inputFile);
        FileReader fileR = new FileReader (file);
        BufferedReader br = new BufferedReader (fileR);
        int next = br.read();
        while (next != -1)
        {
            System.out.print(encodeChar((char)next));
            out.print(encodeChar((char)next));
            next = br.read();
        }
        out.close();
    }

    public void encodeFile(String fileToCompress) throws FileNotFoundException, IOException
    {
        encodeLong(fileToCompress,"inputFile.txt");
        encodeHelper("inputFile.txt", fileToCompress);

    }

    public void encode(String s)
    {
        int value = Integer.parseInt(s,2);
        System.out.println(value);
        encode2(value);
    }

    public void encode2(int i)
    {
        char c = (char)i;
        System.out.println(c);
    }

    public void encodeHelper(String inputFile, String outputFile) throws FileNotFoundException, IOException
    {
        PrintWriter out = new PrintWriter(outputFile + ".huf");
        File file = new File (inputFile);
        FileReader fileR = new FileReader (file);
        BufferedReader br = new BufferedReader (fileR);
        int z=0;
        String s ="";
        while ((z = br.read()) != -1)
        {
            char c = (char)z;
            s += c;
            if (s.length()==8)
            {
                int intValue = Integer.parseInt(s,2);
                out.print((char)intValue);
                s="";
            }
            else if (s.length()!=8 && !br.ready())
            {
                int numZeros = 8 - s.length();
                for (int i=0; i<numZeros; i++)
                {
                    s += '0';
                }
                int intValue2 = Integer.parseInt(s,2);
                out.print((char)intValue2);
                out.print((char)numZeros);
                out.close();
            }
            else if (s.length()==8 && !br.ready())
            {
                int intValue3 = Integer.parseInt(s,2);
                out.print((char)intValue3);
                out.print((char)0);
                out.close();
            }
        }
        out.close();
    }

    public static void main (String [] args) throws FileNotFoundException, IOException
    {
        HuffmanEncoder encoder = new HuffmanEncoder("testMakeCodeFile.txt");
        //System.out.println(encoder.encodeChar('a'));
        //System.out.println(encoder.encodeChar('c'));
        encoder.encodeLong("Tester.txt","output.copy.txt");
        encoder.encodeFile("Tester.txt");
		/*encoder.encode("11100000");
		encoder.encode("11111011");
		encoder.encode("11101011");*/
    }

}
