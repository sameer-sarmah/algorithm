package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {

	public static void main(String[] args) {
		String[] words ={"abandon","able","ability","above","abroad","absolute","abstract","accept","access"};
		TrieNode root = new TrieNode();
		for(String word:words) {
			addWordInTrie(word,0,root);
		}
		List<String> wordsRead = new ArrayList<String>();
		traverseWordsInTrie(root,wordsRead,"");
		System.out.println(wordsRead);
		List<String> wordsWithPrefix = getWordsWithPrefix(root,"abs");
		System.out.println(wordsWithPrefix);
	}
	
	private static void traverseWordsInTrie(TrieNode currentNode,List<String> words,String accumulated) {
		Set<Character> keys =currentNode.getStartCharToNode().keySet();
		if(currentNode.isEndOfWord()) {
			words.add(accumulated);
		}
		for(Character ch :keys) {
			traverseWordsInTrie(currentNode.getStartCharToNode().get(ch),words,accumulated +ch.toString());
		}

	}
	
	private static List<String> getWordsWithPrefix(TrieNode root,String prefix){
		List<String> wordsWithPrefix = new ArrayList<String>();
		getWordsWithPrefix(root,wordsWithPrefix,prefix,0);
		return wordsWithPrefix;
	}
	
	private static void getWordsWithPrefix(TrieNode currentNode,List<String> words,String prefix,int index){
		Set<Character> keys =currentNode.getStartCharToNode().keySet();
		
		if(index == prefix.length()) {
			for(Character ch :keys) {
				traverseWordsInTrie(currentNode.getStartCharToNode().get(ch),words,prefix +ch.toString());
			}
		}
		else {
			Character ch = prefix.charAt(index);
			if(currentNode.getStartCharToNode().containsKey(ch)) {
				getWordsWithPrefix(currentNode.getStartCharToNode().get(ch), words, prefix, index+1);
			}
			else {
				return;
			}
		}

	}
	
	private static void addWordInTrie(String word,int index,TrieNode currentNode) {
		Character ch = word.charAt(index);
		boolean isWordEnd = index == word.length()-1;
		System.out.println("creating node for '"+ch+"'");
		TrieNode childNode = createTrieNode(ch,currentNode,isWordEnd);
		if(!isWordEnd) {
			 addWordInTrie(word,index+1,childNode);
		}else {
			System.out.println("word '"+word+"' inserted in trie");
		}
	}
	
	private static TrieNode createTrieNode(Character ch,TrieNode currentNode,boolean isWordEnd) {
		//check if the character is present as key in the map
		if(!currentNode.getStartCharToNode().containsKey(ch)) {
			TrieNode node = new TrieNode();
			if(isWordEnd) {
				node.setEndOfWord(true);
			}
			currentNode.getStartCharToNode().put(ch, node);
			return node;
		}
		return currentNode.getStartCharToNode().get(ch);
	}
	
	static class TrieNode {
	
		private boolean endOfWord;
		private Map<Character,TrieNode> startCharToNode;
		
		public TrieNode() {
			this(false);
			startCharToNode= new HashMap<>();
		}
		
		public TrieNode(boolean endOfWord) {
			super();
			this.endOfWord = endOfWord;
		}
		public boolean isEndOfWord() {
			return endOfWord;
		}
		
		public boolean setEndOfWord(boolean endOfWord) {
			return this.endOfWord = endOfWord;
		}
		
		public Map<Character, TrieNode> getStartCharToNode() {
			return startCharToNode;
		}

		@Override
		public String toString() {
			return "TrieNode [endOfWord=" + endOfWord + ", startCharToNode=" + startCharToNode + "]";
		}
		
		
	}
}
