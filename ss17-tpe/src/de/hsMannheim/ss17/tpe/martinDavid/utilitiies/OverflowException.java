package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class OverflowException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private Object elementCausedOverflow;
	public Object getElementCausedOverflow() {
		return elementCausedOverflow;
	}
	private void setElementCausedOverflow(Object elementCausedOverflow) {
		this.elementCausedOverflow = elementCausedOverflow;
	}
	
	public OverflowException(String message, Object element) {
		super(message);
		setElementCausedOverflow(element);
	}
}
