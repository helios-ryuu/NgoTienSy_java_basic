package Bai_1;

import java.util.NoSuchElementException;

public class Fraction implements Comparable<Fraction> {
    private int numerator = 0;
    private int denominator = 1;
    private double value;

    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = new Fraction(1, 4);

        System.out.println("Phân só f1: " + f1);
        System.out.println("Phân só f2: " + f2);
        System.out.println("Phân só f3: " + f3);

        Fraction sum = Fraction.sum(f1, f2, f3);
        System.out.println("Tổng (f1 + f2 + f3): " + sum);

        Fraction subtract = Fraction.subtract(f1, f2, f3);
        System.out.println("Hiệu (f1 - f2 - f3): " + subtract);

        Fraction multiply = Fraction.multiply(f1, f2, f3);
        System.out.println("Nhân (f1 * f2 * f3): " + multiply);

        Fraction divide = Fraction.divide(f1, f2, f3);
        System.out.println("Chia (f1 / f2 / f3): " + divide);

        Fraction f4 = new Fraction(18, 90);
        System.out.println("Phân só f4: " + f4);
        Fraction simplified = f4.simplify();
        System.out.println("Phân só f4 được tối giản: " + simplified);

        int compareResult = f1.compareTo(f2);
        if (compareResult > 0) System.out.println("Phân số f1 lớn hơn phân só f2");
        else if (compareResult < 0) System.out.println("Phân số f1 nhỏ hơn phân só f2");
        else System.out.println("Phân số f1 bằng phân só f2");
    }

    // Constructor
    public Fraction() {
        this.update();
    }

    public Fraction(int nume) {
        this.setNumerator(nume);
        this.setDenominator(1);
        this.update();
    }

    public Fraction(int nume, int deno) {
        this.setNumerator(nume);
        this.setDenominator(deno);
        this.update();
    }

    // Getter
    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public double getValue() {
        return this.value;
    }

    // Setter
    public void setNumerator(int t) {
        this.numerator = t;
    }

    public void setDenominator(int m) {
        if (m == 0) {
            throw new IllegalArgumentException("Cannot set the denominator to 0.");
        }
        else this.denominator = m;
    }

    // Other methods
    public Fraction simplify() {
        int gcd_result = Fraction.gcd(this.numerator, this.denominator);
        return new Fraction(this.numerator / gcd_result, this.denominator / gcd_result);
    }

    public static Fraction sum(Fraction... fs) {
        if (fs.length < 2) throw new NoSuchElementException("At least 2 fractions required for sum operation");

        int lcm = fs[0].denominator;
        for (int i = 1; i < fs.length; i++) {
            lcm = lcm(lcm, fs[i].denominator);
        }

        int numerator = 0;
        for (Fraction f : fs) {
            numerator += f.numerator * (lcm / f.denominator);
        }

        return new Fraction(numerator, lcm).simplify();
    }

    public static Fraction subtract(Fraction... fs) {
        if (fs.length < 2) throw new NoSuchElementException("At least 2 fractions required for subtract operation");

        int lcm = fs[0].denominator;
        for (int i = 1; i < fs.length; i++) {
            lcm = Fraction.lcm(lcm, fs[i].denominator);
        }

        int numerator = fs[0].numerator * (lcm / fs[0].denominator);
        for (int i = 1; i < fs.length; i++) {
            numerator -= fs[i].numerator * (lcm / fs[i].denominator);
        }

        return new Fraction(numerator, lcm).simplify();
    }

    public static Fraction multiply(Fraction... fs) {
        if (fs.length < 2) throw new NoSuchElementException("At least 2 fractions required for multiply operation");

        int numerator = 1;
        int denominator = 1;
        for (Fraction f : fs) {
            numerator *= f.numerator;
            denominator *= f.denominator;
        }

        return new Fraction(numerator, denominator).simplify();
    }

    public static Fraction divide(Fraction... fs) {
        if (fs.length < 2) throw new NoSuchElementException("At least 2 fractions required for multiply operation");

        int numerator = 1;
        int denominator = 1;
        for (Fraction f : fs) {
            numerator *= f.denominator;
            denominator *= f.numerator;
        }

        return new Fraction(numerator, denominator).simplify();
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getValue() < 0) result += "-";
        result += Math.abs(this.numerator) + "/" + Math.abs(this.denominator);
        return result;
    }

    @Override
    public int compareTo(Fraction other) {
        double thisValue = (double) this.numerator / this.denominator;
        double otherValue = (double) other.numerator / other.denominator;

        return Double.compare(thisValue, otherValue);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static int lcm(int a, int b) {
        return a * (b / Fraction.gcd(a, b));
    }

    private void update() {
        this.value = (double)this.numerator / this.denominator;
    }
}