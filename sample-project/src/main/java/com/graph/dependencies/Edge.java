package com.graph.dependencies;

/*
  Edge object stores two vertex nodes that are dependent
*/

public class Edge {

    private Vertex one, two;

    public Edge(Vertex one, Vertex two){
        this.one = one;
        this.two = two;
     }

    public Vertex getOne(){
        return this.one;
    }

    public Vertex getTwo(){
        return this.two;
    }

    public String toString(){
        return one + "->" + two;
    }

    public int hashCode() {
        return ( one.getLabel() + two.getLabel() ).hashCode();
    }

    public boolean equals(Object other){
        boolean result = false;
        if(!(other instanceof Edge)){
            result = false;

        }
        else{
            Edge e = (Edge) other;
            result = e.one.equals(this.one) && e.two.equals(this.two);
        }

        return result;
    }

    public Vertex getDirectDependency(Vertex vertex){
        if(! ( vertex.equals(one) || vertex.equals(two)) ){
            return null;
        }else {
            if(vertex.equals(one)){
                return two;
            }
            else {
                return one;
            }
        }
    }
}
