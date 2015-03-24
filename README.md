# Sequence-Alignment-Algorithm
DNA/RNA/Protein Sequence Alignment

## What is

In bioinformatics, a sequence alignment is a way of arranging the sequences of DNA, RNA, or protein to identify regions of similarity that may be a consequence of functional, structural, or evolutionary relationships between the sequences.

## How it works

Create a SequenceAlignmentAlgorithm Object with 2 Sequences (strings) as Input.

The algorithm builds up a Score Matrix representing every path (combination) possible. The shortest Path is the Best, meaning Sequences are aligned.

### Output Example

>> SEQUENCE ALIGNMENT ALGORITHM <<

>> INPUT:

CTACCCAGCATTA
TCCACTTA


>> OUTPUT:

CTACCCAGCATTA
 T-CC-A-C-TTA   
 
### Left to do

Need some minor tweaks, there are some exceptions where the output Alignment is not 100% perfect.
