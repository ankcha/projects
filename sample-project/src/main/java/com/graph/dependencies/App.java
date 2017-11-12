package com.graph.dependencies;

//import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
//import org.apache.tinkerpop.gremlin.structure.*;
//import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
//import java.util.Iterator;


/**
 * This application is responsible to populate the graph, traverse DFS and print in required format
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Graph graph = new Graph();

        Vertex[] vertices = new Vertex[6];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex("" + i);
            graph.addVertex(vertices[i]);
        }

        graph.addEdge(vertices[1], vertices[2]);
        graph.addEdge(vertices[2], vertices[4]);
        graph.addEdge(vertices[2], vertices[3]);
        graph.addEdge(vertices[3], vertices[0]);
        graph.addEdge(vertices[3], vertices[5]);
        graph.addEdge(vertices[1], vertices[4]);

        printDependencies(vertices[1]);

    }


    static void printDependencies(Vertex v){

        System.out.print("\t");

        for (int j = 0; j < v.getDirectDependencyCount(); j++) {
            //System.out.print("|");

            if(!v.visited ) {

                System.out.println(v.getLabel());
            }
            if(j!=0) {
                // 2nd and + dependency = \

                System.out.print("\t\\_");
            }
            else{
                // dependency = |_
                System.out.print("\t|_");
            }

            Edge e = v.getDirectDependency(j);
            Vertex dependency = e.getTwo();

            v.visited = true;
            System.out.println(dependency.getLabel());
            dependency.visited=true;
            //System.out.println();
            printDependencies(v.getDirectDependency(j).getTwo());

        }
    }
 }

