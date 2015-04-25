package functions;

import org.jblas.DoubleMatrix;

public class FunctionSum implements SmoothFunction{
	private SmoothFunction f1;
	private SmoothFunction f2;
	
	public FunctionSum(SmoothFunction f1,
					   SmoothFunction f2){
		if(f1.getDomainDimension() != f2.getDomainDimension() ||
		    f1.getRangeDimension() != f2.getRangeDimension()){
			throw new IllegalArgumentException();
		}
		this.f1 = f1;
		this.f2 = f2;
	}
	@Override
	public DoubleMatrix evaluate(DoubleMatrix x) {
		// TODO Auto-generated method stub
		return f1.evaluate(x).add(f2.evaluate(x));
	}

	@Override
	public DoubleMatrix gradient(DoubleMatrix x, int i) {
		// TODO Auto-generated method stub
		return f1.gradient(x,i).add(f2.gradient(x,i));
	}

	@Override
	public DoubleMatrix hessian(DoubleMatrix x, int i) {
		// TODO Auto-generated method stub
		return f1.hessian(x,i).add(f2.hessian(x,i));
	}
	@Override
	public int getDomainDimension(){
		return f1.getDomainDimension();
	}
	@Override
	public int getRangeDimension() {
		return f1.getRangeDimension();
	}
	
}
