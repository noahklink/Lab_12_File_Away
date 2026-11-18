import javax.swing.*;

void main() {
    Scanner in = new Scanner(System.in);
    ArrayList<String> data = new ArrayList<>();

    boolean done = false;
    do {
        String addedData = "%s,%s,%s,%s,%s".formatted( // generates CSV format of data
                SafeInput.getNonZeroLenString(in, "What is your first name? "),
                SafeInput.getNonZeroLenString(in, "What is your last name? "),
                SafeInput.getRegExString(in, "What is your 6 digit ID number? ",
                        "^\\d{6}$", "000000"),
                SafeInput.getRegExString(in, "What is your email? ",
                        "^[a-zA-Z0-9_.+%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "name@email.com"),
                SafeInput.getRegExString(in, "What is your year of birth? ",
                        "^\\d{4}$", "YYYY"));

        data.add(addedData);
        done = !SafeInput.getYNConfirm(in, "Do you have more data to add? [y/n]: ");
    } while (!done);

    // ask for file name & reprompt if already exists
    Path newFile = null;
    do {
        String fileName = SafeInput.getRegExString(in, "New file name? ",
                "^[^.]*$", "not including file extension"); // adds .csv automatically, user does not need to add

        Path fileLocation = Path.of(System.getProperty("user.dir") + "\\src\\" + fileName + ".csv");

        if (Files.exists(fileLocation))
            IO.println("File already exists: " + fileLocation);
        else
            newFile = fileLocation;
    } while(newFile == null);

    try {
        Files.write(newFile, data);
        IO.println("File written to: " + newFile.getFileName());
    } catch (IOException e) {
        e.printStackTrace();
    }
}