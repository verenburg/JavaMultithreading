package task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (n == this) return true;
        if (n == null) return false;
        if (!(n instanceof Solution)) return false;
        
        if (!(n.getClass().equals(this.getClass()))) return false;
        
        Solution solution = (Solution) n;
        boolean f = false;
        boolean l = false;
        if (this.first != null ? this.first.equals(solution.first) : solution.first == null) {
            f = true;
        }
        if (this.last != null ? this.last.equals(solution.last) : solution.last == null) {
            l = true;
        }
        return (f & l);
    }

    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
