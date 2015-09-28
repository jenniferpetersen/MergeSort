public class MergeSort {
	public static void main(String[] args) {
		int[] skaSorteras = {5,4,3,2,1};
		for (int i : skaSorteras) {
			System.out.print(i + " ");
		}
		System.out.println();

		int [] resultat = sortera(skaSorteras);
		for (int a : resultat) {
			System.out.print(a + " ");
		}
	}
	
	public static int[] sortera(int[] C) {
		int längd = C.length;
	
		if (längd <= 1) {
			return C;
		}
		
		int[] A = new int [längd/2];
		int[] B = new int [längd/2];
			
		int y = 0;
		for (int x = 0; x < längd/2; x++) {
			A[y] = C[x];
			y++;
		}
		
		int z = 0;
		for (int x = (längd/2); x >= (längd/2) && x < längd; x++) {
			B[z] = C[x];
			z++;
		}

		A = sortera(A);
		B = sortera(B);
		
		return merge(A, B);
	}
	
	public static int[] merge(int[] A, int[] B) {
		
		int[] resultat = new int[A.length + B.length];

		if (A.length != 0 && B.length != 0) {
			int y = 0;
			int z = 0;
			for (int x = 0; x < resultat.length; x++) {
					if(z >= B.length || y < A.length && A[y] <= B[z]) {
						resultat[x] = A[y];
						y++;
					}
					else {
						resultat[x] = B[z];
						z++;
					}
			}
		}
		
		return resultat;
	}

}
