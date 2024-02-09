//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main_Runner_Class {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Basic Calculator - basic addition and stuff, no brackets, squared or square roots etc");
        System.out.println("This program will run once, relaunch to replay again. no loop included yet.");

        Calc_Interface calc_interface = new Calc_Interface();

        System.out.println("attempting to run class");
        calc_interface.main_calc_interface();
        System.out.println("completed main calc interface");

    }
}