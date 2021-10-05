package agents;
import calcsrc.MultipleLinearRegression;
import calcsrc.Gradient;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MyAgent extends jade.core.Agent {
    private AgentGUI myGUI;
    protected void setup() {
        myGUI=new AgentGUI(this);
        myGUI.showGui();
    }
    public void executeAgentMLR( double x1, double x2) {
        addBehaviour(new OneShotBehaviour() {
            public void action() {
                double [][] dataset=new double[][]{
                        {41.9,29.1,251.3},
                        {43.4,29.3,251.3},
                        {43.9,29.5,248.3},
                        {44.50,29.7,267.5},
                        {47.3,29.9,273},
                        {47.50,30.3,276.5},
                        {47.9,30.5,270.3},
                        {50.2,30.7,274.9},
                        {52.8,30.8,285},
                        {53.2,30.9,290},
                        {56.7,31.5,297},
                        {57,31.7,302.5},
                        {63.5,31.9,304.5},
                        {65.3,32,309.5},
                        {71.1,32.1,321.7},
                        {77,32.5,330.7},
                        {77.8,32.9,349}
                };
                double[][] dataset2 = new  double[][]{{651,23},{762,26},{856,30},{1063,34},{1190,43},{1298,48},{1421,52},{1440,57},{1518,58}};

                MultipleLinearRegression mlr = new MultipleLinearRegression(dataset);
                mlr.printcalcY(x1,x2);


            }
        } );

    }
    public void executeAgentGradient( double x1) {
        addBehaviour(new OneShotBehaviour() {
            public void action() {
                double[][] dataset = new  double[][]{{651,23},{762,26},{856,30},{1063,34},{1190,43},{1298,48},{1421,52},{1440,57},{1518,58}};

                Gradient g= new Gradient(dataset);
                g.printYHat(x1);

            }
        } );

    }

}
