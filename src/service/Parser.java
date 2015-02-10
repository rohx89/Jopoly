package service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.ParserException;
import model.Settings;
import model.fields.BuyableFieldInterface;
import model.fields.ServiceField;
import model.fields.StreetField;
import model.fields.TrainStationField;

/**
 * Parses files needed for the Game. These files contain configurations and
 * descriptions of the board.
 * 
 * @author rohx89
 *
 */
public class Parser {

	/**
	 * Parses the setting File (./Data/Settings.csv)
	 * 
	 * @ensure Settings.BOARD!=null
	 * @ensure Settings.CURRENCY!=null
	 * @ensure Settings.START_MONEY > 0
	 * @ensure INITIAL_MONEY>0
	 * @throws IOException
	 */
	public void parseSettings() throws ParserException {
		Settings.LOGGER.debug("Start parsing Settings file");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("./Data/Settings.csv")));
			String currentLine = reader.readLine();
			int lineNumber = 1;
			while ((currentLine = reader.readLine()) != null) {
				++lineNumber;
				// split line
				String[] splitedLine = currentLine.split(";");
				if (splitedLine.length != 2) {
					Settings.LOGGER.error("Wrong number of properties in line " + lineNumber
							+ ". Only Key and one value are allowed");
					throw new ParserException();
				}
				String key = splitedLine[0];
				String value = splitedLine[1];

				switch (key) {
					case "InitialMoney":
						Settings.setInitialMoney(Integer.parseInt(value));
						break;
					case "Currency":
						Settings.setCurreny(value);
						break;
					case "Board":
						Settings.setBoard(value);
						break;
					case "StartMoney":
						Settings.setStartMoney(Integer.parseInt(value));
						break;
					default:
						Settings.LOGGER.error("Invalid Key was found in settings file: " + key);
						throw new ParserException();
				}
			}

			// check whether Settings are correct
			if (lineNumber != 5 || Settings.BOARD == null || Settings.CURRENCY == null || Settings.INITIAL_MONEY < 1
					|| Settings.START_MONEY < 1) {
				Settings.LOGGER
						.error("Setting file has wrong amount of lines or (board or currency or initial or money or startmoney was not read coorectly");
				throw new ParserException();
			}

		}
		catch (FileNotFoundException e) {

			Settings.LOGGER.error("Settings file not found");
			throw new ParserException();

		}
		catch (IOException e) {
			Settings.LOGGER.error("IO Exception in SettingsFile occured");
			throw new ParserException();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					Settings.LOGGER.error("Could not Close Reader");
					throw new ParserException();

				}
				Settings.LOGGER.debug("End parsing Settings file");

			}
		}
	}

	/**
	 * Parses all files containing informations about the board
	 * 
	 * @return An {@Link Array} containing objects implementing
	 *         {@link BuyableFieldInterface} The size of the array is 40. All
	 *         non buyable Field are represented by null.
	 * @throws IOException
	 */
	public BuyableFieldInterface[] parseBoardFiles() throws ParserException {
		Settings.LOGGER.debug("Start parsing Board");
		BuyableFieldInterface[] board = new BuyableFieldInterface[40];
		parseTrainStationFile(board);
		parseServiceFile(board);
		parseStreetFile(board);
		return board;
	}

	/**
	 * Fills board with the services found in the ServiceFields*.CSV file
	 * 
	 * @param board
	 * @throws IOException
	 */
	private void parseServiceFile(BuyableFieldInterface[] board) throws ParserException {
		Settings.LOGGER.debug("Start parsing Service file");
		BufferedReader reader = null;
		try {

			// Create reader
			reader = new BufferedReader(new FileReader(new File("./Data/ServiceFields" + Settings.BOARD + ".csv")));

			String currentLine = reader.readLine();
			int lineCounter = 1;
			// Declare needed variables for the fields
			String name1 = null;
			String name2 = null;
			int price = 0;
			int mortage = 0;
			int redemptionOfMortage = 0;
			int factor1 = 0;
			int factor2 = 0;
			// Parse infos
			while ((currentLine = reader.readLine()) != null) {
				++lineCounter;
				// Split line
				String[] splitedLine = currentLine.split(";");
				if (splitedLine.length != 2) {
					Settings.LOGGER.error("Line " + lineCounter + " has " + splitedLine.length
							+ " instead of 2 Properties");
					throw new ParserException();
				}
				String key = splitedLine[0];
				String value = splitedLine[1];

				switch (key) {
					case "Name1":
						name1 = value;
						break;

					case "Name2":
						name2 = value;
						break;
					case "Price":
						price = Integer.parseInt(value);
						break;
					case "Mortage":
						mortage = Integer.parseInt(value);
						break;
					case "RedemptionOfMortage":
						redemptionOfMortage = Integer.parseInt(value);
						break;
					case "Factor1":
						factor1 = Integer.parseInt(value);
						break;
					case "Factor2":
						factor2 = Integer.parseInt(value);
						break;
					default:
						Settings.LOGGER.error("Invalid Key was found: " + key);
						throw new ParserException();
				}
			}

			if (lineCounter != 8) {
				Settings.LOGGER.error("Servcice file must contain 8 lines!!");
				throw new IOException();
			}
			board[12] = new ServiceField(price, name1, redemptionOfMortage, mortage, factor1, factor2);
			board[28] = new ServiceField(price, name2, redemptionOfMortage, mortage, factor1, factor2);

		}
		catch (FileNotFoundException e) {
			Settings.LOGGER.error("ServiceFieldFile not found");
			throw new ParserException();
		}
		catch (IOException e) {
			Settings.LOGGER.error("IO Exception occured while parsing ServiceFile");
			throw new ParserException();
		}
		finally {

			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					Settings.LOGGER.error("Could not Close Reader");
					throw new ParserException();
				}
			}
			Settings.LOGGER.debug("End parsing Service file");
		}

	}

	/**
	 * Parses the train station file and adds the train stations to the board
	 * 
	 * @param board
	 * @throws IOException
	 * @require board!=null
	 */
	private void parseTrainStationFile(BuyableFieldInterface[] board) throws ParserException {
		assert board != null : "Precondition not fullfilled: board";
		BufferedReader reader = null;
		try {
			Settings.LOGGER.debug("Start parsing train station file");
			// Set up Reader
			reader = new BufferedReader(new FileReader(new File("./Data/Trainstation" + Settings.BOARD + ".csv")));
			String currentLine = reader.readLine();
			int lineCounter = 1;

			// Declare variables for trainsStationField objects
			String[] names = new String[4];
			int mortag = -1;
			int redemptionOfMortage = -1;
			int startRent = -1;
			int price = -1;

			// Parsing File
			while ((currentLine = reader.readLine()) != null) {
				++lineCounter;
				String[] splitedLines = currentLine.split(";");
				if (splitedLines.length != 2) {
					Settings.LOGGER.error("Wrong number of properties in line " + splitedLines.length + ":"
							+ currentLine + ". Only Key and one value are allowed");
					throw new ParserException();
				}
				String key = splitedLines[0];
				String value = splitedLines[1];
				switch (key) {
					case "Name1":
						names[0] = value;
						break;
					case "Name2":
						names[1] = value;
						break;
					case "Name3":
						names[2] = value;
						break;
					case "Name4":
						names[3] = value;
						break;
					case "Mortage":
						mortag = Integer.parseInt(value);
						break;
					case "RedemptionOfMortage":
						redemptionOfMortage = Integer.parseInt(value);
						break;
					case "StartRent":
						startRent = Integer.parseInt(value);
						break;
					case "Price":
						price = Integer.parseInt(value);
						break;
					default:
						Settings.LOGGER.error("Invalid Key was found: " + key);
						throw new ParserException();
				}
			}
			if (lineCounter != 9) {
				Settings.LOGGER.error("Train Station file must contain 9 lines!!");
				throw new ParserException();
			}
			int position = 5;
			for (String name : names) {
				// Check whether file was correct
				if (name.equals(null) || mortag < 0 || redemptionOfMortage < 0 || startRent < 0 || price < 0) {
					Settings.LOGGER.error("Trainstation file contains an mistake");
					throw new ParserException();
				}
				board[position] = new TrainStationField(price, name, redemptionOfMortage, mortag, startRent);
				position = position + 10;
			}

		}
		catch (FileNotFoundException e) {
			Settings.LOGGER.error("Trainstation file was not found");
			throw new ParserException();
		}
		catch (IOException e) {
			Settings.LOGGER.error("IO Exception occured while parsing Train station File");
			throw new ParserException();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					Settings.LOGGER.error("Could not close reader");
					throw new ParserException();
				}

			}
			Settings.LOGGER.info("End parsing train station file");
		}

	}

	/**
	 * Parses the street file and adds the streets to the board
	 * 
	 * @param board
	 * @throws IOException
	 * @requires board!=null
	 */
	private void parseStreetFile(BuyableFieldInterface[] board) throws ParserException {
		assert board != null : "Precondition not fullfilled: board=null";
		Settings.LOGGER.info("Start parsing street  file");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("./Data/Streets" + Settings.BOARD + ".csv")));
			String currentLine = reader.readLine();
			int lineCounter = 1;
			while ((currentLine = reader.readLine()) != null) {
				++lineCounter;
				StreetField street = stringToStreet(currentLine);
				board[street.getPosition()] = street;
			}
			if (lineCounter != 23) {
				Settings.LOGGER.error("Streets file must have 23 Lines");
				throw new IOException();
			}
		}
		catch (FileNotFoundException e) {
			Settings.LOGGER.error("Streets file not found");
			throw new ParserException();
		}
		catch (IOException e) {
			Settings.LOGGER.error("IOException occured while parsing the street file");
			throw new ParserException();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					Settings.LOGGER.debug("Could not close Reader");
					throw new ParserException();
				}
			}
			Settings.LOGGER.info("End parsing streed file");
		}

	}

	/**
	 * Creates a {@Link Streetfield} object out of a String
	 * 
	 * @require currentLine!=0
	 * @param currentLine
	 *            A {@link String} with the following
	 *            informations:color;Street;Price
	 *            ;PriceHouses;Rent;Rent1House;Rent2House
	 *            ;Rent3House;Rent4House;RentHotel
	 *            ;Mortage;RedemptionOfMortgage;Position
	 * @return {@link StreetField}
	 * @throws IOException
	 */
	private StreetField stringToStreet(String currentLine) throws ParserException {
		assert currentLine != null : "Precondition not fullfilled: currentLine";
		Settings.LOGGER.info("Start creating Street from String:" + currentLine);
		// get each property
		String[] values = currentLine.split(";");
		// check amount of properties
		if (values.length != 13) {
			Settings.LOGGER.error("Line:" + currentLine + " has the wrong amount of properties.");
			throw new ParserException();
		}
		// get needed parameters
		Color color = null;
		try {
			color = (Color) Class.forName("java.awt.Color").getField(values[0]).get(null);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {

			Settings.LOGGER.error(values[0] + " is not a color");
			throw new ParserException();
		}

		String name = values[1];
		int price = Integer.parseInt(values[2]);
		int priceHouse = Integer.parseInt(values[3]);
		int[] rent = new int[6];
		for (int valueIterator = 4; valueIterator <= 9; valueIterator++) {
			rent[valueIterator - 4] = Integer.parseInt(values[valueIterator]);
		}

		int mortage = Integer.parseInt(values[10]);
		int redemptionOfMortage = Integer.parseInt(values[11]);
		int position = Integer.parseInt(values[12]);

		StreetField result = new StreetField(price, name, redemptionOfMortage, mortage, rent[0], rent[1], rent[2],
				rent[3], rent[4], rent[5], priceHouse, position, color);
		Settings.LOGGER.info(" Street from String:" + currentLine + " was created correctly");

		return result;
	}
}
