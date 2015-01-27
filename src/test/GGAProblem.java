package test;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.util.JMException;
import encodings.solutionType.PermIntSolutionType;


public class GGAProblem extends Problem {

	
	private static final long serialVersionUID = 1L;
	
	GGAProblem(){
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		problemName_ = "GGAProblem";
		solutionType_ = new PermIntSolutionType(this);
		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];
		 numberOfVariables_=15;
		for (int i = 0; i < numberOfVariables_; ++i) {

			upperLimit_[i] = 10;

			lowerLimit_[i] = 0;
		}
	}
	
	GGAProblem(int size, int min, int max){
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		numberOfVariables_=size;
		problemName_ = "GGAProblem";
		solutionType_ = new PermIntSolutionType(this);
		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];
		for (int i = 0; i < numberOfVariables_; ++i) {

			upperLimit_[i] = max;

			lowerLimit_[i] = min;
		}
	}
	@Override
	public void evaluate(Solution solution) throws JMException {
		solution.setObjective(0, 0.0);
	}
	

}
