/**
 * 
 */
package com.google.code.stackoverflow.client.examples;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.code.stackoverflow.client.AsyncStackOverflowApiClient;
import com.google.code.stackoverflow.client.StackOverflowApiClientFactory;
import com.google.code.stackoverflow.schema.Badge;
import com.google.code.stackoverflow.schema.Tag;

/**
 * @author Nabeel Mukhtar
 *
 */
public class AsyncApiExample {

    /**
     * Consumer Key
     */
    private static final String APPLICATION_KEY_OPTION = "key";
	
    /**
     * Name of the help command line option.
     */
    private static final String HELP_OPTION = "help";
    
    /**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
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
     * Process command line options and call the service. 
     */
    private static void processCommandLine(CommandLine line, Options options) throws Exception {
        if(line.hasOption(HELP_OPTION)) {
            printHelp(options);            
        } else if(line.hasOption(APPLICATION_KEY_OPTION)) {
    		final String keyValue = line.getOptionValue(APPLICATION_KEY_OPTION);
    		
    		final StackOverflowApiClientFactory factory = StackOverflowApiClientFactory.newInstance(keyValue);
    		final AsyncStackOverflowApiClient client = factory.createAsyncStackOverflowApiClient();
			System.out.println("Fetching badges and tags asynchronously.");
    		Future<List<Badge>> badgesFuture = client.getBadges();
    		Future<List<Tag>> tagsFuture = client.getTags();
			System.out.println("Done fetching badges and tags asynchronously. Now blocking for result.");
			List<Badge> badges = badgesFuture.get();
			System.out.println("============ Badges ============");
			for (Badge badge : badges) {
				printResult(badge);
			}
			List<Tag> tags = tagsFuture.get();
			System.out.println("============ Tags ============");
			for (Tag tag : tags) {
				printResult(tag);
			}
    		
        } else {
            printHelp(options);
        }
    }
	
	private static void printResult(Tag tag) {
		System.out.println(tag.getName() + ":" + tag.getCount());
	}

	private static void printResult(Badge badge) {
		System.out.println(badge.getName() + ":" + badge.getRank() + ":" + badge.getAwardCount());
	}

	/**
     * Build command line options object.
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
        
        return opts;
    }
    
    /**
     * Print help and usage.
     */
    private static void printHelp(Options options) {
        int width = 80;
        String syntax = AsyncApiExample.class.getName() + " <options>";
        String header = MessageFormat.format("\nThe -{0} option is required.", APPLICATION_KEY_OPTION);
        String footer = "";
        new HelpFormatter().printHelp(width, syntax, header, options, footer, false);
    }
}
