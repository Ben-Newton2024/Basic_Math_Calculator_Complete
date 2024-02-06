//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("main");

        Calc_Interface calc_interface = new Calc_Interface();
        Basic_Math_Func basic_math_func = new Basic_Math_Func();

        System.out.println(basic_math_func.basic_int_addition(1, 2));

        System.out.println("attempting to run class ");
        calc_interface.main_calc_interface();
        System.out.println("completed main calc interface");
    }
}