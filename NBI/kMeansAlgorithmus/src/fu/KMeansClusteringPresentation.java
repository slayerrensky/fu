package fu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Executors;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
/**
 * 
 * @author Thomas.Darimont
 *
 */
public class KMeansClusteringPresentation extends JFrame {
  static Random randomizer = new Random();
  JButton btnStart;
  JPanel panel;
 
  Runnable worker = new Runnable() {
    public void run() {
      KMeans kmeans = new KMeans();
 
      int clusterCount = 10;
 
      DataPointPresenter dataPointPresenter = new DataPointPresenter(panel.getGraphics());
      dataPointPresenter.setClusterColors(generateColorsForCluster(clusterCount));
      dataPointPresenter.clear();
      kmeans.setDimensions(2);
      kmeans.generateRandomDataPoints(6000, 0, 400);
      kmeans.generateRandomClusters(clusterCount, 0, 400);
      kmeans.process(dataPointPresenter);
    }
  };
 
 
  /**
   * @param clusterCount
   * @return
   */
  private Color[] generateColorsForCluster(int clusterCount) {
    Color[] colors = new Color[clusterCount];
    for (int i = 0; i < colors.length; i++) {
      colors[i] = new Color(randomizer.nextInt(255), randomizer.nextInt(255), randomizer.nextInt(255));
    }
    return colors;
  }
 
 
  /**
   * 
   */
  public KMeansClusteringPresentation() {
    super("KMeansClusteringPresentation");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(410, 480);
    setResizable(false);
    setLocationRelativeTo(null);
    panel = new JPanel();
 
    panel.setPreferredSize(new Dimension(410, 480));
 
    btnStart = new JButton("start");
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Executors.newSingleThreadExecutor().execute(worker);
      }
    });
 
    add(panel, BorderLayout.CENTER);
    add(btnStart, BorderLayout.SOUTH);
 
    setVisible(true);
 
  }
 
 
  /**
   * @param args
   */
  public static void main(String[] args) {
    new KMeansClusteringPresentation();
  }
}