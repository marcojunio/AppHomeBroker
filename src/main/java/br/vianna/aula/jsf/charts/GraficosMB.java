/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.stereotype.Component;

/**
 *
 * @author Programming
 */
@Component
@RequestScoped
public class GraficosMB implements Serializable {

    private DonutChartModel donutModel;
    private LineChartModel cartesianLinerModel;

    @PostConstruct
    public void init() {
        createDonutModel();
        createCartesianLinerModel();
    }

    public void createCartesianLinerModel() {
        cartesianLinerModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(20);
        values.add(50);
        values.add(100);
        values.add(75);
        values.add(25);
        values.add(0);
        dataSet.setData(values);
        dataSet.setLabel("Depósito");
        dataSet.setYaxisID("left-y-axis");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Number> values2 = new ArrayList<>();
        values2.add(0.1);
        values2.add(0.5);
        values2.add(1.0);
        values2.add(2.0);
        values2.add(1.5);
        values2.add(0);
        dataSet2.setData(values2);
        dataSet2.setLabel("Saque");
        dataSet2.setYaxisID("right-y-axis");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("Janeiro");
        labels.add("Fevereiro");
        labels.add("Março");
        labels.add("Abril");
        labels.add("Maio");
        labels.add("Junho");
        data.setLabels(labels);
        cartesianLinerModel.setData(data);

        //Options
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");

        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Movimentações");
        options.setTitle(title);

        cartesianLinerModel.setOptions(options);
    }

    public void criarGraficoCartesiano(List<Number> depositos, List<Number> saques, List<String> meses) {
        cartesianLinerModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> values = new ArrayList<>();
        dataSet.setData(depositos);
        dataSet.setLabel("Depósito");
        dataSet.setYaxisID("left-y-axis");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        dataSet2.setData(saques);
        dataSet2.setLabel("Saque");
        dataSet2.setYaxisID("right-y-axis");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        data.setLabels(meses);
        cartesianLinerModel.setData(data);

        //Options
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");

        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Movimentações");
        options.setTitle(title);

        cartesianLinerModel.setOptions(options);
    }

    public void criarGraficoRosquinha(List<Number> qntAcoes, List<String> prefixos) {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        dataSet.setData(qntAcoes);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)"); // VERMELHO
        bgColors.add("rgb(54, 162, 235)"); // AZUL
        bgColors.add("rgb(255, 205, 86)"); // AMARELO
        bgColors.add("rgb(0,255,0)"); // VERDE
        bgColors.add("rgb(0,128,128)"); // TEAL
        bgColors.add("rgb(255,165,0)"); // ROSA
        bgColors.add("rgb(240,230,140)"); // KHANKI
        bgColors.add("rgb(210,105,30)"); // CHOCOLATE
        bgColors.add("rgb(0,100,0)"); // VERDE ESCURO
        bgColors.add("rgb(0,0,139)"); // AZUL ESCURO

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(prefixos);

        donutModel.setData(data);
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);
        values.add(150);
        values.add(120);
        values.add(20);
        values.add(30);
        values.add(76);
        values.add(200);
        values.add(55);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)"); // VERMELHO
        bgColors.add("rgb(54, 162, 235)"); // AZUL
        bgColors.add("rgb(255, 205, 86)"); // AMARELO
        bgColors.add("rgb(0,255,0)"); // VERDE
        bgColors.add("rgb(0,128,128)"); // TEAL
        bgColors.add("rgb(255,165,0)"); // ROSA
        bgColors.add("rgb(240,230,140)"); // KHANKI
        bgColors.add("rgb(210,105,30)"); // CHOCOLATE
        bgColors.add("rgb(0,100,0)"); // VERDE ESCURO
        bgColors.add("rgb(0,0,139)"); // AZUL ESCURO

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("PETR4");
        labels.add("VALE3");
        labels.add("AZUL4");
        labels.add("TEST3");
        labels.add("GABR2");
        labels.add("MARC1");
        labels.add("DAVES");
        labels.add("OOOT2");
        labels.add("PP224");
        labels.add("MALU2");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }

}
