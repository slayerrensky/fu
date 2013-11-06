package fu;

/**
 * 
 * @author Thomas.Darimont
 *
 */
public class DataPoint {
    int dimensions;
    double[] values;
    Cluster cluster;
 
    /**
     * @param x
     * @param y
     */
    public DataPoint(double[] values) {
        this.values = values;
        this.dimensions = values.length;
    }
 
    /**
     * @param x
     * @param y
     */
    public DataPoint(Cluster cluster, double[] values) {
        this(values);
        this.cluster = cluster;
    }
 
    /**
     * @return the dimensions
     */
    public int getDimensions() {
        return dimensions;
    }
 
    /**
     * @return the values
     */
    public double[] getValues() {
        return values;
    }
 
    /**
     * @param values
     *            the values to set
     */
    public void setValues(double[] values) {
        this.values = values;
    }
 
    public double calculateEuclideanDistanceTo(DataPoint dataPoint) {
        double distance = 0.0D;
        for (int i = 0; i < dimensions; i++) {
            distance += Math.pow(values[i] - dataPoint.values[i], 2);
        }
        return Math.sqrt(distance);
    }
 
    public Cluster getCluster() {
        return cluster;
    }
 
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
 
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DataPoint: ");
        for (int i = 0; i < dimensions; i++) {
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(values[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}