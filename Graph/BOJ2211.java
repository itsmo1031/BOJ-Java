package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 2211
 * 네트워크 복구
 * 골드 2
 *
 * 그래프 이론
 * Dijkstra (다익스트라)
 */
public class BOJ2211 {
    static int N;
    static ArrayList<ArrayList<Line>> network;
    static int[] dist, path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        network = new ArrayList<>();
        dist = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);


        for (int i = 0; i <= N; i++) {
            network.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            network.get(a).add(new Line(b, c));
            network.get(b).add(new Line(a, c));
        }

        dijkstra();
        print();
    }

    static void print() {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N + 1; i++) {
            if (path[i] == 0) continue;
            // 값이 0이 아닌 값만 카운트하고, path의 인덱스와 값을 출력
            cnt++;
            sb.append(i).append(" ").append(path[i]).append("\n");
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Line> q = new PriorityQueue<>();
        q.offer(new Line(1, 0));
        dist[1] = 0;

        while (!q.isEmpty()) {
            Line cur = q.poll();
            if (dist[cur.index] < cur.cost) continue;
            for (Line next : network.get(cur.index)) {
                int nc = cur.cost + next.cost;
                if (nc < dist[next.index]) {
                    dist[next.index] = nc;
                    // 이 문제 풀이의 핵심!!
                    // 최단 경로가 갱신될 때마다, 그 경로를 저장
                    // ex) 4번으로 가는 최단 경로가 1-2-4이면, path[4]=2를 저장
                    path[next.index] = cur.index;
                    q.offer(new Line(next.index, nc));
                }
            }
        }
    }

    static class Line implements Comparable<Line> {
        int index;
        int cost;

        public Line(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return this.cost - o.cost;
        }
    }
}
