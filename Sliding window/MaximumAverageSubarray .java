class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        double maxSum ;
        for(int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        maxSum = currentSum;
        for(int j = k; j < nums.length; j++) {
            currentSum = currentSum - nums[j - k] + nums[j];
            if(currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum / k;
    }
}