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
    public int depth = 0;

    public static void main( String[] args ) {

        Graph graph = new Graph();
        App a = new App();

        /* Validate Input */
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

                /* Validate Input */
                if(tokens[0].isEmpty() || tokens[1].isEmpty())
                    throw new Exception("Contents of the input file " +fileName+" are not valid.");

                vertices.put(tokens[0].hashCode(),tokens[0]);
                vertices.put(tokens[1].hashCode(),tokens[1]);
                edgesMap.put((tokens[0]+tokens[1]).hashCode(), line);
            }
            bufferedReader.close();
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        Vertex[] verticesArray = new Vertex[vertices.size()];

        // Create vertex objects
        int index=0;
        for (String value : vertices.values()) {

            verticesArray[index] = new Vertex("" + value);
            graph.addVertex(verticesArray[index]);
            index++;
        }

        System.out.println("Vertices: " + graph.vertexKeys());

        // Create edge objects and update the graph
        for (String line : edgesMap.values()){
            String[] tokens =line.split(TOKEN);
            graph.addEdge(graph.getVertex(tokens[0]), graph.getVertex(tokens[1]));

        }

        System.out.println("Edges: " + graph.getEdges());

        printDependencies(graph.getVertex("A"), a);

    }




    static void printDependencies(Vertex v, App a){

        if(!v.visited) {
            System.out.println(v.getLabel());
        }


        for (int j = 0; j < v.getDirectDependencyCount(); j++) {

            Edge e = v.getDirectDependency(j);
            Vertex dependency = e.getTwo();

            if(v.visited)
                System.out.print("|");

            for (int t = 0; t < a.depth; t++)
                System.out.print("  ");

            if(j!=0) {
                // 2nd and + dependency = \
                a.depth ++;
                System.out.print("\\");
            }
            else{
                a.depth ++;
                // dependency = |
                System.out.print("|");
            }

            e.setWeight(a.depth);

            System.out.println("_ " +dependency.getLabel());

            dependency.visited=true;

            printDependencies(e.getTwo(), a);

        }
        a.depth --;
    }

}

