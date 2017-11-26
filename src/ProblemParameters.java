

public class ProblemParameters {
    private int minFlag;
    private int functionIndex;
    private int numberIterations;
    private int dimension;

    public ProblemParameters(int minFlag, int functionIndex, int numberIterations, int dimension) {
        this.minFlag = minFlag;
        this.functionIndex = functionIndex;
        this.numberIterations = numberIterations;
        this.dimension = dimension;
    }

    public int getMinFlag() {
        return this.minFlag;
    }

    public int getFunctionIndex() {
        return this.functionIndex;
    }
    public int getNumberIterations() {
        return this.numberIterations;
    }
    public int getDimension() {
        return this.dimension;
    }
}
