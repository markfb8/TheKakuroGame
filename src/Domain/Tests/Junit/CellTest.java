package Domain.Tests.Junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Domain.Classes.Cell.BlackCell;
import Domain.Classes.Cell.Cell;
import Domain.Classes.Cell.WhiteCell;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class CellTest {
	
	private int horitzSum;
	private int vertiSum;
	
	private int value;
	private int sol;
	private boolean perm;
	
	private String type;
	private Cell c;
	
	@Before 
	public void initialize() {
		c = new Cell();
	}
	
	public CellTest(String type, int horitzSum, int vertiSum, int val, int solu, boolean perm) {
		this.type = type;
		this.horitzSum = horitzSum;
		this.vertiSum = vertiSum;
		this.value =  val;
		this.sol = solu;
		this.perm = perm;
	}
	
	@SuppressWarnings("rawtypes")
	@Parameters 
	public static Collection getTestParameters(){
		return Arrays.asList(new Object[][] {
			{"b", 20, 35, -1, -1, false},
			{"w", -1, -1, 5, 7, true},
			{"w",  -1, 5, 8, 2, false},
			{"b", 14, 12, 5, 9, true} });
		}

	
	
	@Test 
	public void testCell(){
		if(this.type == "b") {
			c = new BlackCell();
			c.setHNum(this.horitzSum);
			c.setVNum(this.vertiSum);
			assertTrue("IsBlack", this.c.isBlack());
			assertEquals("HoritzontalSum", this.horitzSum, c.getHNum());
			assertEquals("VerticalSum", this.vertiSum, c.getVNum());
		}
		else if(this.type == "w") {
			c = new WhiteCell(this.value, this.perm);
			if(!perm) {
				c.setSol(this.sol);
			}
			assertTrue("IsWhite", this.c.isWhite());
			assertEquals("Value", this.value, c.getValue());
			if(!this.perm)
				assertEquals("Solution", this.sol, c.getSol());
			else
				assertEquals("Solution", this.value, c.getSol());
			assertEquals("Color", Boolean.toString(this.perm), Boolean.toString(c.isPerm()));
			
		}
		
		
	}
	
	
	
}
