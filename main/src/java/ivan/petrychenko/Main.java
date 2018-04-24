package ivan.petrychenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static class Permutator{
        private final int alphabetSize;
        private final int wordLength;
        private final int maxConsequent;
        private final char[] alphabet;
        private int callCounter;

        Permutator( int alphabetSize, int wordLength, int maxConsequent ){

            this.alphabetSize = alphabetSize;
            this.wordLength = wordLength;
            this.maxConsequent = maxConsequent;

            alphabet = new char[alphabetSize];
            for( int i = 0; i < alphabetSize; i++ ){
                alphabet[i] = (char)(((int)'a') + i);
            }

        }

        List<String> generateAllOptimalTime(){
            callCounter = 0;
            return generateAllOfLength( wordLength );
        }

        List<String> generateAllElegantCode(){
            callCounter = 0;
            List<String> allPermutations = new LinkedList<>();
            collectAllAddingTo( allPermutations, "" );
            return allPermutations;

        }

        private void collectAllAddingTo( List<String> allPermutations, String base ){
            callCounter++ ;
            if(  base.length() >= wordLength){
                allPermutations.add( base );
            }else{
                for( char c : alphabet ){
                    if( isEndsWithSeqOfSameChars( base, c, maxConsequent ) ){
                        continue;
                    }
                    collectAllAddingTo( allPermutations, base + c );
                }
            }

        }

        private List<String> generateAllOfLength( int curWordLength ){
            callCounter++ ;
            if( curWordLength <= 0 ){
                return Collections.singletonList( "" );
            }

            List<String> all = new ArrayList<>( (int)Math.pow(alphabetSize, curWordLength) );

            final int prevWordLength = curWordLength - 1;
            List<String> prevStep = generateAllOfLength( prevWordLength );

            for( char c : alphabet ){
                for(String prevWord : prevStep){
                    if( isEndsWithSeqOfSameChars( prevWord, c, maxConsequent ) ){
                        continue;
                    }
                    all.add( prevWord + c );
                }
            }

            return all;

        }

        private boolean isEndsWithSeqOfSameChars( String word, char c, int seqLength ){
            if( word.length() < seqLength ){
                return false;
            }
            boolean longSeq = true;
            for( int j = word.length() - 1; j > word.length() - 1 - seqLength  ; j-- ){
                longSeq &= word.charAt( j ) == c;
            }
            return longSeq;
        }

        String getAlphabet(){
            return new String(alphabet);
        }

        int getCallCounter(){
            return callCounter;
        }
    }

    public static void main(String[] args) {
	    Permutator mutator = new Permutator( 2, 10, 2 );
        System.out.println( "The alphabet: " + mutator.getAlphabet() );
        List<String> words = mutator.generateAllOptimalTime();
        System.out.println( "Permutations ("+words.size()+" total by "+mutator.getCallCounter()+" calls ): " );
        words.forEach( System.out::println );

        words = mutator.generateAllElegantCode();
        System.out.println( "Permutations ("+words.size()+" total by "+mutator.getCallCounter()+" calls ): " );
        words.forEach( System.out::println );

    }
}
