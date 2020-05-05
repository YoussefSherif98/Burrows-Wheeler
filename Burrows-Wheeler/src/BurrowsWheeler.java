import java.util.Arrays;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform()
    {
    	
    	String in = BinaryStdIn.readString();
    	int length = in.length();
    			
    	char[] characters = new char[length];
    	characters[0] = in.charAt(length-1);
    	for (int i = 1; i < length ; i++)
    		characters[i] = in.charAt(i-1);
    	
    	CircularSuffixArray sortedSuffixes = new CircularSuffixArray(in);
    	
    	StringBuilder out = new StringBuilder();
    	int first = -1;
    	for (int i = 0; i < length; i++)
    	{
    		int index = sortedSuffixes.index(i);
    		out = out.append(characters[index]);
    		if (index == 0)
    			first = i;
    	}
    	
    	BinaryStdOut.write(first);
    	BinaryStdOut.write(out.toString());
    	BinaryStdOut.close();

    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform()
    {
    	int first = BinaryStdIn.readInt();
    	String s = BinaryStdIn.readString();
    	BinaryStdIn.close();
    	
    	char[] t = s.toCharArray();
    
    	char[] firstCharacters = t.clone();
    	Arrays.sort(firstCharacters);
    	
    	// Construction next[] using t[] and first
    	int[] next = constructNext(t, firstCharacters, first);
    	
    	// Writing original string to StdOut using next[] and first
    	int index = first;
    	for (int i = 0; i < t.length; i++)
    	{
    		BinaryStdOut.write(firstCharacters[index]);
    		index = next[index];
    	}
    	BinaryStdOut.close();
    }

	// if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args)
    {
    	
    	if (args[0].equals("-"))
    		transform();
    	if (args[0].equals("+"))
    		inverseTransform();


    }
    
    private static int[] constructNext(char[] t, char[] firstCharacters, int first) {
		
    	int[] next = new int[t.length];
    	boolean[] marked  = new boolean[t.length];
    	
    	for (int i = 0; i < t.length; i++)
    		marked[i] = false;
    	
    	for (int i = 0; i < t.length; i++)
    	{
    		char c = t[i];
    		int index = findFirstOccurence(c, firstCharacters, marked);
    		next[index] = i;
    	}
    	
		return next;
	}

	private static int findFirstOccurence(char c, char[] firstCharacters, boolean[] marked) {
		
		int lo = 0, hi = firstCharacters.length-1;
		boolean found = false;
		int index = (lo+hi) / 2;
		
		while (!found)
		{
			index = (lo+hi) / 2;
			char t = firstCharacters[index];
			
			if (t == c)
			{
				found = true;
				
				if (marked[index])
				{
					do
					{
						index++;
					}
					while(marked[index]);
				}
				else
				{
					do
					{
						index--;
					}
					while(index > 0 && firstCharacters[index] == c && !marked[index]);
					if (index<0 || firstCharacters[index] != c || marked[index])
						index++;
				}
				
			}
			else if (c > t)
				lo = index+1;
			else
				hi = index-1;
		}
				
		marked[index] = true;
		return index;
	}

}
