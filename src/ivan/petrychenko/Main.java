package ivan.petrychenko;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String... args) {

        calculateProbabilties(2,2);
        calculateProbabilties(3,3);
        calculateProbabilties(6,2);
        calculateProbabilties(10,10);
        calculateProbabilties(100,100);

    }

    private static void calculateProbabilties( final int m,  final int n) {
        System.out.println("\n"+n+" dices with "+m+" sides each ");
        System.out.println( getOutcomesDistribution( n,m ) );
    }

    private static Map<Integer, Double> getOutcomesDistribution(int n, int m){

        final Map<Integer, Double> probabilities = new LinkedHashMap<>( m*n );

        double atomicProbability = 1.0 / m;
        if( n==1 ){
            for(int i = 1; i < m+1; i++) {
                probabilities.put( i, atomicProbability);
            }
        }else{
            final Map<Integer, Double> prevOutcomes = getOutcomesDistribution(n-1, m);
            prevOutcomes.forEach((k,v) -> {
                for(int i = 1; i < m+1; i++) {
                    int outcome = i + k;
                    double prob = atomicProbability * v;
                    double existingProb = probabilities.computeIfAbsent( outcome, k_outcome -> 0.0 );
                    probabilities.put( outcome, prob + existingProb );
                }
            });
        }
        return probabilities ;
    }

}
