Build an outcome probability distribution for roll of N dices with M sides marked from 1 to M.
The result is an array of floats of size M*N+1 where result[ i ] represents a probability of i. 
Example:
 
N = 2, m = 2 

cases: (1,1), (1,2), (2,1), (2,2)

probability for 0 is 0, for 1 is 0, for 2 is .25, for 3 is .5, and for 4 is .25   

result = [.0, .0, .25, .5, .25]

