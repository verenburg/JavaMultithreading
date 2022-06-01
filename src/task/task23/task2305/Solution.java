package task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] sols = new Solution[2];
        Solution sol = new Solution();
        sol.innerClasses[0] = sol.new InnerClass();
        sol.innerClasses[1] = sol.new InnerClass();
        sols[0] = sol;

        Solution sol1 = new Solution();
        sol1.innerClasses[0] = sol1.new InnerClass();
        sol1.innerClasses[1] = sol1.new InnerClass();
        sols[1] = sol1;

        return sols;
    }

    public static void main(String[] args) {

    }
}
