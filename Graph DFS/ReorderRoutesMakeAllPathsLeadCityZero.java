class Solution {

    public int minReorder(int n, int[][] connections) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : connections) {
            graph.get(edge[0]).add(new int[]{edge[1], 1});
            graph.get(edge[1]).add(new int[]{edge[0], 0});
        }

        boolean[] visited = new boolean[n];

        return dfs(0, graph, visited);
    }

    private int dfs(int city, List<List<int[]>> graph, boolean[] visited) {

        visited[city] = true;
        int changes = 0;

        for (int[] neighbor : graph.get(city)) {

            int nextCity = neighbor[0];
            int needsReverse = neighbor[1];

            if (!visited[nextCity]) {
                changes += needsReverse;
                changes += dfs(nextCity, graph, visited);
            }
        }

        return changes;
    }
}