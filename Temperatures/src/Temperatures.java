//two dimensional array to store min and max temperaturesfor the whole year
import java.util.Random;//included for testing
import java.util.Scanner;

//Required methods: inputTempForMonth, inputTempForYear, calculateAverageHigh, calculateAverageLow, findHighestTemp, findLowestTemp, 

public class Temperatures {
	
	private static Random random = new Random();//Test use: apply random numbers from 0-150
	private static final int MONTHS = 12;//Constant for 1st dimension of 2d array
	private static final int TEMPS = 2;//Constant for 2nd dimension of 2d array, sorting high and low temperatures.
	private static int temp[][] = new int [MONTHS][TEMPS];//Creating 2d array
	private static int option;//Test use: allows user to select 2 modes of auto-generated values
	private static String yesNo;//Test use: Auto-generated values vs manual input.
	
	public static void main(String[] args) throws InterruptedException{//Interrupt for sleep.  Makes viewing more pleasing.
		System.out.println("Welcome to the temperature statistics analyzer.\n\n\n\n\n");
		Thread.sleep(2000);
		System.out.println("Would you like to use auto-generated values? (Yes/No)");//Auto vs manual data entry.
		Scanner keyboard = new Scanner(System.in);
		yesNo = keyboard.next();
		if(yesNo.contains("Y")||yesNo.contains("y"))
			option = tempDataEntry();//Throws to auto data entry choices
		inputTempForYear();//Creates 12 variables for months, which calls intputTempForMonth which inputs 2 values for temperatures in the month
		Thread.sleep(1000);
		System.out.println("\n\n\n\nThe average low temperature for the year is " + calculateAverage(temp, 0));//1 method for calculateAverageHigh and low
		Thread.sleep(1000);
		System.out.println("The average high temperature for the year is " + calculateAverage(temp, 1));
		Thread.sleep(1000);
		System.out.println("\n\nThe lowest temp is " + findLowestTemp(temp));
		Thread.sleep(1000);
		System.out.println("The highest temp is " + findHighestTemp(temp));
	}
	
	public static int[][] inputTempForYear() {
		for(int i=0; i<MONTHS; i++){//12 times
			inputTempForMonth(temp, i);
		} return temp;
	}
	
	public static void inputTempForMonth(int[][] arrayTemp, int month) {
		String[] monthNames = { "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November", "December" };
//		int[] testTemps = { 40, -10, 55, 25, 60, 40, 88, 20, 72, 55, 95, 80, 97, 87, 110, 98, 79, 68, 31, 30, 58, -25, 32, -20 };
		int monthMin;
		Scanner keyboard = new Scanner(System.in);
		
		if(yesNo.contains("N")||yesNo.contains("n")){
			System.out.println("Please enter the low and high values for " + monthNames[month]);
			for(int i=0; i<TEMPS; i++){//2 times
			arrayTemp[month][i] = keyboard.nextInt();
			}
		}
		
		if(option==1){//Auto generate random positive numbers from 0-150.
			for(int i=0; i<TEMPS; i++){//2 times
				arrayTemp[month][i] = random.nextInt(150);
			}
		}
		else if(option==2){//Use the professor's test values.
			int[] highTemps = { 40, 55, 60, 88, 72, 95, 97, 110, 79, 31, 58, 32 };
			int[] lowTemps = { -10, 25, 40, 20, 55, 80, 87, 98, 68, 30, -25, -20 };
			arrayTemp[month][0] = lowTemps[month];
			arrayTemp[month][1] = highTemps[month];
		}
//		else if(option==3) {
//			
//			System.out.println("Please enter the low and high values for " + monthNames[month]);
//			
//			for(int i=0; i<TEMPS; i++){//2 times
//				arrayTemp[month][i] = keyboard.nextInt();
//				System.out.println("\t" + arrayTemp[0][0]);
//				System.out.println("\t" + arrayTemp[month][0]);
//				System.out.println("\t" + arrayTemp[0][i]);
//				System.out.println("\t" + arrayTemp[month][i]);
//			}
//			keyboard.close();
//		}
		
		if(temp[month][0]>temp[month][1]){
			monthMin = temp[month][1];
			temp[month][1] = temp[month][0];
			temp[month][0] = monthMin;
		}
	}
	
	public static int tempDataEntry(){
		Scanner keyboard = new Scanner(System.in);
		int option;
		
		System.out.println("\n\n\n\nPlease select from the following options by entering the option number:");
		System.out.println("\t1. Auto generate random positive numbers from 0-150.");
		System.out.println("\t2. Use the professor's test values.");
		
		while (true) {
			option = keyboard.nextInt();
			if( option==1 || option==2 ){
				break;
			}
			else
				System.out.println("Invalid option choice.  Please enter only the numbers 1, or 2.");
		}
		return option;
	}
	
	public static int calculateAverage(int[][] arrayTemp, int lowHigh){
		int temp=0;
		for (int i=0; i<MONTHS; i++){
			temp += arrayTemp[i][lowHigh];
		}
		return temp/12;
	}
	
	public static int findLowestTemp(int[][] arrayTemp) {
		int minimum = arrayTemp[0][0];
		for (int i = 0; i < MONTHS; i++) {
			if (arrayTemp[i][0] < minimum){// minimum check
				minimum = arrayTemp[i][0];
			}
		}
		return minimum;
	}
	
	public static int findHighestTemp(int[][] arrayTemp) {
		int maximum = arrayTemp[0][1];
		for (int i = 0; i < MONTHS; i++) {
			if (arrayTemp[i][1] > maximum){// minimum check
				maximum = arrayTemp[i][1];
			}
		}
		return maximum;
	}
}