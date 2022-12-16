// Agent agent_carbohydrate_intake in project trabalho_final

/* Initial beliefs and rules */

/* Initial goals */

!increase_glicemy.

/* Plans */

+!increase_glicemy: true <-
	.wait(10000);
	carbohydrateIntake(0);
	.print("glicemy increased");
	!increase_glicemy.