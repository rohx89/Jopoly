package service;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import model.ParserException;
import model.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//TODO More testing
@RunWith(Parameterized.class)
public class ParserNegativTest {
	/**
	 * The Folder containing the testfiles for this test
	 */
	private final String	_testDataFolder			= "./Testfiles/ParserNegativTest/";
	private final String	_configFolder			= "./Data/";
	private Path			_sourceServiceFieldConfig;
	private Path			_sourceSettingConfig;
	private Path			_sourceStreetFieldConfig;
	private Path			_sourceTrainStationFieldConfig;
	private Path			_targetServiceFieldConfig;
	private final Path		_targetSettingConfig	= new File(_configFolder + "Settings.csv").toPath();
	private Path			_targetStreetFieldConfig;
	private Path			_targetTrainStationFieldConfig;
	private String			_testname;

	/**
	 * Sets the parameters
	 * 
	 * @return The String[] contains : The Name of the Testcase Trainstation 4
	 *         -> Name of the Test
	 */
	@Parameters(name = "Test{index}:{0}")
	public static ArrayList<String[]> parameters() {
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		String[] testnames = { "Settings2ValuesTest", "Service2ValuesTest", "TrainStation2ValuesTest",
				"Streets1MoreValueTest", "SettingsToManyLinesTest", "ServiceToManyLinesTest", "StreetsToManyLinesTest",
				"SettingsMissingLineTest" };
		for (String testname : testnames) {
			parameters.add(new String[] { testname });
		}
		Settings.LOGGER.info("Parameters initialized");
		return parameters;

	}

	/**
	 * Sets fields
	 * 
	 * @param serviceFieldConfig
	 * @ensure testname!=0
	 */
	public ParserNegativTest(String testname) {
		// Preconditions
		assert testname != null : "Precondition not fullfilled: testname";
		_testname = testname;
		// set source fields
		_sourceServiceFieldConfig = new File(_testDataFolder + "ServiceFields" + testname + ".csv").toPath();
		_sourceSettingConfig = new File(_testDataFolder + "Settings" + testname + ".csv").toPath();
		_sourceStreetFieldConfig = new File(_testDataFolder + "Streets" + testname + ".csv").toPath();
		_sourceTrainStationFieldConfig = new File(_testDataFolder + "Trainstation" + testname + ".csv").toPath();

		// set target fields
		_targetServiceFieldConfig = new File(_configFolder + "ServiceFields" + testname + ".csv").toPath();
		_targetStreetFieldConfig = new File(_configFolder + "Streets" + testname + ".csv").toPath();
		_targetTrainStationFieldConfig = new File(_configFolder + "Trainstation" + testname + ".csv").toPath();

	}

	@Before
	/**
	 * Copies the Test config files to the directory used by the app
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		Files.copy(_sourceSettingConfig, _targetSettingConfig, REPLACE_EXISTING);
		Files.copy(_sourceServiceFieldConfig, _targetServiceFieldConfig, REPLACE_EXISTING);
		Files.copy(_sourceStreetFieldConfig, _targetStreetFieldConfig, REPLACE_EXISTING);
		Files.copy(_sourceTrainStationFieldConfig, _targetTrainStationFieldConfig, REPLACE_EXISTING);
	}

	@After
	public void tearDown() throws Exception {
		// Replace test settings file with backup file
		Path settingsBackup = new File(_configFolder + "Backup/Settings.csv").toPath();
		Files.copy(settingsBackup, _targetSettingConfig, REPLACE_EXISTING);
		// Delete the other test settings files
		Files.delete(_targetServiceFieldConfig);
		Files.delete(_targetStreetFieldConfig);
		Files.delete(_targetTrainStationFieldConfig);
	}

	@Test(expected = ParserException.class)
	/**
	 * Test whether a {@link ParserException} is thrown if a config file is wrong.
	 * The test config will test:
	 * 1->Setting file has more than two properties
	 * 2->TrainStation file has more than two properties
	 * 3->Streets file has one value too much
	 * 
	 * @throws ParserException
	 */
	public void test() throws ParserException {
		Settings.LOGGER.info("---Started" + _testname + "---");
		Parser parser = new Parser();
		parser.parseSettings();
		parser.parseBoardFiles();

	}
}
