package bnb;

import java.util.*;

public class Main {
   
   public static void main(String[] args) {
   

      
      List<Item> items = new LinkedList<Item>();
      Item item = new Item();
      item.label = 1;
      item.value = 45;
      item.weight = 3;
      items.add(item);
      
      Item item2 = new Item();
      item2.label = 2;
      item2.value = 30;
      item2.weight = 5;
      items.add(item2);
      
      Item item3 = new Item();
      item3.label = 3;
      item3.value = 45;
      item3.weight = 9;
      items.add(item3);
      
      Item item4 = new Item();
      item4.label = 4;
      item4.value = 10;
      item4.weight = 5;
      items.add(item4);
      
      int capacity = 16;
      
      List<KnapsackSolver> solvers = new ArrayList<KnapsackSolver>();
      
      if (items.size() <= 20) 
      solvers.add(new BranchAndBoundSolver(items, capacity));
      
      for (KnapsackSolver solver : solvers) {
         System.out.println(solver.solve());
      }
   }
}
