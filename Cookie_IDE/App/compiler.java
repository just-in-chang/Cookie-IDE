package App;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


public class compiler
{
    public static void main( String[] args ) throws Exception
    {
        PrintWriter out = new PrintWriter(
            new BufferedWriter( new FileWriter( "out.java" ) ) );

        out.println( "import javafx.*;" );
    }
}
