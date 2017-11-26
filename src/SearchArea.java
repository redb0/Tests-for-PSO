import java.util.Arrays;

public class SearchArea {
    private double[] top;
    private double[] bottom;
    private int dimension;
    private double[] errors;
    private double[] extremum;

    public SearchArea(double[] restriction_top, double[] restriction_bottom, int dimension) {
        this.top = restriction_top;
        this.bottom = restriction_bottom;
        this.dimension = dimension;
        this.modifyingSizeSearchArea();
    }

    public SearchArea(int functionIndex) {
        if (functionIndex == 1) {
            this.testFunction_1();
        }
        if (functionIndex == 4) {
            this.testFunction_4();
        }
        if (functionIndex == 7) {
            this.testFunction_7();
        }
        if (functionIndex == 16) {
            this.testFunction_16();
        }
        this.modifyingSizeSearchArea();
    }

    public boolean checkSearchScope(Point p) {
        double[] coordinates = p.getCoordinates().clone();
        for (int n = 0; n < this.top.length; n++) {
            if (!(coordinates[n] >= this.bottom[n]) || !(coordinates[n] <= this.top[n])) {
                return false;
            }
        }
        return true;
    }

    public double sizeDim(int dim) {
        return this.top[dim] - this.bottom[dim];
    }

    public void modifyingSizeSearchArea() {
        if (this.dimension != this.top.length) {
            double t = this.top[0];
            double b = this.bottom[0];
            this.top = new double[this.dimension];
            this.bottom = new double[this.dimension];
            Arrays.fill(this.top, t);
            Arrays.fill(this.bottom, b);
        }
    }

    public double[] getRestrictionTop() {
        return this.top;
    }

    public double[] getRestrictionBottom() {
        return this.bottom;
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getNumberErrors() {
        return errors.length;
    }

    public double getError(int n) {
        return errors[n];
    }

    public double[] getExtremum() {
        return extremum;
    }

    // ограничения для тестовых функций
    private void testFunction_1() {
        double[] top = {500};
        double[] down = {-500};
        int dimension = 2;
        double[] errors = {0.01, 0.05, 0.1, 0.25, 0.5};
        double[] extremum = new double[dimension];
        Arrays.fill(extremum, 0);

        this.top = top;
        this.bottom = down;
        this.dimension = dimension;
        this.errors = errors;
        this.extremum = extremum;
    }

    private void testFunction_4() {
        double[] top = {10};
        double[] down = {-10};
        int dimension = 2;
        double[] errors = {0.01, 0.05, 0.1, 0.25, 0.5};
        double[] extremum = {-4, -4};

        this.top = top;
        this.bottom = down;
        this.dimension = dimension;
        this.errors = errors;
        this.extremum = extremum;
    }

    private void testFunction_7() {
        double[] top = {5.12};
        double[] down = {-5.12};
        int dimension = 2;
        double[] errors = {0.01, 0.05, 0.1, 0.25, 0.5};
        double[] extremum = {1, 1};

        this.top = top;
        this.bottom = down;
        this.dimension = dimension;
        this.errors = errors;
        this.extremum = extremum;
    }

    private void testFunction_16() {
        double[] top = {6};
        double[] down = {-6};
        int dimension = 2;
        double[] errors = {0.01, 0.05, 0.1, 0.25, 0.5};
        double[] extremum = {2, -3};

        this.top = top;
        this.bottom = down;
        this.dimension = dimension;
        this.errors = errors;
        this.extremum = extremum;
    }
}
