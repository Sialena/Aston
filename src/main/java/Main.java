import resources.CustomArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CustomArrayList<Integer> customList = new CustomArrayList<>(0);
        customList.add(1);
        int size = customList.size();
        System.out.println(size);
    }
}
