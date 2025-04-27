import java.util.*;

public class BellmanFord {

    static class Edge {
        int src, t, wt;
        Edge(int src, int t, int wt) {
            this.src = src;
            this.t = t;
            this.wt = wt;
        }
    }

    static void bellmanFord(List<Edge> e, int v, int src) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 1; i < v; i++) {
            for (Edge edge : e) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.wt < dist[edge.t]) {
                    dist[edge.t] = dist[edge.src] + edge.wt;
                }
            }
        }
        
        for (Edge edge : e) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.wt < dist[edge.t]) {
                System.out.println("Graph contains a negative wt cycle");
                return;
            }
        }
        
        System.out.println("Vertex\tDistance from src");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int v = 5;
        List<Edge> e = new ArrayList<>();

        e.add(new Edge(0, 1, -1));
        e.add(new Edge(0, 2, 4));
        e.add(new Edge(1, 2, 3));
        e.add(new Edge(1, 3, 2));
        e.add(new Edge(1, 4, 2));
        e.add(new Edge(3, 2, 5));
        e.add(new Edge(3, 1, 1));
        e.add(new Edge(4, 3, -3));

        bellmanFord(e, v, 0);
    }
}
