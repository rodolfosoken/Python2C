package sintaxe;

public class Goto {
	public final String nTerminal;
	public final int estado;
	
	public Goto(String nTerminal, int estado){
		this.nTerminal = nTerminal;
		this.estado = estado;
	}
	
	public String toString(){
		return "Goto "+ nTerminal + " --> " + estado;
	}

}
