package info.smartkit.crispy_octo_moo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
@SpringBootApplication
@EnableNeo4jRepositories
public class App extends Neo4jConfiguration {

	public App() {
		setBasePackage("info.smartkit.crispy_octo_moo");
	}

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabase("target/crispy_octo_moo.db");
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
