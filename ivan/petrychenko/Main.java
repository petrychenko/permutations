package ivan.petrychenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static class Permutator{
        private final int alphabetSize;
        private final int wordLength;
        private final int maxConsequent;
        private final char[] alphabet;

        Permutator( int alphabetSize, int wordLength, int maxConsequent ){

            this.alphabetSize = alphabetSize;
            this.wordLength = wordLength;
            this.maxConsequent = maxConsequent;

            alphabet = new char[alphabetSize];
            for( int i = 0; i < alphabetSize; i++ ){
                alphabet[i] = (char)(((int)'a') + i);
            }

        }

        List<String> generateAll(){

            return generateAllOfLength( wordLength );

        }

        private List<String> generateAllOfLength( int curWordLength ){
            if( curWordLength <= 0 ){
                return Collections.singletonList( "" );
            }

            List<String> all = new ArrayList<>( (int)Math.pow(alphabetSize, curWordLength) );

            final int prevWordLength = curWordLength - 1;
            List<String> prevStep = generateAllOfLength( prevWordLength );

            for( char c : alphabet ){
                for(String prevWord : prevStep){
                    if( curWordLength > maxConsequent ){
                        boolean longSeq = true;
                        for( int j = prevWordLength - 1; j > prevWordLength - 1 - maxConsequent  ; j-- ){
                            longSeq &= prevWord.charAt( j ) == c;
                        }

                        if( longSeq ){
                            continue;
                        }

                    }
                    all.add( prevWord + c );
                }
            }

            return all;

        }

        String getAlphabet(){
            return new String(alphabet);
        }
    }

    public static void main(String[] args) {
	    Permutator mutator = new Permutator( 2, 10, 2 );
        System.out.println( "The alphabet: " + mutator.getAlphabet() );
        final List<String> words = mutator.generateAll();
        System.out.println( "Permutations ("+words.size()+" total): " );
        words.forEach( System.out::println );
	    
    }
}
