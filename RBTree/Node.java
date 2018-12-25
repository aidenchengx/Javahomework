/**
 * 
 */
/**
 * @author cx597
 *
 */
package TreeStruct;
/**
 * 树节点
 * @author  *
 * @param <T>
 */
public class Node {

 
    int min;
    int max;
    int sub;//用来表示是左子树 还是右子树 0=左 1=右 -1=根
    int smax;
    int id;//课程编号
    String name;//课程名称
    boolean color;//0=black 1=red
    Node parent;
    Node lchild;//子节点
    Node rchild;//子节点
    /**
     * 构造函数
     * @param cont
     * @param parent
     * @param nodeList
     */
    public Node(int t1,int t2,int id,String name0) {
        super();
        if(t1>t2) System.out.println("WRONG INPUT");;
       this.min=t1;
       this.max=t2;
       this.smax=t2;
       this.id=id;
       this.color=true;
        this.parent =null;
        this.lchild=null;
        this.rchild=null;
        this.sub=-1;
        this.name=name0;
    }
    public Node() {
        super();
   
    }

    /**
     * get &set
     * @return
     */
    public Node getParent() {
        return this.parent;
    }
    public boolean getBrotherColor() {//由于只涉及到父为红色节点的判断 可以直接判断
    	Node P=this.parent,Q;
    	if(P==null) return false;			//叔父节点不存在
    	if (this.sub==0) Q=P.rchild;
    	else Q=P.lchild;
    	//System.out.println("info");
    	if(Q==null) return false;	//System.out.println("info");
    	if(Q.color==false) return false; 	//叔父节点是黑色
    	return true;
    }
    public Node getBrotherNode() {
    	Node P=this.parent;
    	if(P==null) return null;
    	if(this.sub==0) return P.rchild;
    	else return P.lchild;
    }
    public void changeBrotherColor() {
    	Node P=this.parent,Q;
    	if(P==null) return;
    	if(this.sub==0) Q=P.rchild;
    	else Q=P.lchild;
    	if(Q==null) return;
    	Q.color=false;
    }
    public void changeParentColor(boolean v) {
    	Node P=this.parent;
    	if(P==null) return;
    	P.color=v;
    	return;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void printNode() {
    	if (this==null) return;
    	System.out.println(this.min+"-"+this.max+" color:"+this.color+" smax:"+this.smax+" id:"+this.id+" name:"+this.name);
    }
    public void setlchild(Node N) {
    	this.lchild=N;
    	if(N!=null)N.sub=0;
    }
    public void setrchild(Node N) {
    	this.rchild=N;
    	if(N!=null)N.sub=1;
    }


}
