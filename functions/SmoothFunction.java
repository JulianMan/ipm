package functions;

import org.jblas.DoubleMatrix;

public interface SmoothFunction {
	
	/**
	 * Evaluate function at point x
	 * @param x
	 * @return function value
	 */
	public DoubleMatrix evaluate(DoubleMatrix x);
	
	/**
	 * Evaluate function gradient at point x
	 * @param x
	 * @return function gradient
	 */
	public DoubleMatrix gradient(DoubleMatrix x, int i);
	
	/**
	 * Evaluate function Hessian (second derivative matrix) at point x
	 * 
	 * Return matrix H s.t.
	 * H(i,j) = (d/dxi)(d/dxj)f(x)
	 * @param x
	 * @param i Function index (zero based)
	 * @return function Hessian
	 */
	public DoubleMatrix hessian(DoubleMatrix x,int i);
	
	public int getDomainDimension();
	
	public int getRangeDimension();
}
