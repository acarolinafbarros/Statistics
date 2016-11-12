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

(Anotações aula teórica)

- SAD não tem mal estar em português, mas prof disse "não vos faz mal nenhum treinar o inglês"

- Single Page Aplication (Slide Connecting the dots)

(Cliente)
browser 		-------> 	web server
Model			  DTO 		Resource
View			JSON/XML
Controller

(Usem o SublimeText para ficar tudo formatado)

-- Autor : Manuel Correia --

***** Quarta-feira, 9 de Novembro de 2016 *****

- Package "client" :

	- Diagrama de Sequência "iStat.com" :
		- Não dá para adicionar o "cell" do "CalcController" para "iStatView"

	- Diagrama de Classes "client_class_diagram" :
		- Classe "CalcController" :
			- Não dá para adicionar o "input" e o "request" sem adicionar o tipo de dados
		- Classe "Cell" :
			- O mesmo problema para "value". Escrevi javascript só para verem que obriga a ter algo lá, como é óbvio não pode ficar lá escrito isto

- Package "backend" :

	- Diagrama de Sequência "iStat.com/api" :
		- Não dá para adicionar o "response" do "IStatCalc" para "iStat : iStat.com"
		- É para escrever "IStatCalc" ou "iStatCalc" ? (Porque no diagrama de seq do client escrevemos "iStatView")

	- Diagrama de Classes "backend_class_diagram" :
		- Classe "IStatCalc" :
			- Não dá para adicionar o "request"
			- Se o nome se alterar, aqui também tem de mudar
	- Classe "CalcService" :
			- Não dá para adicionar o "list <int> input"

- Adicionei as imagens de ontem com os mesmos nomes e a mesma estrutra de pastas, para ser mais fácil ver os problemas que estou a relatar

- Mal possam façam a revisão, para a Carolina poder colocar tudo no documento SAD. E pf não se esqueçam de rever o documento SAD, pode ser preciso melhorar o inglês ou detalhar mais as coisas

-- Autor : Manuel Correia --

***** Sábado, 12 de Novembro de 2016 *****

- Modificado :

	- Table 1
	- Cap. 2.1.1 (Correções inglês só)
	- Cap. 2.1.2
	- Cap. 2.1.3
	- Cap. 3.1.1
	- Cap. 3.2.1, 3.2.3.1
	- Cap. 3.3.1, 3.3.3.1, 3.3.3.2
	- Cap. 3.4.1, 3.4.3.1, 3.4.3.2, 3.4.3.3
	- Cap. 3.5.3.1, 3.5.3.2
	- Cap. 3.6.1, 3.6.3.1, 3.6.3.2
	- Cap. 4.1.1
	- Cap. 4.2.1
	- Cap. 4.3.1
	- Cap. 4.4.1
	- Diagram Context
	- Diagram C&C

- Em falta :

	- Decidir biblioteca para Importação/Exportação. 
	- Mudar diagrama de contexto no relatório.
	- Mudar diagrama C&C no relatório (após decisão de biblioteca I/O também é preciso atualizar mesmo o diagrama).
	- Perceber pattern presente no use case Importar ou Exportar.
	- Corrigir legendas das figuras (Ilustrações em português).
	- Reler tudo!!!!

-- Autor : Mafalda Landeiro --

***** <dia da semana>, <dia> de <mês> de <ano> *****

-- Autor(a) : <nome> --



