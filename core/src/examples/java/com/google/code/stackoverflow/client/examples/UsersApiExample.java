/**
 * 
 */
package com.google.code.stackoverflow.client.examples;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.code.stackoverflow.client.StackOverflowApiClient;
import com.google.code.stackoverflow.client.StackOverflowApiClientFactory;
import com.google.code.stackoverflow.schema.User;

/**
 * The Class UsersApiExample.
 */
public class UsersApiExample {

    /** The Constant APPLICATION_KEY_OPTION. */
    private static final String APPLICATION_KEY_OPTION = "key";
	
    /** The Constant ID_OPTION. */
    private static final String ID_OPTION = "id";
    
    /** The Constant HELP_OPTION. */
    private static final String HELP_OPTION = "help";

    /**
     * The main method.
     * 
     * @param args the arguments
     */
	public static void main(String[] args) {
		Options options = buildOptions();
        try {
            CommandLine line = new BasicParser().parse(options, args);
            processCommandLine(line, options);
        } catch(ParseException exp ) {
            System.err.println(exp.getMessage());
            printHelp(options);
        }
	}
	
    /**
     * Process command line.
     * 
     * @param line the line
     * @param options the options
     */
    private static void processCommandLine(CommandLine line, Options options) {
        if(line.hasOption(HELP_OPTION)) {
            printHelp(options);            
        } else if(line.hasOption(APPLICATION_KEY_OPTION)) {
    		final String keyValue = line.getOptionValue(APPLICATION_KEY_OPTION);
    		
    		final StackOverflowApiClientFactory factory = StackOverflowApiClientFactory.newInstance(keyValue);
    		final StackOverflowApiClient client = factory.createStackOverflowApiClient();
    		
    		if(line.hasOption(ID_OPTION)) {
    			String idValue = line.getOptionValue(ID_OPTION);
    			List<User> users = client.getUsers(Long.valueOf(idValue));
    			printResult(users.get(0));
    		} else {
    			List<User> users = client.getUsers();
    			for (User user : users) {
					printResult(user);
				}
    		}
        } else {
            printHelp(options);
        }
    }
	
	/**
	 * Prints the result.
	 * 
	 * @param user the user
	 */
	private static void printResult(User user) {
		System.out.println(user.getDisplayName() + ":" + user.getReputation() + ":" + user.getUserType());
	}

	/**
	 * Builds the options.
	 * 
	 * @return the options
	 */
    private static Options buildOptions() {
       
        Options opts = new Options();
        
        String helpMsg = "Print this message.";
        Option help = new Option(HELP_OPTION, helpMsg);
        opts.addOption(help);

        String consumerKeyMsg = "You API Key.";
        OptionBuilder.withArgName("key");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(consumerKeyMsg);
        Option consumerKey = OptionBuilder.create(APPLICATION_KEY_OPTION);
        opts.addOption(consumerKey);
        
        String idMsg = "ID of the users to whom a message is to be sent (separated by comma).";
        OptionBuilder.withArgName("id");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(idMsg);
        Option id = OptionBuilder.create(ID_OPTION);
        opts.addOption(id);
        
        return opts;
    }
    
    /**
     * Prints the help.
     * 
     * @param options the options
     */
    private static void printHelp(Options options) {
        int width = 80;
        String syntax = UsersApiExample.class.getName() + " <options>";
        String header = MessageFormat.format("\nThe -{0} option is required. The -{1} option is optional.", APPLICATION_KEY_OPTION, ID_OPTION);
        String footer = "";
        new HelpFormatter().printHelp(width, syntax, header, options, footer, false);
    }
}
