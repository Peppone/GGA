package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;

import operator.HalfBalancingCrossover;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.metaheuristics.nsgaII.NSGAII_main;
import jmetal.operators.crossover.UniformCrossover;
import jmetal.operators.mutation.MyRebalanceMutation;
import jmetal.operators.selection.SelectionFactory;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import core.GGASolution;
import encodings.variable.PermInt;



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
			//crossover = new UniformCrossover(parameters);
			crossover= new HalfBalancingCrossover(parameters);
			parameters.put("mutationProbability",
					0.0 / problem.getNumberOfVariables());
			parameters.put("serverNumber", 6);
			mutation = new MyRebalanceMutation(parameters);

			parameters = null;
			selection = SelectionFactory.getSelectionOperator("BinaryTournament",
					parameters);
			algorithm.addOperator("selection", selection);
			algorithm.addOperator("crossover", crossover);
			algorithm.addOperator("mutation", mutation);
			
			
			SolutionSet sol =algorithm.execute();
		for (int i = 0; i < 1; i++) {

			Solution s = sol.get(0);
			PermInt[] var = (PermInt[]) s.getDecisionVariables();
			GGASolution ggasol = new GGASolution(var);
			ggasol.print();
			ggasol.group(true);
			System.out.println();
			ggasol.print();
			ggasol.group(false);
			System.out.println();
			ggasol.print();
		}
			
			
			
			
			
			
		}
}
