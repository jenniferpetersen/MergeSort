import java.util.Scanner;
import java.io.*;

public class CountInversions {
	
	public static void main (String[] args) {
		int[] skaRäknas = new int[100000];
		try {
			Scanner scanner = new Scanner(new File("IntegerArray.txt"));
			int i = 0;
			while(scanner.hasNextInt()){
			   skaRäknas[i++] = scanner.nextInt();
			}
			scanner.close();
			System.out.println("Number of inversions: " + sortAndCount(skaRäknas).getInvLong());
			
		}
		catch (FileNotFoundException e1) {
			System.out.println("Filen hittades ej!");
		}
		
	}
	
	public static Par sortAndCount(int[] arr) {
		int längd = arr.length;
		if (längd <= 1) {
			return new Par(arr, 0);
		}
		else {
			int[] left = new int [längd/2];
			int[] right = new int [längd - left.length];
			
			for (int x = 0; x < left.length; x++) {
				left[x] = arr[x];
			}
			
			for (int x = 0; x < right.length; x++) {
				right[x] = arr[left.length + x];
			}

			Par leftPar = sortAndCount(left);
			Par rightPar = sortAndCount(right);
			
			Par resultat = mergeAndCount(leftPar.getArr(), rightPar.getArr());
			arr = resultat.getArr();
			
			long totalInversions = Long.valueOf(leftPar.getInvLong() + rightPar.getInvLong() + resultat.getInvLong());
			
			return (new Par(arr, totalInversions));
		}
	}
	
	public static Par mergeAndCount(int[] left, int[] right) {
		long inversions = 0;
		int längd = left.length + right.length;
		int[] mergeArr = new int[längd];
		
		if(left.length > 0 && right.length > 0) {
			int i = 0;
			int j = 0;
			for (int k = 0; k < längd; k++) {
				if ( j >= right.length || i < left.length && left[i] < right[j]) {
					mergeArr[k] = left[i];
					i++;
				}
				else {
					mergeArr[k] = right[j];
					inversions += ((left.length) - i);
					j++;
					
					if (inversions < 0) {
						System.out.println("Negativ i mergeAndCount");
					}
				}
			}
		}

		Par resultat = new Par(mergeArr, inversions);

		return resultat;
	}

}
