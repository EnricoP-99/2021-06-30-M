/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnContaArchi"
    private Button btnContaArchi; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSoglia"
    private TextField txtSoglia; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doContaArchi(ActionEvent event) {
    	txtResult.clear();
    	
    	this.model.creaGrafo();
    	
    	txtResult.appendText("Grafo Creato con successo!\n");
    	txtResult.appendText("#Vertici: "+ this.model.getNVertici()+"\n");
    	txtResult.appendText("#Archi: "+ this.model.getNArchi()+"\n");
    	txtResult.appendText("Peso Minimo: "+ this.model.getMinPeso()+"\n");
    	txtResult.appendText("Peso Massimo: "+ this.model.getMaxPeso()+"\n");
    	
    	
    	if(!txtSoglia.getText().equals(""))
    	{
    		try
    		{
    			double s=Double.parseDouble(txtSoglia.getText());
    			if(s>this.model.getMaxPeso())
    	    	{
    	    		txtResult.appendText("valore soglia superiore al max valore degli archi");
    	    	}
    	    	else if(s<this.model.getMinPeso())
    	    	{
    	    		txtResult.appendText("valore soglia inferiore al min valore degli archi");
    	    	}
    	    	else
    	    	{
    	    		txtResult.appendText("Soglia: "+s+"--> Maggiore: "+ this.model.GetArchiMaggiori(s)+
    	    				", Minore: " + this.model.GetArchiMinori(s));
    	    	}
    		}
    		catch(NumberFormatException e)
    		{
    			txtResult.clear();
    			txtResult.setText("il valore soglia deve essere un numero");
    			e.printStackTrace();
    		}
    	}
    		
    }

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		
	}
}
