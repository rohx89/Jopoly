package model;

/**
 * This Testsuite contains all Test testing classes in the model package
 * @author rohx89
 *
 */
import model.fields.ServiceFieldTest;
import model.fields.StreetFieldTest;
import model.fields.TrainStationFieldTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ServiceFieldTest.class, StreetFieldTest.class,
		TrainStationFieldTest.class, DiceTest.class })
public class ModelTestSuite {

}
