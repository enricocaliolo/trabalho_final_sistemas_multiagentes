// Environment code for project testando_ambiente

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;
import java.util.*;
import java.text.SimpleDateFormat;;

public class env extends Environment {

    private Logger logger = Logger.getLogger("testando_ambiente."+env.class.getName());
    private int glicemia = 250;
    private int ratioInsulinGlicemy = 35;
    private Date date = new Date();
    private long msDiff = 0;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
        	logger.info("" + this.date + "");
			addPercept("monitorador_glicemia", ASSyntax.parseLiteral("glicemia(" + this.glicemia + ")" ));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        if(action.getFunctor().equals("reduzirGlicemia")) {
        	try {
        		int reducao = Integer.valueOf(action.getTerm(0).toString());
        		String glicemia = "glicemia(" + this.glicemia + ")";
        		removePercept("monitorador_glicemia", ASSyntax.parseLiteral(glicemia));

        		int newGlicemiaValue = this.glicemia - reducao;
        		this.glicemia = this.glicemia - reducao;
        		
        		String newGlicemia = "glicemia(" + newGlicemiaValue + ")";
        		addPercept("monitorador_glicemia", ASSyntax.parseLiteral(newGlicemia));
        	} catch(ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        if(action.getFunctor().equals("aplicarInsulina")) {
        	try {
        		int glicemia = Integer.valueOf(action.getTerm(0).toString());
        		int insulin_doses = Math.round((glicemia - 100)/this.ratioInsulinGlicemy);
        		int newGlicemia = this.glicemia - (this.ratioInsulinGlicemy * insulin_doses);
        		
        		
        		String strGlicemia = "glicemia(" + this.glicemia + ")";
        		removePercept("monitorador_glicemia", ASSyntax.parseLiteral(strGlicemia));
        		
        		this.glicemia = newGlicemia;
        		
        		String newStrGlicemia = "glicemia(" + newGlicemia + ")";
        		addPercept("monitorador_glicemia", ASSyntax.parseLiteral(newStrGlicemia));
        		
        	} catch(ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        if(action.getFunctor().equals("aplicarGlicose")) {
        	try {
        		String strGlicemia = "glicemia(" + this.glicemia + ")";
        		removePercept("monitorador_glicemia", ASSyntax.parseLiteral(strGlicemia));
        		
        		this.glicemia = 150;
        		
        		String newStrGlicemia = "glicemia(" + this.glicemia + ")";
        		addPercept("monitorador_glicemia", ASSyntax.parseLiteral(newStrGlicemia));
        		
        		
        	} catch(ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        if(action.getFunctor().equals("alimentacao")) {
        	try {
        		Date date = new Date();
        		
        		this.msDiff = date.getTime() - this.date.getTime();
        		
        		logger.info("Last eaten: " + Long.toString(this.msDiff));
        		
        		int value = Integer.valueOf(action.getTerm(0).toString());
        		
        		String strGlicemia = "glicemia(" + this.glicemia + ")";
        		removePercept("monitorador_glicemia", ASSyntax.parseLiteral(strGlicemia));
        		
        		int newGlicemia = this.glicemia + value;
        		this.glicemia = this.glicemia + value;
        		
        		String newStrGlicemia = "glicemia(" + newGlicemia + ")";
        		addPercept("monitorador_glicemia", ASSyntax.parseLiteral(newStrGlicemia));
        		
        	} catch(ParseException e) {
        		e.printStackTrace();
        	}
        }
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
