// Agent cerberus in project trabalho_final

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+glicemy(G)[source(AG)]: G >= 180 <-
	.send(insulin_applicator, tell, applyInsulin(G)).
	
+glicemy(G)[source(AG)]: G <= 70 <-
	.send(glucose_applicator, tell, applyGlucose(G)).