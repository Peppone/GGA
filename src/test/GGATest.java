package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.metaheuristics.nsgaII.NSGAII_main;
import jmetal.operators.crossover.UniformCrossover;
import jmetal.operators.mutation.MyRebalanceMutation;
import jmetal.operators.mutation.SwapMutation;
import jmetal.operators.selection.SelectionFactory;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import core.GGASolution;



public class GGATest extends NSGAII_main{
	
	public static Algorithm setup(Problem problem) {
		Algorithm algorithm;
		algorithm = new NSGAII(problem);
		algorithm.setInputParameter("populationSize", 1000);
		algorithm.setInputParameter("maxEvaluations", 25000);
		return algorithm;
	}
		public static void main (String args[]) throws ClassNotFoundException, JMException, SecurityException, IOException{
			
			logger_ = Configuration.logger_;
			fileHandler_ = new FileHandler("GGATest.log");
			logger_.addHandler(fileHandler_);
			
			Problem problem=new GGAProblem(10,0,5);; // The problem to solve
			Algorithm algorithm=GGATest.setup(problem); // The algorithm to use
			Operator crossover; // Crossover operator
			Operator mutation; // Mutation operator
			Operator selection; // Selection operator
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("crossoverProbability", 0.9);
			crossover = new UniformCrossover(parameters);
			//crossover= new TwoCutPointsCrossover (parameters);
			parameters.put("mutationProbability",
					0.0 / problem.getNumberOfVariables());
			mutation = new SwapMutation(parameters);

			parameters = null;
			selection = SelectionFactory.getSelectionOperator("BinaryTournament",
					parameters);
			algorithm.addOperator("selection", selection);
			algorithm.addOperator("crossover", crossover);
			algorithm.addOperator("mutation", mutation);
			
			
			SolutionSet sol =algorithm.execute();
			
			GGASolution s= (GGASolution)sol.get(0);
			s.print();
			
			
			
			
			
		}
}
