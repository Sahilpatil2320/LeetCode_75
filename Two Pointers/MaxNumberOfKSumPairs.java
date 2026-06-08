class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            int sum = 0;
            sum += nums[left] + nums[right];
            if(sum == k) {
                count++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}