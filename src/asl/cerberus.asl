// Agent cerberus in project testando_ambiente

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+glicemia(G)[source(AG)]: G >= 180 <-
	.send(aplicador_insulina, tell, aplicarInsulina(G)).
	
+glicemia(G)[source(AG)]: G <= 70 <-
	.send(aplicador_glicose, tell, aplicarGlicose(G)).