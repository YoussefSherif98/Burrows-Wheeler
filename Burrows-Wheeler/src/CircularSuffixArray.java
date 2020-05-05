
public class CircularSuffixArray {
	
	private Suffix[] suffixes;
	private int length;
	private int[] index;


    // circular suffix array of s
    public CircularSuffixArray(String s)
    {
    	if (s == null)
    		throw new IllegalArgumentException("Null Argument");
    	
    	this.length = s.length();
    	suffixes = new Suffix[length];
    	index = new int[length];
    	
    	for (int i = 0 ; i < length ; i++)
    	{
    		suffixes[i] = new Suffix(s,i);
    		index[i] = i;
    	}
    	
    	sort(suffixes, 0 , length-1 , 0);
    	
    }

    // length of s
    public int length()
    {
    	return length;
    }

    // returns index of ith sorted suffix
    public int index(int i)
    {
    	if (i < 0 || i >= length)
    		throw new IllegalArgumentException("Index outside prescribed range");
    	
    	return index[i];
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	CircularSuffixArray tester = new CircularSuffixArray("ABRACADABRA!");
    	System.out.println("Length is "+tester.length());
    	System.out.println();
    	System.out.println("Index[]");
    	for (int i = 0; i < tester.length(); i++)
    		System.out.println(tester.index(i));
    	
    }
    
    private void sort(Suffix[] suffixes, int lo, int hi, int d)
    {
    	if (d >= length)
    		return;
    	
    	if (hi <= lo)
    		return;
    	
    	char v = suffixes[lo].charAt(d);
    	int lt = lo , gt = hi;
    	int i = lo+1;
    	
    	while (i<=gt)
    	{
    		char t = suffixes[i].charAt(d);
    		if (t < v)
    		{
    			exch(suffixes, lt++, i);
    		}
    		else if (t > v)
    		{
    			exch(suffixes, gt--, i--);
    		}
    		i++;
    	}
    	
    	sort(suffixes, lo, lt-1, d);
    	if (v >= 0) sort(suffixes, lt, gt, d+1);
    	sort(suffixes, gt+1, hi, d);
    	
    }

	private void exch(Suffix[] suffixes, int i, int j)
    {
    	if (i == j)
    		return;
    	
    	Suffix suffTemp = suffixes[i];
    	suffixes[i] = suffixes[j];
    	suffixes[j] = suffTemp;
    	
    	int intTemp = index[i];
    	index[i] = index[j];
    	index[j] = intTemp;
    }
    
    private static class Suffix {
    	
    	private String s;
    	private int start;
    	private int length;
    	
    	private Suffix (String s, int start)
    	{
    		this.s = s;
    		this.start = start;
    		this.length = s.length();
    	}
    	
    	private char charAt(int i)
    	{
    		return this.s.charAt((start+i) % length);
    	}
    	
    }

}
