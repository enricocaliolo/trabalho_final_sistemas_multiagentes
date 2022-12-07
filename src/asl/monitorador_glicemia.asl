// Agent sample_agent in project testando_ambiente

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+glicemia(G): G < 180 & G > 70 <- .print("glicemia: ", G).

+glicemia(G): G >= 180 <- 
	.print("glicemia alta: ", G);
	.send(cerberus, tell, glicemia(G)).

+glicemia(G): G <= 70 <- 
	.print("glicemia baixa: ", G);
	.send(cerberus, tell, glicemia(G)).
