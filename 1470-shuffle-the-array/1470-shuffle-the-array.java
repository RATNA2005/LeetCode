class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] arr=new int[nums.length];
        int i=0;
        int j=n;
        int c=0;
        while(j<nums.length)
        {
            arr[c]=nums[i];
            arr[c+1]=nums[j];
            j++;
            i++;
            c=c+2;
        }
        return arr;
    }
}