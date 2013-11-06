package fu;

import java.util.ArrayList;
import java.util.List;
 
/**
 * 
 * @author Thomas.Darimont
 *
 */
public class Cluster extends DataPoint {
    List<DataPoint> dataPoints;
    String name;
    int id;
 
    public Cluster(double[] values) {
        super(values);
        dataPoints = new ArrayList<DataPoint>();
    }
 
    public void add(DataPoint dataPoint) {
        getDataPoints().add(dataPoint);
    }
 
    public static void swap(Cluster from, Cluster to, DataPoint dataPoint) {
        if (null != from) {
            from.remove(dataPoint);
        }
        dataPoint.setCluster(to);
        to.add(dataPoint);
    }
 
    void remove(DataPoint dataPoint) {
        getDataPoints().remove(dataPoint);
    }
 
    /**
     * @return the dataPoints
     */
    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }
 
    /**
     * @param dataPoints
     *            the dataPoints to set
     */
    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
 
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
 
    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
 
    public double calculateDistanceTo(Cluster cluster) {
        return getCenter().calculateEuclideanDistanceTo(cluster.getCenter());
    }
 
    public DataPoint getCenter() {
        double[] values = new double[dimensions];
 
        for (int i = 0, size = getDataPoints().size(); i < size; i++) {
            DataPoint dataPoint = getDataPoints().get(i);
            for (int j = 0; j < dimensions; j++) {
                values[j] += dataPoint.values[j];
            }
        }
 
        for (int j = 0; j < dimensions; j++) {
            values[j] += this.values[j];
        }
 
        for (int j = 0, size = getDataPoints().size() + 1; j < dimensions; j++) {
            values[j] *= 1.0 / size;
        }
 
        DataPoint centerDataPoint = new DataPoint(this, values);
 
        return centerDataPoint;
    }
    
    
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    @Override
    public String toString() {
        return "Cluster: " + getCenter();
    }
}