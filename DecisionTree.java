/*
 * A decision tree is a data structure that can be evaluated on a set of signals and return a decision (e.g. Yes or No ("Y" or "N")). Each interior node of the tree is associated with a particular signal and a constant value against which to compare that signal, and each leaf node has a value which will be returned by the tree. To evaluate the tree on a set of signals we traverse the tree, starting at the root and comparing the appropriate signal value to the constant associated with each interior node. If the signal value is less than the constant we proceed down the left subtree and if it is greater than or equal to the constant we proceed down the right subtree. We continue until we reach a leaf at which point we return the value associated with the leaf.
For example, suppose that we have a set of integer-valued signals {X1, X2, X3}. Consider the following decision tree:
           X1 < 3
        ------------
       |            |
    X2 < 1       X1 < 6
-----------    ---------
|           |  |         |
N           Y  N      X3 < 2
                    ----------
                   |          |
                   Y          N
If we evaluate this tree on signals {X1: 2, X2: 1, X3: 11} the result will be Y. Evaluating on signals {X1: 8, X2: 4, X3: 12} we get N. We can use these to implement decisions that need to be made repeatedly on different input values. For instance, a given decision tree might represent a rule to decide whether or not a given transaction looks fraudulent, and the signals could represent different quantities like X1) the age of the account in days, X2) the dollar value of the transaction, and X3) the time in hours since the last transaction attempt.
In real life, we would probably grow a decision tree via some machine learning algorithm. In this exercise, however, we will manually create the tree that we want. We can grow a decision tree by starting with a single-leaf tree and recursively splitting the leaves of the tree. We do this by associating a split condition to a node, creating two new leaves below it, and associating a return value to each of those leaves.
So to grow the tree above we start with a single-leaf tree:
Y
Then add the split condition X1 < 3 and (optionally) assign return values to the new leaves:
     X1 < 3
  ------------
|            |
Y            N
Then add a split condition to the left leaf:
           X1 < 3
        ------------
       |            |
    X2 < 1          N
-----------
|           |
X           X
Assign return values to the new leaves:
           X1 < 3
‍‍‌‌‌‍‌‌‍‌‌‌‍‌‍‌‍‍‍        ------------
       |            |
    X2 < 1          N
-----------
|           |
N           Y
And so on until we are done.
The goal of this question is to implement a decision tree that can be grown incrementally in this fashion and evaluated on a particular set of signals. Concretely you should implement the following pseudocode API in the language of your choice:
```
class DecisionTree:
  method add_spli‍‍‌‍‌‍‌‌‍‌‍‍‌‌‍‍‌‌‍‌t(leaf, signal_name, constant):
    Add a split condition to the given leaf node.
    Return the newly created leaves for future calls.
  method set_leaf_value(leaf, value):
    Set the return value for a leaf node.
  method evaluate(signals):
    Evaluate the tree on a mapping of signal_name -> signal_value.
    Return the value of the leaf node reached by traversing the tree.
```
Afterwards, use your solution to grow the example tree above and write some test cases.
 */


public class DecisionTree {
    
}
/*
 * class DecisionTree {

    private String signalName;
    private Integer constant;
    private String returnValue;

    private DecisionTree left;
    private DecisionTree right;

    public DecisionTree[] static add_split(DecisionTree leaf, String signal_name, Integer constant) {
        leaf.setSingalName = signal_name;
        leaf.setConstant = constant;

        DecisionTree left = new DecisionTree();
        DecisionTree right = new DecisionTree();

        leaf.setLeft(left);
        leaf.setRight(right);

        return DecisionTree[2]{left, right};
    }

    public static void setLeafValue(DecisionTree decisionTree, String returnValue) {
        decisionTree.setReturnValue(returnValue);
    }

    public String evaluate(Queue<Integer> signals) {
        Integer current = signals.pop();
        if (current < constant) return Objects.isNull(left) ? returnValue : this.left.evaluate(signals);
        return Objects.isNull(right) ? returnValue : this.right.evaluate(singals);
    }
}

DecisionTree leaf1 = new DecisionTree();

DecisionTree[] leaves = DecisionTree.add_split(leaf1, "currentAge", 5);
DecisionTree.setLeafValue(leaf1, "N");
DecisionTree.add_split(leaves[0], "someString", 2);
DecisionTree.add_split(leaves[1], "someString", 8);
 */
