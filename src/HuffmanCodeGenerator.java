import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HuffmanCodeGenerator
{
    private HashMap<Character,Integer> frequencyMap;
    private PriorityQueue<LaPortaNodey> frequencyQueue;
    private HashMap<Character,String> codeMap;
    String outputFile;

    public HuffmanCodeGenerator (String inputFile) throws FileNotFoundException, IOException
    {
        outputFile = "";
        frequencyQueue = new PriorityQueue();
        codeMap = new HashMap<Character,String>();
        File file = new File (inputFile);
        FileReader fileR = new FileReader (file);
        frequencyMap = new HashMap<Character,Integer>();
        BufferedReader br = new BufferedReader (fileR);
        int z=0;
        while ((z = br.read()) != -1)
        {
            char next = (char)z;
            if (!frequencyMap.containsKey(next))
            {
                frequencyMap.put(next,1);
            }
            else if (frequencyMap.containsKey(next))
            {
                int count = frequencyMap.get(next);
                frequencyMap.replace(next,count+1);
            }
        }
        queueGenerator();
        treeGenerator();
        makeCodeMap();
        makeCodeFile(inputFile);
    }

    public int getFrequency(char c)
    {
        if (frequencyMap.containsKey(c))
        {
            int frequency = frequencyMap.get(c);
            return frequency;
        }
        return 0;
    }

    public void queueGenerator()
    {
        for (Character key: frequencyMap.keySet())
        {
            LaPortaNodey nodey = new LaPortaNodey(key,frequencyMap.get(key));
            frequencyQueue.add(nodey);
        }
    }

    public void treeGenerator()
    {
        while(frequencyQueue.size() != 1)
        {
            LaPortaNodey node1 = frequencyQueue.poll();
            LaPortaNodey node2 = frequencyQueue.poll();
            LaPortaNodey node3 = new LaPortaNodey((char)0,node1.getFrequency() + node2.getFrequency());
            node3.setLeft(node1);
            node3.setRight(node2);
            frequencyQueue.add(node3);
        }

    }

    public void makeCodeMap()
    {
        LaPortaNodey nodeStart = frequencyQueue.poll();
        getCodes(nodeStart,"");

    }

    public void getCodes(LaPortaNodey nodey, String code)
    {
        if(!nodey.hasChildren())
        {
            codeMap.put(nodey.getLetter(),code);
            return;
        }
        getCodes(nodey.getLeft(),code + "0");
        getCodes(nodey.getRight(), code + "1");
    }

    public String getCode(char c)
    {
        return codeMap.get(c);
    }

    public void makeCodeFile(String codeFile) throws FileNotFoundException, IOException
    {
        PrintWriter out = new PrintWriter("testMakeCodeFile.txt");
        for (int i=0; i<128; i++)
        {
            String code = getCode((char)i);
            if (code != null)
            {
                out.println(getCode((char)i));
            }
            else if (code==null)
            {
                out.println();
            }
        }
        out.close();
        //System.out.println(codeMap.containsKey('a'));
    }

    public static void main (String [] args) throws FileNotFoundException, IOException
    {
        HuffmanCodeGenerator generator = new HuffmanCodeGenerator("Tester.txt");
        System.out.println(generator.getFrequency('a'));
        System.out.println(generator.getFrequency('b'));
        System.out.println(generator.getCode('a'));
        System.out.println(generator.getCode('b'));
        System.out.println(generator.getCode('c'));
        System.out.println(generator.getCode('d'));
        System.out.println(generator.getCode('e'));

    }


}

