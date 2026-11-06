class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
  
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }
        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[m];
        Map<Integer, Integer> countMap = new HashMap<>();
        int j = 0, k = 0;
        
        for (int i = 0; i < m; i++) {
            int r = qs[i][0];
            int idx = qs[i][1];
            int l = r - x;
            
            while (k < logs.length && logs[k][1] <= r) {
                countMap.put(logs[k][0], countMap.getOrDefault(logs[k][0], 0) + 1);
                k++;
            }
            
            while (j < logs.length && logs[j][1] < l) {
                int server = logs[j][0];
                countMap.put(server, countMap.get(server) - 1);
                if (countMap.get(server) == 0) {
                    countMap.remove(server);
                }
                j++;
            }
            
            ans[idx] = n - countMap.size();
        }
        
        return ans;
    }
}
   