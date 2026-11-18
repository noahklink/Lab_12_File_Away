import javax.swing.*;

void main() {
    String rec = "";
    int lineCnt = 0;
    int charCnt = 0;
    String words = "";
    int wordCnt = 0;

    List<String> lines = new ArrayList<>();
    JFileChooser chooser = new JFileChooser();
    File workingDirectory = new File(System.getProperty("user.dir"));
    chooser.setCurrentDirectory(workingDirectory);

    if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
        IO.println("No file selected");
        System.exit(0);
    }

    File selectedFile = chooser.getSelectedFile();

    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
    } catch (FileNotFoundException e) {
        IO.println("File not found: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    IO.println("> Reading...");
    for (String l : lines) {
        lineCnt++;
        charCnt += l.length();
        wordCnt = l.split(" ").length;
        System.out.printf("\nLine %d \t%s ", lineCnt, l);
    }

    IO.println("\n\n> Data file read");
    IO.println("File name: " + selectedFile.getName());
    IO.println("Lines: " + lineCnt);
    IO.println("Words: " + wordCnt);
    IO.println("Chars: " + charCnt);
}
