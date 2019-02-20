package com.test;

import java.io.*;
import java.util.*;

class MinCost_pramp {
 
  static class Node {
      
    int cost;
    Node[] children;
    Node parent;

    Node(int cost) {
      this.cost = cost;
      children = null;
      parent = null;
    }
  }

  static class SalesPath {
        
    
     int getCheapestCost(Node node) {
      
       if(node.children == null) {
         return node.cost;
       }
       
       int minCost = Integer.MAX_VALUE;
       
       for(int i=0; i<node.children.length ; i++) {
         int returnvalue = getCheapestCost(node.children[i]);
         if(returnvalue < minCost) {
           minCost = returnvalue;
         }
       }
       
       return minCost+ node.cost;
      
    }
  }
    
  /*********************************************
   * Driver program to test above method     *
   *********************************************/

  public static void main(String[] args) {

  }
}
