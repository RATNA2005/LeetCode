import java.util.*;

class FrontMiddleBackQueue {

    private Deque<Integer> left;
    private Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    private void balance() {
        if (left.size() > right.size()) {
            right.offerFirst(left.pollLast());
        } else if (right.size() > left.size() + 1) {
            left.offerLast(right.pollFirst());
        }
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (left.size() < right.size()) {
            left.offerLast(val);
        } else {
            right.offerFirst(val);
        }
    }

    public void pushBack(int val) {
        right.offerLast(val);
        balance();
    }

    public int popFront() {
        if (left.isEmpty() && right.isEmpty()) {
            return -1;
        }

        int ans;
        if (!left.isEmpty()) {
            ans = left.pollFirst();
        } else {
            ans = right.pollFirst();
        }

        balance();
        return ans;
    }

    public int popMiddle() {
        if (left.isEmpty() && right.isEmpty()) {
            return -1;
        }

        int ans;
        if (left.size() == right.size()) {
            ans = left.pollLast();
        } else {
            ans = right.pollFirst();
        }

        balance();
        return ans;
    }

    public int popBack() {
        if (right.isEmpty()) {
            return -1;
        }

        int ans = right.pollLast();
        balance();
        return ans;
    }
}