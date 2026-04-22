# Mini Compiler
A simple compiler component that takes an arithmetic expression composed of only numbers, operators, and parentheses as input and processes it through multiple compilation phases such as lexical analysis, parsing, and evaluation.

## Description
When run, MiniCompiler.java accepts a String arithmetic expression as an input. The program then calls Tokenizer.java, which breaks the String into a list of characters composed of every number, operator, and parenthesis provided. Using these tokens, the expression is then parsed in ASTGenerator.java. If the expression is invalid, the program will return an error message detailing what went wrong. The expression will not be evaluated if it is invalid. If the expression is valid, it will pass through ASTGenerator.java with no errors and an expression tree representative of the expression will be produced by TreePrinter.java. If the expression is invalid, an expression tree will still be produced as a visual guide to determining the expression's invalidity. Finally, after the expression tree is produced, the expression is evaluated and the result is returned by Evaluator.java.

## Examples
### Valid Input
Input: (3 + 2) * 5 - 1 <br>
Output: <br>
Tokens: [(, 3, +, 2, ), *, 5, -, 1] <br>
Parse Tree:

                - 
            /       \ 
         *               1 
       /    \ 
      +       5
     / \
    3   2

Evaluation Result: 24

### Invalid Input
Input: 3 + * 5, (), 3 + (4 - ) <br>
Output: <br>
Tokens: [3, +, *, 5, ,, (, ), ,, 3, +, (, 4, -, )] <br>
Parse Tree:

      +
     / \
    3   *

Exception in thread "main" java.lang.NumberFormatException: <br>
Syntax Error: Expected a number but received * <br>
        at Evaluator.evaluate(Evaluator.java:23) <br>
        at Evaluator.evaluate(Evaluator.java:28) <br>
        at MiniCompiler.main(MiniCompiler.java:43) <br>
        
## Authors
Avery Leber and Maya Thomas
