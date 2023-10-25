public class LaPortaNodey implements Comparable {
    private Character letter;
    private int frequency;
    private LaPortaNodey left;
    private LaPortaNodey right;
    public LaPortaNodey(char letter, int charFrequency)
    {
        this.letter = letter;
        frequency = charFrequency;
        this.left = null;
        this.right = null;
    }

    public char getLetter()
    {
        return letter;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public boolean isLeaf()
    {
        if (letter != null)
        {
            return true;
        }
        return false;
    }

    public int compareTo(Object other)
    {
        if (frequency > ((LaPortaNodey)other).getFrequency())
        {
            return 1;
        }
        else if (frequency < ((LaPortaNodey)other).getFrequency())
        {
            return -1;
        }
        return 0;
    }

    public LaPortaNodey getLeft() {
        return left;
    }

    public void setLeft(LaPortaNodey left) {
        this.left = left;
    }

    public LaPortaNodey getRight() {
        return right;
    }

    public void setRight(LaPortaNodey right) {
        this.right = right;
    }

    public boolean hasChildren() {
        return left != null || right != null;
    }
}