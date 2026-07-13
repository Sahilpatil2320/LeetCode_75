class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        Map<String, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(new Pair(b, value));
            graph.get(b).add(new Pair(a, 1.0 / value));
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                ans[i] = -1.0;
            } else {
                Set<String> visited = new HashSet<>();
                ans[i] = dfs(start, end, 1.0, visited, graph);
            }
        }

        return ans;
    }

    private double dfs(String current, String target, double product,
                       Set<String> visited,
                       Map<String, List<Pair>> graph) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Pair neighbor : graph.get(current)) {

            if (!visited.contains(neighbor.node)) {

                double result = dfs(neighbor.node,
                                    target,
                                    product * neighbor.value,
                                    visited,
                                    graph);

                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }

    class Pair {

        String node;
        double value;

        Pair(String node, double value) {
            this.node = node;
            this.value = value;
        }
    }
}