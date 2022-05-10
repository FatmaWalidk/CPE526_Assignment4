package cpe526_assignment4;

import java.awt.Point;  
import java.util.Scanner; 
/**
 https://github.com/FatmaWalidk/CPE526_Assignment4 
 */
public class CPE526_Assignment4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter your key > ");
        String key = in.nextLine();
        key = key.toUpperCase().replace("J", "I");  
        String[][] playfairMatrix = new String[5][5];  
        String keyString = key+ "ABCDEFGHIKLMNOPQRSTUVWXYZ"; 
        for(int i = 0; i < 5; i++)  
            for(int j = 0; j < 5; j++)  
                playfairMatrix[i][j] = ""; 
        for(int k =0; k< keyString.length();k++){
            boolean repeat = false;  
            boolean used = false;  
            for(int i =0; i< playfairMatrix.length; i++){
                for(int j=0; j<playfairMatrix[i].length;j++){
                    if(playfairMatrix[i][j].equals("" + keyString.charAt(k))){
                       repeat = true;
                    } else if(playfairMatrix[i][j].equals("") && !repeat && !used){
                       playfairMatrix[i][j] = "" + keyString.charAt(k);  
                       used = true;
                    } 
                }
            }
        }
        System.out.println("Playfair application will use following key matrix: ");
        for(int i =0; i< playfairMatrix.length; i++){
            for(int j=0; j<playfairMatrix[i].length;j++){
                System.out.print(playfairMatrix[i][j]+" ");       
            }
            System.out.println("");
        }
        System.out.println("-------------------------");
        int c ;
        do{
            System.out.println("Operations");
            System.out.println(" 1- Encrypt \n 2- Decrypt \n 3- Exit");
            System.out.print("Please choose an operation: ");
            c = in.nextInt();
            switch(c){
                case 1: Encrypt(playfairMatrix); break;
                case 2: Decrypt(playfairMatrix); break;
                default: if(c!= 3) System.out.println("Please choose a valid choice."); break;
            }
            
            if(c!= 3) 
                System.out.println("-------------------------");
        }while (c !=3);
    }
    
    static void Encrypt(String [][] key){
        Scanner inp = new Scanner (System.in);
        System.out.println("Enter the plain text: ");
        String plain = inp.nextLine();
        plain = plain.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");  
        
        for (int i = 0; i < (plain.length()/2 + plain.length()%2); i++ ){
            if( plain.charAt(2*i) == plain.charAt(2*i+1)){
                plain = new StringBuffer(plain).insert(2*i+1,'X').toString();
            }
        }
        
        if(plain.length()%2 != 0){
            plain += "X";
        }
        
        String [] digraph = new String [plain.length()/2];
        String cipher = "";
        for(int i = 0; i< digraph.length; i++){
            digraph[i] = plain.charAt(2*i)+""+plain.charAt(2*i+1);
        }
        
        for(int i = 0; i < digraph.length; i++){   
            char a = digraph[i].charAt(0);  
            char b = digraph[i].charAt(1);  
            int r1 = (int) getPoint(a, key).getX();  
            int r2 = (int) getPoint(b, key).getX();  
            int c1 = (int) getPoint(a, key).getY();  
            int c2 = (int) getPoint(b, key).getY();   
            if(r1 == r2){ // same row - go one to the right  
                c1 = (c1 + 1) % 5;  
                c2 = (c2 + 1) % 5;  
            }  
            else if(c1 == c2){ // same column - go one down 
                r1 = (r1 + 1) % 5;  
                r2 = (r2 + 1) % 5;  
            }  
            else {  // corners - the opposite corner
                int temp = c1;  
                c1 = c2;  
                c2 = temp;  
            }  
            cipher += key[r1][c1] + "" + key[r2][c2];  
        }  
        
        
        System.out.println(cipher);
    }
    
    static void Decrypt(String [][] key){  
        Scanner inp = new Scanner (System.in);
        System.out.println("Enter the cipher text: ");
        String cipher = inp.nextLine();

        String plain = "";  
        for(int i = 0; i < cipher.length() / 2; i++) {  
            char a = cipher.charAt(2*i);  
            char b = cipher.charAt(2*i+1);  
            int r1 = (int) getPoint(a, key).getX();  
            int r2 = (int) getPoint(b, key).getX();  
            int c1 = (int) getPoint(a, key).getY();  
            int c2 = (int) getPoint(b, key).getY();  
            if(r1 == r2){  // same row - go one to the left
                c1 = (c1 + 4) % 5;  
                c2 = (c2 + 4) % 5;  
            } else if(c1 == c2){  // same column - one up
                r1 = (r1 + 4) % 5;  
                r2 = (r2 + 4) % 5;  
            } else {  // corners - the opposite corner 
                int temp = c1;  
                c1 = c2;  
                c2 = temp;  
            }  
            plain += key[r1][c1] + key[r2][c2];  
        }  
        System.out.println(plain);
    }  
    
    static Point getPoint(char c, String [][] key)  
    {  
        Point pt = new Point(0,0);  
        for(int i = 0; i < 5; i++)  
        for(int j = 0; j < 5; j++)  
            if(c == key[i][j].charAt(0))  
                pt = new Point(i,j);  
        return pt;  
    }  
        
        
}
    
    
    
