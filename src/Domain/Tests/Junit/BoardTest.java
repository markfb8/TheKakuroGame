package Domain.Tests.Junit;

import org.junit.BeforeClass;
import org.junit.Test;
import Domain.Classes.Board;
import Domain.Controllers.CtrlBoard;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;

import static org.junit.Assert.*;

public class BoardTest {
		
		public BoardTest() {
			
		}
		private static Board brd;
		
		
		@BeforeClass
		public static void getBoard() {
			CtrlBoard ctrl = new CtrlBoard();
			try {
				brd = ctrl.getBoard("420","EASY");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		

		@Test 
		public void testCountCells() {
			System.out.println("Testing CountCells functionality");
		
			int emptyCells = brd.getEmptyCells();
			int iniCells = brd.getIniCells();
			int iniWhiteCells = brd.getWhiteCells();
			int iniBlackCells = brd.getBlackCells();
				
			brd.countCells();
			
			assertEquals(iniWhiteCells, brd.getWhiteCells());
			assertEquals(iniBlackCells, brd.getBlackCells());
			assertEquals(emptyCells, brd.getEmptyCells());
			assertEquals(iniCells, brd.getIniCells());
			
		}
		
		@Test
		public void testSetValue() {
			System.out.println("Testing SetValue functionality");
			int emptyCells = brd.getEmptyCells();
			int inivalue =  brd.getValue(4, 5);
			try {
				brd.setValue(4, 5, 4);
			} catch (ExceptionOutOfRangeValue e) {
				e.printStackTrace();
			} catch (ExceptionOutOfRangePosition e) {
				e.printStackTrace();
			}
			
			assertEquals(emptyCells-1, brd.getEmptyCells());
			assertNotEquals(inivalue, brd.getValue(4, 5));
			
		}
		@Test
		public void testFindNextWhiteCell() {
			System.out.println("Testing FindNextWhiteCell functionality");
			int[] posicio = {0,0};
			brd.findNextWhiteCell(posicio);
			
			assertTrue(brd.getValue(posicio[0], posicio[1]) != -1); // Les casselles blanques tenen sempre un valor diferent a -1
			
		}
		
		@Test
		public void testSetBlack() {
			System.out.println("Testing SetBlack functionality");
			int i = 4;
			int j = 4;
			int sumaV = 0;
			int sumaH = 15;
			brd.setBlack(i, j, sumaV, sumaH);
			
			assertTrue(brd.getValue(i, j) == -1);
			assertEquals(brd.getHNum(i, j), sumaH);
			assertEquals(brd.getVNum(i, j), sumaV);
		}
		
	}


