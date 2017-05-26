package de.hsMannheim.ss17.tpe.martinDavid.utilitiies.stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayStackTest extends StackTest {

	@Override
	public void setUp() {
		stack = new ArrayStack(initalMaxSize);
	}

}
