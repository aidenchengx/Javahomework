import java.util.Arrays;

public class Sort{
  
    public static void main(String[] args) 
    {int i,j=0;
    int n[]= {6000000,6500000,7000000,7500000,8000000,8500000,9000000,9500000,10000000,10000000};
    long[][] stime=new long[10][8];
    for(j=0;j<10;j++) {
    int[] a=new int[n[j]];
  
    	for(i=0;i<n[j];i++)
    		a[i]=(int)(Math.random()*n[j]*10);			//数组初始化
    	  System.out.println(n[j]);						//数据规模
    	
    	stime[j][0]=SelectSort(a);
    	stime[j][1]=BubbleSort(a);
    	stime[j][2]=InsertSort(a);
    	stime[j][3]=MergeSort(a);
    	stime[j][4]=ShellSort(a);
    	stime[j][5]=QuickSort(a);
    	stime[j][6]=PileSort(a);
    	stime[j][7]=CountSort(a);
   		System.out.println("选择排序运行时间"+stime[j][0]+"ms\n");
    	System.out.println("冒泡排序运行时间"+stime[j][1]+"ms\n");
        System.out.println("插入排序运行时间"+stime[j][2]+"ms\n");
        System.out.println("归并排序运行时间"+stime[j][3]+"ms\n");
        System.out.println("希尔排序运行时间"+stime[j][4]+"ms\n");
        System.out.println("快速排序运行时间"+stime[j][5]+"ms\n");
        System.out.println("堆排序运行时间"+stime[j][6]+"ms\n");
        System.out.println("计数排序运行时间"+stime[j][7]+"ms\n");
        }
        }
    public static long SelectSort(int a[]) {//简单选择排序
    	long time1=System.currentTimeMillis();  
    	long time2,interval;
    	int i,j=0,min,k;
    	int l=a.length;
    	int[] s1=new int[l];
    	for(i=0;i<l;i++)
    		s1[i]=a[i];					//保留原始数组
    	for(i=0;i<l-1;i++)
    	  {min=s1[i];
    		for(k=i;k<l;k++)
    		if(s1[k]<min) {				//记录最小值位置和数据
    			j=k;
    			min=s1[k];
    		}
       	  s1[j]=s1[i];					//数据交换
    	  s1[i]=min;
    	  }
    time2=System.currentTimeMillis(); 
    interval=time2-time1;
    return interval;
}
    public static long BubbleSort(int a[]) {//冒泡排序
    	long time1=System.currentTimeMillis();  
    	long time2,interval;
    	int i,j,l=a.length,mark=1,swap;
    	int[] s=new int[l];
    	for(i=0;i<l;i++)
    		s[i]=a[i];
    //	System.out.println(Arrays.toString(s));
    	for(i=0;i<l;i++)
    		{if(mark==0) break;				//上一轮没有交换过程 用标志位提高算法性能
    		mark=0;							//标志位清0
    		for(j=0;j<l-i-1;j++)
    		
    			if(s[j]>s[j+1]) 
    			{mark=1;					//有交换过程
    			swap=s[j];
    			s[j]=s[j+1];
    			s[j+1]=swap;
    			}
    	//	System.out.println(Arrays.toString(s));
    		}
    	time2=System.currentTimeMillis(); 
    	interval=time2-time1;
    	return interval;
    }
    public static long InsertSort(int a[]) {//插入排序
    	long time1=System.currentTimeMillis();  
    	long time2,interval=0;
    	int i,j,k,l=a.length;
    	int[] s=new int[l];

    	s[0]=a[0];
    	for(i=1;i<l;i++)
    		{for(j=0;j<i;j++)
    		{if(a[i]<s[j])				//找到位置
    		 {for(k=i;k>j;k--)
    			 s[k]=s[k-1];			//挪动位置
    		 s[j]=a[i];
    		 break;   	
    		 }
    		s[i]=a[i];
    		}//System.out.println(Arrays.toString(s));
    		}	
    	time2=System.currentTimeMillis();  
    	interval=time2-time1;
    	return interval;
    }
    public static long MergeSort(int a[]) {//归并排序递归调用
    	long time1=System.currentTimeMillis();  
    	long time2,interval;
    	int i,l=a.length,m=0;
    	int len=1;
    	int[] s=new int[l];
    	for(i=0;i<l;i++)
    		s[i]=a[i];

    	while (len<l) {
    		m=len;
    		len=2*m;						
    		i=0;
    		while(i+len<l) 
    			{Merge(s,m,i,i+len-1);			//区间归并
    			i=i+len;}
    		if(i+m<l)	Merge(s,m,i,l-1);			//尾部区间归并
        	}
   
    	time2=System.currentTimeMillis();
    	interval=time2-time1;
    	return interval;
    	
    }
    public static void Merge(int s[],int m,int l,int r) {	//归并排序子程序
    	int lj=l,rj=0,j=0;
    	rj=lj+m;
    	int[] t=new int[r-l+1];
    	while(j<r-l+1)
    		{if(s[lj]<=s[rj]) {				//左侧较小
    			t[j++]=s[lj++];	
    			if(lj==l+m) break;
    		}
    		else {							//右侧较小
    			t[j++]=s[rj++];			
    			if(rj>r) break;
    		     }
    		}
    	if(lj==l+m) {//System.out.println(j);			
    		while(j<r-l+1) t[j++]=s[rj++];}		//剩余的直接加入尾部
    	else if(rj>r){while(j<r-l+1) t[j++]=s[lj++];}
    	j=0;
    	for(;l<=r;l++)
    		{s[l]=t[j++];}
    }
    		
    public static long ShellSort(int a[]) {		//希尔排序
    	int i,x,temp,j;
    	int l=a.length;
    	int d=l;
    	long time1=System.currentTimeMillis();  
    	long time2,interval;
    	int[] s=new int[l];
    	for(i=0;i<l;i++)
    		s[i]=a[i];
    	
    	  while(true)
          {		d=d/2;				//增量每次减半
              for(x=0;x<d;x++)
              {
                  for(i=x+d;i<l;i=i+d)
                  {
                      temp=s[i];
                      for(j=i-d;j>=0&&s[j]>temp;j=j-d)
                      {
                          s[j+d]=s[j];			//内部使用直接插入排序
                      }
                      s[j+d]=temp;
                  }
              }
            //  System.out.println(Arrays.toString(s));
              if(d==1)
              {
                  break;
              }
          }
      	time2=System.currentTimeMillis();
      	interval=time2-time1;
      	return interval;
}
    
      public static long QuickSort(int a[]) {	//快速排序递归调用
    	 	long time1=System.currentTimeMillis();  
        	long time2,interval;
        	int l=a.length;
    	  
    	int[] s=new int[l];
      	for(int i=0;i<l;i++)
      		s[i]=a[i];
      	QuickS(0,l-1,s);
      	time2=System.currentTimeMillis();
      	interval=time2-time1;
      	return interval;
      }
      
      public static void QuickS(int left,int right,int a[]) {	//快速排序子函数

      	int blank,key,start=left,end=right;
      	blank=right;
      	if(left>=right || right<=left) return;      	//区间长度为0
      	key=a[right];
    
      	while(left<right)								
      	{while(left<right && a[left]<=key) left++; 		//左指针移动 直到需要交换
      		a[blank]=a[left];
      		blank=left;
      	while(left<right && a[right]>=key) right--;		//右指针移动 直到需要交换
      		a[blank]=a[right];
      		blank=right;
      	}
      	a[blank]=key;//System.out.println(right);
     // 	System.out.println(Arrays.toString(a));
      	QuickS(start,blank-1,a);						
      	QuickS(blank+1,end,a);
      	return;
      }
      
      public static long PileSort(int a[]) {		//堆排序
  	 	long time1=System.currentTimeMillis();  
    	long time2,interval;
    	int swap;
    	int l=a.length;
      	int[] s=new int[l];
      	for(int i=0;i<l;i++)
      		s[i]=a[i];
    	PileSpawn(s,l); 			//生成大顶堆
    	// System.out.println(Arrays.toString(s)); 
    	for(int i=0;i<l-1;i++)
    	{swap=s[l-i-1];						//选出最大的 扔到数组尾部 之后 再次调整重整为大顶堆
    		s[l-i-1]=s[0];
    		s[0]=swap; //System.out.println(Arrays.toString(s)); 
    		HeadAdjust(0,s,l-i-1);  //System.out.println(Arrays.toString(s)); 
    	}//System.out.println(Arrays.toString(s)); 
    	time2=System.currentTimeMillis(); 
      	interval=time2-time1;
      	return interval;  
      }
      public static void PileSpawn(int s[],int l) {	//生成大顶堆
    	  int i;
    	  for(i=l/2-1;i>=0;i--)
    	  {HeadAdjust(i,s,l);
    	
    	  }
    	  }
      
      public static void HeadAdjust(int i,int s[],int l) {	//调整某一节点 使该节点下的子树满足大顶堆
    	  int max;
    	  if(i>l/2-1) return;
    	  if (s[2*i]>s[2*i+1]) max=2*i;
    	  else max=2*i+1;
    	  if(s[max]>s[i]) {				//子节点与父节点交换
    		  int swap=s[max];
    		  s[max]=s[i];
    		  s[i]=swap;
    		  HeadAdjust(max,s,l);		//交换后子节点以下子树重整 成为大顶堆
    		  
    	  }
    	  return;
      }
      
      public static long CountSort(int[]a){	   //计数排序
    	 	long time1=System.currentTimeMillis();  
        	long time2,interval;
    	  int l=a.length;
          int s[] = new int[l];
          int max = a[0],min = a[0];
          for(int i:a){					//遍历整个数组
              if(i>max){					//记录最大值
                  max=i;}
              if(i<min){					//记录最小值
                  min=i;}
          			   }
          int k=max-min+1;					//元素大小极值差+1
          int c[]=new int[k];
          for(int i=0;i<l;++i){
              c[a[i]-min]++;				//标记比最小值大某一数的数 他的出现次数
          }
          for(int i=1;i<c.length;++i){
              c[i]=c[i]+c[i-1];}				//计算比最小值大某一数的数 他应该所在的位置
          for(int i=a.length-1;i>=0;--i){
              s[--c[a[i]-min]]=a[i];}		//按照c数组的记录 将元素放在对应位置
  	time2=System.currentTimeMillis(); 
  	interval=time2-time1;
  	return interval;  
      }
  
}
