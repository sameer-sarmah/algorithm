package algorithm.backtracking;

import java.util.Arrays;

public class GraphColoring {
    int graph[][] = 
    	 {  {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0},
        };
    int[] colors=new int[graph.length];
    enum Color{
        RED(2),GREEN(3),BLUE(4);
        private final int id;
        Color(int id) { this.id = id; }
        public int getValue() { return id; }
    }
    
    public static void main(String [] args)
    {
        GraphColoring gc=new GraphColoring();
        gc.colorInitialization();
        gc.assignColor(0);
    }
    
    public void colorInitialization()
    {
        for(int i=0;i<graph.length;i++)
            colors[i]=0;
        
    }
    
    public void assignColor(int node)
    {   
        if(node<graph.length)
        {
        for(int i=0;i<graph[node].length;i++)
        {
            if(graph[node][i]==1)
            {
                System.out.println(node+","+i+" are connected");
                if(colors[node]==colors[i])
                {
                System.out.println("Change Color");
                colors[i]=colors[node]+1;
                }
                
            }
        }
        System.out.println(Arrays.toString(colors));
        assignColor( ++node);
        }
        
    }
}
