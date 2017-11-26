

public class AlgorithmParameters {
    private int N;
    private double omega;
    private double fi_p;
    private double fi_g;

    public AlgorithmParameters(int N, double omega, double fi_p, double fi_g) {
        this.N = N;
        this.omega = omega;
        this.fi_g = fi_g;
        this.fi_p = fi_p;
    }


    public int getN() {
        return this.N;
    }
    public double getOmega() {
        return this.omega;
    }
    public double getFi_p() {
        return this.fi_p;
    }
    public double getFi_g() {
        return this.fi_g;
    }
}
