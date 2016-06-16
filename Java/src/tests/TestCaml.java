package tests;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class TestCaml {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

                try {
                    ProcessBuilder pb = new ProcessBuilder("make");
                    pb.directory(new File("../Ocaml/"));
                    File log = new File("log");
                    pb.redirectErrorStream(true);
                    pb.redirectOutput(Redirect.appendTo(log));
                    Process p = pb.start();
                    assert pb.redirectInput() == Redirect.PIPE;
                    assert pb.redirectOutput().file() == log;
                    assert p.getInputStream().read() == -1;
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
	}

}
