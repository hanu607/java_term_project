package DDking;

import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class MyUtility {
    
    public static int pow(int a, int b) {
        if (b == 0)
            return 1;
        int ret = pow(a, b / 2);
        if (b % 2 == 0)
            return ret * ret;
        else
            return a * ret * ret;
    }

    public static String randomName(String path) throws Exception {
        File file = new File(path);
        ArrayList<String> sList = new ArrayList<String>();
        Random random = new Random();
        Scanner input = new Scanner(file);
        int n = 0;
        while (input.hasNext()) {
            sList.add(input.nextLine());
            n++;
        }
        int r = random.nextInt(0, n-1);
        String name = r == 0 ? "" : sList.get(r);
        input.close();
        return name;
    }
}
