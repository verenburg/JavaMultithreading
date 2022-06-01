package task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        if (!(o instanceof Solution)) return false;

        Solution n = (Solution) o;
        boolean f = false;
        boolean l = false;
        if (n.first != null ? n.first.equals(this.first) : this.first == null) f = true;
        if (n.last != null ? n.last.equals(this.last) : this.last == null) l = true;

        return (f && l);
    }

    @Override
    public int hashCode() {
        int result = this.first != null ? this.first.hashCode() : 0;
        result = 31 * result + (this.last != null ? this.last.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
