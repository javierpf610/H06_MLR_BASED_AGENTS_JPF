package calcsrc;

public class Gradient {
    private double [][] dataset;
    private double beta0=0,beta1=0;


    public Gradient(double[][] dataset) {
        this.dataset = dataset;
        calcGradient();
    }

    public double getBeta0(double[][] dataset,double b0,double b1){
        double result = 0;
        double save=0;
        for(int i=0;i<dataset.length;i++){
            save=dataset[i][0]-(b0+b1*dataset[i][1]);
            result+=save;
        }
            result=-2*result/dataset.length;
        return result;
    }

    public double getBeta1(double[][] dataset,double b0,double b1){
        double result = 0;
        double save=0;
        for(int i=0;i<dataset.length;i++){
            save=dataset[i][0]-(b0+b1*dataset[i][1]);
            result+=dataset[i][1]*save;
        }
        result=-2*result/dataset.length;
        return result;
    }

    public double getError(double[][] dataset,double b0,double b1){
        double result = 0;
        double save=0;
        for(int i=0;i<dataset.length;i++){
            save=dataset[i][0]-(b0+b1*dataset[i][1]);
            result+=save;
        }
        result=result*result/dataset.length;
        return result;
    }

    public void calcGradient(){
        double a=0.0005,error=0;
        for (int i = 0; i <150000 ; i++) {
            double tempb0=getBeta0(dataset,beta0,beta1);
            double tempb1=getBeta1(dataset,beta0,beta1);
            error=getError(dataset,beta0,beta1);
            beta0 = (beta0 - a*tempb0);
            beta1 = (beta1 - a*tempb1);
        }

        System.out.println("Error: "+error);

    }
    public double yhat(double x){return beta0+beta1*x;}

    public void printYHat(double x){
        System.out.println("Gradient: ");
        System.out.println("Y"+(char) 94+"="+beta0+"+"+beta1+"("+x+")");
        System.out.println("Y"+(char) 94+"="+yhat(x));
    }


}
