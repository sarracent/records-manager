/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author haroldg
 */
public class Estadisticas {

    private BarChartModel model;

    public Estadisticas() {
        model = new BarChartModel();
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
        
        model.addSeries(boys);
       
        model.setTitle("Bar Chart");
        model.setLegendPosition("ne");
        org.primefaces.model.chart.Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
        org.primefaces.model.chart.Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);

    }

    public BarChartModel getModel() {
        return model;
    }

}
