import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PSO_alg {
    private double bestFunctionValue;
    private double[] bestCoordinates;

    private int stopIteration;

    //private
    ArrayList<Point> initializationPoint(AlgorithmParameters algParam, SearchArea searchArea,
                                                 TestFunctions testFunc) {
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < algParam.getN(); i++) {
            Point p = new Point(searchArea, testFunc);
            points.add(p);
        }
        return points;
    }

    //private
    double[] findEvaluateFunction(ArrayList<Point> points, TestFunctions testFunc) {
        double[] fitnessValues = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            fitnessValues[i] = testFunc.getValueTestFunction(points.get(i).getCoordinates());

            /*
            Ошибка в измерении тестовой функции
             */
            //TODO Random rnd = new Random();
            //TODO fitnessValues[i] = fitnessValues[i] + (rnd.nextDouble() * 0.25);

            points.get(i).setFitnessValue(fitnessValues[i]);
        }
        return fitnessValues;
    }

    private void findBestPosition(double[] fitnessValues, ArrayList<Point> points, int iter) {
        if ((Arrays.stream(fitnessValues).min().getAsDouble() < this.bestFunctionValue) || (iter == 0)) {
            this.bestFunctionValue = Arrays.stream(fitnessValues).min().getAsDouble();
            int indexOfMin = getMinIndex(fitnessValues);
            this.bestCoordinates = points.get(indexOfMin).getCoordinates().clone();
        }
    }

    public void PSO_algorithm(AlgorithmParameters algParam, ProblemParameters parameters) {
        TestFunctions testFunc = new TestFunctions(parameters.getFunctionIndex());
        SearchArea searchArea = new SearchArea(parameters.getFunctionIndex());

        Random rnd = new Random();

        ArrayList<Point> points = new ArrayList<Point>();
        points = initializationPoint(algParam, searchArea, testFunc);

        double[] fitnessValues = new double[points.size()];
        fitnessValues = findEvaluateFunction(points, testFunc);

        findBestPosition(fitnessValues, points, 0);

        for (int i = 0; i < parameters.getNumberIterations(); i++) {
            for (Point p : points) {
                findVelocity(p, algParam, parameters);
                findNewPosition(p);
                findNewBestLocalPosition(p, testFunc);
                findNewBestGlobalPosition(p);
            }
            this.stopIteration = i + 1;
        }
    }

    private void findNewBestLocalPosition(Point p, TestFunctions testFunc) {
        double newFunctionValue = testFunc.getValueTestFunction(p.getCoordinates());
        p.setFitnessValue(newFunctionValue);
        if (p.getBestFitnessValue() > newFunctionValue) {
            p.setBestFitnessValue(newFunctionValue);
            p.setBestCoordinates(p.getCoordinates().clone());
        }
    }

    private void findNewBestGlobalPosition(Point p) {
        double functionValue = p.getBestFitnessValue();

        if (this.bestFunctionValue > functionValue) {
            this.bestFunctionValue = functionValue;
            this.bestCoordinates = p.getCoordinates().clone();
        }
    }

    private void findVelocity(Point p, AlgorithmParameters algParam, ProblemParameters parameters) {
        double[] r_p = new double[parameters.getDimension()];
        double[] r_g = new double[parameters.getDimension()];

        Random rnd = new Random();

        double[] velocity = p.getVelocity().clone();
        double[] bestPositionPoint = p.getBestCoordinates().clone();
        double[] position = p.getCoordinates().clone();

        for (int j = 0; j < r_p.length; j++) {
            r_p[j] = rnd.nextDouble();
            r_g[j] = rnd.nextDouble();
            double a = algParam.getFi_p() * r_p[j] * (bestPositionPoint[j] - position[j]);
            double b = algParam.getFi_g() * r_g[j] * (this.bestCoordinates[j] - position[j]);
            velocity[j] = algParam.getOmega() * velocity[j] + a + b;
        }
        p.setVelocity(velocity);
    }

    private void findNewPosition(Point p) {
        double[] position = p.getCoordinates().clone();
        double[] velocity = p.getVelocity();
        for (int i = 0; i < position.length; i++) {
            position[i] = position[i] + velocity[i];
        }
    }

    private int getMinIndex(double[] array) {
        int indexOfMax = 0;
        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > array[indexOfMax])
            {
                indexOfMax = i;
            }
            else if (array[i] < array[indexOfMin])
            {
                indexOfMin = i;
            }
        }
        //return indexOfMax;
        return indexOfMin;
    }

    private double[] multiplication(double fi, double[] r, Point p, double[] vec) {
        double[] coordinates = p.getCoordinates().clone();
        // double[] bestPositionPoint = p.getBestCoordinates().clone();
        double[] res = new double[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            res[i] = fi * r[i] * (vec[i] - coordinates[i]);
        }
        return res;
    }

    // свойства
    public double getBestFunctionValue() {
        return this.bestFunctionValue;
    }
    public double[] getBestCoordinates() {
        return this.bestCoordinates.clone();
    }

    public static void main(String[] args) {
        int testFuncIndex = 4;
        int minFlag = 1;
        int maxIter = 1000;
        int dimension = 2;

        int N = 50;
        double fi_g = 1;
        double fi_p = 1;
        double omega = 1;

        //TODO заменить на объект
        double epsilon = 5 * Math.pow(10, -10);

        ProblemParameters parameters = new ProblemParameters(minFlag, testFuncIndex, maxIter, dimension);
        AlgorithmParameters algParam = new AlgorithmParameters(N, omega, fi_p, fi_g);

        PSO_alg pso = new PSO_alg();
        pso.PSO_algorithm(algParam, parameters);

        System.out.println("Минимум функции F" + testFuncIndex + ": " + pso.getBestFunctionValue());
        System.out.println("Координаты: " + Arrays.toString(pso.getBestCoordinates()));
        System.out.println("Всего итераций: " + pso.stopIteration);

    }
}
