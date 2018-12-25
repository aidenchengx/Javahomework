package TreeStruct;

public class Tree {
	private Node root;
	public Node getRoot(){
		return this.root;
	}
	public Node[] Nodelist;
	public int Num;
	public void DeleteNode(Node N) {
		if(N.parent==null) {	//根节点
			this.root=null;return;
		}
		if(N.rchild==null&& N.lchild==null) { //叶子节点直接删除
			Node P;
			P=N.getParent();
			if(N.sub==0) P.setlchild(null);
			else P.setrchild(null);
			return;
		}
		if((N.rchild==null&&N.lchild!=null)||(N.rchild!=null&&N.lchild==null)) {//兄弟节点其中一个是空
				//根据红黑树性质 必须是单支的黑节点
				Node P,Q;
				if(N.rchild==null) Q=N.lchild;
				else Q=N.rchild;
				Q.color=false;//子节点置为黑
				P=N.getParent();
				Q.setParent(P);
				if(P==null) {//N是根节点
					this.root=Q;
					return;
				}
				if(N.sub==0) {	//N是左子树
					P.lchild=Q;
				}
				else P.rchild=Q;
				return;
			}
		else {				//既有左子树又有右子树直接去找
			Node R=findreplaceNode(N);						//左子树里最大值
			N.id=R.id;N.max=R.max;N.min=R.min;N.smax=R.smax;//把R的data域交给N
			//现在只要删除R节点即可
			Node P=R.getParent();Node Q=R.lchild;       //R节点最多有一个左子树
			if(R.sub==0) P.lchild=Q;		
			else P.rchild=Q;				//删除了R节点
			if(R.color==true) {	//这必然是一个无子节点的红色节点 即为Q是null
				R.parent=null;	
				return;
			}
			if(Q!=null) Q.parent=P;
			while(true) {
			if(Q.color==true) {	//如果Q是一个红色节点 则Q是叶子
				//Q.setParent(P);
				Q.color=false;
				return;
			}
			if(Q.getBrotherColor()==true) {//Q的兄弟是红色节点
				Node W;
				W=Q.getBrotherNode();
				P.color=true;
				W.color=false;
				this.TreeSpan(P, 'l');//对P左旋
			}
			Node W=Q.getBrotherNode();		//接下来都是兄弟节点是黑色的情况
			if((W.lchild==null||W.lchild.color==false)&&(W.rchild==null||W.rchild.color==false)) {//W的子节点都是黑色
				W.color=true;
				Q=Q.getParent();
				continue;
			}
			if((W.lchild!=null&&W.lchild.color==true)&&(W.rchild==null||W.rchild.color==false)) {//W右子节点是黑色
				W.color=true;
				W.lchild.color=false;
				this.TreeSpan(W, 'r');
			}
			else {//W左子节点黑色
				P=W.parent;
				W.color=P.color;
				P.color=false;//交换父亲和W颜色
				this.TreeSpan(P, 'l');
				if(W.rchild!=null) W.rchild.color=false;
				return;
			}
			}			
			}
		
	}
	public void AddNode(Node N) {
		Node P;boolean v1,v2;
		P=this.findinsertposition(N.min);
		System.out.println(P.min);
		if(P==null) {//树但无根节点的插入
			N.setParent(null);
			this.root=P;
			return;
		}		
		if(P.min>N.min) P.setlchild(N);
			else P.setrchild(N);
			N.setParent(P);
		while(true) {	
		if(P.color==false) {//父节点是黑色||根的直接插入即可
			return;
		}
		if(P.color==true && P.getBrotherColor()==true) { //父节点红色叔父节点红色
			Node G=null;
				P.color=false;
				P.changeBrotherColor();
				G=P.getParent();//G是祖父节点
				if(G==null) return;
				G.color=true;
				G=G.getParent();
				
			
				if(G==null) {P.color=false;//P是根
				return;
				}P=G;//改变P继续递归
				//this.printTree();
				continue;//以P开始继续递归
		}
		if(P.color==true && P.getBrotherColor()==false){ //父节点是红色的叔父节点黑色或者空 分情况讨论
			if(P.min>N.min) {//新节点是左子树
				if(P.sub==0) {		//左左结构
				P.color=false;				
				P.changeParentColor(true);
				this.TreeSpan(P,'r');//对父节点进行右旋转
				return;}
				else {				//右左结构
				this.TreeSpan(N,'r');//对新节点进行右旋转
				N.color=false;
				N.changeParentColor(true);
				this.TreeSpan(N,'l');
				N.color=false;
				return;
				}
			}
			else {//System.out.println("debug info");	
				//新节点是右子树
				if(P.sub==0) {		//左右结构
				this.TreeSpan(N,'l');//对新节点进行左旋转
				N.color=false;
				N.changeParentColor(true);
				this.TreeSpan(N,'r');
				N.color=false;
				return;
				}
				else {				//右右结构
					P.color=false;				
					P.changeParentColor(true);
					this.TreeSpan(P,'l');//对父节点进行右旋转
					return;
				}
			}
		}
		}
	}
	public Node findinsertposition(int key) {
		Node N,P=null;
		N=this.getRoot();
		//P=N;
		while(N!=null) {
			P=N;
			if(key>=N.min) N=N.rchild;
			else N=N.lchild;
		}
		return P;
	}
	public Node findreplaceNode(Node N) {
		N=N.lchild;
		while(N.rchild!=null) {
			N=N.rchild;
		}
		return N;//找到左子树最大值
	}
	public void TreeSpan(Node N,char v) {
		if(v=='l') {//左旋
			Node P,Q;
			P=N.getParent();
			if(P==null) return;
			Q=P.getParent();
			if(Q!=null) {
				if(P.sub==0) Q.setlchild(N);
				else Q.setrchild(N);
			}	
			N.sub=P.sub;	//继承P属性
			P.setrchild(N.lchild);
			P.setParent(N);
			N.setlchild(P);
		
			P.sub=0;
			N.setParent(Q);
			if(N.sub==-1) {
				//N是新的根
				System.out.println(N.sub);		
				this.root=N;
			}
			return;
		}
		if(v=='r') {	//右旋
			Node P,Q;
			P=N.getParent();
			if(P==null) return;
			Q=P.getParent();	
			if(Q!=null) {
				if(P.sub==0) Q.setlchild(N);
				else Q.setrchild(N);
			}	
			N.sub=P.sub;
			P.setlchild(N.rchild);
			P.setParent(N);
			N.setrchild(P);
		
			P.sub=1;
			N.setParent(Q);
			if(N.sub==-1) {
				//N是新的根	
				this.root=N;
			}
			return;}
	}
	 public Tree(Node root) {
	        super();
	    //  root.setParent(null);
	        root.color=false;
	        this.root = root;
	        
	 }
	 public void printTree() {
		 	Node N,L,R;
		 	N=this.getRoot();
		 	printTreelist(N);

	 }
	 public void printTreelist(Node N) {
		 if(N==null) return;
		 this.root.color=false;
		 N.printNode();
		 printTreelist(N.lchild);
		 printTreelist(N.rchild);
		 
	 }
	 public int adjustTreesmax(Node N) {
		 if(N==null) return -1;
		 if(N.rchild==null&&N.lchild==null) {
			 N.smax=N.max;
			 return N.smax;
		 }
		 int s1,s2;
		 s1=adjustTreesmax(N.rchild);
		 s2=adjustTreesmax(N.lchild);
		 if(s1>s2) N.smax=s1;
		 else N.smax=s2;
		 return N.smax;
	 }
	 public Node findNode(int id) {
		 Node N=this.getRoot();
		 N=findNodebyId(N,id);
		 return N;
	 }
	 public Node findNodebyId(Node N,int id) {
		 if(N==null) return null;
		 if(N.id==id) return N;//找到了对应值
		 Node L=null;
		 L=findNodebyId(N.lchild,id);
		 if(L==null) L=findNodebyId(N.rchild,id);
		 return L;
	 }
	 public String courselist(int i1,int i2) {
		 if(i1>i2) return "none";
		 this.Num=0;
		 Node N=this.getRoot();
		 String str="";
		 str=this.courselistsub(N, i1, i2);
		 return str;
	 }
	 public String courselistsub(Node N,int i1,int i2) {
		 String str="";
		 if(N==null) return str;
		 if(i1>=N.smax) {
			 return "";
		 }
		 if(!(N.min>=i1&&N.max<=i2)) {
		 if(N.lchild!=null&&N.lchild.smax>=i1) {
			 str+=courselistsub(N.lchild,i1, i2);
		 }
		  str+=courselistsub(N.rchild,i1, i2);}
		 if(N.min>=i1&&N.max<=i2)
		 { str+=N.name;
		 str+=courselistsub(N.lchild,i1, i2)+courselistsub(N.rchild,i1, i2);;
			this.DeleteNode(N);
	   			this.adjustTreesmax(this.getRoot());
		 }
		 
		 return str;
	 }
}
