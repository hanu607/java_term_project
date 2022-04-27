package DDking;

public class methods {
    public static int pow(int a,int b) {
        if (b==0) return 1;
        int ret = pow(a,b/2);
        if(b%2==0) return ret*ret;
        else return a*ret*ret;
    }
}
