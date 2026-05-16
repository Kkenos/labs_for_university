package com.kkenos.deliverypizza;

public class Edge {
    public long idStart;
    public long idEnd;
    public double weight;
    public Edge(long idStart, long idEnd, double weight) {
        this.idStart = idStart;
        this.idEnd = idEnd;
        this.weight = weight;
    }
}
