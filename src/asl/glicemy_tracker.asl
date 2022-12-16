// Agent glicemy_tracker in project trabalho_final

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+glicemy(G): G < 180 & G > 70 <- .print("glicemy: ", G).

+glicemy(G): G >= 180 <- 
	.print("high glicemy: ", G);
	.send(cerberus, tell, glicemy(G)).

+glicemy(G): G <= 70 <- 
	.print("low glicemy: ", G);
	.send(cerberus, tell, glicemy(G)).
