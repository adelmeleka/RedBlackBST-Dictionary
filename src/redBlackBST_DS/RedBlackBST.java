package redBlackBST_DS;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.NoSuchElementException;

public class RedBlackBST<KEY extends Comparable,Value> {


    private Node root;
    private  final boolean RED = true;
    private  final boolean BLACK = false;
    private  final boolean LEFT = true;
    private  final boolean RIGHT = false;

    private class Node {
        private KEY key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(KEY key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {
    }

    public  int longestPath(){
        int length=longestpath(root);
        return length;
    }
    private int longestpath(Node h){
        if(h!=null){
            if (h.left==null&&h.right==null){
                return 1;
            }
            else if (h.left!=null&&h.right==null){
                return 1+longestpath(h.left);
            }
            else if (h.left==null&&h.right!=null){
                return 1+longestpath(h.right);
            }
            else if(h.left.size>h.right.size){
                return 1+longestpath(h.left);
            }
            else{
                return 1+longestpath(h.right);
            }
        }
        return 0;
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }
    public boolean isEmpty() {
        return root == null;
    }

    public Value get(KEY key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, KEY key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public boolean contains(KEY key) {
        return get(key) != null;
    }

    public void put(KEY key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, KEY key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val   = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    public Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(KEY key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node delete(Node h, KEY key) {
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }



    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
   
 private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public KEY min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public KEY max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }


    //Graphicaly Display the tree
    public ResizableCanvas draw(){
        ResizableCanvas resizableCanvas=new ResizableCanvas();
        return resizableCanvas;
    }
    public class ResizableCanvas extends Pane {
        private double  radius =50;

        private double totalWidth=0.0;
        private double totalhight=0.0;


        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> drawtree());
            heightProperty().addListener(evt -> drawtree());
        }
        private void drawtree(){
            double width = getWidth();
            double height = getHeight();
            this.getChildren().clear();
            draw(root,width,50);
        }

        private void draw(Node h, double x, double y) {
            if(h==null){
                return;
            }
            else {
                double offset;
                Circle c = new Circle();
                Text text = new Text(h.key.toString());
                Text size = new Text(String.valueOf(h.size));
                c.setFill(Color.TRANSPARENT);
                if(h.color==true){
                   c.setStroke(Color.RED);
                }
                else {
                    c.setStroke(Color.BLACK);
                }
                    c.setCenterX(x);
                    c.setCenterY(y);
                    c.setRadius(radius);
                    text.setX(x-radius/2);
                    text.setY(y);
                    size.setX(x-radius/2);
                    size.setY(y+radius/4);
                    this.getChildren().add(c);
                    this.getChildren().add(text);
                    this.getChildren().add(size);

                if (totalWidth > this.getMinWidth()) {
                    this.setMinWidth(totalWidth);
                }

                if (totalhight > this.getMinHeight()) {
                    this.setMinHeight(totalhight);
                }
                if (h.left!=null){
                    offset=(Math.log(h.left.size)/Math.log(2)+1e-10)*(radius*6)+(radius*2);
                    drawLine(x,y,offset,h.left.color,LEFT);
                    draw(h.left,x-offset,y+(radius*3/2));
                    totalWidth=x+offset;
                    totalhight = y +(radius*3/2);

                }
                if (h.right!=null){
                    offset=(Math.log(h.right.size)/Math.log(2)+1e-10)*(radius*6)+(radius*2);
                    drawLine(x,y,offset,h.right.color,RIGHT);
                    draw(h.right,x+offset,y+(radius*3/2));
                    totalWidth=x+offset;

                    totalhight = y +(radius*3/2);

                }
            }
        }

        private void drawLine( double x, double y,Double offset,Boolean color,Boolean place){
            Line line =new Line();
            if(color==RED){
                line.setStroke(Color.RED);
            }
            else{
                line.setStroke(Color.BLACK);
            }
            if(place ==LEFT){
                line.setStartX(x-radius*4/5);
                line.setStartY(y+radius/2);
                line.setEndX(x-offset+radius*2/3);
                line.setEndY(y+(radius*3/4));
                this.getChildren().add(line);
            }
            else{
                line.setStartX(x+radius*4/5);
                line.setStartY(y+radius/2);
                line.setEndX(x+offset-radius*2/3);
                line.setEndY(y+(radius*3/4));
                this.getChildren().add(line);
            }
        }
        @Override
        public boolean isResizable() {
            return true;
        }

    }
   
    
    //Delete Min <MODIFIED>
    private Node rotateLeftTree(Node h){

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
       
        x.size = h.size;
        h.size = 1 + size(h.left)+ size(h.right);
        
        return x;

    }
    private Node rotateRightTree(Node h){

        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left)+ size(h.right);
        return x;
    }
    private void flipColorsTree(Node h){

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;

    }
    private Node moveRedLeftTree(Node h){
    	// Assuming that h is red and both h.left and h.left.left
    	// are black, make h.left or one of its children red.
    	
    	if(h.left != null && h.right != null){
    		flipColorsTree(h);
    	
    	if (isRed(h.right.left)){
    		h.right = rotateRightTree(h.right);
    		h = rotateLeftTree(h);
    	}
    	
    	}
    	return h;
    }
    public void deleteMinTree(){
    	if (!isRed(root.left) && !isRed(root.right))
    		root.color = RED;
    	root = deleteMinTree(root);
    	if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMinTree(Node h){
    	
    	if (h.left == null)
    		return null;
    	
    	if (!isRed(h.left) && !isRed(h.left.left))
    		h = moveRedLeftTree(h);
    	h.size--;
    	h.left = deleteMinTree(h.left);
    	
    	if(h.left==null && h.right!=null ){
    		h.color = BLACK;
    		h.right.color = RED;
    	}
    	
    	return balanceTree(h);
    }
    private Node balanceTree(Node h){
    	
    	
    	if (isRed(h.right)) h = rotateLeftTree(h);
    	
    	if(h.left!= null && h.left.left == null && h.left.right != null){
    		System.out.println("da5al");
    		h.left = rotateLeftTree(h.left);
    		h.left.left.color = BLACK;
    	}
    	
    	return h;
    }
    
}
