class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k / 2; i++) {
            int r1 = x + i;
            int r2 = x + k - 1 - i;

            for (int j = y; j < y + k; j++) {
                int temp = grid[r1][j];
                grid[r1][j] = grid[r2][j];
                grid[r2][j] = temp;
            }
        }

        return grid;
    }
}