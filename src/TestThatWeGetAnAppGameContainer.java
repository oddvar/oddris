import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.oddvar.oddris.TestRun;


public class TestThatWeGetAnAppGameContainer
extends TestCase {
	public TestThatWeGetAnAppGameContainer(
			String name) {
		super(name);
	}
	public void testSay() throws SlickException {
		AppGameContainer app = new AppGameContainer(new TestRun());
		
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(
				TestThatWeGetAnAppGameContainer.class);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
