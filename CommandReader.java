package filesprocessing;

import java.io.*;
import java.util.LinkedList;

/**
 * class used  to read the command file, create the sections, and read the source file directory.
 */
class CommandReader {

    private static final CommandReader instance = new CommandReader();

    private CommandReader(){}


    static CommandReader getInstance(){
        return instance;
    }

    /**
     * reads the command file and creates the sections
     * @param commandFile - the path of the command file to be read
     * @return - linked list of sections
     * @throws ErrorException - in case of I/O error - missing ORDER/FILTER headline
     */
    LinkedList<Section> getSections(String commandFile) throws ErrorException {
        LinkedList<Section> sections = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(commandFile));
            String currLine = reader.readLine();
            int lineNum = 1;
            // Entering the command file reading loop, in every loop a section is being read.
            while (currLine != null){
                // Checking FILTER headline.
                if (!currLine.equals("FILTER")){
                    throw new ErrorException("ERROR: Missing or invalid FILTER headline \n");
                }
                String secFilter = reader.readLine();
                currLine = reader.readLine();
                //Checking ORDER headline.
                if (currLine == null || !currLine.equals("ORDER")) {
                    throw new ErrorException("ERROR: Missing or invalid ORDER headline \n");
                }
                currLine = reader.readLine();
                if (currLine != null && !currLine.equals("FILTER")) {
                    sections.add(SectionFactory.getInstance().makeSection(secFilter, currLine, lineNum));
                }
                else {
                    lineNum--;
                    sections.add(SectionFactory.getInstance().makeSection(secFilter, "abs", lineNum));
                }
                if (currLine != null && !currLine.equals("FILTER"))
                    currLine = reader.readLine();
                lineNum = lineNum + 4;
            }
        }
        catch (FileNotFoundException e){
            throw new ErrorException("ERROR: commandFile does not exist \n");
        }
        catch (IOException e){
            throw new ErrorException("ERROR: I/O error occurred while attempting to read command file \n");
        }
        return sections;
    }

    /**
     * creates an array of the files inside the source directory.
     * @param srcDir - path of source directory
     * @return - array of the files
     * @throws ErrorException - in the path does not exists or is not of directory
     */
    File[] getFiles(String srcDir) throws ErrorException {

        File sourceDirectory = new File(srcDir);

        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()){
            throw new ErrorException("ERROR: srcDir does not exist or is not a directory \n");
        }
        return sourceDirectory.listFiles(File::isFile);

    }
}
