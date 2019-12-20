public class DisjointSets {

	public DisjointSets(int numberOfElements) {
		set = new int[numberOfElements];
		for (int i = 0; i < set.length; i++)
			set[i] = -1;
	}

	public void union(int a, int b) {
		if (set[b] < set[a])
			set[a] = b;
		else {
			if (set[a] == set[b])
				set[a]--;
			set[b] = a;
		}
	}

	public int find(int x) {
		if (set[x] < 0)
			return x;
		else
			return set[x] = find(set[x]);
	}

	private int[] set;

	public static void main(String[] args) {
		int numberOfElements = 128;
		int numberOfElementsInSameSet = 16;

		DisjointSets ds = new DisjointSets(numberOfElements);
		int set1, set2;

		for (int k = 1; k < numberOfElementsInSameSet; k *= 2) {
			for (int j = 0; j + k < numberOfElements; j += 2 * k) {
				set1 = ds.find(j);
				set2 = ds.find(j + k);
				ds.union(set1, set2);
			}
		}

		for (int i = 0; i < numberOfElements; i++) {
			System.out.print(ds.find(i) + "*");
			if (i % numberOfElementsInSameSet == numberOfElementsInSameSet - 1)
				System.out.println();
		}
		System.out.println();
	}
}
