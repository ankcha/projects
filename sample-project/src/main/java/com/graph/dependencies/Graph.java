package com.graph.dependencies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


/*
  Graph includes a bunch of vertex and edges connecting the vertices
*/
public class Graph {

    private HashMap<String, Vertex> vertices;
    private HashMap<Integer, Edge> edges;

    public Graph() {
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();
    }

    public Graph(ArrayList<Vertex> vertices) {
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();

        for (Vertex v: vertices){
            // Labels are unique
            this.vertices.put(v.getLabel(), v);
        }
    }

    public boolean addEdge(Vertex one, Vertex two){

        boolean result = false;

        if(one.equals(two)){
            // Avoiding direct cyclic
            return false;
        }
        else {

            Edge e = new Edge(one, two);

            // Let's quickly check the graph to see if an edge already exists
            if (edges.containsKey(e.hashCode())) {
                result = false;
            } else if (one.containsDirectDependency(e) || two.containsDirectDependency(e)) {
                // Edge is already part of the vertex object. One way to avoid this is to not expose Vertex CRUD operations publicly
                result = false;
            } else {
                // A new edge, so let's add it
                edges.put(e.hashCode(), e);
                one.addDirectDependency(e);
                result = true;
            }
        }
        return result;
    }


    public boolean addVertex(Vertex vertex){
        boolean result = false;

        if(vertex == null){
            result = false;
        } else {
            // Check if the vertex already exist in the graph
            Vertex current = this.vertices.get(vertex.getLabel());
            if (current != null) {
                //already exists
                result = true;
            } else {
                //New vertex, let's add it to the graph
                vertices.put(vertex.getLabel(), vertex);
                result = true;
            }
        }
        return result;
    }

    // will be used once we read graph.txt
    // Check if graph contains an Edge
    public boolean containsEdge(Edge e){
        boolean result = false;
        if(e == null)
            result = false;
        else if(e.getOne() == null || e.getTwo() == null){
            result = false;
        } else
            result = this.edges.containsKey(e.hashCode());
        return result;
    }

    // will be used once we read graph.txt
    public boolean containsVertex(Vertex vertex){
        boolean result = false;
        if(this.vertices.get(vertex.getLabel()) != null)
            result=true;
        else
            result = false;
        return result;
    }

    public Vertex getVertex(String label){
        return vertices.get(label);
    }

    public Set<String> vertexKeys() {
        return this.vertices.keySet();
    }

    public Set<Edge> getEddges() {
        return new HashSet<Edge>(this.edges.values());
    }


}
