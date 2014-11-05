package model;

import java.math.BigInteger;

public class Matrix {

    private int nrows;
    private int ncols;
    private BigInteger[][] data;

    public Matrix(BigInteger[][] dat) {
        this.data = dat;
        this.nrows = dat.length;
        this.ncols = dat[0].length;
    }

    public Matrix(int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new BigInteger[nrow][ncol];
    }

    public int getNrows() {
        return nrows;
    }

    public void setNrows(int nrows) {
        this.nrows = nrows;
    }

    public int getNcols() {
        return ncols;
    }

    public void setNcols(int ncols) {
        this.ncols = ncols;
    }

    public BigInteger[][] getValues() {
        return data;
    }

    public void setValues(BigInteger[][] values) {
        this.data = values;
    }

    public void setValueAt(int row, int col, BigInteger value) {
        data[row][col] = value;
    }

    public BigInteger getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isSquare() {
        return nrows == ncols;
    }

    public int size() {
        if (isSquare())
            return nrows;
        return -1;
    }

    public Matrix multiplyByConstant(BigInteger constant) {
        Matrix mat = new Matrix(nrows, ncols);
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                mat.setValueAt(i, j, data[i][j].multiply(constant));
            }
        }
        return mat;
    }
    public Matrix insertColumnWithValue1() {
        Matrix X_ = new Matrix(this.getNrows(), this.getNcols()+1);
        for (int i=0;i<X_.getNrows();i++) {
            for (int j=0;j<X_.getNcols();j++) {
                if (j==0)
                    X_.setValueAt(i, j, new BigInteger("1"));
                else
                    X_.setValueAt(i, j, this.getValueAt(i, j-1));
            }
        }
        return X_;
    }

    public void afficherMatrice(){
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                System.out.print(getValueAt(i, j)+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }


}
