class Solution {
    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        );

        int left = 0;
        int right = n - 1;

        // Add first candidates from left
        for (int i = 0; i < candidates && left <= right; i++) {
            minHeap.offer(new int[]{costs[left], left});
            left++;
        }

        // Add first candidates from right
        for (int i = 0; i < candidates && left <= right; i++) {
            minHeap.offer(new int[]{costs[right], right});
            right--;
        }

        long total = 0;

        while (k-- > 0) {

            int[] worker = minHeap.poll();

            total += worker[0];

            if (left <= right) {

                if (worker[1] < left) {
                    minHeap.offer(new int[]{costs[left], left});
                    left++;
                } else {
                    minHeap.offer(new int[]{costs[right], right});
                    right--;
                }
            }
        }

        return total;
    }
}