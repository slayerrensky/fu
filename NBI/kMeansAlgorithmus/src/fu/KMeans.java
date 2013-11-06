package fu;

import java.util.Random;

/**
 * 
 * @author Thomas.Darimont
 *
 */
public class KMeans {
  int dimensions;
  Cluster[] clusters;
  DataPoint[] dataPoints;
  static Random randomizer = new Random();
 
/**
 * 
 * @param clusterCount
 * @param start
 * @param end
 */
  public void generateRandomClusters(int clusterCount, int start, int end) {
    clusters = new Cluster[clusterCount];
    for (int i = 0; i < clusterCount; i++) {
      double[] values = new double[dimensions];
      for (int j = 0; j < values.length; j++) {
        values[j] = start + randomizer.nextInt(end - start);
      }
      Cluster cluster = new Cluster(values);
      cluster.setId(i);
      clusters[i] = cluster;
    }
  }
 
/**
 * 
 * @param countOfDataPointsToBeGenerated
 * @param start
 * @param end
 */
  public void generateRandomDataPoints(int countOfDataPointsToBeGenerated, int start, int end) {
    DataPoint[] dataPoints = new DataPoint[countOfDataPointsToBeGenerated];
    for (int i = 0; i < dataPoints.length; i++) {
      double[] values = new double[dimensions];
      for (int j = 0; j < dimensions; j++) {
        values[j] = start + randomizer.nextInt(end - start);
      }
      dataPoints[i] = new DataPoint(values);
    }
    this.dataPoints = dataPoints;
  }
 
/**
 * 
 * @param dataPoints
 */
  public void setData(DataPoint[] dataPoints) {
    this.dataPoints = dataPoints;
  }
 
/**
 * 
 * @param presenter
 */
  public void process(DataPointPresenter presenter) {
 
    int countOfSwapsOld = -1;
    int countOfCurrentSwaps = 0;
    int run = 0;
 
    while (countOfSwapsOld != countOfCurrentSwaps) {
 
      countOfSwapsOld = countOfCurrentSwaps;
      countOfCurrentSwaps = 0;
 
      System.out.println("Run: " + (run++));
 
      for (int j = 0; j < dataPoints.length; j++) {
        DataPoint currentDataPoint = dataPoints[j];
 
        Cluster nearestCluster = null;
        double currentMinimumDistance = Double.MAX_VALUE;
        for (int i = 0; i < clusters.length; i++) {
          Cluster currentCluster = clusters[i];
 
          if (null != presenter) {
            presenter.render(currentCluster);
          }
 
          double distanceToCluster = currentCluster.getCenter().calculateEuclideanDistanceTo(currentDataPoint);
 
          if (distanceToCluster < currentMinimumDistance) {
            currentMinimumDistance = distanceToCluster;
            nearestCluster = currentCluster;
          }
 
        }
 
        if (nearestCluster != currentDataPoint.getCluster()) {
          Cluster.swap(currentDataPoint.getCluster(), nearestCluster, currentDataPoint);
          countOfCurrentSwaps++;
        }
 
        if (null != presenter) {
          presenter.render(currentDataPoint);
        }
 
        // System.out.println("#################");
      }
 
      // try {
      // TimeUnit.SECONDS.sleep(1);
      // } catch (InterruptedException e) {
      // e.printStackTrace();
      // }
      System.out.println("Swaps:" + countOfCurrentSwaps);
 
    }
  }
 
 
  public int getDimensions() {
    return dimensions;
  }
 
 
  public void setDimensions(int dimensions) {
    this.dimensions = dimensions;
  }
}