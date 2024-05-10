/*
 * permanent stack。题目如下
'''
In typical Object Oriented world, a stack has two methods that mutate the data of the stack
that is being operated on, push() and pop(). We'd like to implement an immutable version of
the class, which we'll call PStack.
#
class PStack
PStack() # constructor
int size() # returns number of elements in the stack
int peek() # returns the most recently pushed element
PStack push(int) # returns an instance of PStack with the element added
PStack pop() # returns an instance of Pstack with the top element removed
'''
 */
import java.util.ArrayList;
import java.util.List;

public class PStack {
    private final List<Integer> elements;

    //constructor
    public PStack(List<Integer> newElements) {
        this.elements = new ArrayList<>(newElements);
    }

    public int size() {
        return elements.size();
    }

    public int peek() {
        if (size() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        //return last number added
        return elements.get(size() - 1);
    }

    public PStack push(int element) {
        List<Integer> newElements = new ArrayList<>(elements);
        newElements.add(element);
        return new PStack(newElements);
    }

    public PStack pop() {
        if (size() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        //return last number added and remove from the list
        List<Integer> newElements = new ArrayList<>(elements);
        newElements.remove(size() - 1);
        return new PStack(newElements);
    }

    public static void main(String[] args) {
        PStack stack1 = new PStack(new ArrayList<>());
        PStack stack2 = stack1.push(5).push(10).push(15);
        System.out.println(stack2.peek());  // Output: 15
        System.out.println(stack2.pop().peek());  // Output: 10
        System.out.println(stack2.size());  // Output: 3
        System.out.println(stack2.pop().pop().pop().size());  // Output: 0
    }

}
