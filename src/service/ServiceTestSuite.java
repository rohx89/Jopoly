package service;

/**
 * This test suite contains all test testing services.
 * @author rohx89
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ParserPositivTest.class, ParserNegativTest.class })
public class ServiceTestSuite {

}
