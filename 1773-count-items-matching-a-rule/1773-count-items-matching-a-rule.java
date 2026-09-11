class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int i;
        if(ruleKey.equals("type"))
        {
            i=0;
        }
        else if(ruleKey.equals("color"))
        {
           i=1;
        }
        else
        {
           i=2;
        }
        int count=0;
        for(int j=0;j<items.size();j++)
        {
        if(items.get(j).get(i).equals(ruleValue))
        {
            count++;
        }
        }
        return count;
    }
}