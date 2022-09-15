import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import fr.inria.atlanmod.commons.log.Log;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Creates an Apache Drill Drillbit.
 * <p>
 * Data files such as csv files can be stored in the resources folder to be available for the drillbit in the
 * classpath file system storage.
 */
public class DrillbitConnection {

    private static final String CONFIGURATION_FILE = "configuration.properties";

    /**
     * The url of the database.
     */
    private String url;

    /**
     * The driver connection.
     */
    private Connection conn;

    /**
     * Instantiates a new {@link DrillbitConnection}
     * <p>
     * It connects to a local database made up of the {@code .csv} file(s) stored in the resources folder.
     */
    public DrillbitConnection() {
        Configurations configurations = new Configurations();
        PropertiesConfiguration configuration;
        try {
            configuration = configurations.properties(Thread.currentThread().getContextClassLoader().getResource(CONFIGURATION_FILE));
            url = configuration.getString("url");
        } catch (ConfigurationException e) {
            Log.error("Configuration file {0} not found", CONFIGURATION_FILE);
            e.printStackTrace();
        }
        try {
            Class.forName("org.apache.drill.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            Log.error(e, "An error occurred while connecting to {0}, see the attached exception", url);
        }
    }

    /**
     * The entry point of the application.
     * <p>
     * It creates a {@link DrillbitConnection}.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        DrillbitConnection drillbitConnection = new DrillbitConnection();
    }
}
