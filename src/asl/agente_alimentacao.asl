// Agent monitorador_alimentacao in project testando_ambiente

/* Initial beliefs and rules */

/* Initial goals */

!aumentar_glicemia.

/* Plans */

+!aumentar_glicemia: true <-
	.wait(10000);
	alimentacao(200);
	.print("aumentou glicemia");
	!aumentar_glicemia.