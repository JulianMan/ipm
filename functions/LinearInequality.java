package functions;

import org.jblas.DoubleMatrix;

/**
 * Implements linear inequality Ax <= b <-> Ax - b <= 0
 * @author Julian
 *
 */
public class LinearInequality implements SmoothFunction{
	private DoubleMatrix A;
	private DoubleMatrix b;
	private DoubleMatrix hessian;
	
	public LinearInequality(DoubleMatrix A, DoubleMatrix b){
		if(A.getRows() != b.getRows() || b.getColumns() > 1){
			throw new IllegalArgumentException();
		}
		this.A = A;
		this.b = b;
		
		// Precalculate hessian
		this.hessian = DoubleMatrix.zeros(A.getColumns(),A.getColumns()); 
	}
	/**
	 * Overloaded constructor for arrays A and b
	 */
	public LinearInequality(double[][] A, double[] b){
		this(new DoubleMatrix(A), new DoubleMatrix(b));
	}
	@Override
	public DoubleMatrix evaluate(DoubleMatrix x) {
		// TODO Auto-generated method stub
		return A.mmul(x).sub(b);
	}

	@Override
	public DoubleMatrix gradient(DoubleMatrix x,int i) {
		// TODO Auto-generated method stub
		return A.getRow(i).transpose();
	}

	@Override
	public DoubleMatrix hessian(DoubleMatrix x, int i) {
		// TODO Auto-generated method stub
		return hessian;
	}

	@Override
	public int getDomainDimension() {
		// TODO Auto-generated method stub
		return A.getColumns();
	}

	@Override
	public int getRangeDimension() {
		// TODO Auto-generated method stub
		return A.getRows();
	}
	
}
