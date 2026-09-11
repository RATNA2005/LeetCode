class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List <Boolean> bool=new ArrayList<>();
        for(int i=0;i<candies.length;i++)
        {
            int total=candies[i]+extraCandies;
            int c=0;
            for(int j=0;j<candies.length;j++)
            {
                if(total>=candies[j])
                {
                    c++;
                }
                
            }
            if(c==candies.length)
                {
                    bool.add(true);   
                }
                else
                {
                    bool.add(false);
                }
        }
        return bool;
        
    }
}