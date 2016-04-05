import java.io.*;
import java.util.*;
 
public class Apriori extends Observable
{
	public static void main(String[] args) throws Exception
	{
		Apriori ap = new Apriori(args);
	}
 
	List<int[]> co = new ArrayList<int[]>();
	List<int[]> items ;
	String FileName;
	int n;
	int fields;
	double sup;
	double conf;
	boolean usedAsLibrary = false;
	
	public Apriori(String[] args) throws Exception
	{
		readFile(args);
		start();
	}
	
	private void readFile(String[] args)
	{
		try
		{
		System.out.println("Enter Full File path for the txt file .. ");
		BufferedReader fp= new BufferedReader(new InputStreamReader(System.in));
		FileName = fp.readLine();
		
		System.out.println("Enter Min Support count for algorithm. (Eg: 0.3) ");
		BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
		sup=Double.parseDouble(sc.readLine()); 
		
		System.out.println("Enter confidence for algorithm. (Eg: 0.7) ");
		BufferedReader cc= new BufferedReader(new InputStreamReader(System.in));
		conf=Double.parseDouble(cc.readLine()); 
		
		BufferedReader br = new BufferedReader(new FileReader(FileName));
		while (br.ready())
		{
			String line=br.readLine();
			fields++;
			StringTokenizer t = new StringTokenizer(line,","); 
			while (t.hasMoreTokens())
			{
				int x = Integer.parseInt(t.nextToken());
				if (x+1>n) n=x+1;
			}
		}
		outputConfig();
		}catch(Exception e){
			System.out.println("\n INVALID INPUTS \n"
					+ "Please give valid inputs \n"
					+ "1. Only numerical data in text format is accepted. \n"
					+ "2. Support value should be in decimal form (eg: 0.3).\n"
					+ "3. Confidence value should be in decimal form (eg 0.7).\n"
					+ "");	
				
			}
			finally 
			{
				System.out.println("File  is Read");
			}
				
	}
	
	private void start() throws Exception
	{
		long start = System.currentTimeMillis();

		generateItemset();
		int count=1;
		int nbFrequentSets=0;
		while (items.size()>0)
		{
			calculateFrequentitems();
			if(count!=1)
			{
				getRules();
			}
			
			if(items.size()!=0)
			{
				nbFrequentSets+=items.size();
				System.out.println("Found "+items.size()+" frequent "+ count +" items of size  (with support "+(sup*100)+"%)\n \n ");
				generateItemsetsAgain();
			}
			
			else if(items.size()==0)
			{
				
				System.out.println("\n NULL for  "+count+" freequency ItemSets");
			}
			count++;
		}

		long end = System.currentTimeMillis();
		System.out.println("\nExecution time is: "+((double)(end-start)/1000) + " seconds.");
		System.out.println("Algorithm Terminated at Iteration "+Math.round(fields*sup));
		System.out.println("Apriori Algorithm Ended");
		

	}

	private void ifFrequent(int[] itemset, int support)
	{
		
		
		if (usedAsLibrary)
		{
			this.setChanged();
			notifyObservers(itemset);
		}
		else
		{
			System.out.println(Arrays.toString(itemset) + " Appeared "+support+" times in the data set");
			

			//System.out.format("%15s%10s%15s",Arrays.toString(itemset), (count[i] / (double) (fields), ((support / (double) fields))+"\n");
		}
		
	}
	
private void ifRules(int[] itemset, int support)
	{
		
		
		if (usedAsLibrary)
		{
			this.setChanged();
			notifyObservers(itemset);
		}
		else
		{

			System.out.println("Association rules for "+Arrays.toString(itemset) + " is accepted because it has crossed the minimum Confidence "+(conf*100)+"%");
			

			//System.out.format("%15s%10s%15s",Arrays.toString(itemset), (count[i] / (double) (fields), ((support / (double) fields))+"\n");
		}
		
	} 

	private void generateItemset()
	{
		//System.out.println("Generating Freequent1 Itemsets.......");
		items = new ArrayList<int[]>();
		for(int i=0; i<n; i++)
		{
			int[] cand = {i};
			items.add(cand);
			
		}
	}

	private void generateItemsetsAgain()
	{
		int currentSizeOfitems = items.get(0).length;

		HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>();
		for(int i=0; i<items.size(); i++)
		{
			for(int j=i+1; j<items.size(); j++)
			{
				int[] X = items.get(i);
				int[] Y = items.get(j);
				assert (X.length==Y.length);
				int [] newCand = new int[currentSizeOfitems+1];
				for(int s=0; s<newCand.length-1; s++)
				{
					newCand[s] = X[s];
				}
				int ndifferent = 0;
				for(int s1=0; s1<Y.length; s1++)
				{
					boolean found = false;
					for(int s2=0; s2<X.length; s2++)
					{
						if (X[s2]==Y[s1])
						{
							found = true;
							break;
						}
					}
					if (!found)
					{ 
						ndifferent++;
						newCand[newCand.length -1] = Y[s1];
						
					}
				}
				assert(ndifferent>0);
				if (ndifferent==1)
				{

					Arrays.sort(newCand);
					tempCandidates.put(Arrays.toString(newCand),newCand);
				}
			}	
		}
		items = new ArrayList<int[]>(tempCandidates.values());
		System.out.println("Created "+items.size()+" Freequency2 Item Sets "+(currentSizeOfitems+1));
	}
 
	private void calculateFrequentitems() throws Exception
	{
		System.out.println("----------------------------------------------------------");
		System.out.println("Generating "+items.get(0).length+" Freequency Itemsets ");
		System.out.println("----------------------------------------------------------");
		List<int[]> frequentCandidates = new ArrayList<int[]>(); 

		boolean match;
		int count[] = new int[items.size()];

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName)));
		boolean[] t = new boolean[n];
		for (int i = 0; i < fields; i++)
		{
			String line = br.readLine();
			line2booleanArray(line, t);

			for (int c = 0; c < items.size(); c++)
			{
				match = true;
				int[] cand = items.get(c);
				for (int xx : cand)
				{
					if (t[xx] == false)
					{
						match = false;
						break;
					}
				}
				if (match)
				{
					count[c]++;
				}
				
			}
		}
		br.close();
		System.out.println("Deleting all the transactions with support count less than given support count "+"("+(sup*100)+"%)\n");
		//System.out.format("%15s%15s%15s","Item Sets", "Support", "Confidence \n");
		
		for (int i = 0; i < items.size(); i++)
		{
			
		
			if ((count[i] / (double) (fields)) >= sup)
			{
				ifFrequent(items.get(i),count[i]);
				frequentCandidates.add(items.get(i));
				
				co.add(items.get(i));
	
				
			}
		
		}
		items = frequentCandidates;
	}
	
	
	
	private void getRules() throws Exception
	{
		System.out.println("\n\n");
		List<int[]> rules = new ArrayList<int[]>(); 

		boolean match;
		int count[] = new int[items.size()];

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName)));
		boolean[] t = new boolean[n];
		for (int i = 0; i < fields; i++)
		{
			String line = br.readLine();
			line2booleanArray(line, t);

			for (int c = 0; c < items.size(); c++)
			{
				match = true;
				int[] cand = items.get(c);
				for (int xx : cand)
				{
					if (t[xx] == false)
					{
						match = false;
						break;
					}
				}
				if (match)
				{
					count[c]++;
				}
				
			}
		}
		br.close();
		
		for (int i = 0; i < items.size(); i++)
		{
			if ((count[i] / (double) (fields)) >= conf)
			{
				if ((count[i] / (double) (fields)) >= sup)
				{
			    ifRules(items.get(i),count[i]);
				rules.add(items.get(i));
				}
			}
		
		}
		items = rules;
	}
	
	private void outputConfig()
	{
		System.out.println("Input configuration: "+n+" items, "+fields+" transactions, "+"Support "+sup+"\n");
	}
	
	private void line2booleanArray(String line, boolean[] trans)
	{
		Arrays.fill(trans, false);
		StringTokenizer stFile = new StringTokenizer(line, ",");
		while (stFile.hasMoreTokens())
		{
			int parsedVal = Integer.parseInt(stFile.nextToken());
			trans[parsedVal]=true;
		}
	}

	
	
	}