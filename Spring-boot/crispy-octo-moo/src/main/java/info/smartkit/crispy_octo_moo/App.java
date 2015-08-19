package info.smartkit.crispy_octo_moo;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.DefaultFileSystemAbstraction;
import org.neo4j.kernel.StoreLocker;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@SpringBootApplication
@EnableNeo4jRepositories
public class App extends Neo4jConfiguration {
	// static GraphDatabaseService graphDb;
	public static final String DB_PATH = "target/crispy_octo_moo.db";
	//
	// public App() {
	// setBasePackage("info.smartkit.crispy_octo_moo");
	// }
	//
	// @Bean(destroyMethod = "shutdown")
	// public GraphDatabaseService graphDatabaseService() {
	// //
	// StoreLocker lock = new StoreLocker(new DefaultFileSystemAbstraction());
	// lock.checkLock(new File(DB_PATH));
	// try {
	// lock.release();
	// if (graphDb == null) {
	// graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	// }
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	//// return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	// return graphDb;
	// }

	public static void main(String[] args) throws IOException {
//		FileUtils.deleteRecursively(new File(DB_PATH));
		SpringApplication.run(App.class, args);
	}

}
