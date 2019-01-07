package filesprocessing;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

public class DirectoryProcessor {

    private LinkedList<Section> sections = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();

    /**
     * The manager method of the whole program
     * @param args - input from user, should be srcDir and commandFile paths.
     * @throws ErrorException - in case of invalid paths, directory, file.
     */
    private void manager(String[] args) throws ErrorException {

        if (args.length != 2){
            System.err.print("ERROR: Invalid input, should be sourcedir commandfile \n");
        }
        else {
            String srcDir = args[0];
            String commandFile = args[1];

            CommandReader reader = CommandReader.getInstance();
            sections.addAll(reader.getSections(commandFile));
            files.addAll(Arrays.asList(reader.getFiles(srcDir)));

            for (Section section : sections) {
                section.process(files);
            }

            Printer printer = new Printer();
            printer.print(sections);
        }
    }

    public static void main(String[] args) {
        DirectoryProcessor directoryProcessor = new DirectoryProcessor();

        try {
            directoryProcessor.manager(args);
        }
        catch (ErrorException e){
            System.err.print(e.getMessage());
        }
    }
}
