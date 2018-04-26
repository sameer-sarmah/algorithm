package tree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 Given a sorted list construct a tree with minimum height
 * */
public class MinimalTree {
public static void main(String[] args) {
	List<Integer> list=IntStream.rangeClosed(1, 7).boxed().collect(Collectors.toList());//sorted list
	TreeNode<Integer> root=createMinimalTree(list);
	new BFS<Integer>().bfs(root);
}

private static  TreeNode<Integer> assignLeftRight(TreeNode<Integer> parent,TreeNode<Integer> left,TreeNode<Integer> right){
	parent.setLeft(left);
	parent.setRight(right);
	left.setParent(parent);
	right.setParent(parent);
	return parent;
}

public static TreeNode<Integer> createMinimalTree(List<Integer> list){
	if(list == null) {
		return null;
	}
    else if(list.size()==1) {
		return new TreeNode<Integer>(list.get(0));
	}
	else {
		int mid = list.size()/2;
		TreeNode<Integer> leftNode=createMinimalTree(list.subList(0, mid));
		TreeNode<Integer> midNode=new TreeNode<Integer>(list.get(mid));
		TreeNode<Integer> rightNode=null;
		if(mid+1<list.size()) {
		rightNode=createMinimalTree(list.subList(mid+1,list.size()));
		}
		return assignLeftRight(midNode,leftNode,rightNode);
		
	}
	
}

}
