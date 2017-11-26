import java.util.Arrays;

public class TestFunctions {
    private int index;

    public TestFunctions(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int value) {
        this.index = value;
    }

    public double getValueTestFunction(double[] coordinates) {
        if (this.index == 1) {
            return function_1(coordinates);
        }
        if (this.index == 4) {
            return function_4(coordinates);
        }
        if (this.index == 7) {
            return function_7(coordinates);
        }
        if (this.index == 16) {
            return function_16(coordinates);
        }
        throw new IllegalArgumentException("Передан некорректный индекс тестовой функции");
    }

    private static double function_1(double[] coordinates) {
        //double[] coordinates = p.getCoordinates().clone();

        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = Math.pow(coordinates[i], 2);
        }
        return Arrays.stream(coordinates).sum();
    }

    private static double function_4(double[] coordinates) {
        double a, b, c, d, x, y;
        //double[] coordinates = p.getCoordinates().clone();
        x = Math.pow((coordinates[0] - 4), 2);
        y = Math.pow((coordinates[1] - 4), 2);
        a = Math.exp((-(Math.pow((x + y), 2))) / 1000 + 0.05);
        d = Math.exp(-(Math.pow((x + y), 2)));
        x = Math.pow((coordinates[0] + 4), 2);
        y = Math.pow((coordinates[1] + 4), 2);
        b = Math.exp((-(Math.pow((x + y), 2))) / 1000);
        c = Math.exp(-(Math.pow((x + y), 2)));
        return -(a+ b + 0.15 * c + 0.15 * d);
    }

    private static double function_7(double[] coordinates) {
        double a, b;
        //double[] coordinates = p.getCoordinates().clone();
        a = Math.pow((1 - coordinates[0]), 2);
        b = Math.pow((coordinates[1] - Math.pow(coordinates[0], 2)), 2);
        return a + 100 * b;
    }

    private static double function_16(double[] coordinates) {
        //double[] coordinates = p.getCoordinates().clone();

        double[] res = {func2_1(coordinates), func2_2(coordinates), func2_3(coordinates), func2_4(coordinates), func2_5(coordinates),
                func2_6(coordinates), func2_7(coordinates), func2_8(coordinates), func2_9(coordinates), func2_10(coordinates)};

        return Arrays.stream(res).min().getAsDouble();
    }

    //---------------------------Вспомогательные функции--------------------------------
    private static double func2_1(double[] coord) {
        double a;
        a = 7 * Math.pow(Math.abs(coord[0] - 2), 0.7) + 7 * Math.pow(Math.abs(coord[1] + 3), 0.9);
        return a;
    }

    private static double func2_2(double[] coord) {
        double a;
        a = 4 * Math.pow(Math.abs(coord[0] + 4), 0.6) + 5 * Math.pow(Math.abs(coord[1] - 3), 1.2) + 2;
        return a;
    }

    private static double func2_3(double[] coord) {
        double a;
        a = 6 * Math.pow(Math.abs(coord[0] - 4), 1.2) + 6 * Math.pow(Math.abs(coord[1] - 5), 0.3) + 2.5;
        return a;
    }

    private static double func2_4(double[] coord) {
        double a;
        a = 5 * Math.pow(Math.abs(coord[0] + 2), 0.6) + 7 * Math.pow(Math.abs(coord[1] - 1), 0.3) + 4;
        return a;
    }

    private static double func2_5(double[] coord) {
        double a;
        a = 3.5 * Math.pow(Math.abs(coord[0] + 3), 1.5) + 5 * Math.pow(Math.abs(coord[1] - 4), 2) + 5;
        return a;
    }

    private static double func2_6(double[] coord) {
        double a;
        a = 7 * Math.pow(Math.abs(coord[0] + 5), 0.5) + 3 * Math.pow(Math.abs(coord[1] + 3), 0.9) + 6;
        return a;
    }

    private static double func2_7(double[] coord) {
        double a;
        a = 6 * Math.pow(Math.abs(coord[0] - 4), 2) + 5 * Math.pow(Math.abs(coord[1] + 2), 0.6) + 6.5;
        return a;
    }

    private static double func2_8(double[] coord) {
        double a;
        a = 3 * Math.pow(Math.abs(coord[0] - 2), 1.7) + 6.3 * Math.pow(Math.abs(coord[1] - 2), 1.1) + 7;
        return a;
    }

    private static double func2_9(double[] coord) {
        double a;
        a = 4.5 * Math.pow(Math.abs(coord[0] + 3), 1.1) + 5 * Math.pow(Math.abs(coord[1] - 5), 0.8) + 8;
        return a;
    }

    private static double func2_10(double[] coord) {
        double a;
        a = 2 * Math.pow(Math.abs(coord[0] + 1), 0.6) + 3 * Math.pow(Math.abs(coord[1] + 1), 1.1) + 9;
        return a;
    }
}
