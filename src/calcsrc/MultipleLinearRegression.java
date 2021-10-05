package calcsrc;


import jdk.swing.interop.SwingInterOpUtils;

public class MultipleLinearRegression {
    private double[][] dataSet;
    private double[][] yVector;
    private double[][] betaVector;
    private double[][] xMatrix;
    private double[][] xMatrixT;


    public MultipleLinearRegression(double[][] dataSet){
        this.dataSet = dataSet;
        yVector=getYVector(dataSet);
        xMatrix=getXMatrix(dataSet);
        xMatrixT=getTMatrix(xMatrix);
    }

    public double[][] calcMLR(){
        double[][]xt_x=matrixMultiplication(xMatrixT,xMatrix);
        double[][] invx_tx=getInvMatrix(xt_x);
        double[][]xt_y=matrixMultiplication(xMatrixT,yVector);
        betaVector=matrixMultiplication(invx_tx,xt_y);

        return betaVector;
    }

    private double [][] getXMatrix(double[][] dataSet){
        double[][] dataSetX = new double[dataSet.length][dataSet[0].length];
        for(int i=0;i<dataSet.length;i++){
            dataSetX[i][0]=1;
            for (int j=0; j < dataSet[0].length-1; j++) {
                dataSetX[i][j+1]=dataSet[i][j];
            }
        }
        return dataSetX;
    }

    private double [][] getYVector(double[][] dataSet){
        double [][] vResult = new double[dataSet.length][1];
        for (int i = 0; i < dataSet.length; i++) {
            vResult[i][0]= dataSet[i][dataSet[0].length-1];
        }
        return vResult;
    }


    private double[][] getTMatrix(double[][] dataSet){
        double[][] mResult = new double[dataSet[0].length][dataSet.length];
        for (int i = 0; i <dataSet.length ; i++) {
            for (int j = 0; j <dataSet[0].length ; j++) {
                mResult[j][i] = dataSet[i][j];
            }
        }
        return mResult;
    }

    private static double[][] matrixMultiplication(double[][] matrix1, double[][] matriz2){
        double[][] mResult = new double[matrix1.length][matriz2[1].length];

        for (int i = 0; i <matrix1.length ; i++) {

            for (int j = 0; j <matriz2[0].length ; j++) {
                double aux=0;
                for (int k = 0; k <matrix1[0].length ; k++) {
                    aux += matrix1[i][k] * matriz2[k][j];
                }
                mResult[i][j] = aux;
            }
        }
        return mResult;
    }

    private double[][] getIdentityMatrix(double[][] dataSet) {
        double[][] mResult = new double[dataSet.length][dataSet[0].length];
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < dataSet.length; j++) {
                if (i == j) mResult[i][j] = 1;
                else mResult[i][j] = 0;
            }
        }
        return mResult;
    }

    private double[][] getInvMatrix(double[][] dataSet){
        double[][] mResult = getIdentityMatrix(dataSet);
        double point;
        double save;
        for (int i = 0; i <dataSet.length ; i++) {
            point = dataSet[i][i];
            for (int j = 0; j < dataSet.length; j++) { 
                dataSet[i][j] = dataSet[i][j] / point;
                mResult[i][j] = mResult[i][j] / point;
            }
            for (int j = 0; j <dataSet.length ; j++) {
                if (i!=j){
                    save = dataSet[j][i];
                    for (int k = 0; k < dataSet.length; k++) {
                        dataSet[j][k] = dataSet[j][k] -save*dataSet[i][k];
                        mResult[j][k] = mResult[j][k] -save*mResult[i][k];
                    }
                }
            }
        }
        return mResult;
    }

    public double calcY(double x1,double x2){
        return betaVector[0][0]+betaVector[1][0]*x1+betaVector[2][0]*x2;
    }

    public void print(double[][] dataSet){
        for (int x=0; x < dataSet.length; x++) {
            System.out.print("|");
            for (int y=0; y < dataSet[x].length; y++) {
                System.out.print (dataSet[x][y]);
                if (y!=dataSet[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    public void printMLR(){
        print(calcMLR());
    }

    public void printcalcY(double x1,double x2){
        System.out.println("Y="+betaVector[0][0]+"+"+betaVector[1][0]+"*"+x1+"+"+betaVector[2][0]+"*"+x2);
        System.out.println("Y="+calcY(x1,x2));
    }

}

