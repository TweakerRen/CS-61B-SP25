import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    WeightedQuickUnionUF DS_percolation;
    WeightedQuickUnionUF DS_isfull;
    int N;
    boolean[] grid;
    int top;
    int bottom;
    int count;

    public Percolation(int N) {
        DS_percolation = new WeightedQuickUnionUF(N * N + 2);
        DS_isfull = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N * N];
        this.N = N;
        top = N * N;
        bottom = N * N + 1;
        count = 0;
    }

    private int xyTo1D(int r, int c) {
        return r * N + c;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N)
            throw new java.lang.IllegalArgumentException();
        if (isOpen(row, col)) return;
        count++;
        int num = xyTo1D(row, col);
        grid[num] = true;
        if (row == 0) {
            DS_percolation.union(top, num);
            DS_isfull.union(top, num);
        }
        if (row == N - 1) DS_percolation.union(bottom, num);
        if (row > 0 && isOpen(row - 1, col)) {
            int n2 = xyTo1D(row - 1, col);
            DS_percolation.union(num, n2);
            DS_isfull.union(num, n2);
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            int n2 = xyTo1D(row + 1, col);
            DS_percolation.union(num, n2);
            DS_isfull.union(num, n2);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            int n2 = xyTo1D(row, col - 1);
            DS_percolation.union(num, n2);
            DS_isfull.union(num, n2);
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            int n2 = xyTo1D(row, col + 1);
            DS_percolation.union(num, n2);
            DS_isfull.union(num, n2);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N)
            throw new java.lang.IllegalArgumentException();
        int num = xyTo1D(row, col);
        return grid[num];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N)
            throw new java.lang.IllegalArgumentException();
        int num = xyTo1D(row, col);
        if (DS_isfull.find(top) == DS_isfull.find(num))
            return true;
        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (DS_percolation.find(top) == DS_percolation.find(bottom))
            return true;
        return false;
    }
}
