############## Informações ##############

***** Segunda-feira, 19 de Dezembro de 2016 *****

- Documento SAD

	- Capitulo 4 - USES CASES

	4.1	Use Case 1: Calculate Median 
			- Diagrama de sequência Cliente-Backend (Atualizado)
			- Diagrama de sequência Cliente         (Atualizado)
			- Diagrama de sequência Backend         (Atualizado)
			- Diagrama de classes Cliente           (Atualizado)
			- Diagrama de classes Backend           (Atualizado)

	4.2	Use Case 2: Transform - Transpose
		- Diagrama de sequência Cliente-Backend (Atualizado)
		- Diagrama de sequência Cliente         (Atualizado)
		- Diagrama de sequência Backend         (Atualizado)
		- Diagrama de classes Cliente           (Atualizado)
		- Diagrama de classes Backend           (Atualizado)

	4.3	Use Case 3: Save dataset
		- Diagrama de sequência Cliente-Backend (Atualizado)
		- Diagrama de sequência Cliente         (Atualizado)
		- Diagrama de sequência Backend         (Atualizado)
		- Diagrama de classes Cliente           (Atualizado)
		- Diagrama de classes Backend           (Atualizado)

	4.4	Use Case 4: Export data
		- Diagrama de sequência Cliente-Backend (Atualizado)
		- Diagrama de sequência Cliente         (Atualizado)
		- Diagrama de sequência Backend         (Atualizado)
		- Diagrama de classes Cliente           (Atualizado)
		- Diagrama de classes Backend           (Atualizado)

	4.4	Use Case 5: Import data
		- Diagrama de sequência Cliente-Backend (Atualizado)
		- Diagrama de sequência Cliente         (Atualizado)
		- Diagrama de sequência Backend         (Atualizado)
		- Diagrama de classes Cliente           (Atualizado)
		- Diagrama de classes Backend           (Atualizado)


-- Autor : Carolina Barros --

***** Terça-feira, 20 de Dezembro de 2016 *****

- Documento SAD

	- Capitulo 2

		- Secção 2.1.2 ) Goals and Context

				The goal for the iStat architecture is to spare it in three concerns: user interface, logic and persistence. The purpose of this separation is to have more flexibility on the application. 

				In order to ensure better accessibility, performance and availability a good option would be the use of RIA as user interface, since it garantees a great user experience by only making requests to the server on more complex operations and allowing the user to work while the data is being transferred.

				The logic part should respect the principle of responsibility, which allows to have extensibility. Dividing it in three layers(iServices, iBLL and iDAL), maintaining the logic part away from the user interface, garantees more security to the system and it also allows to make modifications on each part without having to do big changes on the other, like for example the implementation for mobile devices.

				About other topics ans assumptions, the objective is to:
					* Use external libraries for import/export datasets and graphics design.
					* Run the web application only in online mode:
						The goal is to ensure that the user is always online and he has access to all the business logic.
					* Use non-relational database :
						Given the lack of business rules and the simplicity of data manipulation (inserts, updates,
						and readings). Also, is a good choice if we want to improve the performance of data manipulation and simplify the
						database management.

	- Capitulo 3 

		- Secção 3.1 ) Functional View

			3.1.2 Primary Representation (Atualizado)

			3.1.3.1	Elements

					- User : Our application user
					- Calculate dataset : The use case for applying different calculations on a dataset
					- Transform dataset : The use case for applying different transformations on a dataset
					- Chart dataset : The use case for applying different charts on a dataset
					- Save dataset : The use case for saving a dataset in a database
					- Import/Export dataset : The use case for importing/exporting a dataset

			Nota : 
					- Pode-se remover os pontos 3.1.4 , 3.1.5 e 3.1.6
					- Falta atualizar esta secção do SAD com as informações acima

			###############################################################################################
		
		- Secção 3.2 ) Context Diagram View

			3.2.2 Primary Representation (Atualizado)

			3.2.3.1 Elements

					- Remover UtilsGraph e UtilsInOut

			Nota :

					- Pode-se remover os pontos 3.2.4 , 3.2.5 e 3.2.6
					- Falta atualizar esta secção do SAD com as informações acima

			###############################################################################################

		- Secção 3.3 ) Module Decomposition & Uses View

			3.2.2 Primary Representation (Atualizado)

			3.2.3.1 Elements

					- API : Our application programming interface
					- External API : An external application programming interface, one for draw graphs and other to import/export data files

			Nota :

					- Pode-se remover os pontos 3.3.4 , 3.3.5 e 3.3.6
					- Falta atualizar esta secção do SAD com as informações acima

			###############################################################################################	

		- Secção 3.5 ) Module Layer View

			3.5.2 Primary Representation (Atualizado)

			3.5.3.1 Elements

					- iService : Contains the interfaces consumed by the services 
 					
 					Notes : Consult the subsection 3.5.4 Other Diagrams for more detailed information about the structure

			3.5.4 Other Diagrams

					- Diagrama representativo do componente iStat.com (Em falta)
					- Diagrama representativo do componente iStatDB (Em falta)
					- Diagrama representativo do componente iStat.com/api (Feito)
						- iService (Feito)
						- iBLL (Feito)
						- iDAL (Feito)
			Nota :

					- Pode-se remover os pontos 3.5.5 e 3.5.6
					- Falta atualizar esta secção do SAD com as informações acima

			###############################################################################################		

		- Secção 3.6 ) Deployment View

			3.6.2 Primary Representation (Atualizado)

			Nota :

					- Pode-se remover os pontos 3.6.4, 3.6.5 e 3.6.6
					- Falta atualizar esta secção do SAD com as informações acima

			###############################################################################################	

-- Autor : Manuel Correia --



***** <dia da semana>, <dia> de <mês> de <ano> *****

-- Autor(a) : <nome> --

												############################################
												Melhorias relativamente à primeira entrega :
												############################################

1 - Limpar do documento aquelas infos e estrutura que não se usou (FEITO)
2 - Nos Goals and Objectives estamos a descrever a solução e não a dizer os objetivos (FEITO)
3 - Na primeira view, faltou identificar os use case como elementos (FEITO)
4 - No context diagram, as bibliotecas não se representam (FEITO)
5 - É preciso definir API porque é diferente a das bibliotecas e a do backend (FEITO)
6 - No deployment falta identificar se a nossa API não vai correr num Tomcat ou assim (FEITO)
7 - O layer diagram tem de ser mais detalhado porque ele quer ver o que contém cada componente (FEITO)
8 - No Calculate, temos de explicar melhor a parte da chamada do método que depois recebe um json e a cell (FEITO)
9 - Temos de explicar porque estamos a fazer um export para o server (FEITO)

