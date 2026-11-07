class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }
        
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });
        
        int time = 0;
        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);
            
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] b = busy.poll();
                free.offer(new int[]{b[1], b[2]});
            }
            
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] b = busy.poll();
                    free.offer(new int[]{b[1], b[2]});
                }
            }
            
            int[] bestServer = free.poll();
            int weight = bestServer[0];
            int index = bestServer[1];
            
            ans[j] = index;
            busy.offer(new int[]{time + tasks[j], weight, index});
        }
        
        return ans;
    }
}
   