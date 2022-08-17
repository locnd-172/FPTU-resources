package algorithmanalysis;

import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class AlgorithmAnalysis {

    public static void main(String[] args) {
        System.out.println("Matrix multiplication:");
        MatrixMultiplication();

        System.out.println("\nGauss elimination:");
        GaussElimination();
        
        System.out.println("\nKnapsack:");        
        KnapsackProblem();
    }

    public static void MatrixMultiplication() {
        // matrix A (2 x 3)
        double[][] A = {
            {1, 2, 3},
            {7, 3, 1}
        }; 
        // matrix B (3 x 4)
        double[][] B = {
            {5, 3, 2, 8},
            {2, 4, 1, 0},
            {2, 1, 6, 7}
        }; 

        int M = A.length, N = A[0].length, P = B[0].length;
        double[][] C = new double[M][P];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < P; ++j) {
                C[i][j] = 0;
                for (int k = 0; k < N; ++k) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < P; j++) {
                System.out.printf("%6.1f ", C[i][j]);
            }
            System.out.println("");
        }

    }

    public static void GaussElimination() {
        // matrix A size 3 x 4
        double[][] A = {
            {1, 2, 5, -9},
            {1, -1, 3, 2},
            {3, -6, -1, 25}
        }; 
        int N = A.length;

        for (int i = 0; i < N - 1; i++) 
            for (int j = i + 1; j < N; j++) {
                for (int k = N; k >= i; --k) {
                    A[j][k] = A[j][k] - (A[i][k] * A[j][i] / A[i][i]);
                }
            }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                System.out.printf("%6.1f ", A[i][j]);
            }
            System.out.println("");
        }
    }

    public static void KnapsackProblem() {
        /* 
        sample test case
        10 15
        4 4 2 6 3 5 8 2 4 9
        4 5 1 2 6 10 2 12 3 14
        -----------
        1 time: 33
        N times: 84
        */
        int N, W;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = sc.nextInt();
        
        int[] w = new int[N + 1]; // weight
        int[] v = new int[N + 1]; // value
        
        for (int i = 1; i <= N; ++i) w[i] = sc.nextInt();
        for (int i = 1; i <= N; ++i) v[i] = sc.nextInt();
        
        int res1 = knapsack01(N, W, w, v);
        int res2 = knapsackN(N, W, w, v);
        System.out.println("1 time: " + res1 + "\nN times: " + res2);
    }

    public static int knapsack01(int N, int W, int[] w, int[] v) {
        if (N <= 0 || W <= 0) return 0;

        int[][] f = new int[N + 1][W + 1];
        for (int i = 0; i <= W; i++) f[0][i] = 0;
        for (int i=0; i <= N; i++) f[i][0]=0;
        
        for (int i = 1; i <= N; i++) 
            for (int j = 1; j <= W; j++) {
                if (j >= w[i]) 
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j - w[i]] + v[i]);
                else 
                    f[i][j] = f[i-1][j];
            }
        
        return f[N][W];
    }
    
    public static int knapsackN(int N, int W, int[] w, int[] v) {
        if (N <= 0 || W <= 0) return 0;

        int[][] f = new int[N + 1][W + 1];
        for (int i = 0; i <= W; i++) f[0][i] = 0;
        for (int i=0; i <= N; i++) f[i][0]=0;
        
        for (int i = 1; i <= N; i++) 
            for (int j = 1; j <= W; j++) {
                if (j >= w[i]) 
                    f[i][j] = Math.max(f[i-1][j], f[i][j - w[i]] + v[i]);
                else 
                    f[i][j] = f[i-1][j];
            }
        return f[N][W];
    }
    
}
