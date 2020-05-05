import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	
	private static final int RADIX = 256;
	private static char[] orderedSequence = initSequence();

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode()
    {
    	// Initialize the ordered sequence using Stack structure
    	char[] sequence = orderedSequence.clone();

    	while(!BinaryStdIn.isEmpty())
    	{
    		char c = BinaryStdIn.readChar();
    		
    		// Finding postion of character
    		char position = 0;
    		while(sequence[position] != c)
    			position++;
    
    		BinaryStdOut.write(position);
    		
    		// Shifting the sequence
    		char popped = sequence[position];
    		char d = position;
    		for (int i = 0; i < position; i++)
    		{
    			sequence[d] = sequence[d-1];
    			d--;
    		}
    		sequence[0] = popped;
    	}
    	BinaryStdIn.close();
    	BinaryStdOut.close();
    }


	// apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode()
    {
    	
    	// Initializing the ordered sequence
    	char[] sequence = orderedSequence.clone();
    	    	
    	while(!BinaryStdIn.isEmpty())
    	{
    		char c = BinaryStdIn.readChar();
    		
    		BinaryStdOut.write(sequence[c]);
    		
    		char d = c;
    		for (char i=0; i < c; i++)
    		{
    			sequence[d] = sequence[d-1];
    			d--;
    		}
    		sequence[0] = c;
    	}
    	BinaryStdIn.close();
    	BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args)
    {
    	if (args[0].equals("-"))
    		encode();
    	if (args[0].equals("+"))
    		decode();
    }
    

    private static char[] initSequence() {
    	orderedSequence = new char[RADIX];
    	for (int i = 0; i < RADIX; i++)
    		orderedSequence[i] = (char) i;
    	return orderedSequence;
	}

}
