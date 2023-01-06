package org.tuc.main;

import java.io.IOException;


import org.tuc.avlTree.AVLTree;
import org.tuc.bPlusTree.BPlusConfiguration;
import org.tuc.bPlusTree.BPlusTree;
import org.tuc.bPlusTree.BPlusTreePerformanceCounter;
import org.tuc.bTree.BTree;
import org.tuc.bTree.BTreeStatistics;
import org.tuc.misc.FileReader;
import org.tuc.misc.InvalidBTreeStateException;

public class MainClass {
	
	private static String FILENAME = "keys_1000000_BE.bin";
	private static String FILENAME_DEL = "keys_delete_100_BE.bin"; 
	private static String FILENAME_INS = "keys_insert_100_BE.bin"; 
	private static String FILENAME_SEARCH = "keys_search_100_BE.bin"; 
	
	//btree degrees
	private static int BTREE_DEGREE_3 = 3;
	private static int BTREE_DEGREE_64 = 64;
	
	
	private static int KEYS = 100;
	
	private static int KEYS1m   = 1000000;
	private static int KEYS100k = 100000;
	private static int KEYS200k = 200000;
	private static int KEYS300k = 300000;
	private static int KEYS400k = 400000;
	private static int KEYS500k = 500000;
	private static int KEYS600k = 600000;
	private static int KEYS700k = 700000;
	private static int KEYS800k = 800000;
	private static int KEYS900k = 900000;
	
	private static int btreeplus_keysize = 8;
	private static int btreeplus_entrysize = 4;

	
	public static void main(String[] args) {
		
		BTree btree3 = null;
		BTree btree64 = null;
		AVLTree avltree = null;
		BPlusTree bplustree128 = null;			
		BPlusTree bplustree256 = null;			
		
		BPlusConfiguration btConf128 = null;
		BPlusConfiguration btConf256 = null;
		
		BPlusTreePerformanceCounter bPerf128 = null;
		BPlusTreePerformanceCounter bPerf256 = null;
		
		MainClass main = new MainClass();
		
		System.out.println("\n" + main.toString());
		
		//open file of 1 million keys
		FileReader fr = new FileReader(FILENAME);
		
		//open delete 100 , insert 100 , search 100 files
		FileReader del = new FileReader(FILENAME_DEL);
		FileReader ins = new FileReader(FILENAME_INS);
		FileReader search = new FileReader(FILENAME_SEARCH);

		
		if (fr.isEmpty() || del.isEmpty() || ins.isEmpty() || search.isEmpty()) {
			System.out.println("values NOT loaded");
		}
		else {
			int[] values = fr.getValues();
			int[] delete_values = del.getValues();
			int[] insert_values = ins.getValues();
			int[] search_values = search.getValues();
			
			//======================================
			//fill all data structures with 100 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_100k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_100k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 100K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS100k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "100K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "100K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "100K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			
			//======================================

			//======================================
			//fill all data structures with 200 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);
			
			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_200k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_200k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 200K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS200k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "200K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "200K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "200K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 300 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_300k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_300k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 300K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS300k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "300K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "300K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "300K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 400 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_400k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_400k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 400K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS400k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "400K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "400K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "400K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 500 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_500k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_500k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 500K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS500k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "500K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "500K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "500K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 600 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_600k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_600k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 600K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS600k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "600K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "600K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "600K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 700 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_700k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_700k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 700K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS700k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "700K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "700K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "700K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 800 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_800k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_800k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 800K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS800k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "800K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "800K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "800K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 900 thousand integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_900k.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_900k.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 900K keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS900k);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "900K");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "900K");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "900K");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			//======================================
			//fill all data structures with 1 Million integers
			//--------------------------------------
			
			btree3 = new BTree(BTREE_DEGREE_3);
			btree64 = new BTree(BTREE_DEGREE_64);
			avltree = new AVLTree();
			
			//b+ tree
			
			btConf128 = new BPlusConfiguration(128,btreeplus_keysize,btreeplus_entrysize);
			btConf256 = new BPlusConfiguration(256,btreeplus_keysize,btreeplus_entrysize); 
			
			bPerf128 = new BPlusTreePerformanceCounter(true);
			bPerf256 = new BPlusTreePerformanceCounter(true);

			try {
				bplustree128 = new BPlusTree(btConf128,"rw+","tree128_1m.bin",bPerf128);
				bplustree256 = new BPlusTree(btConf256,"rw+","tree256_1m.bin",bPerf256);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
						
			System.out.print("\nCreating all data structures for 1 M keys . . .  ");
			main.ADTs_Initialization(values, btree3, btree64, bplustree128, bplustree256, avltree, KEYS1m);
			System.out.println(" OK");		
			main.insert100loop(insert_values, btree3, btree64, bplustree128, bplustree256, avltree, "1 M");			
			main.delete100loop(delete_values, btree3, btree64, bplustree128, bplustree256, avltree, "1 M");
			main.search100loop(search_values, btree3, btree64, bplustree128, bplustree256, avltree, "1 M");
			
			btree3 = null; btree64 = null; avltree = null; bplustree128 = null; bplustree256 = null;
			
			btConf128 = null; btConf256 = null;
			bPerf128 = null; bPerf256 = null;
			
			System.out.println("\nforce Garbage collector");
			System.gc();
			System.out.println();
			//======================================
			
			
			
			
		}
		
		//close input stream
		fr.close();
		del.close();
		ins.close();
		search.close();
	}//main
	

	@Override
	public String toString() {
		return "||ABSTRACT DATA TYPES - PROJECT 3 - MICHAILIDIS STERGIOS 2020030080||\n";
	}
	
	public void prompt (String number_of_keys, String type_of_process) {
		System.out.println("\n============COUNTERS FOR " + type_of_process + " 100 IN " + number_of_keys + " KEYS============");
	}
	
	//print all for insertion
	public void insert100loop(int[] insertValues, BTree btree3,BTree btree64,BPlusTree bplustree128,BPlusTree bplustree256,AVLTree avltree, String num_of_keys) {
		//first do the btree of degree 3, then reset the counters and do one for the remaning adts
		//if its done simultaneously , counters in static class BTreeStatistics get mixed up.
		for(int i = 0; i<KEYS; i++) {
			btree3.insert(insertValues[i]);
		}
		
		this.prompt(num_of_keys, "INSERTION");
		System.out.println("BTREE OF ||DEGREE 3|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));	
		BTreeStatistics.reset();
		
		for(int j = 0; j<KEYS; j++) {
			btree64.insert(insertValues[j]);
			try {
				bplustree128.insertKey(insertValues[j], "1", false);
				bplustree256.insertKey(insertValues[j], "1", false);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
			avltree.insert(avltree.root, insertValues[j]);
		}
		System.out.println("BTREE OF ||DEGREE 64|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));
		System.out.println("AVL TREE COMPARISONS : " +  (avltree.getASSIGNMENT_COUNT() + avltree.getCOMPARISON_COUNT())  );
		System.out.println("B+TREE ||128|| DISC ACCESSES : " + 
						  ( bplustree128.getPerformanceClass().getPageReads() + bplustree128.getPerformanceClass().getPageWrites())  );
		System.out.println("B+TREE ||256|| DISC ACCESSES : " + 
				  ( bplustree256.getPerformanceClass().getPageReads() + bplustree256.getPerformanceClass().getPageWrites())  );
		
		//reset counters
		BTreeStatistics.reset();
		avltree.resetCounters();
		bplustree128.getPerformanceClass().startPageTracking();
		bplustree256.getPerformanceClass().startPageTracking();
	}
	
	//print all for deletion
	public void delete100loop(int[] deleteValues, BTree btree3,BTree btree64,BPlusTree bplustree128,BPlusTree bplustree256,AVLTree avltree, String num_of_keys) {
		//first do the btree of degree 3, then reset the counters and do one for the remaning adts
		//if its done simultaneously , counters in static class BTreeStatistics get mixed up.
		for(int i = 0; i<KEYS; i++) {
			btree3.remove(deleteValues[i]);
		}
		
		this.prompt(num_of_keys, "DELETION");
		System.out.println("BTREE OF ||DEGREE 3|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));	
		BTreeStatistics.reset();
		
		for(int j = 0; j<KEYS; j++) {
			btree64.remove(deleteValues[j]);
			try {
				bplustree128.deleteKey(deleteValues[j], false);
				bplustree256.deleteKey(deleteValues[j], false);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
			avltree.deleteNode(avltree.root, deleteValues[j]);
		}
		System.out.println("BTREE OF ||DEGREE 64|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));
		System.out.println("AVL TREE COMPARISONS : " +  (avltree.getASSIGNMENT_COUNT() + avltree.getCOMPARISON_COUNT())  );
		System.out.println("B+TREE ||128|| DISC ACCESSES : " + 
				  ( bplustree128.getPerformanceClass().getPageReads() + bplustree128.getPerformanceClass().getPageWrites())  );
		System.out.println("B+TREE ||256|| DISC ACCESSES : " + 
				  ( bplustree256.getPerformanceClass().getPageReads() + bplustree256.getPerformanceClass().getPageWrites())  );

		//reset counters
		BTreeStatistics.reset();
		avltree.resetCounters();
		bplustree128.getPerformanceClass().startPageTracking();
		bplustree256.getPerformanceClass().startPageTracking();
	}
	
	//print all for search
	public void search100loop(int[] searchValues, BTree btree3,BTree btree64,BPlusTree bplustree128,BPlusTree bplustree256,AVLTree avltree, String num_of_keys) {
		//first do the btree of degree 3, then reset the counters and do one for the remaning adts
		//if its done simultaneously , counters in static class BTreeStatistics get mixed up.
		for(int i = 0; i<KEYS; i++) {
			btree3.search(searchValues[i]);
		}
		
		this.prompt(num_of_keys, "SEACRH");
		System.out.println("BTREE OF ||DEGREE 3|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));	
		BTreeStatistics.reset();
		
		for(int j = 0; j<KEYS; j++) {
			btree64.search(searchValues[j]);
			try {
				bplustree128.searchKey(searchValues[j], false);
				bplustree256.searchKey(searchValues[j], false);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
			avltree.search(avltree.root, searchValues[j]);
		}
		System.out.println("BTREE OF ||DEGREE 64|| COMPARISONS : " + (BTreeStatistics.btree_assignments + BTreeStatistics.btree_comparisons));
		System.out.println("AVL TREE COMPARISONS : " +  (avltree.getASSIGNMENT_COUNT() + avltree.getCOMPARISON_COUNT())  );
		System.out.println("B+TREE ||128|| DISC ACCESSES : " + 
				  ( bplustree128.getPerformanceClass().getPageReads() + bplustree128.getPerformanceClass().getPageWrites())  );
		System.out.println("B+TREE ||256|| DISC ACCESSES : " + 
				  ( bplustree256.getPerformanceClass().getPageReads() + bplustree256.getPerformanceClass().getPageWrites())  );

		//reset counters
		BTreeStatistics.reset();
		avltree.resetCounters();
		bplustree128.getPerformanceClass().startPageTracking();
		bplustree256.getPerformanceClass().startPageTracking();
	}
	
	//initialise all data srtuctures
	public void ADTs_Initialization(int[] values, BTree btree3,BTree btree64,BPlusTree bplustree128,BPlusTree bplustree256,AVLTree avltree, int num_of_keys) {
		
		for (int i = 0; i<num_of_keys; i++) {
			btree3.insert(values[i]);
			btree64.insert(values[i]);
			avltree.insert(avltree.root, values[i]);
			
			try {
				bplustree128.insertKey(values[i],"1",false);
				bplustree256.insertKey(values[i], "1", false);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidBTreeStateException e) {
				e.printStackTrace();
			}
			
		}
		
		bplustree128.getPerformanceClass().startPageTracking();
		bplustree256.getPerformanceClass().startPageTracking();
				
		//reset comparison and assignment counters
		avltree.resetCounters();
		BTreeStatistics.reset();
	}
	
}
