// Agent agent_reduce_glicemy in project trabalho_final

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start  <- !reduce_glicemy.

+!reduce_glicemy: true <- 
	.wait(2000);
	reduceGlicemy(10);
	.print("glicemy reduced");
	!reduce_glicemy.
	
