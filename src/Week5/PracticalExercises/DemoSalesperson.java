package Week5.PracticalExercises;

public class DemoSalesperson {
    public static void main(String[] args) {
        Salesperson[] personArrayList  = new Salesperson[10];
        int count = 1;
        int culmEmpID = 111;
        Double culmEmpSales = 25000.0;

        for (Salesperson i : personArrayList){
            i = new Salesperson(culmEmpID, culmEmpSales);
            System.out.println("For employee:" + count + ", ID number is: " + i.getIdNumber() + " and the annual sales is: " + i.getAnnualSales());
            count += 1;
            culmEmpID += 1;
            culmEmpSales += 5000;
        }
    }
}
