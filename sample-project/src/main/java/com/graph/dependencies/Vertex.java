package com.graph.dependencies;

import java.util.ArrayList;

/*
  Vertex object stores a label, list of dependencies and an additional boolean to check if it has been traversed
*/

public class Vertex {

    private ArrayList<Edge> directDependencies;
    private String label;
    boolean visited;

    public Vertex(String label){
        if(_validateLabel(label))
            this.label = label;
        else
            throw new IllegalArgumentException("Unable to create vertex. The label is invalid.");

        this.directDependencies = new ArrayList<Edge>();
    }

    public void addDirectDependency(Edge edge){
        if(this.directDependencies.contains(edge)){
            // edge already exists for the given vertex, avoid adding duplicate edges
            return;
        }
        this.directDependencies.add(edge);
    }

    public Edge getDirectDependency(int index){
        return this.directDependencies.get(index);
    }

    public int getDirectDependencyCount(){
        return this.directDependencies.size();
    }

    public String getLabel() {
        return this.label;
    }

    public String toString(){
        return label;
    }

    public int hashCode(){
        return this.label.hashCode();
    }

    // compare vertex based on labels
    public boolean equals(Object other){
        boolean result = false;

        if(other instanceof Vertex){
            Vertex v = (Vertex) other;
            result = this.label.equals(v.label);
        }
        else{
            result = false;
        }

        return result;
    }

    // check if an edge already exists for a given vertex
    public boolean containsDirectDependency(Edge other){
        return this.directDependencies.contains(other);
    }

    private boolean _validateLabel(String label){
        boolean result = false;
        if(label != null){
            if(!label.isEmpty()){
                result = true;
            }
        }
        return result;
    }
}
