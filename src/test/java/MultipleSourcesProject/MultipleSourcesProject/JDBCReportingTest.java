package MultipleSourcesProject.MultipleSourcesProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import muad.dib.jdbc.Reporting;

/**
 * Unit test for simple App.
 */
public class JDBCReportingTest extends TestCase {

	private Reporting reporting;

	public void setUp() {
		reporting = new Reporting();

	}

	public void testLogBuildingAddressLines() {
		Logger logger = LoggerFactory.getLogger(JDBCReportingTest.class);
		for (String line : reporting.getBuildingAddressLines()) {
			logger.warn(line);
		}
	}

	public void testLogPersonsLines() {
		Logger logger = LoggerFactory.getLogger(JDBCReportingTest.class);
		for (String line : reporting.getPersonsLines()) {
			logger.warn(line);
		}
	}
}
