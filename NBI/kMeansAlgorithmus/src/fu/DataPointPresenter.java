package fu;

import java.awt.Color;
import java.awt.Graphics;
 
/**
 * 
 * @author Thomas.Darimont
 *
 */
public class DataPointPresenter {
 
    Color[] clusterColors;
 
    Graphics graphics;
 
    public DataPointPresenter(Graphics graphics) {
        this.graphics = graphics;
    }
 
    public void render(DataPoint dataPoint) {
        Color oldColor = graphics.getColor();
        if (null != dataPoint.getCluster()) {
            graphics.setColor(clusterColors[dataPoint.getCluster().getId()]);
        }
        graphics.fillOval((int) (dataPoint.values[0] - 2.5),
                (int) (dataPoint.values[1] - 2.5), 5, 5);
        graphics.setColor(oldColor);
    }
 
    public void render(Cluster cluster) {
        Color oldColor = graphics.getColor();
        graphics.setColor(clusterColors[cluster.getId()]);
        DataPoint dataPoint = cluster.getCenter();
        graphics.fillOval((int) (dataPoint.values[0] - 5),
                (int) (dataPoint.values[1] - 5), 10, 10);
        graphics.setColor(oldColor);
    }
 
    public Color[] getClusterColors() {
        return clusterColors;
    }
 
    public void setClusterColors(Color[] clusterColors) {
        this.clusterColors = clusterColors;
    }
    
    public void clear(){
        graphics.clearRect(0,0,640, 480);
    }
 
}