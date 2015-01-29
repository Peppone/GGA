package test;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.util.JMException;
import encodings.solutionType.ArrayPermIntSolutionType;
import encodings.solutionType.PermIntSolutionType;
import encodings.variable.ArrayPermInt;
import encodings.variable.PermInt;


public class GGAProblem extends Problem {

	
	private static final long serialVersionUID = 1L;
	
	GGAProblem(){
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		problemName_ = "GGAProblem";
		//solutionType_ = new PermIntSolutionType(this);
		solutionType_ = new ArrayPermIntSolutionType(this);
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
		solutionType_ = new ArrayPermIntSolutionType(this);
		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];
		for (int i = 0; i < numberOfVariables_; ++i) {

			upperLimit_[i] = max;

			lowerLimit_[i] = min;
		}
	}
	@Override
	public void evaluate(Solution solution) throws JMException {
		ArrayPermInt vars = (ArrayPermInt) solution.getDecisionVariables()[0];
		vars.sortByValues();
		PermInt[] allocation = (PermInt[]) vars.getDecisionVariables();
		int maxFirst=0;
		int maxLast=0;
		int maxLength=1;
		int first=0;
		int last=0;
		int length=1;
		vars.sortByValues();
		Integer group=(int)vars.getDecisionVariables()[0].getValue();
		Integer previous=group;
		
		for(int i=1;i<allocation.length;++i){
			int temp=(int)vars.getDecisionVariables()[i].getValue();
			if(temp!=previous){
				last=i-1;
				if(length>maxLength){
					maxFirst=first;
					maxLast=last;
					maxLength=length;
				}
				length=0;
				first=last=i;
				
			}
			if(i==allocation.length-1){
				last=i;
				length++;
				if(length>maxLength){
					maxFirst=first;
					maxLast=last;
					maxLength=length;
				}
			}
			previous=temp;
			length++;
		}
		
		
		vars.setFirstMaxMakespan(maxFirst);
		vars.setLastMaxMakespan(maxLast);
		vars.setMaxMakespan((int)group);
		solution.setObjective(0, maxLength);
		
	}
	

}
