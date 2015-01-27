package encodings.solutionType;

import jmetal.core.Problem;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.IntSolutionType;
import encodings.variable.PermInt;

public class PermutationIntSolutionType extends IntSolutionType{

	public PermutationIntSolutionType(Problem problem) {
		super(problem) ;
	} // Constructor

	/**
	 * Creates the variables of the solution
	 */

	
	public Variable[] createVariables() {
		Variable[] variables = new Variable[problem_.getNumberOfVariables()];

		for (int var = 0; var < problem_.getNumberOfVariables(); var++)
			variables[var] = new PermInt((int)problem_.getLowerLimit(var),
					(int)problem_.getUpperLimit(var),var);    

		return variables ;
	} // createVariables

}
