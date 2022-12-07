// Agent usuario1 in project testando_ambiente

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start  <- !reduzir_glicemia.

+!reduzir_glicemia: true <- 
	.wait(2000);
	reduzirGlicemia(10);
	.print("reduziu glicemia");
	!reduzir_glicemia.
	
