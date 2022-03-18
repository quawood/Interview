import org.apache.commons.cli.*;


public class CookieProgram {
    private static Options OPTIONS = new Options() {{
        addOption(Option.builder("d")
                .longOpt("date")
                .argName("date")
                .hasArg()
                .required(true)
                .desc("date to grab cookie from in YYYY-MM-DD format")
                .build());
    }};

    public static void main(String[] args) {

        try {
            run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            HelpFormatter helper = new HelpFormatter();
            helper.printHelp(" ", OPTIONS);
            System.exit(0);
        }
    }

    protected static void run(String[] args) throws Exception {
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        cmd = parser.parse(OPTIONS, args);
        String logFile = cmd.getArgs()[0];
        CookieHelper.getMostActiveCookie(logFile, cmd.getOptionValue("date"))
                .forEach(System.out::println);
    }

}
