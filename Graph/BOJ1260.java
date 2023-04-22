package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1260
 * DFS와 BFS
 * 실버 2
 *
 * 그래프 이론
 * 그래프 탐색
 * BFS (너비 우선 탐색)
 * DFS (깊이 우선 탐색)
 */
public class BOJ1260 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 정점의 번호

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 1; i < N + 1; i++) {
            list.get(i).sort(Comparator.naturalOrder());
        }

        dfs(V, list, visited);
        System.out.println();
        Arrays.fill(visited, false);
        bfs(V, list, visited);
    }

    static void dfs(
            int start,
            ArrayList<ArrayList<Integer>> graph,
            boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }

    static void bfs(
            int start,
            ArrayList<ArrayList<Integer>> graph,
            boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.remove();
            System.out.print(current + " ");

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}
