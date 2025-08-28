import deque.ArrayDeque61B;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class).
                filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic())).
                toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.addFirst(8);
        assertThat(lld1.get(-3)).isEqualTo(null);
        assertThat(lld1.get(26324)).isEqualTo(null);
        assertThat(lld1.get(4)).isEqualTo(3);
        assertThat(lld1.get(6)).isEqualTo(null);
    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst(3);
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addFirst(3);
        lld1.addFirst(4);
        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.addLast(5);
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5).inOrder();
        lld1.addFirst(7);
        assertThat(lld1.toList()).containsExactly(7, 0, 1, 2, 3, 4, 5).inOrder();
    }

    @Test
    public void testRemoveFirst() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(3, 4).inOrder();
        assertThat(lld1.removeFirst()).isEqualTo(3);
        assertThat(lld1.removeFirst()).isEqualTo(4);
        assertThat(lld1.removeFirst()).isEqualTo(null);
    }

    @Test
    public void testRemoveLast() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        assertThat(lld1.removeLast()).isEqualTo(7);
        assertThat(lld1.toList()).containsExactly(5, 6).inOrder();
        assertThat(lld1.removeLast()).isEqualTo(6);
        assertThat(lld1.removeLast()).isEqualTo(5);
        assertThat(lld1.removeLast()).isEqualTo(null);
    }

    @Test
    public void addFirstTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);
        lld1.addFirst(7);
        lld1.addFirst(6);
        lld1.addFirst(5);
        assertThat(lld1.toList()).
                containsExactly(5, 6, 7, 0, 1, 2, 3, 4).inOrder();
        lld1.addFirst(15);
        assertThat(lld1.toList()).
                containsExactly(15, 5, 6, 7, 0, 1, 2, 3, 4).inOrder();
    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        lld1.addLast(0);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addLast(1);
        lld1.addLast(8);
        lld1.addLast(9);
        assertThat(lld1.toList()).
                containsExactly(2, 3, 4, 5, 6, 7, 0, 1, 8, 9).inOrder();
    }

    private ArrayDeque61B<Integer> testRemove() {
        ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        lld1.addLast(0);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addLast(1);
        lld1.addLast(8);
        lld1.addLast(9);
        return lld1;
    }

    @Test
    public void testResizingRemoveFirst() {
        ArrayDeque61B<Integer> lld1 = testRemove();
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.removeFirst()).isEqualTo(3);
        assertThat(lld1.removeFirst()).isEqualTo(4);
        assertThat(lld1.removeFirst()).isEqualTo(5);
        assertThat(lld1.removeFirst()).isEqualTo(6);
        assertThat(lld1.removeFirst()).isEqualTo(7);
        assertThat(lld1.capacity()).isEqualTo(8);
        assertThat(lld1.toList()).containsExactly(0, 1, 8, 9).inOrder();
        assertThat(lld1.removeFirst()).isEqualTo(0);
    }

    @Test
    public void testResizingRemoveLast() {
        ArrayDeque61B<Integer> lld1 = testRemove();
        assertThat(lld1.removeLast()).isEqualTo(9);
        assertThat(lld1.removeLast()).isEqualTo(8);
        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.removeLast()).isEqualTo(0);
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.removeLast()).isEqualTo(7);
        assertThat(lld1.capacity()).isEqualTo(8);
        assertThat(lld1.toList()).containsExactly(3, 4, 5, 6).inOrder();
        assertThat(lld1.removeLast()).isEqualTo(6);
    }

    @Test
    public void testIteration() {
        ArrayDeque61B<Integer> lld1 = testRemove();
        for (int item : lld1) {
            System.out.print(item);
        }
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }
}
