package dna;

import java.util.HashSet;

public class DNA
{

    private final String dna;
    private String dnaNoJunk = "";
    private char[] DNA_chars;
    private HashSet<String> DNAcodons = new HashSet<String>();

    public DNA(String s)
    {
        dna = s;
        DNA_chars = s.toCharArray();

        String codon = "";
        for(char c:DNA_chars)
        {
            if(c == 'A' || c == 'C' || c == 'G' || c == 'T')
            {
                dnaNoJunk = dnaNoJunk + c;
                if(codon.length() < 3)
                {
                    codon = codon + c;
                    if(codon.length() == 3)
                    {
                        DNAcodons.add(codon);
                        codon = "";
                    }
                }
            }
        }
        if(codon.length() != 0)
            throw new IllegalArgumentException("Invalid DNA Sequence");
    }

    public boolean isProtein()
    {
        if (DNAcodons.size() < 5)
        {
            return false;
        }
        else if (((nucleotideCount('C') * 111.103) + (nucleotideCount('G') * 151.128))/totalMass() < .3)
        {
            return false;
        }
        else
        {
            if(dnaNoJunk.substring(0,2).equals("ATG") && (
                    dnaNoJunk.substring(dnaNoJunk.length() - 4, dnaNoJunk.length() - 1).equals("TAA")
                    || dnaNoJunk.substring(dnaNoJunk.length() - 4, dnaNoJunk.length() - 1).equals("TAG")
                    || dnaNoJunk.substring(dnaNoJunk.length() - 4, dnaNoJunk.length() - 1).equals("TGA")))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public double totalMass() // done
    {
        double mass = 0;
        for(int i = 0; i < DNA_chars.length; i++)
        {
            char nucleotide = DNA_chars[i];
            if(nucleotide == 'A')
            {
                mass += 135.128;
            }
            else if(nucleotide == 'T')
            {
                mass += 125.107;
            }
            else if(nucleotide == 'G')
            {
                mass += 151.128;
            }
            else if(nucleotide == 'C')
            {
                mass += 111.103;
            }
            else
            {
                mass += 100;
            }
        }

        final double retMass = Math.round(mass * 10.0) / 10.0;

        return retMass;
    }

    public int nucleotideCount(char c) //done
    {
        if(c != 'A' && c != 'T' && c != 'C' && c != 'G')
        {
            return 0;
        }
        else
        {
            int count = 0;
            for(int i = 0; i < DNA_chars.length; i++)
            {
                char nucleotide = DNA_chars[i];
                if(nucleotide == c)
                {
                    count++;
                }
            }
            return count;
        }
    }

    public String sequence() //done
    {
        return dna;
    }



}