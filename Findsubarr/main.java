import java.util.Arrays;
public class main {

	private static int[][] cs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int na=20,nb=15;//数组长度
		int[] arr = new int[na+1];
		int[] brr=new int[nb+1];
		for(int i=1;i<=na;i++) {
			arr[i]=(int)(na*Math.random());//随机数组生成
		}
		for(int i=1;i<=nb;i++) {
			brr[i]=(int)(nb*Math.random());//随机数组生成
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(brr));
		findsubmn(arr,brr);
	}
	public static void findsubmn(int a[],int b[]) {
		int tail;//标识cs数组的尾部
		
		cs=new int[a.length][b.length];
		for(int i=0;i<a.length;i++) {cs[i][0]=0;}
		for(int j=0;j<b.length;j++) {cs[0][j]=0;}
				
		for(int i=1;i<a.length;i++)
			for(int j=1;j<b.length;j++)
			{if(a[i]==b[j]) {cs[i][j]=cs[i-1][j-1]+1;}
			else cs[i][j]=max(cs[i-1][j],cs[i][j-1]);
			}
		//现在cs保留的就是LCS的长度
		int maxlength=cs[a.length-1][b.length-1];
		
		/*输出LCS长度*/
		int[] lcs=new int[maxlength];
		for(int i=1,count=0;count<maxlength;i++)
			for(int j=1;j<b.length;j++) {
				if(cs[i][j]>count) {lcs[count]=a[i];count++;continue;}
			}
		System.out.println(maxlength);
		System.out.println(Arrays.toString(lcs));	
	}
	public static int max(int a,int b) {
		if(a>=b) return a;
		else return b;
	}
}
