package com.graph.dependencies;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * This application is responsible to populate the graph, traverse DFS and print in required format
 *
 */
public class App 
{
    public static String TOKEN = "->";

    public static void main( String[] args ) {

        Graph graph = new Graph();

        if(args.length != 1)
            throw new IllegalArgumentException("Location of graph input not provided. Usage: java com.graph.dependencies.App LOCATION_OF_GRAPH.TXT");

        String fileName = args[0];

        HashMap<Integer,String> vertices = new HashMap<Integer, String>();
        HashMap<Integer,String> edgesMap = new HashMap<Integer, String>();


        try {
            FileInputStream fileInputStreamtream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStreamtream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] tokens = line.split(TOKEN);

                vertices.put(tokens[0].hashCode(),tokens[0]);
                vertices.put(tokens[1].hashCode(),tokens[1]);
                edgesMap.put((tokens[0]+tokens[1]).hashCode(), line);
            }
            bufferedReader.close();
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        Vertex[] verticesArray = new Vertex[vertices.size()];

        int index=0;
        for (String value : vertices.values()) {

            verticesArray[index] = new Vertex("" + value);
            graph.addVertex(verticesArray[index]);
            index++;
        }

        System.out.println("Vertices: " + graph.vertexKeys());

        for (String line : edgesMap.values()){
            String[] tokens =line.split(TOKEN);
            graph.addEdge(graph.getVertex(tokens[0]), graph.getVertex(tokens[1]));

        }

        System.out.println("Edges: " + graph.getEdges());

        printDependencies(graph.getVertex("A"));

    }




    static void printDependencies(Vertex v){

        //System.out.print("\t");

        //System.out.println(v.getDirectDependencyCount());

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

            printDependencies(v.getDirectDependency(j).getTwo());

        }
    }
 }

