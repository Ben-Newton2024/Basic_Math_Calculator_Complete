public class Basic_Math_Func {


    // Basic Int and Float for all 4 main forms (addition subtraction, multiplication, division)

    public int basic_int_addition(int a, int b) {
        return a + b;
    }

    public int basic_int_subtraction(int a, int b) {
        return a - b;
    }

    public int basic_int_multiplication(int a, int b) {
        return a * b;
    }

    public int basic_int_division(int a, int b) {
        return a / b;
    }

    public float basic_float_addition(float a, float b) {
        return a + b;
    }

    public float basic_float_subtraction(float a, float b) {
        return a - b;
    }

    public float basic_float_multiplication(float a, float b) {
        return a * b;
    }

    public float basic_float_division(float a, float b) {
        return a / b;
    }

    // Extra methods for mathematics like Square roots, squaring a number, or even cubing. made for Int and Float.

    public int basic_indices(int a, int b){
        // could be hard coded with a loop multiplying itself. but using build in Math simple.
        return (int) Math.pow(a, b);
    }
}