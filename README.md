Uma gramática simples para nossa tradução sintática: 

expr -> digit oper
oper -> + digit oper
     | - digit oper
     | ε
digit -> 0 | .. | 9

O passo 1 implementa uma tradução simples para compreensão do front-end convertendo a expressão em notação infixada (2+2) para pós-fixada (22+).

O passo 2 descreve sobre a estrutura léxica e o fato dela especificar a forma como os caracteres e palavras devem ser agrupadas para por exemplo, especificar que a tradução considere números com mais de um dígito.

O passo 3 reescreve o passo 1 do tradutor simples para suportar o scanner, que é o passo do compilador de executar a análise léxica dos caracteres.