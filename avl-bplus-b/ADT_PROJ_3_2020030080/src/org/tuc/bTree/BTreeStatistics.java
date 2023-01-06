package org.tuc.bTree;

public class BTreeStatistics {
	
	public static int btree_comparisons = 0;
	public static int btree_assignments = 0;

	
	public static void reset() {
		btree_assignments = 0;
		btree_comparisons = 0;
	}
	
}
