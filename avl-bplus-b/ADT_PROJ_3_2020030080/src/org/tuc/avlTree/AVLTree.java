package org.tuc.avlTree;

public class AVLTree {

	private int COMPARISON_COUNT;
	private int ASSIGNMENT_COUNT;
    public Node root;
    
    public AVLTree() {
    	this.root = null;
    	this.ASSIGNMENT_COUNT = 0;
    	this.COMPARISON_COUNT = 0;
    }
 
    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
 
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
 
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
                
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        this.ASSIGNMENT_COUNT+=2;
        
 
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
 
        // Return new root
        
        if(root == y) {
        	root = x;
            this.ASSIGNMENT_COUNT++;
        }
        
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        this.ASSIGNMENT_COUNT+=2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        
        if (x == root) {
        	root = y;
        	this.ASSIGNMENT_COUNT++;
        }
        
        return y;
    }
 
    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
 
        return height(N.left) - height(N.right);
    }
 
    public Node insert(Node node, int key) {
    	
        /* 1.  Perform the normal BST insertion */
    	this.COMPARISON_COUNT++;
        if (node == null) {
        	Node n = new Node(key);   
        	
        	this.COMPARISON_COUNT++;
        	if (root == null) {
        		
        		this.ASSIGNMENT_COUNT++;
        		root = n;
        		//System.out.println("FIRST ROOT INITIALISATION");
        	}
        	return n;
        }
         //return (new Node(key));
 
        this.COMPARISON_COUNT++;
        if (key < node.key) {
            node.left = insert(node.left, key);
        }
        else { 
        	this.COMPARISON_COUNT++;
        	if (key > node.key) {
        		node.right = insert(node.right, key);
        	}
        	else // Duplicate keys not allowed
        		return node;
        }
 
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                              height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);
 
        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 ) {
        	this.COMPARISON_COUNT++;
        	if (key < node.left.key) {
            	return rightRotate(node);
        	}
        }
        // Right Right Case
        if (balance < -1) {
        	this.COMPARISON_COUNT++;
        	if (key > node.right.key ) {
        		return leftRotate(node);
        	}
        }
        // Left Right Case
        if (balance > 1) {
        	this.COMPARISON_COUNT++;
        	if(key > node.left.key) {
        		this.ASSIGNMENT_COUNT++;
	            node.left = leftRotate(node.left);
	            return rightRotate(node);
        	}
        }
 
        // Right Left Case
        if (balance < -1) {
        	this.COMPARISON_COUNT++;
        	if (key < node.right.key ) {
        		this.ASSIGNMENT_COUNT++;
	            node.right = rightRotate(node.right);
	            return leftRotate(node);
        	}
        }
 
        /* return the (unchanged) node pointer */
        return node;
    }
 
    // A utility function to print pre-order traversal
    // of the tree.
    // The function also prints height of every node
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    private class Node {
        int key, height;
        Node left, right;
     
        Node(int d) {
            key = d;
            height = 1;
        }
    }
    
    public Node deleteNode(Node root, int key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
    	this.COMPARISON_COUNT++;
        if (root == null)
            return root;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
    	this.COMPARISON_COUNT++;
        if (key < root.key)
            root.left = deleteNode(root.left, key);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else {
        	this.COMPARISON_COUNT++;
        	if (key > root.key) {
        		this.ASSIGNMENT_COUNT++;
        		root.right = deleteNode(root.right, key);
        	}
	        // if key is same as root's key, then this is the node
	        // to be deleted
	        else
	        {
	 
	            // node with only one child or no child
	        	this.COMPARISON_COUNT+=2;
	            if ((root.left == null) || (root.right == null))
	            {
	                Node temp = null;
	                if (temp == root.left) {
	                	this.ASSIGNMENT_COUNT++;
	                    temp = root.right;
	                }
	                else {
	                	this.ASSIGNMENT_COUNT++;
	                    temp = root.left;
	                }
	                // No child case
	                if (temp == null)
	                {
	                	this.ASSIGNMENT_COUNT+=2;;
	                    temp = root;
	                    root = null;
	                }
	                else { // One child case
	                	this.ASSIGNMENT_COUNT++;
	                    root = temp; // Copy the contents of
	                }              // the non-empty child
	            }
	            else
	            {
	 
	                // node with two children: Get the inorder
	                // successor (smallest in the right subtree)
	                Node temp = minValueNode(root.right);
	 
	                // Copy the inorder successor's data to this node
	                this.ASSIGNMENT_COUNT++;
	                root.key = temp.key;
	 
	                // Delete the inorder successor
	                this.ASSIGNMENT_COUNT++;
	                root.right = deleteNode(root.right, temp.key);
	            }
	        }
        }
        // If the tree had only one node then return
        this.COMPARISON_COUNT++;
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
        	this.ASSIGNMENT_COUNT++;
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
 
        return root;
    }
    
    
    
    
    
    
    private Node minValueNode(Node node) {
        Node current = node;
        
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
        	this.COMPARISON_COUNT++;
        	current = current.left;
        }
        
        this.COMPARISON_COUNT++;
        
        return current;
	}

	//always start from root
	
	public int getCOMPARISON_COUNT() {
		return COMPARISON_COUNT;
	}

	public int getASSIGNMENT_COUNT() {
		return ASSIGNMENT_COUNT;
	}
 	
	public void setCOMPARISON_COUNT(int cOMPARISON_COUNT) {
		COMPARISON_COUNT = cOMPARISON_COUNT;
	}

	public void setASSIGNMENT_COUNT(int aSSIGNMENT_COUNT) {
		ASSIGNMENT_COUNT = aSSIGNMENT_COUNT;
	}
	
	//start from root
	public Node search (Node node,int key) {
		
		if (node == null) return null; //empty node
		
		this.COMPARISON_COUNT++;
		if (key < node.key) {
			node = search(node.left,key);
		} else {
			this.COMPARISON_COUNT++;
			if (key > node.key) {
				node = search(node.right,key);
			} else {
				this.COMPARISON_COUNT++;
				if (key == node.key) {
					return node;
				}
			}
		}	
		return node;
	}
	
	public Node search(int key) {
	    Node node = root;
	    
	    this.COMPARISON_COUNT++;
	    while (node != null) {
	    	this.COMPARISON_COUNT++;
	        if (node.key == key) {
	            break;
	        }
	        
			this.COMPARISON_COUNT++;
			this.ASSIGNMENT_COUNT++;
	        node = node.key < key ? node.right : node.left;
	    }
	    return node;
	}
	
	public void resetCounters() {
		this.setASSIGNMENT_COUNT(0);
		this.setCOMPARISON_COUNT(0);
	}
	
	
}
