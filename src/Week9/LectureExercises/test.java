package Week9.LectureExercises;

public class test {
    public static void main(String[] args) {
        int[] testList = {11,22,33,44,55};
        testMethod1(testList);
        System.out.println(testList[0]);
        for (int each:testList){
        System.out.println(each);}
    }

    public static void testMethod1(int[] testList1){
        testList1[0] = 862;
        testList1[3] = 862;
    }
}
