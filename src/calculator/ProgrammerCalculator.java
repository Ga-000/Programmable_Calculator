package calculator;

import java.util.Arrays;
import java.util.Scanner;

public class ProgrammerCalculator {
	public static void main(String args[]) {
		int select, index=0;
		int max_length;
		int[] a;
		int[] b;
		int[] temp;
		int[] temp2;
		int[] quotient;
		int[] result;
		int k=0;
		int sign=1;//부호 1이라면 양수, 0이라면 음수
		String str_a,str_b,str_temp;
		String operator="";
		Scanner input = new Scanner(System.in);
		
		System.out.println("2진수 계산은 1, 10진수 계산은 2를 입력하라.");
		select=input.nextInt();
		
		if(select==2) {//10진수 계산기
			System.out.println("숫자를 입력하라.");
			str_a=input.next();
			if(str_a.charAt(0)=='-'){//문자열의 첫 글자가 '-'이라면
				sign=0;
				System.out.print("0-");
				str_a=str_a.substring(1, str_a.length());//'-'를 제외한 str_a 재초기화
				System.out.print(str_a);
				System.out.printf("\n");
			}
			else {
				sign=1;
			}
			while(true) {
				System.out.println("연산자를 입력하라.(+,-,/,*,0,abs,ff)");
				operator=input.next();
				if(operator.equals("0")) break;//연산자가 0이면 계산기 종료.
				
				if(operator.equals("abs")) {//절댓값 연산
					if(sign==0){
						sign=1;//부호를 +로 바꿈.
						System.out.printf("현재 계산 결과\n");
						System.out.print(str_a);
						System.out.printf("\n");
					}
					else {
						System.out.printf("현재 계산 결과\n");
						System.out.print(str_a);
						System.out.printf("\n");
					}
				}

				if(operator.equals("ff")) {//팩토리얼 연산
					String sum;
					int[] s;
					sum= str_a;
					str_b = "1";
					if(str_a.equals("2")||str_a.equals("1")) {
						System.out.printf("현재 계산 결과\n");
						System.out.printf(str_a);
						System.out.printf("\n");										
					}
					if(str_a.equals("0")) {
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1");
						System.out.printf("\n");
						str_a="1";										
					}
					while(true) { //먼저 sum-1=sum 		
						if(sum.equals("2")) {
							System.out.printf("현재 계산 결과\n");
							System.out.printf(str_a);
							System.out.printf("\n");
						}
						if(sum.equals("2")||sum.equals("1")||sum.equals("0")) {
							break;											
						}				
						max_length=Math.max(sum.length(), str_b.length());
						s = new int[max_length];
						b=new int[max_length];
						temp=new int[max_length];

						index=max_length-1;
						
						for(int i=sum.length()-1;i>=0;i--) {
							s[index]=sum.charAt(i)-'0';
							index--;
						}
						index=max_length-1;
						for(int i=str_b.length()-1;i>=0;i--) {
							b[index]=str_b.charAt(i)-'0';
							index--;
						}
						
						for(int i=max_length-1;i>=0;i--) {
							temp[i]=s[i]-b[i];
						}
						for(int i=max_length-1;i>0;i--) {
							if(temp[i]<0) {							
								temp[i]=10+temp[i];
								temp[i-1]=temp[i-1]-1;
							}
						}
						
						if(temp[0]==0) {
							result=new int[max_length-1];
							for(int i=0;i<max_length-1;i++) {
								result[i]=temp[i+1];
							}
							sum=Arrays.toString(result).replaceAll("[^0-9]","");
						}
						else {
							sum=Arrays.toString(temp).replaceAll("[^0-9]","");
						}
						
						for(int i=0;i<max_length;i++) {
							if(temp[i]!=0) {//(8)
								result=new int[max_length-i];
								for(int j=0;j<result.length;j++) {
									result[j]=temp[i];
									i++;
								}
								
								sum=Arrays.toString(result).replaceAll("[^0-9]","");
								break;
							}
						}
						if(sum.equals("0")) break;   												
			
						//곱하기   sum * str_a = str_a
						if(sum.equals("1")==false) {
						max_length=str_a.length()+sum.length();//최대 자리수는 두 수의 자리수를 더한 값
						a = new int[str_a.length()];
						s = new int[sum.length()];
						temp=new int[max_length];
							
						for(int i=0;i<str_a.length();i++) {
							a[i]=str_a.charAt(i)-'0';							
						}
						
						for(int i=0;i<sum.length();i++) {
							s[i]=sum.charAt(i)-'0';
						}
														
						for(int i=0;i<str_a.length();i++){
							for(int j=0;j<sum.length();j++) {
								temp[max_length-i-j-2]=temp[max_length-i-j-2]+a[str_a.length()-i-1]*s[sum.length()-j-1]/10;
								temp[max_length-i-j-1]=temp[max_length-i-j-1]+a[str_a.length()-i-1]*s[sum.length()-j-1]%10;								
							}
						}
								
						for(int i=max_length-1;i>0;i--) {
							if(temp[i]>=10) {
								temp[i-1]=temp[i-1]+temp[i]/10;
								temp[i]=temp[i]%10;
									
							}
						}								

						if(temp[0]==0) {
							result=new int[max_length-1];
							for(int i=0;i<max_length-1;i++) {
								result[i]=temp[i+1];
							}
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");
						}
						else {
							str_a=Arrays.toString(temp).replaceAll("[^0-9]","");
						}						
						}
					}
				}
				
				if(!operator.equals("abs")&&!operator.equals("ff")) {//이외의 연산자들은 str_b를 받아야함.
				System.out.println("숫자를 입력하라.");
				str_b=input.next();
				
				if((operator.equals("+")&&sign==1)||(operator.equals("-")&&sign==0)) {//덧셈 연산. 양수와 양수의 + 또는 음수와 음수의 -
					max_length=Math.max(str_a.length(),str_b.length());
					a=new int[max_length+1];//올림수가 발생할 수 있으므로 +1
					b=new int[max_length+1];
					temp=new int[max_length+1];	
					index=max_length;
					for(int i=str_a.length()-1;i>=0;i--) {//배열의 끝에서부터 초기화.
						a[index]=str_a.charAt(i)-'0';
						index--;
					}				
					index=max_length;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}									
					for(int i=max_length; i>0;i--) {//덧셈 연산.
						temp[i]=a[i]+b[i];						
					}					
					for(int i=max_length;i>0;i--) {//올림수 처리 연산.
						if(temp[i]>=10) {
							temp[i]=temp[i]%10;
							temp[i-1]=temp[i-1]+1;//윗자리 +1
						}
					}					
					if(temp[0]==0) {//올림수가 없다면?
						result=new int[max_length];
						for(int i=0;i<max_length;i++) {//맨 윗자리 0을 뺀 값들을 배열result에 초기화.
							result[i]=temp[i+1];
						}
						str_a=Arrays.toString(result).replaceAll("[^0-9]","");//배열result를 문자열str_a로.
					}
					else {
						str_a=Arrays.toString(temp).replaceAll("[^0-9]","");//올림수가 있으므로 배열temp를 문자열str_a로.
					}
					System.out.printf("현재 계산 결과\n");
					if(sign==0) System.out.printf("-");//음수라면 부호 - 출력
					System.out.print(str_a);
					System.out.printf("\n");
				}
				
								
				else if((operator.equals("-")&&sign==1)||(operator.equals("+")&&sign==0)) {//뺄셈 연산. 양수의 양수의 - 또는 음수와 양수의 +
					max_length=Math.max(str_a.length(), str_b.length());
					a=new int[max_length];
					b=new int[max_length];
					temp=new int[max_length];
										
					if(str_b.length()>str_a.length()) {//배열의 길이가 길다면 긴 배열이 더 큰 수.
						str_temp=str_a;//SWAP
						str_a=str_b;
						str_b=str_temp;
						if(sign==1) {//양수 - 양수에서 두번째 양수가 더 큰 값이므로
							sign=0;//부호가 바뀜.
						}
						else {
							sign=1;
						}
					}
					if(str_a.length()==str_b.length()) {//배열의 길이가 같은 경우.
						for(int i=0;i<max_length;i++) {//가장 윗 자리수부터 비교.
							if(str_b.charAt(i)-'0'>str_a.charAt(i)-'0') {//b의 자리가 더 크다면 b가 더 큰 수.
								str_temp=str_a;//SWAP
								str_a=str_b;
								str_b=str_temp;
								if(sign==1) {//양수 - 양수에서 두번째 양수가 더 큰 값이므로
									sign=0;//부호가 바뀜.
								}
								else {
									sign=1;
								}								
							}
							if(str_a.charAt(i)-'0'>str_b.charAt(i)-'0') {//a가 더 큰 경우.
								break;//비교연산을 빠져 나옴.
							}
						}
					}
					
					if(str_a.equals(str_b)) {//-0이 나오지 않게하기위함.
						sign=1;
					}
															
					index=max_length-1;
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}					
					
					index=max_length-1;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}
					
					for(int i=max_length-1;i>=0;i--) {//뺄셈 연산.
						temp[i]=a[i]-b[i];
					}					
					
					for(int i=max_length-1;i>0;i--) {//빌려오는 수 처리 연산.
						if(temp[i]<0) {
							temp[i]=10+temp[i];
							temp[i-1]=temp[i-1]-1;
						}
					}
					
					for(int i=0;i<max_length;i++) {
						if(i==max_length-1&&temp[i]==0) {//모두 다 0이라면?
							result=new int[1];//0 하나 있는 배열result 선언 후
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");//str_a로 초기화.
							break;
						}
						if(temp[i]!=0) {//0이 아닌 값을 만날때까지
							result=new int[max_length-i];
							for(int j=0;j<result.length;j++) {//0이 아닌 값부터 다시 배열result에 초기화.
								result[j]=temp[i];
								i++;
							}							
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");//str_a로 초기화.
							break;
						}
					}
					
					System.out.printf("현재 계산 결과\n");
					if(sign==0) {
						System.out.print("-");
					}
					System.out.printf(str_a);
					System.out.printf("\n");
					
				}
				
				else if(operator.equals("/")) {//나눗셈 연산
					max_length=Math.max(str_a.length(), str_b.length());
					a=new int[max_length];
					b=new int[max_length];
					temp=new int[max_length];
					temp2=new int[max_length];
					quotient=new int[max_length];
					int count=0;//나눠진 횟수
					
					for(int i=0;i<max_length;i++) {//-1로 초기화하여 나눠지는지 아닌지 알기 위함.
						quotient[i]=-1;
					}
						
					index=max_length-1;
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length-1;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}

					for(int i=1;i<max_length+1;i++) {	
					
						for(int j=1;j<max_length;j++) {//이전의 결과를 한칸씩 왼쪽으로 SHIFT.
							temp[j-1]=temp[j];
						}
						temp[max_length-1]=a[i-1];//마지막 자리는 a의 인덱스 값을 불러옴.
						
						for(int t=0;t<max_length;t++) {
							if(b[t]>temp[t]) {//나눠지지 않음.
								quotient[i-1]=0;
								break;
							}
							if(temp[t]>b[t]) {
								break;
							}						
						}
						
						if(quotient[i-1]==-1) {//나눠지는 경우
							do {
								for(int t=0;t<max_length;t++) {
									if(temp[t]>b[t]) {
										break;
									}		
									if(b[t]>temp[t]) {
										quotient[i-1]=count;//여태까지 나눠진 횟수를 몫배열에 저장.
										temp=temp2;//계속 이전 연산의 결과를 다음 연산으로 넘겨줌.
										break;
									}
								}
								if(quotient[i-1]!=-1) {
									break;
								}
								for(int z=max_length-1;z>=0;z--) {//뺄셈 연산.
									temp2[z]=temp[z]-b[z];
								}
								for(int x=max_length-1;x>0;x--) {//빌려오는 수 처리 연산.
									if(temp2[x]<0) {
										temp2[x]=10+temp2[x];
										temp2[x-1]=temp2[x-1]-1;
									}
								}
								
								count++;								
								temp=temp2;
							}while(true);
							count=0;							
						}
																																									
					}
					for(int i=0;i<max_length;i++) {
						if(quotient[i]!=0) {//윗 자리의 0을 제외.
							result=new int[max_length-i];
							for(int j=0;j<result.length;j++) {
								result[j]=quotient[i];
								i++;
							}
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");
							break;
						}
					}
					
					for(int i=0;i<max_length;i++) {
						if(b[i]>a[i]) {//b가 더 큰 수 였다면 0을 결과로.
							result=new int[1];
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");	
							sign=1;
							break;
						}
						if(a[i]>b[i]) {
							break;
						}
					}
					
					System.out.printf("현재 계산 결과\n");
					if(sign==0) {
						System.out.print("-");
					}
					System.out.print(str_a);
					System.out.printf("\n");
				}
				
				else if(operator.equals("*")) {//곱셈 연산
					max_length=str_a.length()+str_b.length();//배열의 길이는 두 길이를 더한 값.
					a=new int[str_a.length()];
					b=new int[str_b.length()];
					temp=new int[max_length];
					
					for(int i=0;i<str_a.length();i++) {
						a[i]=str_a.charAt(i)-'0';							
					}
										
					for(int i=0;i<str_b.length();i++) {
						b[i]=str_b.charAt(i)-'0';
					}
					
					for(int i=0;i<str_a.length();i++){
						for(int j=0;j<str_b.length();j++) {
							temp[max_length-i-j-2]=temp[max_length-i-j-2]+a[str_a.length()-i-1]*b[str_b.length()-j-1]/10;//각 자리마다 곱한 값을 10으로 나눈 몫을 다음 자리에 더함.
							temp[max_length-i-j-1]=temp[max_length-i-j-1]+a[str_a.length()-i-1]*b[str_b.length()-j-1]%10;//각 자리마다 곱한 값을 10으로 나눈 나머지를 저장.				
						}
					}
						
					for(int i=max_length-1;i>=0;i--) {
						if(temp[i]>=10) {//올림수가 있다면?
							temp[i-1]=temp[i-1]+temp[i]/10;//처리.
							temp[i]=temp[i]%10;
							
						}
					}	
					
					if(temp[0]==0) {//가장 윗자리 0을 지우는 연산.
						result=new int[max_length-1];
						for(int i=0;i<max_length-1;i++) {
							result[i]=temp[i+1];
						}
						str_a=Arrays.toString(result).replaceAll("[^0-9]","");
					}
					else {
						str_a=Arrays.toString(temp).replaceAll("[^0-9]","");
					}
					
					System.out.printf("현재 계산 결과\n");
					if(sign==0) {
						System.out.printf("-");
					}
					System.out.printf(str_a);
					System.out.printf("\n");
				}												
			}
			}
		}
		
		if(select==1) {//2진수 계산기
			System.out.println("숫자를 입력하라.");
			str_a=input.next();
			if(str_a.charAt(0)=='-'){
				sign=0;
				System.out.print("0-");
				str_a=str_a.substring(1, str_a.length());
				System.out.print(str_a);
				System.out.printf("\n");
			}
			else {
				sign=1;
			}
			
			while(true) {
				System.out.println("연산자를 입력하라.(+,-,/,*, &, |, ^, <<, >>>, 0)");
				operator=input.next();
				if(operator.equals("0")) break;
				System.out.println("숫자를 입력하라.");
				str_b=input.next();
				
				if((operator.equals("+")&&sign==1)||(operator.equals("-")&&sign==0)) {//덧셈 연산. 경우의 수는 10진수와 같음.
					max_length=Math.max(str_a.length(), str_b.length());
					a=new int[max_length+1];
					b=new int[max_length+1];
					temp=new int[max_length+1];
					
					index=max_length;					
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}

					for(int i = max_length; i>0; i--) {						
						int value = a[i] + b[i];//배열 자리마다 더한 값을 value에 저장.
						a[i] = value % 2;
						a[i - 1] += (value / 2);//올림 수 처리.
					}

					index=max_length;
					if(a[0]==0) {
						temp=new int[max_length];
						index=max_length-1;
						for(int i=0;i<max_length;i++) {
							temp[index]=a[index+1];
							index--;
						}
					}
					else {
						temp=new int[max_length+1];
						for(int i=0;i<max_length+1;i++) {
							temp[i]=a[i];
							index++;
						}
					}
					
					str_a=Arrays.toString(temp).replaceAll("[^0-9]","");
					if(sign==1) {//양수의 경우
						System.out.printf("현재 계산 결과\n");
						System.out.printf(str_a);
						System.out.printf("\n");
					}	
					if(sign==0) {//음수의 경우
						for(int i=0;i<max_length;i++) {
							if(temp[i]==0) {//0과 1을 바꾸고
								temp[i]=1;
							}
							else {
								temp[i]=0;
							}
						}
						temp[max_length-1]=temp[max_length-1]+1;//1을 더하고
						for(int i=max_length-1;i>0;i--) {
							if(temp[i]>1) {//올림수 처리
								temp[i]=temp[i]%2;
								temp[i-1]=temp[i-1]+1;
							}
						}					
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");//표현하지 못한 비트들을 표시. ex) 101 == 0000 0000 0000 0101 과 같음.
						for(int i=0;i<temp.length;i++) {
							System.out.print(temp[i]);
						}
						System.out.printf("\n");
					}				
																			
				}
				
				else if((operator.equals("-")&&sign==1)||(operator.equals("+")&&sign==0)) {//뺄셈 연산. 경우의 수는 10진수와 같음.
					max_length=Math.max(str_a.length(), str_b.length());
					a=new int[max_length];
					b=new int[max_length];
					temp=new int[max_length];
					result=new int[max_length];					
					
					if(str_b.length()>str_a.length()) {//크기 비교는 10진수와 동일
						str_temp=str_a;
						str_a=str_b;
						str_b=str_temp;
						if(sign==1) {
							sign=0;
						}
						else {
							sign=1;
						}
					}					
					if(str_a.length()==str_b.length()) {//크기 비교는 10진수와 동일
						for(int i=0;i<max_length;i++) {
							if(str_b.charAt(i)-'0'>str_a.charAt(i)-'0') {
								str_temp=str_a;
								str_a=str_b;
								str_b=str_temp;
								if(sign==1) {
									sign=0;
								}
								else {
									sign=1;
								}
								break;								
							}
							if(str_a.charAt(i)-'0'>str_b.charAt(i)-'0') {
								break;
							}
						}						
					}
													
					index=max_length-1;
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length-1;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}

					for(int i=max_length-1;i>=0;i--) {
						temp[i]=a[i]-b[i];
					}
					
					for(int i=max_length-1;i>0;i--) {
						if(temp[i]<0) {//빌려온 수가 있다면
							temp[i]=temp[i]+2;//처리
							temp[i-1]=temp[i-1]-1;
						}									
					}
					
					for(int i=0;i<max_length;i++) {//윗 자리들의 0을 지우는 연산.
						if(temp[i]!=0) {
							index=i;
							result=new int[max_length-i];
							for(int j=0;j<max_length-i;j++) {
								result[j]=temp[index];
								index++;
							}
							break;
						}						
					}
					
					if(str_a.equals(str_b)) {//두 수가 같다면? 0 하나만 있는 배열로
						result=new int[1];
					}
					
					if(str_a.equals(str_b)) {//-0이 나오지 않기 위함.
						sign=1;
					}
					
					str_a=Arrays.toString(result).replaceAll("[^0-9]","");
					
					if(sign==1) {//양수의 경우
						System.out.printf("현재 계산 결과\n");						
						System.out.print(str_a);
						System.out.printf("\n");
					}
					if(sign==0) {//음수의 경우. 2의 보수 표현.
						for(int i=0;i<result.length;i++) {
							if(result[i]==0) {
								result[i]=1;
							}
							else {
								result[i]=0;
							}
						}
						result[result.length-1]=result[result.length-1]+1;
						for(int i=result.length-1;i>0;i--) {
							if(result[i]>1) {
								result[i]=result[i]%2;
								result[i-1]=result[i-1]+1;
							}
						}					
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");
						for(int i=0;i<result.length;i++) {
							System.out.print(result[i]);
						}
						System.out.printf("\n");
					}				
				}
				
				else if(operator.equals("&") || operator.equals("|") || operator.equals("^")) {//비트 연산.
					max_length=Math.max(str_a.length(), str_b.length());
					a=new int[max_length];
					b=new int[max_length];
					temp2=new int[max_length];
					
					index=max_length-1;
					for(int i=str_a.length()-1; i>=0; i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length-1;
					for(int i=str_b.length()-1; i>=0; i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}
					
					index=max_length;
					temp=new int[max_length];
					
					if(sign==0) {//음수라면? 2의 보수로 바꾼 후 연산.
						sign=1;
						for(int i=0;i<max_length;i++) {
							if(a[i]==0) {
								a[i]=1;
							}
							else {
								a[i]=0;
							}
						}
						a[a.length-1]=a[a.length-1]+1;
						for(int i=a.length-1;i>0;i--) {
							if(a[i]>1) {
								a[i]=a[i]%2;
								a[i-1]=a[i-1]+1;
							}
						}
						
					}
					
					
					for(int i=0; i<max_length; i++) {																		
						if(operator.equals("&")) {//&연산자라면
							if (a[i]==1 && b[i]==1) temp[i]=1;
							if (a[i]==1 && b[i]==0) temp[i]=0;
							if (a[i]==0 && b[i]==1) temp[i]=0;
							if (a[i]==0 && b[i]==0) temp[i]=0;
							index--;
						}
						else if(operator.equals("|")) {//|연산자라면
							if (a[i]==1 && b[i]==1) temp[i]=1;
							if (a[i]==1 && b[i]==0) temp[i]=1;
							if (a[i]==0 && b[i]==1) temp[i]=1;
							if (a[i]==0 && b[i]==0) temp[i]=0;
							index--;
						}
						else if(operator.equals("^")) {//^연산자라면
							if (a[i]==1 && b[i]==1) temp[i]=0;
							if (a[i]==1 && b[i]==0) temp[i]=1;
							if (a[i]==0 && b[i]==1) temp[i]=1;
							if (a[i]==0 && b[i]==0) temp[i]=0;
							index--;
						}
					}					
					
					for(int i=0;i<max_length;i++) {
						if(temp[i]!=0) {
							temp2=new int[max_length-i];
							for(int j=0;j<temp2.length;j++) {
								temp2[j]=temp[i];
								i++;
							}
							break;
						}
						if(i==max_length-1&&temp[max_length-1]==0) {
							temp2=new int[1];
							temp2[0]=0;
						}
					}
					
					str_a=Arrays.toString(temp2).replaceAll("[^0-9]","");
					System.out.printf("현재 계산 결과\n");
					System.out.printf(str_a);
					System.out.printf("\n");
					
					}
			
				else if(operator.equals("*")) {//곱셈 연산.
					max_length=str_a.length()+str_b.length();
					a=new int[max_length];
					b=new int[max_length];
					temp=new int[max_length];
					result=new int[max_length];
					
					index=max_length-1;
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length-1;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}				

					for(int i=0;i<str_a.length();i++){
						for(int j=0;j<str_b.length();j++) {
							temp[max_length-i-j-1]=temp[max_length-i-j-1]+a[max_length-i-1]*b[max_length-j-1];//각 자리마다 더한 값을 저장.					
						}																	
					}
					
					for(int i=max_length-1;i>=0;i--) {
						if(temp[i]>=2) {//올림 수가 있다면 처리.
							temp[i-1]=temp[i-1]+temp[i]/2;
							temp[i]=temp[i]%2;							
						}
					}
					
					for(int i=0;i<max_length;i++) {
						if(temp[i]!=0) {
							result=new int[max_length-i];
							for(int j=0;j<result.length;j++) {
								result[j]=temp[i];
								i++;
							}
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");
							break;
						}
					}
					
					if(str_b.length()==1&&str_b.charAt(0)=='0') {//0을 곱하는 경우.
						result=new int[1];//0 하나를 원소로 갖는 배열 선언 후 문자열로 넘김.
						str_a=Arrays.toString(result).replaceAll("[^0-9]","");
					}

					if(sign==1) {//양수의 경우.
						System.out.printf("현재 계산 결과\n");
						System.out.print(str_a);
						System.out.printf("\n");
					}
					if(sign==0) {//음수의 경우.
						for(int i=0;i<result.length;i++) {
							if(result[i]==0) {
								result[i]=1;
							}
							else {
								result[i]=0;
							}
						}
						result[result.length-1]=result[result.length-1]+1;
						for(int i=result.length-1;i>0;i--) {
							if(result[i]>1) {
								result[i]=result[i]%2;
								result[i-1]=result[i-1]+1;
							}
						}					
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");
						for(int i=0;i<result.length;i++) {
							System.out.print(result[i]);
						}
						System.out.printf("\n");
					}
				}
				
				else if(operator.equals("/")) {//나눗셈 연산
					max_length=Math.max(str_a.length(),str_b.length());
					String str_a2;
					a=new int[max_length];
					b=new int[max_length];
					temp=new int[max_length];
					temp2=new int[max_length];
					quotient = new int[max_length];
					result= new int[max_length];
					str_a2=str_a;
					
					for(int i=0;i<max_length;i++) {//10진수와 같이 -1로 초기화
						quotient[i]=-1;
					}
					
					index=max_length-1;					
					for(int i=str_a.length()-1;i>=0;i--) {
						a[index]=str_a.charAt(i)-'0';
						index--;
					}
					
					index=max_length-1;
					for(int i=str_b.length()-1;i>=0;i--) {
						b[index]=str_b.charAt(i)-'0';
						index--;
					}					
					
					for(int i=0;i<max_length;i++) {//하나씩 쉬프트 연산. 10진수의 경우와 같음.						
						for(int j=0;j<max_length-1;j++) {
							temp[j]=temp[j+1];
						}
						temp[max_length-1]=a[i];

						for(int t=0;t<max_length;t++) {
							if(b[t]>temp[t]) {
								quotient[i]=0;
								break;
							}
							if(temp[t]>b[t]) {
								break;
							}
						}
						
						if(quotient[i]==-1) {//2진수의 경우는 하나씩 쉬프트하여 나눠지느냐, 안나눠지느냐의 경우만 있음.
							quotient[i]=1;
							for(int z=max_length-1;z>=0;z--) {
								temp2[z]=temp[z]-b[z];
							}
							for(int x=max_length-1;x>0;x--) {
								if(temp2[x]<0) {
									temp2[x]=temp2[x]+2;
									temp2[x-1]=temp2[x-1]-1;
								}
							}

							temp=temp2;											
						}						
						
					}
					
					for(int i=0;i<max_length;i++) {
						if(quotient[i]!=0) {
							result=new int[max_length-i];
							for(int j=0;j<result.length;j++) {
								result[j]=quotient[i];
								i++;
							}
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");
							break;
						}
						if(i==max_length-1&&quotient[i]==0) {
							result=new int[1];
							result[0]=0;
							str_a=Arrays.toString(result).replaceAll("[^0-9]","");
						}
					}


					if(sign==0) {//크기 비교. 두 번째 수가 더 큰 경우
					if(str_b.length()>str_a2.length()) {//크기 비교는 10진수와 동일

						str_a="0";
						if(sign==0) {
							sign=1;
						}
					}					
					if(str_a2.length()==str_b.length()) {
						for(int i=0;i<max_length;i++) {
							if(str_b.charAt(i)-'0'>str_a2.charAt(i)-'0') {
								str_a="0";
								if(sign==0) {
									sign=1;
								}
								break;								
							}
							if(str_a2.charAt(i)-'0'>str_b.charAt(i)-'0') {
								break;
							}
						}						
					}
					}
					
					if(sign==1) {//양수의 경우.
						System.out.printf("현재 계산 결과\n");
						System.out.print(str_a);
						System.out.printf("\n");
					}
					
					
					if(sign==0) {//음수의 경우.
						for(int i=0;i<result.length;i++) {
							if(result[i]==0) {
								result[i]=1;
							}
							else {
								result[i]=0;
							}
						}
						result[result.length-1]=result[result.length-1]+1;
						for(int i=result.length-1;i>0;i--) {
							if(result[i]>1) {
								result[i]=result[i]%2;
								result[i-1]=result[i-1]+1;
							}
						}	
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");
						for(int i=0;i<result.length;i++) {
							System.out.print(result[i]);
						}
						System.out.printf("\n");
					}	
				}
				
				else if(operator.equals("<<")) {
					a=new int[str_a.length()];//배열 객체 생성...크기가 str_a.length
					int numb=Integer.parseInt(str_b);//문자열인 str_b를 정수로 바꾼 값을 가지는 변수 numb
					
					if(str_a.charAt(0)=='0') {////0에서 << 연산 시 0이 중복되는 str_a가 형성되는 것을 막고자함
						temp=new int[1];////위처럼 str_a값이 0, 00, 000, 0000, ......인 경우에 모두 0으로 초기화한 후에야 연산과정을 시작한다
					}
					else {
						temp=new int[str_a.length()+numb];//배열 객체 생성...크기가 str_a.length + numb
					}
					for(int i=0;i<str_a.length();i++) {//배열 끝부터 저장
						a[i]=str_a.charAt(i)-'0';//str_a.charAt(0) - 0의 ASCII값인 80 => 숫자 그대로를 받음					}
					}
					for(int t=0;t<str_a.length();t++) {////그대로 복사
						temp[t]=a[t];
					}

					
					str_a=Arrays.toString(temp).replaceAll("[^0-9]", "");//하지 않으면 str_a = "1234"가 아닌 str_a = “[1, 2, 3, 4,]”처럼 초기화됨
					if(sign==1) {
					System.out.printf("현재 계산 결과\n");
					System.out.printf(str_a);
					System.out.printf("\n");
					}
					if(sign==0) {
						for(int i=0;i<temp.length;i++) {
							if(temp[i]==0) {
								temp[i]=1;
							}
							else {
								temp[i]=0;
							}
						}
						temp[temp.length-1]=temp[temp.length-1]+1;
						for(int i=temp.length-1;i>0;i--) {
							if(temp[i]>1) {
								temp[i]=temp[i]%2;
								temp[i-1]=temp[i-1]+1;
							}
						}					
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");
						for(int i=0;i<temp.length;i++) {
							System.out.print(temp[i]);
						}
						System.out.printf("\n");
					}
				}
				
				else if (operator.equals(">>>")) {
					a=new int[str_a.length()];//배열 객체 생성...크기가 str_a.length
					int numb=Integer.parseInt(str_b);//문자열인 str_b를 정수로 바꾼 값을 가지는 변수 numb
					
					if(str_a.length() <= numb) {////배열의 크기를 음수로 지정하여 NegativeArraySizeException이 나오는 문제와, 빈 문자열로 인한 빈 결과값 출력 문제를 해결 
						temp=new int[1];////위의 경우엔 배열의 크기를 0으로 초기화한 뒤 연산과정 시작함
					}
					else {////배열의 크기가 양수인 경우
						temp=new int[str_a.length()-numb];//numb를 뺀만큼의 크기로 배열 설정..안그러면 자릿수 유지
					}
					
					for(int i=0;i<str_a.length();i++) {////배열 끝부터 저장
						a[i]=str_a.charAt(i)-'0';////str_a.charAt(0) - 0의 ASCII값인 80 => 숫자 그대로를 받음					}
					}
					
					for(int t=0;t<str_a.length()-numb;t++) {////범위 조정을 통해 담고 싶은 곳까지만 담고 끊기
						temp[t]=a[t];////해결! Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
					}

					str_a=Arrays.toString(temp).replaceAll("[^0-9]", "");//하지 않으면 str_a = "1234"가 아닌 str_a = “[1, 2, 3, 4,]”처럼 초기화됨
					if(sign==1) {
					System.out.printf("현재 계산 결과\n");
					System.out.printf(str_a);
					System.out.printf("\n");
					}
					if(sign==0) {
						for(int i=0;i<temp.length;i++) {
							if(temp[i]==0) {
								temp[i]=1;
							}
							else {
								temp[i]=0;
							}
						}
						temp[temp.length-1]=temp[temp.length-1]+1;
						for(int i=temp.length-1;i>0;i--) {
							if(temp[i]>1) {
								temp[i]=temp[i]%2;
								temp[i-1]=temp[i-1]+1;
							}
						}					
						System.out.printf("현재 계산 결과\n");
						System.out.printf("1111...1 ");
						for(int i=0;i<temp.length;i++) {
							System.out.print(temp[i]);
						}
						System.out.printf("\n");
					}
					
				}
					
			}
		}
	}
}
			