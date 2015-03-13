import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main {

	/**
	 * @param args
	 * @return 
	 */
	
	public static ArrayList<Node> bfsArr = new ArrayList<Node>();
	
	public static void main(String[] args) {
		String inorder_str = "4,2,7,5,8,1,3,9,6,11,10";
		String postorder_str = "4,7,8,5,2,9,11,10,6,3,1";
		
		String[] inArr = inorder_str.split(",");
		String[] postArr = postorder_str.split(",");
		
		Node n = new Node(9999999);
		
		pasteRoot(inArr,0, inArr.length-1, n, postArr );
		
		trace("DONE");
		
		Queue bfs = new LinkedList<Node>();
		breadth(bfs, n);

	}
	
	public static void breadth(Queue bfs, Node n){
		bfsArr.add(n);

		System.out.print(n.num+", ");
		for(Node x : n.childrens){
			if(x != null){
				bfs.add(x);
			}
			
			
		}
		
		if(bfs.size()>0){
			breadth(bfs, (Node) bfs.remove());
		}
	}
	
	public static void pasteRoot(String[] ar, int s, int e, Node par, String[] post){
		trace(s+" -- "+e +"  i:"+Arrays.toString(ar));
		trace(s+" -- "+e +"  p:"+Arrays.toString(post));
		if(e-s == 0){
			// these are leaves
			Node l = new Node(Integer.parseInt(ar[s]));
			
			par.addChild(l);
			
			trace("END ONE"+ar[s]);
		}else if(e-s < 0){
			trace("empty");
		}else{
			int mNum = Integer.parseInt( post[post.length-1] );
			Node middle = new Node(mNum);
			
			int mpos = findIndex(ar, mNum);
			
			trace("mpos"+mpos + "   mnum"+mNum+"  e:"+e+" s:"+s);
			
			par.addChild(middle);
			
			trace(s+" - "+ (mpos+(e-mpos))+"   post:"+Arrays.toString(post));
			//String[] newPostArrL = Arrays.copyOfRange(post,s, mpos );
			String[] newPostArrL = Arrays.copyOfRange(post,0, mpos-s );
			trace("xxL:"+Arrays.toString(newPostArrL));
			pasteRoot(ar, s, mpos-1, middle, newPostArrL);
			
			//String[] newPostArrR = Arrays.copyOfRange(post, mpos, mpos+(e-mpos) );
			String[] newPostArrR = Arrays.copyOfRange(post, newPostArrL.length, post.length-1 );
			trace("R post:"+Arrays.toString(newPostArrR) + " mNum:"+mNum+"  e:"+e);
			
			pasteRoot(ar, mpos+1, e, middle, newPostArrR);	
		}
	}
	
	public static int findIndex(String[] ar, int tofind){
		int n = -99;
		for(int i = 0;i<ar.length; i++){
			if(Integer.parseInt(ar[i]) == tofind){
				n = i;
				break;
			}
		}
		return n;
	}
	
	public static void trace(String n){
		System.out.println(n);
	}
}
class Node{
	public int num;
	public Node[] childrens = new Node[2];

	public Node(int n){
		num = n;
	}
	public void addChild(Node n){
		if(childrens[0] == null){
			childrens[0] = n;
		}else{
			childrens[1] = n;
		}
	}

}
