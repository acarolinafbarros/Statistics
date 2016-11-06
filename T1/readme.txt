############## Informações ##############

***** Quarta-feira, 2 de Novembro de 2016 *****

- O StarUML sem licença não deixa abrir projetos já criados
- O StarUML não deixa criar as ligações como tinhamos desenhado, com as interfaces
- O StarUML depois de criar e gravar um determinado tipo de diagrama não deixa criar um novo diagrama desse mesmo tipo

- Usei então o Astah, com a licença de estudante. Não é tão intuitivo como o StarUML mas tem todas as funcionalidades que precisamos.

- No diagrama C&C:
 	- Não consegui mudar o root de <<cmp>> para <<iStat>> (e ainda tive uns bons minutos nisto). Se depois algum de vocês descobrir é só alterar no ficheiro ou então alterar no paint xD Também existe a possibilidade de não colocar aquela moldura que representa o sistema. [SOLVED] -> É necessário criar subsystems para colocar os diagramas, depois explico isto, o melhor teria sido criar todos os diagramas no mesmo project. Mas agora não vale a pena mudar ;)

- No diagrama de deployment:
	- Não assume aqueles dois traços na box (que torna as coisas componentes) - [SOLVED] - Isso acho que esta correto, porque são nodes em UML, não tem que ser components, não fazem parte de components da nossa arquitetura.
		- Podem ver aqui: https://en.wikipedia.org/wiki/Node_(UML)
		
	- Obriga a dar um nome ao componente <<linux>> (temporariamente chamei-lhe servidor) - [SOLVED] - Só podes aplicar um namespace a cada component, como colocaste " " consegues com isto "  ".
	- Falta definir que base de dados vamos usar para atualizar o diagrama - [SOLVED] - Este já não esta definido como MongoDB?

- Naturalmente não apaguei nenhum dos diagramas da Carolina porque até podemos conseguir arranjar alguma resolução para os problemas que referi antes. Para não atrasar, os diagramas que ela tinha feito foram igualmente criados com o Astah. [SOLVED]

- Relativamente ao documento SAD, optei por criar um ficheiro de texto com a mesma estrutura do documento, onde fui colocando lá alguns textos para ficarmos com uma visão geral da coisa. Assim, no final, apenas um de nós mexer no documento. Os textos que escrevi precisam claramente de uma revisão!!

(Usem o SublimeText para ficar tudo formatado)

-- Autor : Manuel Correia --

***** <dia da semana>, <dia> de <mês> de <ano> *****

-- Autor(a) : <nome> --



- SAD não tem mal estar em português, mas prof disse "não vos faz mal nenhum treinar o inglês"

- Single Page Aplication (Slide Connecting the dots)

(Cliente)
browser 		-------> 	web server
Model			  DTO 		Resource
View			JSON/XML
Controller

