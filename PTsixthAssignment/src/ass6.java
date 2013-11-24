// Assignment 6 Strahler & Pruning Numbers
// Author Nouf Albarakati


import java.io.IOException;
import java.util.Scanner;

public class ass6 {
	public static int[] left;
	public static int[] right;
	public static int[] prn;
	public static int n;
	static int tree_perm = 1;
	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		
		// variables definintion
		int[] str;
		
		// get nodes info
		System.out.println("Generation of Binary Tree");
		System.out.println("*************************");
		System.out.print("\n\n Please enter number of nodes: ");
		//variavles initialization
		n = in.nextInt();
		if (n==0)
		{
			System.out.println("Null Tree, Good bye!"); 
			System.exit(0);
		}
		else if (n==1)
		{
			System.out.println("Single node Tree, no more trees! Good bye!"); 
			System.exit(0);
		}
			
		left = new int[n+1];
		right = new int[n+1];
		str = new int[n+1];
		prn = new int[n+1];
		
		// either get tree info or begin from the right tree
		System.out.print("\n\n Do you want to enter tree info and test the Strahler number and pruning number? (y/n): ");
		char ans = (char) System.in.read();
		if (ans == 'y' || ans == 'Y')
		{
			int i=1;
			System.out.println("Enter left node and right node for n nodes binary tree, -1 -1 to finish:");
			do{
				int l,r;
				l=in.nextInt();
				r=in.nextInt();
				if(l==-1 || r==-1)
					break;
				else
				{
					left[i]=l;
					right[i]=r;
					i++;
				}
			}while(i<=n);
			for(int j=i;j<=n;j++)
			{

				left[j]=0;
				right[j]= 0;
			}
//			test the strahler number and pruning number
			//initialize the str no with 0
			for(int a=1; a<=n;a++)
				str[a]=0;
			//initialize the prn no with 0
			for(int a=1; a<=n;a++)
				prn[a]=0;
			
//			int str_done=0;
//			// find the leaf nodes and update str no with 1
//			for(int a=1; a<=n;a++)
//			{
//				if(left[a]==0 && right[a]==0)
//				{
//					str[a]=1;
//					str_done++;
//				}
//			}
//			
//			while(str_done<n)
//			{
//				for(int a=n; a>0;a--)
//				{
//					if(str[a]==0)
//					{
//						if(str[left[a]] != 0 && str[right[a]]!=0)
//						{
//							if(str[left[a]] != str[right[a]])
//								str[a]= Math.max(str[left[a]], str[right[a]]);
//							else
//								str[a]=str[left[a]]+1;
//							str_done++;
//						}
//					}
//						
//				}
//			}
//			
//			System.out.println(" The strahler no for the enterned tree is : " + str[1]);

			
			int next=1, l_prn=0,r_prn;
			do{
				r_prn=pruning(next);
				if(r_prn>l_prn)
					l_prn=r_prn;
				next=right[next];
			}
			while(next!=0);
			
			System.out.println("The Pruning no of the tree  is: " + l_prn);
		
		}
		else{
		///	
			
		}

		genTree(n, left, right);
				
	}

	public static int pruning(int root)
	{
		
		int result=0, next, index=0;
		if(left[root]==0 && right[root]==0)
			return 1;
		else if (right[root]!=0)
		{
			next=root;
			while(right[next]!=0)
			{
				if(left[next]!=0)
				{
					prn[index]=pruning(left[next]);
					index++;
				}
				else
				{
					prn[index]=1;
					index++;
				}
				next=right[next];
			}
			//find the largest pruning number
			int count=0;
			for(int a=0;a<index;a++)
			{
				if(prn[a]>result){
					result=prn[a];
					count=0;
				}
					
				if(prn[a]==result)
					count++;
			}
			if(count>=2)
				result++;
			
		}
		return result;		
	}
	public static void genTree(int n, int[] left, int[] right){
		Boolean Done = false;
		display_array(n, left, right);
		do
		{
			Done = false;
			int p =1 , pred =0, next =0, count = 0, update= 0;
			while(left[p]!=0 || right[p] !=0)  // traverse till reach leaf node
			{
				while (right[p] !=0) //traverse till reach the most right leaf node which is the next node to be move back in order
				{
					pred = p;
					next = right[p];
					p = next;
					count=0;
				}
				if(left[p]!=0)  // if the most right leaf node has a left subtree
				{
					count++;   // count how many nodes need to be fixed
					p=left[p];
				}
			}
			if(pred == 0)
				{Done = true;  // no more permutations
				break;
				}
			
			// else,  find where to update the position of tree nodes for the next lexicographic order tree 
			else if(left[pred] == 0)  // if no left subtree for  the predessessr of most right leaf, update this pred
				update =pred;
			else					// or continue traversing towards the most right leaf node of that left successor 
			{
				p = left[pred];
				while(right[p] != 0)
				{
					p = right[p];
				}
				update = p;
			}
			
//			update tree to the next lexicographic order
			
//			1- update the position of the next node to be move back in order which is the most right leaf node   
			if(right [update] == 0)
				right[update]=next;
			else
				left[update]=next;
			right[pred]=0;
			left[next]=0;
			right[next]=0;
			next++;
			
			int t=1;
			while(right[t]!=0) 
				t=right[t];
			for(int i=1; i<=count;i++)
			{
				right[t]=next;
				left[next]=0;
				right[next]=0;
				t=next;
				next++;
			}
		tree_perm++;	
		if(n<6)
			display_array(n, left, right);
		}while(Done!=true);
		System.out.println("\nTotal number of trees is "+ tree_perm);
		
	}
	
	public static void display_array(int n, int[] left, int[] right)
	{
		int s[], output[];
		int top = -1, count = 0, rtptr = -1;
        int p = 1, q;
        s = new int[n];
        output = new int[2*n];
        
        // Arrays output
        System.out.println("\nTree# "+tree_perm);
		System.out.print("L : ");
		for(int i=1; i<=n;i++)
			System.out.print(left[i]+" ");
		System.out.print("\nR : ");
		for(int i=1; i<=n;i++)
			System.out.print(right[i]+" ");
		
		// correspondence 0's 1's representation
		
        while (p != 0 || top != -1) 
        {
         	if (p != 0) 
         	{
         		output[count++] = 1;
         		s[++top] = p;
         		if (left[p] != 0) 
         		{
         			p = left[p];
                } 
         		else 
         		{
                 	if (count == (output.length ))
                 		break;
                    output[count++] = 0;
                    p = right[p];
                }
            } 
         	else 
         	{
              	if (count == (output.length )) 
                	break;
                    
                output[count++] = 0;
                do 
                {
                 	q = s[top];
                 	top--;
                    if (top != -1) 
                    {
                    	rtptr = right[s[top]];
                    } 
                    else 
                    {
                     	rtptr = -1;
                    }
                } while (top != -1 && q == rtptr);
                
                p = rtptr;

         	}
        }
        System.out.print("\n0 1 reperesentation :  ");
            for (int j = 0; j < output.length; j++) 
                System.out.print(output[j] + " ");
            System.out.print("\n");
            int node=1;
            for (int j = 0; j < output.length - 1; j++) 
            {
                if (output[j] == 1)
                {
                    System.out.print("( "+ node++ +" ");
                    
                } 
                else if (output[j] == 0) 
                {
                    System.out.print(")");
                }
            }
            
            if (output[output.length - 1] == 1) 
            {
                System.out.println("( ");
            } 
            else if (output[output.length - 1] == 0) 
            {
                System.out.println(") ");
            }

            
        
	}
	
}
