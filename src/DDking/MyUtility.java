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
        while (input.hasNext())
            sList.add(input.nextLine());
        if (sList.isEmpty()) {
            System.out.println("text file is empty!");
            System.exit(0);
        }
        int r = random.nextInt(0, sList.size() - 1);
        String name = sList.get(r);
        input.close();
        return name;
    }
}
