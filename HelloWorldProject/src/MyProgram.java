/*
 * Drew D 
 * 1/13/25
 * PD3
 * StackQueue Train Program
 */
import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MyProgram {
	public static int val = 0;
	static Stack[] tracks;
	static Queue trackZero;
	static Queue<Train> trackOne;
	static int[] weights;
	static int[] weightlimits;
	public static void main(String[] args) {

		int limitTrackA = 100000, limitTrackB = 100000, limitTrackC = 100000;
	
		Scanner x = new Scanner(System.in);

		try{
			File f = new File("HelloWorldProject/src/data.txt");
			x = new Scanner (f);
			// String name = x.nextLine();
			// System.out.println(name);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		weightlimits = new int[3];
		weightlimits[0] = limitTrackA;
		weightlimits[1] = limitTrackB;
		weightlimits[2] = limitTrackC;
		weights = new int[4];
		tracks = new Stack[4];
		tracks[0] = new Stack<Train>();
		tracks[1] = new Stack<Train>();
		tracks[2] = new Stack<Train>();
		tracks[3] = new Stack<Train>();
		trackZero = new LinkedList();
		trackOne = new LinkedList();
		String input = x.nextLine();
		while (!input.equals( "END")) {
			if(input.indexOf("CAR")!= -1)
				trackZero.add(new Train(input, x.nextLine(),x.nextLine(), x.nextLine(), x.nextInt(),x.nextInt()));
			else if(input.indexOf("ENG") !=-1)
				trackZero.add(new Engine(input, x.nextLine()));
			input = x.nextLine();
		}//end while
		//System.out.println(trackZero);
		processTrack(trackZero);
		processTrack(trackOne);
	}//end main 
	public static void processTrack(Queue track)
	{
		while(!track.isEmpty())
		if(track.peek() instanceof Engine)
		{
			Engine toDepart = (Engine)track.remove();
			depart(tracks[destinationToTrackNum(toDepart.getDestination())],toDepart);
		}else
		{
			Train toTransfer = (Train)track.remove();
			if(toTransfer.getMiles() >= 700)
			{
				toTransfer.setMiles(100);
				trackOne.add(toTransfer);
			}else
			{
				int moveTo = destinationToTrackNum(toTransfer.getDestination());
				weights[moveTo] += toTransfer.getWeight();
				if(moveTo != 3 && weights[moveTo]>weightlimits[moveTo]){
					depart(tracks[moveTo], new Engine("ENG0000",trackNumTodestination(moveTo)));
					weights[moveTo] = toTransfer.getWeight();
				}//end if 
				tracks[moveTo].push(toTransfer);
				
			}//end if else
		}//end if/else
	}//end processTrack
	public static void depart(Stack track, Engine front)
	{
		System.out.println(front.getName() + " leaving for "+front.getDestination() + " with the following cars:");
		while(!track.isEmpty())
		{
			Train removedTrain = (Train)track.pop();
			System.out.println(removedTrain.getName() + " containing "+ removedTrain.getProduct());
		}//end while
		weights[destinationToTrackNum(front.getDestination())] = 0;
	}//end depart
	public static void end()
	{
		depart(tracks[0],new Engine("ENG0000",trackNumTodestination(0)));
		depart(tracks[1],new Engine("ENG0000",trackNumTodestination(1)));
		depart(tracks[2],new Engine("ENG0000",trackNumTodestination(2)));
		while(!tracks[3].isEmpty())
		{
			Train removedTrain = (Train)tracks[3].pop();
			System.out.println(removedTrain.getName() + " containing "+ removedTrain.getProduct());
		}//end while
	}//end end
	public static int destinationToTrackNum(String destination)
	{
		if(destination.equals("Trenton"))
			return 0;
		else if(destination.equals("Charlotte"))
			return 1;
		else if (destination.equals("Baltimore"))
			return 2;
		else
			return 3;
	}//end destinationToTrackNum
	public static String trackNumTodestination(int tracknum)
	{
		if(tracknum == 0)
			return "Trenton";
		else if(tracknum == 1)
			return "Charlotte";
		else if (tracknum == 2)
			return "Baltimore";
		else
			return "Other";
	}//end destinationToTrackNum
}//end class
