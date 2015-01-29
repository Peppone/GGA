package encodings.solutionType;

import jmetal.core.Problem;
import jmetal.core.SolutionType;
import jmetal.core.Variable;
import encodings.variable.ArrayPermInt;

public class ArrayPermIntSolutionType extends SolutionType{

	int numberOfVariables;
	
	public ArrayPermIntSolutionType(Problem problem) {
		super(problem);
		numberOfVariables= problem.getNumberOfVariables();
	}

	@Override
	public Variable[] createVariables() throws ClassNotFoundException {
		Variable [] var = new Variable [1];
		var[0]=new ArrayPermInt(numberOfVariables);
		return null;
	}

}
