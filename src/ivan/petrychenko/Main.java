package ivan.petrychenko;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String... args) {
        System.out.println( getOutcomesDistribution( 2, 2 ) );
        System.out.println( getOutcomesDistribution( 2, 6 ) );
        System.out.println( getOutcomesDistribution( 3, 3 ) );
        System.out.println( getOutcomesDistribution( 3, 6 ) );
        System.out.println( getOutcomesDistribution( 10, 10 ) );
    }

    private static Map<Integer, Integer> getOutcomesDistribution(int n, int m){
        System.out.println("\n"+n+" dices with "+m+" sides each ");
        double[] probabilities = new double[ m*n + 1 ];
        Set<Integer> options = buildOptionsList( m, n ) ;
        //System.out.println( options  );
        float minimalProbability = 1f / options.size();
        System.out.println( minimalProbability );
        List<Integer> sums = options.stream()
            .map( opt ->  opt.stream().mapToInt(Integer::intValue).sum() )
            .collect( Collectors.toList());

        sums.forEach( s -> probabilities[s] += minimalProbability );

        Map<Integer, Integer> r = new LinkedHashMap<>( m*n );
        for( int i = n; i < probabilities.length; i++ ){
            r.put( i, (int)Math.round(probabilities[i]*100));
        }

        return r ;
    }

    @SuppressWarnings( "unchecked" )
    private static List<Integer> buildOptionsList( final int m, final int n ){

        List<List<Integer>> allSubOptions;
        if( n == 1 ){
            allSubOptions = Collections.singletonList( Collections.emptyList() );
        }else if (n > 1){
            allSubOptions = buildOptionsList( m , n - 1 );
        }else{
            throw new IllegalStateException( " something went wrong, as it cannot be negative number of dices ");
        }
        List<List<Integer>> ret  = new LinkedList<>();
        for( int i = 1; i < m + 1 ; i++ ){
            final int currentI = i;
            allSubOptions.forEach( subOption ->{
                List<Integer> option = new LinkedList<>();
                option.add( currentI );
                option.addAll( subOption );
                ret.add( option );
            });
        }
        System.out.println("level "+n+" built ");
        return ret;
    }

}
