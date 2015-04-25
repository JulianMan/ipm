package functions;

import org.jblas.DoubleMatrix;

/**
 * Implements log barrier function for SmoothFunction f
 * def: h(x) = sum(-log(-fi(x)))
 * 				i
 * Used to approximate inequality constraints f(x) <= 0
 * @author Julian
 *
 */

public class LogBarrier implements SmoothFunction{
	
	private SmoothFunction f;
	private double scaleParameter = 1.0;
	
	public LogBarrier(SmoothFunction f){
		this.f = f;
	}
	public LogBarrier(SmoothFunction f, double scaleParameter){
		this(f);
		this.setScaleParameter(scaleParameter);
	}

	@Override
	public DoubleMatrix evaluate(DoubleMatrix x) {
		// TODO Auto-generated method stub
		double sum = 0;
		DoubleMatrix fVal = f.evaluate(x);
		for(int i = 0; i < f.getRangeDimension(); i++){
			sum -= Math.log(-fVal.get(i));
		}
		return DoubleMatrix.scalar(sum / scaleParameter);
	}

	@Override
	public DoubleMatrix gradient(DoubleMatrix x, int i) {
		// TODO Auto-generated method stub
		DoubleMatrix grad = DoubleMatrix.zeros(f.getDomainDimension());
		DoubleMatrix fVal = f.evaluate(x);
		for(int j = 0; j < f.getRangeDimension(); j++){
			grad.addi(f.gradient(x,j).divi(-fVal.get(j)));
		}
		return grad.divi(scaleParameter);
	}

	@Override
	public DoubleMatrix hessian(DoubleMatrix x, int i) {
		// TODO Auto-generated method stub
		DoubleMatrix hessian = DoubleMatrix.zeros(f.getDomainDimension(),
												  f.getDomainDimension());
		//DoubleMatrix fGrad = f.gradient(x);
		DoubleMatrix fVal = f.evaluate(x);
		for(int j = 0; j < f.getRangeDimension(); j++){
			DoubleMatrix fGrad = f.gradient(x, j);
			hessian.addi(fGrad.mmuli(fGrad.transpose()).divi(fVal.get(j)));
			hessian.subi(f.hessian(x, j).divi(fVal.get(j)));
		}
		return hessian.divi(scaleParameter);
	}

	@Override
	public int getDomainDimension() {
		// TODO Auto-generated method stub
		return f.getDomainDimension();
	}

	@Override
	public int getRangeDimension() {
		// TODO Auto-generated method stub
		return 1;
	}
	public double getScaleParameter() {
		return scaleParameter;
	}
	/**
	 * 
	 * @param scaleParameter New scaleParameter, must be positive
	 */
	public void setScaleParameter(double scaleParameter) {
		if(scaleParameter <= 0){
			throw new IllegalArgumentException();
		}
		this.scaleParameter = scaleParameter;
	}
	
}
