

public class Point {
    double[] coordinates;
    double[] velocity;
    double fitnessValue;

    double bestFitnessValue;
    double[] bestCoordinates;

    public double[] getCoordinates() {
        return this.coordinates;
    }
    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates.clone();
    }

    public double[] getVelocity() {
        return this.velocity;
    }
    public void setVelocity(double[] velocity) {
        this.velocity = velocity.clone();
    }

    public double getFitnessValue() {
        return this.fitnessValue;
    }
    public void setFitnessValue(double value) {
        this.fitnessValue = value;
    }

    public double[] getBestCoordinates() {
        return this.bestCoordinates;
    }
    public void setBestCoordinates(double[] value) {
        this.bestCoordinates = value.clone();
    }

    public double getBestFitnessValue() {
        return this.bestFitnessValue;
    }
    public void setBestFitnessValue(double value) {
        this.bestFitnessValue = value;
    }

    public Point(SearchArea searchArea, TestFunctions testFunc) {
        double[] top = searchArea.getRestrictionTop().clone();
        double[] bottom = searchArea.getRestrictionBottom().clone();
        this.coordinates = new double[top.length];
        this.velocity = new double[top.length];
        this.bestCoordinates = new double[top.length];
        // случайная генерация точки в области поиска
        for (int i = 0; i < top.length; i++) {
            this.coordinates[i] = Math.random() * (top[i] - bottom[i]) + bottom[i];
            this.bestCoordinates[i] = this.coordinates[i];
        }
        // начальное значение скорости
        for (int i = 0; i < this.velocity.length; i++) {
            double low = -(top[i] - bottom[i]);
            double high = top[i] - bottom[i];
            this.velocity[i] = Math.random() * (high - low) + low;
        }

        this.fitnessValue = testFunc.getValueTestFunction(this.coordinates);
        this.bestFitnessValue = this.fitnessValue;
        this.bestCoordinates = this.coordinates.clone();

//        findStartFitnessValue(testFunc, this);
    }

    private void findStartFitnessValue(TestFunctions testFunc, Point p) {
        this.fitnessValue = testFunc.getValueTestFunction(p.getCoordinates());
        this.bestFitnessValue = this.fitnessValue;
        this.bestCoordinates = p.getCoordinates().clone();
    }
}
