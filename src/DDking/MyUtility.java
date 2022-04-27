package DDking;
import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class MyUtility {
    public static int pow(int a,int b) {
        if (b==0) return 1;
        int ret = pow(a,b/2);
        if(b%2==0) return ret*ret;
        else return a*ret*ret;
    }
    public static String randomEnemyName() throws Exception{
        ArrayList<String> sList = new ArrayList<String>(); 
        Random random = new Random();
        File file = new File("Enemy (name)");
        Scanner input = new Scanner(file);
        int n = 0;
        while (input.hasNext()) {
            sList.add(input.nextLine());
            n++;
        }
        int r = random.nextInt(0,n);
        String name = sList.get(r);
        return name;
    }
}
