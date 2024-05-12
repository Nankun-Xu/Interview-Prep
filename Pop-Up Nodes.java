import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class DomNode {
    String id;
    boolean hidden;
    List<DomNode> children;
}

public class PopupHandler {
    public boolean dfs(DomNode root) {
        //reach leaf nodes
        if (root == null)return false;
        //find popup, cancel hidden
        if ("POPUP".equals(root.id)) {
            root.hidden = false;
            return true;//hasPopup
        }

        boolean hasPopup = false;

        HashMap<DomNode, Boolean> nodeHasPopup = new HashMap<>();

        for (DomNode child : root.children) {
            nodeHasPopup.put(child, dfs(child));//give each node a state: haspopup
            hasPopup = hasPopup || nodeHasPopup.get(child);//update haspopup -> is popup or has popup
        }
        if (hasPopup) {
            for (DomNode child : root.children) {
                if (nodeHasPopup.get(child)) {//has popup, show
                    child.hidden = false;
                } else {
                    child.hidden = true;
                }
            }
        }
        return hasPopup;
    }

    //do find popup and switch state of hiddden
    public void openPopup(DomNode root) {
        if (root == null) {
            return;
        }
        boolean hasPopup = dfs(root);
        if (hasPopup) {
            root.hidden = false;
        }
    }
    public static void main(String[] args) {
        // Create DOM nodes
        DomNode popup = new DomNode();
        popup.id = "POPUP";
        popup.hidden = true;
        popup.children = new ArrayList<>();

        DomNode child1 = new DomNode();
        child1.id = "DIV";
        child1.hidden = true;
        child1.children = new ArrayList<>();

        DomNode child2 = new DomNode();
        child2.id = "BUTTON";
        child2.hidden = true;
        child2.children = new ArrayList<>();

        DomNode child3 = new DomNode();
        child3.id = "POPUP";
        child3.hidden = true;
        child3.children = new ArrayList<>();

        // Add children to the parent node
        popup.children.add(child1);
        popup.children.add(child2);
        popup.children.add(child3);

        // Instantiate PopupHandler
        PopupHandler handler = new PopupHandler();

        // Test openPopup method
        System.out.println("Testing openPopup method:");
        handler.openPopup(popup);

        // Check if the popup and its child popup are visible
        System.out.println("Popup visibility: " + popup.hidden);
        for (DomNode child : popup.children) {
            System.out.println("Child visibility: " + child.id + child.hidden);
        }
    }
}
