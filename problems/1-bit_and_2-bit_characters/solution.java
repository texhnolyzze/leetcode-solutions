class Solution {
    public static boolean isOneBitCharacter(int[] bits) {
//      Defining finite state machine, so we don't have if-else checks
        StateNode n = new StateNode();
        n.childs[0] = new StateNode();
        n.childs[1] = new StateNode();
        n.childs[1].childs[0] = new StateNode();
        n.childs[1].childs[1] = new StateNode();
        n.childs[0].childs[0] = n.childs[0];
        n.childs[0].childs[1] = n.childs[1];
        n.childs[1].childs[0].childs[0] = n.childs[0];
        n.childs[1].childs[0].childs[1] = n.childs[1];
        n.childs[1].childs[1].childs[0] = n.childs[0];
        n.childs[1].childs[1].childs[1] = n.childs[1];
//      End of fsm defining
        StateNode curr = n;
        for (int i = 0; i < bits.length; i++) {
            curr = curr.childs[bits[i]];
        }            
        return curr == n.childs[0];
    }
    
    private static class StateNode {
        StateNode[] childs = new StateNode[2];
    }
}