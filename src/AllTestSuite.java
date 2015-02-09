/**
 * This Test suite runs all test suites
 * @author rohx89
 *
 */
import model.ModelTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import service.ServiceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ModelTestSuite.class, ServiceTestSuite.class })
public class AllTestSuite {

}
