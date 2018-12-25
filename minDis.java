import java.util.Arrays;


public class findmindis{
	public static class Node 
	{int num;
	 double X;
	 double Y;}	//	原始点储存结构
	public static class points{
		int n1;
		int n2;
		double dis;
	}			//储存最近点对信息结构
	public static void main(String[] args) {
		/*主函数 生成点对 进行排序*/
		int n;double d0=0;
		int n1=0,n2=0;
		n=1000;
			Node[] dot=new Node[n];
			Node[] dot3=new Node[n];
			points pair0=new points();
			for (int i=0;i<n;i++) {
				dot[i]=new Node();
				dot[i].X=(Math.random()*n*8);
				dot[i].Y=(Math.random()*n*8);//让点稀疏
			}
			for(int i=0;i<n;i++){
				dot3[i]=new Node();
				dot3[i]=dot[i];
			}
			ShellSortbyX(dot);//O(nlgn)排序
			for(int i=0;i<n;i++) 
				{dot[i].num=i;}//排序后标号
			print(0,n-1,dot);
			pair0=findmindis(0,n-1,dot);
			n1=pair0.n1;n2=pair0.n2;d0=pair0.dis;
			
	    System.out.println("最近的两点("+dot[n1].X+","+dot[n1].Y+")"+"和("+dot[n2].X+","+dot[n2].Y+")");
	    System.out.println("最近距离"+d0);
	    double dmin=10000000,d9;int i1=0,i2=0;
	    		for(int i=0;i<n-1;i++)
	    			for(int j=i+1;j<n;j++)
	    			{d9=caldis(dot3[i],dot3[j]);
	    			if(d9<dmin) {dmin=d9;i1=i;i2=j;} } 
	    		System.out.println("最近距离"+dmin+"节点1:"+i1+"节点2:"+i2);
	}
    
	public static void print(int left,int right,Node []D) {
			/*打印点对*/
		for(int i=left;i<=right;i++) 
		  {System.out.println(i+":("+D[i].X+","+D[i].Y+")"+"num: "+D[i].num);}
	}
	  public static points findmindis(int left,int right,Node D[]) {
		  /*分治寻找最近点对*/
		  points pair=new points();
		  double mindis=0;
		  if(right-2<=left) 
			  {pair=findminlessthanThree(left,right,D);
			  return pair;
			  }
		  points pair1=new points();
		  points pair2=new points();
		  points pair3=new points();//三个点对结构 储存分治的三种点对
		  int n1,n2;
		  double midx=0,d1,d2;
		  
		  int mid=(left+right)/2;			//中位线确认
		
		  pair1=findmindis(left,mid,D);  	//	左区域内最近点对寻找
		  d1=pair1.dis;
		
		  pair2=findmindis(mid+1,right,D);	//	右区域内最近点对寻找
		  d2=pair2.dis;
		
		  if(d1<=d2) {n1=pair1.n1;n2=pair1.n2;mindis=d1;}
		 	else {n1=pair2.n1;n2=pair2.n2;mindis=d2;}//比较两个区域内各自点最小的一个
		  pair.n1=n1;pair.n2=n2;pair.dis=mindis;
		 // System.out.println("区域计算n1:"+pair.n1+"n2:"+pair.n2+"dmin"+mindis);
		  
		  /*两个点位于两个区域内计算*/
		  double d0=mindis;
		  midx=D[mid].X;
		  for(int i=0;i<=mid;i++)
			  if(midx-D[i].X<=d0) {left=i;break;}
		  for(int i=mid+1;i<=right;i++) 
			  if(D[i].X-midx>d0) {right=i-1;break;}
		  if(right==mid) return pair;//中位线区域内没有别的点
		  double d3=Double.MAX_VALUE;
		  if(right<=left+2)  pair3=findminlessthanThree(left,right,D); 
		  //点数不超过3直接暴搜
	

		  Node DY[]=new Node[right-left+1];		//准备对Y坐标排序
		  for (int i=0,j=left;i<right-left+1;i++,j++) {
				DY[i]=new Node();
				DY[i].num=D[j].num; 
				DY[i].X=D[j].X;
				DY[i].Y=D[j].Y;
			} 							//确认区域所有点
		  ShellSortbyY(DY); 			//对Y坐标排序
		/*上下各找4个点最多8个*/
		  for(int i=0;i<mid-left+1;i++)
		  {
			  for(int j=1;i-j>=0&&j<=4;j++)  //上找4个点
			  {
			      double dl=caldis(DY[i],DY[i-j]);
				  if(dl-d0<-0.0001) {			//存在更小的点
				  pair.n1=DY[i].num;pair.n2=DY[i-j].num;
				  d0=dl;}
			  }
			  for(int j=1;i+j<mid-left+1&&j<=4;j++)  //下找4个点
			  { 
				  
				  double dr=caldis(DY[i],DY[i+j]);
				  if(dr-d0<-0.0001) {			//存在更小的点
				  pair.n1=DY[i].num;pair.n2=DY[i+j].num;
				  d0=dr;}
			  } 
		  }
		  
		  /*比较跨区域两点和之前最小值的比较*/
		  pair.dis=d0;
		  return pair;
	  }
	  public static double caldis(Node l,Node r) {
		  return Math.hypot(l.X-r.X, l.Y-r.Y);
	  }

	  public static points findminlessthanThree(int left,int right,Node []D) {
		  /*不超过三个点的算法*/
		  points pair=new points();
		  if(left>=right)  
		  		{pair.dis=Double.MAX_VALUE;return pair;}		//一个点以下
		  	else if(right==left+1) {pair.dis=caldis(D[left],D[right]);//两个点
		  pair.n1=left;
		  pair.n2=right;
		  return pair;
		  }
		  else if(right==left+2) {								//三个点进行暴搜 若三点分治开销反而会大
		
			  int k0;
			  double d1=caldis(D[left],D[left+1]);
			  double d2=caldis(D[left],D[left+2]);
			  double d3=caldis(D[left+1],D[left+2]);
		
			  if((d1<=d2)&& (d1<=d3)) {pair.n1=left;pair.n2=left+1; pair.dis=d1;}
			  else if((d2<=d1)&& (d2<=d3)) {pair.n1=left;pair.n2=left+2; pair.dis=d2;}

			  else pair.n1=left+1;pair.n2=left+2; pair.dis=d3;
			  return pair;
		  }
		return pair;
	  }
	  public static void mins(double d1,double d2,double d3){
	
	  }
