package adt.bst;

import adt.bt.BTNode;
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      return this.height(this.getRoot());
   }

   private int height(BTNode<T> root) {
      if (root.isEmpty()) {
         return -1;
      }
      if(this.height(root.getRight()) > this.height(root.getLeft())){
    	  return 1+this.height(root.getRight());
      }else{
    	  return 1+this.height(root.getLeft());
      }
   }

   @Override
   public BSTNode<T> search(T element) {
      if (element != null) {
         return this.search(this.getRoot(), element);
      }
      return null;
   }

   private BSTNode<T> search(BSTNode<T> root, T element) {
      BSTNode<T> findNode = new BSTNode<T>();
      if(!root.isEmpty()){
    	  if(element.compareTo(root.getData()) < 0){
    		  findNode = search((BSTNode<T>) root.getLeft(), element);
    	  }else if(element.compareTo(root.getData()) > 0){
    		  findNode = search((BSTNode<T>) root.getRight(), element);
    	  }else{
    		  findNode = root;
    	  }
      }
      	return findNode;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         this.insert(this.getRoot(), element);
      }
   }

   private void insert(BSTNode<T> root, T element) {
      if(root.isEmpty()){
    	  root.setData(element);
			BSTNode<T> nodeLeft = new BSTNode.Builder<T>().parent(root).build();
			BSTNode<T> nodeRight = new BSTNode.Builder<T>().parent(root).build();
    	  root.setLeft(nodeLeft);
    	  root.setRight(nodeRight);
      }else{
    	  if(element.compareTo(root.getData()) > 0){
    		  insert((BSTNode<T>) root.getRight(), element);
    	  }else if(element.compareTo(root.getData()) < 0){
    		  insert((BSTNode<T>) root.getLeft(), element);
    	  }
      }
   }

   @Override
   public BSTNode<T> maximum() {
	   if(this.root.isEmpty()){
		   return null;
	   }
	   		return maximum(this.getRoot());
   }

   private BSTNode<T> maximum(BSTNode<T> root) {
	   if(root.getRight().isEmpty()){
		   return root;
	   }else{
		   return maximum((BSTNode<T>) root.getRight());
	   }
   }

   @Override
   public BSTNode<T> minimum() {
      if(this.root.isEmpty()){
    	  return null;
      }
      	  return minimum(this.getRoot());
   }

   private BSTNode<T> minimum(BSTNode<T> root) {
	   if(root.getLeft().isEmpty()){
		   return root;
	   }else{
		   return minimum((BSTNode<T>) root.getLeft());
	   }
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> node = this.search(element);
      BSTNode<T> sucessor = null;
      if (!node.isEmpty()) {
         if (!node.getRight().isEmpty()) {
            sucessor = minimum((BSTNode<T>) node.getRight());
         } else {
            BSTNode<T> rootAux = (BSTNode<T>) node.getParent();
            sucessor = rootAux;
            while (rootAux != null && node.equals(rootAux.getRight())) {
               node = rootAux;
               rootAux = (BSTNode<T>) rootAux.getParent();
               sucessor = rootAux;
            }
         }
      }
      return sucessor;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> node = search(element);
      BSTNode<T> predecessor = null;

      if (!node.isEmpty()) {
         if (!node.getLeft().isEmpty()) {
            predecessor = maximum((BSTNode<T>) node.getLeft());

         } else {
            BSTNode<T> rootAux = (BSTNode<T>) node.getParent();
            predecessor = (BSTNode<T>) node.getParent();
            while (rootAux != null && node.equals(rootAux.getLeft())) {
               node = rootAux;
               rootAux = (BSTNode<T>) rootAux.getParent();
               predecessor = rootAux;
            }

         }
      }

      return predecessor;
   }

   @Override
   public T[] preOrder() {
      T[] elements = (T[]) new Integer[this.size()];
      preOrder(elements, 0, this.getRoot());
      return elements;
   }

   private int preOrder(T[] elements, int i, BSTNode<T> root) {
      if (!root.isEmpty()) {
         elements[i++] = root.getData();
         i = preOrder(elements, i++, (BSTNode<T>) root.getLeft());
         i = preOrder(elements, i++, (BSTNode<T>) root.getRight());
      }
      return i;
   }

   @Override
   public T[] order() {
      T[] elements = (T[]) new Integer[this.size()];
      order(elements, 0, this.getRoot());
      return elements;
   }

   private int order(T[] elements, int inicial, BSTNode<T> root) {
      if (!root.isEmpty()) {
         inicial = this.order(elements, inicial++, (BSTNode<T>) root.getLeft());
         elements[inicial++] = root.getData();
         inicial = this.order(elements, inicial++, (BSTNode<T>) root.getRight());
      }
      return inicial;
   }

   @Override
   public T[] postOrder() {
      T[] elements = (T[]) new Integer[this.size()];
      postOrder(elements, 0, this.getRoot());
      return elements;
   }

   private int postOrder(T[] elements, int i, BSTNode<T> root) {
      if (!root.isEmpty()) {
         i = postOrder(elements, i++, (BSTNode<T>) root.getLeft());
         i = postOrder(elements, i++, (BSTNode<T>) root.getRight());
         elements[i++] = root.getData();
      }
      return i;
   }

   @Override
   public void remove(T element) {
     if(element != null){
    	 BSTNode<T> node = search(element);
    	 remove(node);
     }
   }

   @SuppressWarnings("unchecked")
   private void remove(BSTNode<T> findRoot) {
	 if(!findRoot.isEmpty()){
		 if(findRoot.isLeaf()){
			 if(this.getRoot().equals(findRoot)){
				 root = new BSTNode<T>();
			 }else{
				 BSTNode<T> newParent = (BSTNode<T>) findRoot.getParent();
				 BSTNode<T> newAux = new BSTNode.Builder<T>().parent(newParent).build();
				 if(newParent.getLeft().equals(findRoot)){
					 newParent.setLeft(newAux);
				 }else if(newParent.getRight().equals(findRoot)){
					 newParent.setRight(newAux);
				 }
			 }
		 }else if(findRoot.getLeft().isEmpty()){
			 if(!findRoot.equals(this.getRoot())){
				 BSTNode<T> newParent = (BSTNode<T>) findRoot.getParent();
				 BSTNode<T> newNode = (BSTNode<T>) findRoot.getRight();
				 if(newParent.getLeft().equals(findRoot)){
					 newParent.setLeft(newNode);
				 }else if(newParent.getRight().equals(findRoot)){
					 newParent.setRight(newNode);
				 }
				 	newNode.setParent(newParent);

			 }else{
				 root = (BSTNode<T>) findRoot.getRight();
				 findRoot.getRight().setParent(null);
			 }
		 }else if(findRoot.getRight().isEmpty()){
			 if(!findRoot.equals(this.getRoot())){
				 BSTNode<T> newNode = (BSTNode<T>) findRoot.getLeft();
				 BSTNode<T> newParent = (BSTNode<T>) findRoot.getParent();
				 if(newParent.getLeft().equals(findRoot)){
					 newParent.setLeft(newNode);
				 }else if(newParent.getRight().equals(findRoot)){
					 newParent.setRight(newNode);
				 }
				 	newNode.setParent(newParent);
			 }else{
				 root = (BSTNode<T>) findRoot.getLeft();
				 findRoot.getLeft().setParent(null);
			 }
		 }else{
			 BSTNode<T> sucessor = sucessor(findRoot.getData());
			 remove(sucessor);
			 findRoot.setData(sucessor.getData());
		 }
	 }
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      if(!node.isEmpty()){
    	  result = 1+size((BSTNode<T>) node.getLeft())+size((BSTNode<T>) node.getRight());
      }
      	return result;
   }
   
   /* testando estatística de ordemmmmmmmmmmmmmmmmm */
   
   
   public T estatisticaDeOrdem(int k){
	   return this.oMetodo(this.getRoot(), k);
   }
   
   public T oMetodo(BSTNode<T> node, int k){
	   T result = null;
	   if(!node.isEmpty()){
		   if(size((BSTNode<T>) node.getLeft()) > k-1){
			   result = oMetodo((BSTNode<T>) node.getLeft(), k);
		   }else if(size((BSTNode<T>) node.getLeft()) == k-1){
			   result = node.getData();
		   }else{
			   result = oMetodo((BSTNode<T>) node.getRight(), k-1-size((BSTNode<T>) node.getLeft()));
		   }
	   }
	   	return result;
   }

}
