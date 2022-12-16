// Environment code for project testando_ambiente

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;
import java.util.*;

public class env extends Environment {

    private Logger logger = Logger.getLogger("testando_ambiente."+env.class.getName());
    private int glicemy = 250;
    private int ratioInsulinGlicemy = 35;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        this.addGlicemyPercept(this.glicemy);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        if(action.getFunctor().equals("reduceGlicemy")) {
        	this.removeGlicemyPercept();
        	
        	int reducao = Integer.valueOf(action.getTerm(0).toString());
        	int newGlicemyValue = this.glicemy - reducao;
        	this.glicemy = this.glicemy - reducao;
        	
        	this.addGlicemyPercept(newGlicemyValue);
        }
        
        if(action.getFunctor().equals("applyInsulin")) {
    		this.removeGlicemyPercept();

    		int glicemy = Integer.valueOf(action.getTerm(0).toString());
    		int insulin_doses = Math.round((glicemy - 100)/this.ratioInsulinGlicemy);
    		int newGlicemyValue = this.glicemy - (this.ratioInsulinGlicemy * insulin_doses);
    		this.glicemy = newGlicemyValue;
    		
    		this.addGlicemyPercept(newGlicemyValue);
        }
        
        if(action.getFunctor().equals("applyGlucose")) {
    		this.removeGlicemyPercept();
    		
    		this.glicemy = 150;
    		
    		this.addGlicemyPercept(this.glicemy);
        }
        
        if(action.getFunctor().equals("carbohydrateIntake")) {
    		this.removeGlicemyPercept();
    		
    		int glicemicValue = (int)Math.floor(Math.random()*(400-35+1)+35);
    		
    		int newGlicemyValue = this.glicemy + glicemicValue;
    		this.glicemy = this.glicemy + glicemicValue;
    		
    		this.addGlicemyPercept(newGlicemyValue);	
        }
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
    
    public void removeGlicemyPercept() {
    	try {
    		String current_glicemy_percept = "glicemy(" + this.glicemy + ")";
        	removePercept("glicemy_tracker", ASSyntax.parseLiteral(current_glicemy_percept));
    	} catch(ParseException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void addGlicemyPercept(Integer glicemy_value) {
    	try {
    		String current_glicemy_percept = "glicemy(" + glicemy_value + ")";
    		addPercept("glicemy_tracker", ASSyntax.parseLiteral(current_glicemy_percept));
    	} catch(ParseException e) {
    		e.printStackTrace();
    	}
    }
}
