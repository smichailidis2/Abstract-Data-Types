package org.tuc.misc;

import org.tuc.avlTree.AVLTree;

public class TestMain {

	public static void main(String[] args) {
		
		AVLTree avl = new AVLTree();
		
		avl.insert(avl.root, 10);
		avl.insert(avl.root, 15);
		avl.insert(avl.root, 19);
		avl.insert(avl.root, 121);
		avl.insert(avl.root, 5);
		avl.insert(avl.root, 1);
		
		avl.preOrder(avl.root);
		
	}

}
